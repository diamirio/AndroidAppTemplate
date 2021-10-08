package com.tailoredapps.androidapptemplate.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tailoredapps.androidapptemplate.persistence.daos.ExampleDao
import com.tailoredapps.androidapptemplate.persistence.entities.ExampleEntity

@Database(
    entities = [ExampleEntity::class],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}