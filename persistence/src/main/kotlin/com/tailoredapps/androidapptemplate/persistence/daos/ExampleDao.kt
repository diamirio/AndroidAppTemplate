package com.tailoredapps.androidapptemplate.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import com.tailoredapps.androidapptemplate.persistence.entities.ExampleEntity

@Dao
internal interface ExampleDao {

    @Query("SELECT * FROM example")
    fun getAll(): List<ExampleEntity>

}