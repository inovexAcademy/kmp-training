package de.inovex.kmp_training.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(CIO)
}

