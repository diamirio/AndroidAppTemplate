package com.tailoredapps.androidapptemplate.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example")
internal data class ExampleEntity(

    @PrimaryKey(autoGenerate = true)
    val key: Long,

    @ColumnInfo(name = "example_field")
    val exampleField: String

)
