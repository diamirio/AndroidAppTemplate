package com.tailoredapps.androidapptemplate.remote.mapper

import com.tailoredapps.androidapptemplate.core.model.Example
import com.tailoredapps.androidapptemplate.remote.dtos.ExampleDto

internal fun ExampleDto.toObject(): Example {
    return Example(
        exampleField = exampleField
    )
}

internal fun Example.toDto(): ExampleDto {
    return ExampleDto(
        exampleField = exampleField
    )
}