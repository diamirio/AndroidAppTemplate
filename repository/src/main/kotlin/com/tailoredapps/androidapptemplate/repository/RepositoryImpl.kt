package com.tailoredapps.androidapptemplate.repository

import com.tailoredapps.androidapptemplate.core.model.Example
import com.tailoredapps.androidapptemplate.persistence.Database
import com.tailoredapps.androidapptemplate.remote.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class RepositoryImpl(
    private val database: Database,
    private val api: Api
) : Repository {

    override suspend fun getExample(): Example = withContext(Dispatchers.IO) {
        Example(exampleField = "")
    }

}