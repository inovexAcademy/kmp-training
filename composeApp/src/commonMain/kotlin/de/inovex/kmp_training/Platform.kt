package de.inovex.kmp_training

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform