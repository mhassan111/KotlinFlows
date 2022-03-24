package com.unit.composetut.flows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unit.composetut.ui.theme.ComposeTutTheme

class FlowPracticeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTutTheme {
                val viewModel = viewModel<FlowViewModel>()
                val countDown = viewModel.countDownFlow.collectAsState(initial = 0)
                val stringState = viewModel.stringStateFlow
                val intStateFlow = viewModel.intStateFlow.collectAsState(initial = 0)

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Count Down = ${countDown.value}")
                    Text(text = "String State = ${stringState.value}")
                    Text(text = "String State = ${intStateFlow.value}")
                }
            }
        }

    }
}