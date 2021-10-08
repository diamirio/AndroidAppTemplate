package com.tailoredapps.androidapptemplate.remote

import com.tailoredapps.androidapptemplate.core.model.Example

interface Api {

    suspend fun getExample(): Example

}