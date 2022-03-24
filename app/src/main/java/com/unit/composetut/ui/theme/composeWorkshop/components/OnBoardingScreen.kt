package com.unit.composetut.ui.theme.composeWorkshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {

    androidx.compose.material.Surface {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome To Basic Code Lab!!!")
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = { onContinueClicked() }) {
                Text(text = "Continue")
            }
        }
    }
}