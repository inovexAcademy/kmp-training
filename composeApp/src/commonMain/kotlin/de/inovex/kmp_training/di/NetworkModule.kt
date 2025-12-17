package de.inovex.kmp_training.di

import de.inovex.kmp_training.core.network.MockApiService
import de.inovex.kmp_training.core.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }
    single { MockApiService(get()) }
}
