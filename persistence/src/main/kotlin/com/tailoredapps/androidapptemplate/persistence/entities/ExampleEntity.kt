package com.tailoredapps.androidapptemplate.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "example")
internal data class ExampleEntity(

    @ColumnInfo(name = "example_field")
    val exampleField: String

)
