package de.inovex.kmp_training.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            databaseModule,
            networkModule,
            repositoryModule,
            viewModelModule
        )
    }
}

