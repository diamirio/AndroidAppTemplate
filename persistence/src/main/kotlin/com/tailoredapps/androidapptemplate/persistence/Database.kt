package com.tailoredapps.androidapptemplate.persistence

import com.tailoredapps.androidapptemplate.core.model.Example

interface Database {

    suspend fun getExamples(): List<Example>

}