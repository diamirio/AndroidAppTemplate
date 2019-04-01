/*
 * Copyright 2019 Florian Schuster.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tailoredapps.androidapptemplate.core.remote

import com.google.gson.Gson
import com.tailoredapps.androidapptemplate.core.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideApi<MyApi>(okHttpClient = get(), gson = get(), baseUrl = get()) }
}

data class BaseUrl(val url: String)

private fun provideOkHttpClient() =
    OkHttpClient().newBuilder().apply {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            addInterceptor(loggingInterceptor)
        }
    }.build()

private inline fun <reified T> provideApi(okHttpClient: OkHttpClient, gson: Gson, baseUrl: BaseUrl): T =
    Retrofit.Builder().apply {
        baseUrl(baseUrl.url)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create(gson))
        addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
    }.build().create(T::class.java)