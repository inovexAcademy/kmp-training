package de.inovex.kmp_training

import androidx.compose.runtime.Composable
import de.inovex.kmp_training.ui.navigation.AppNavHost
import de.inovex.kmp_training.ui.theme.TaskManagerTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    TaskManagerTheme {
        AppNavHost()
    }
}
