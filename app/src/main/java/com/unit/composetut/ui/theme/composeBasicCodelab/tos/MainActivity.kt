package com.unit.composetut.ui.theme.composeBasicCodelab.tos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.unit.composetut.ui.theme.ComposeTutTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyApp {
                MainComposable()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeTutTheme {
        content()
    }
}

@Composable
fun MainComposable() {
    Surface(color = MaterialTheme.colors.background) {
        DeviceAdminPermission()
//        ServiceActivationScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

}