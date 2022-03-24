package com.unit.composetut.ui.theme.composeBasicCodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unit.composetut.R
import com.unit.composetut.ui.theme.ComposeTutTheme
import com.unit.composetut.ui.theme.NewYorkFamily
import com.unit.composetut.ui.theme.composeBasicCodelab.tos.ServiceActivationScreen

class LibraryUIActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

@ExperimentalMaterialApi
@Composable
fun MainComposable() {

    val sheetState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetPeekHeight = 40.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 12.dp),
        topBar = {
        Row(
            modifier = Modifier
                .padding(start = 28.dp, end = 28.dp, top = 45.dp, bottom = 31.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "13",
                fontWeight = FontWeight.Bold,
                fontFamily = NewYorkFamily,
                fontSize = 38.sp
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Mon", fontFamily = NewYorkFamily, fontSize = 14.sp, color = Color.Gray)
                Text(
                    text = "Dec 2021",
                    fontFamily = NewYorkFamily,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.QrCode, contentDescription = "QR Code")
            }
            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }, sheetContent = {
        Text(
            text = "My books",
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .height(40.dp),
            fontFamily = NewYorkFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 16.dp
            )
        ) {
            items(myBookItems) { myBookItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clickable { }
                        .padding(horizontal = 28.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = myBookItem.book.image),
                        contentScale = ContentScale.FillHeight,
                        contentDescription = "Cover",
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = myBookItem.book.title,
                            fontFamily = NewYorkFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(text = myBookItem.book.author, fontSize = 13.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Return until ${myBookItem.returnDate}",
                            color = MaterialTheme.colors.primary,
                            fontSize = 13.sp
                        )
                        LinearProgressIndicator(myBookItem.timeLeftPercentage, modifier = Modifier.fillMaxWidth())
                    }
                    IconButton(onClick = {  }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
                    }
                }
            }
        }
    }) { paddingValue ->
        Column(modifier = Modifier.padding(paddingValues = paddingValue)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New arrivals",
                    fontFamily = NewYorkFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                TextButton(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(horizontal = 0.dp)
                ) {
                    Text(text = "View all")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = "View All"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 28.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { book ->
                    Column(modifier = Modifier
                        .width(130.dp)
                        .clickable { }) {
                        Image(
                            painter = painterResource(id = book.image),
                            contentDescription = "Book Cover",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = book.title,
                            fontFamily = NewYorkFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = book.author, color = Color.Gray, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApp {
        MainComposable()
    }
}
