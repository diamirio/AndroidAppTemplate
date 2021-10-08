package com.tailoredapps.androidapptemplate.persistence

import com.tailoredapps.androidapptemplate.core.model.Example
import com.tailoredapps.androidapptemplate.persistence.mapper.toObject
import com.tailoredapps.androidapptemplate.persistence.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DatabaseImpl(
    private val appDatabase: AppDatabase
) : Database {

    override suspend fun getExamples(): List<Example> = withContext(Dispatchers.IO) {
        appDatabase.exampleDao().getAll().map { it.toObject() }
    }

}