package com.tailoredapps.androidapptemplate.repository

import com.tailoredapps.androidapptemplate.core.model.Example

interface Repository {

    suspend fun getExample(): Example

}