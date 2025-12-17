package de.inovex.kmp_training

import androidx.compose.ui.window.ComposeUIViewController
import de.inovex.kmp_training.di.initKoin

fun MainViewController() = ComposeUIViewController { 
    App() 
}

fun initKoinIos() {
    initKoin()
}
