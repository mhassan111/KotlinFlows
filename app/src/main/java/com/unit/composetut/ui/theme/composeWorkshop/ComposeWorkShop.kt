package com.unit.composetut.ui.theme.composeWorkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unit.composetut.ui.theme.ComposeTutTheme
import com.unit.composetut.ui.theme.composeWorkshop.components.OnBoardingScreen

class ComposeWorkShop : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                ComposeHoisting()
            }
        }
    }

}

@Composable
fun ComposeHoisting() {

    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldShowOnboarding) {
        OnBoardingScreen(onContinueClicked = {
            shouldShowOnboarding = false
        })
    } else {
        RowsAndColumns(onShowHomeClick = {

        })
    }
}

@Composable
fun lazyList(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            ColumnItem(name = name, onShowHomeClick = {})
        }
    }
}

@Composable
fun RowsAndColumns(names: List<String> = listOf("Hassan", "Ahmad"), onShowHomeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxSize()
    ) {
        for (name in names) {
            ColumnItem(name = name, onShowHomeClick = {
                onShowHomeClick()
            })
        }
    }
}

@Composable
fun ColumnItem(name: String, onShowHomeClick: () -> Unit) {

    var expanded by remember {
        mutableStateOf(false)
    }

//    val extraPadding = if (expanded.value) 48.dp else 0.dp

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primaryVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
                top = 24.dp,
                bottom = extraPadding.coerceAtLeast(0.dp)
            )
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello,")
                Text(text = name)
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { onShowHomeClick() }) {
                    Text(text = "Show Home")
                }
            }
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(
                    if (expanded) "Show Less" else "Show More"
                )
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeTutTheme {
        Surface(color = MaterialTheme.colors.primary) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android $it" }) {

    var counterState by remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        NamesList(names = names, modifier = Modifier.weight(1f))
        Counter(counterState, updateCount = { newCount ->
            counterState = newCount
        })
        if (counterState > 5) {
            Text(text = "I love to count!")
        }
    }
}

@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) {
            Greeting(name = it)
            Divider()
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text(text = "I've been clicked $count times")
    }
}

@Composable
fun Greeting(name: String) {

    var isSelected by remember {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if (isSelected) Color.Red else Color.Transparent,
        animationSpec = tween(durationMillis = 4000)
    )
    Surface(color = targetColor) {
        Text(
            text = "Hello $name",
            modifier = Modifier
                .clickable { isSelected = !isSelected }
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApp {
        RowsAndColumns(onShowHomeClick = {

        })
    }
}
