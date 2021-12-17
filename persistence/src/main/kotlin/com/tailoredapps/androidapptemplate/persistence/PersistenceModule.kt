package com.tailoredapps.androidapptemplate.persistence

import androidx.room.Room
import com.tailoredapps.androidapptemplate.persistence.room.AppDatabase
import org.koin.dsl.module

private val persistenceModule = module {
    single<Database> {
        val appDatabase = Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).build()
        DatabaseImpl(appDatabase = appDatabase)
    }
}

val persistenceModules = listOf(persistenceModule)