package com.unit.composetut.ui.theme.composeBasicCodelab.tos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * [ServiceActivationScreen]
 * This composable is responsible for drawing the content of Device Activation Screen. This Screen contains an input text
 * field where User will have to put the Activation Code to activate the service. And a button to start the activation process.
 * These two are the major components of this Activation Screen.
 */
@Composable
fun ServiceActivationScreen() {

    // Scaffold State
    val scaffoldState = rememberScaffoldState()
    // Coroutine Scope
    val scope = rememberCoroutineScope()

    // remember activation code state
    var activationCode by remember {
        mutableStateOf("")
    }

    // Validates if activation code is empty or not
    val isActivationCodeValid by derivedStateOf {
        activationCode.isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 50.dp),
            text = "TheOneSpy",
            color = MaterialTheme.colors.primary,
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "Welcome",
                color = MaterialTheme.colors.primary,
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = activationCode,
                onValueChange = { activationCode = it },
                label = { Text(text = "Activation Code") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                },
                enabled = isActivationCodeValid,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Activate")
            }
        }
    }
}