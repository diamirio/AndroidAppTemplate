package com.tailoredapps.androidapptemplate.remote

import com.tailoredapps.androidapptemplate.core.model.Example
import com.tailoredapps.androidapptemplate.remote.mapper.toObject
import com.tailoredapps.androidapptemplate.remote.retrofit.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ApiImpl(
    private val retrofitApi: RetrofitApi
) : Api {

    override suspend fun getExample(): Example = withContext(Dispatchers.IO) {
        retrofitApi.getExample().toObject()
    }

}