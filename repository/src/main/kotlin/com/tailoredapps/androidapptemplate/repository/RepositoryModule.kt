package com.tailoredapps.androidapptemplate.repository

import org.koin.dsl.module

private val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(database = get(), api = get())
    }
}

val repositoryModules = listOf(repositoryModule)