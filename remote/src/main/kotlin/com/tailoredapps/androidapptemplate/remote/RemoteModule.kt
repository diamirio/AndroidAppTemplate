package com.tailoredapps.androidapptemplate.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tailoredapps.androidapptemplate.core.model.AppBuildInfo
import com.tailoredapps.androidapptemplate.remote.retrofit.RetrofitApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val remoteModule = module {
    // TODO: We theoretically don't want to expose this dependencies to other modules because they are specific to this module, we should create them manually and don't expose them via Koin
    // single { GsonBuilder().create() }
    //factory { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    //single { provideOkHttpClient(loggingInterceptor = get(), appBuildInfo = get()) }

    single<Api> {
        val gson = provideGson()
        val loggingInterceptor = provideLoggingInterceptor()
        val okHttpClient = provideOkHttpClient(
            loggingInterceptor = loggingInterceptor,
            appBuildInfo = get()
        )
        val retrofitApi = provideRetrofitApi<RetrofitApi>(
            okHttpClient = okHttpClient,
            gson = gson,
            appBuildInfo = get()
        )

        ApiImpl(retrofitApi = retrofitApi)
        //val retrofitApi = provideApi<RetrofitApi>(okHttpClient = get(), gson = get(), appBuildInfo = get())
    }
}

val remoteModules = listOf(remoteModule)

private fun provideGson(): Gson = GsonBuilder().create()

private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    appBuildInfo: AppBuildInfo
): OkHttpClient = OkHttpClient().newBuilder().apply {
    if (appBuildInfo.debug) addInterceptor(loggingInterceptor)
}.build()

private inline fun <reified T> provideRetrofitApi(
    okHttpClient: OkHttpClient,
    gson: Gson,
    appBuildInfo: AppBuildInfo
): T = Retrofit.Builder().apply {
    baseUrl(appBuildInfo.baseUrl)
    client(okHttpClient)
    addConverterFactory(GsonConverterFactory.create(gson))
}.build().create(T::class.java)