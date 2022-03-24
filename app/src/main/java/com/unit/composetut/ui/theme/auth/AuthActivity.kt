package com.unit.composetut.ui.theme.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.unit.composetut.ui.theme.LoginTutTheme
import com.unit.composetut.ui.theme.auth.components.SignInScreen
import com.unit.composetut.ui.theme.auth.components.SignUpScreen

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                SignUpScreen()
            }
        }
    }

}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    LoginTutTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    LoginTutTheme {
        SignInScreen()
    }
}
