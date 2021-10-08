package com.tailoredapps.androidapptemplate.persistence.mapper

import com.tailoredapps.androidapptemplate.core.model.Example
import com.tailoredapps.androidapptemplate.persistence.entities.ExampleEntity

internal fun ExampleEntity.toObject(): Example {
    return Example(
        exampleField = exampleField
    )
}

internal fun Example.toEntity(): ExampleEntity {
    return ExampleEntity(
        exampleField = exampleField
    )
}