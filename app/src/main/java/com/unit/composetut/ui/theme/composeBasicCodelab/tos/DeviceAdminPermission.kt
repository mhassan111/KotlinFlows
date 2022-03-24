package com.unit.composetut.ui.theme.composeBasicCodelab.tos

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unit.composetut.R

@Composable
fun DeviceAdminPermission(
) {

    Box(
        modifier = Modifier
            .padding(top = 100.dp, start = 30.dp, end = 30.dp, bottom = 100.dp)
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                drawRoundRect(
                    color = Color.Gray,
                    size = size,
                    cornerRadius = CornerRadius(30.dp.toPx())
                )
            }
        }

        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .background(color = Color.Gray)
        ) {

            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Activate device admin app",
                textAlign = TextAlign.Start,
                color = Color.Black
            )

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Canvas(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(10.dp)
                ) {
                    drawRoundRect(
                        color = Color.Green,
                        size = size,
                        cornerRadius = CornerRadius(20.dp.toPx())
                    )
                }

                Column(modifier = Modifier.fillMaxSize()) {
//                    CenterComposable()

                    Box(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Android Wifi dddd Update",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }


                }
            }
        }
    }
}

@Composable
fun CenterComposable() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Magenta)
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            Modifier
                .size(width = 40.dp, height = 40.dp)
                .clip(RoundedCornerShape(40.dp))
        )

        Text(
            text = "Android Wifi Manager Update",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun preview() {
    DeviceAdminPermission()
}