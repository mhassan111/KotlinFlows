package com.unit.composetut.flows

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unit.composetut.ui.theme.ComposeTutTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowMainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectLatestLifeCycleFlow(mainViewModel.immutableStateFlow) { number ->
            Toast.makeText(this, "$number", Toast.LENGTH_SHORT).show()
        }

        collectLatestLifeCycleFlow(mainViewModel.testSharedFlow) {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }

        setContent {
            ComposeTutTheme {
                val viewModel = viewModel<MainViewModel>()
                val time = viewModel.countDownFlow.collectAsState(initial = 0)
                val stateValue = viewModel.stateFlow.collectAsState()
                val mStateValue = viewModel.immutableStateFlow.collectAsState(initial = 0)

                Box(modifier = Modifier.fillMaxSize()) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Counter = ${mStateValue.value}")
                    }
                }

//                lifecycleScope.launchWhenStarted {
//                    viewModel.stateFlow.collectLatest {
//                        stateValue = it
//                    }
//                }

                // A surface container using the 'background' color from the theme
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stateValue.value,
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

val str3: String = "string" // String is the class and the type

// But above quickly falls apart with nullables
val str4: String? = "string" // String is the Class and String? is the type


fun unreachable(someNullable: String?) {
    val nonNull: String = someNullable ?: throw NotImplementedError()
    nonNull.last()
    val thing: Nothing = TODO() // Nothing type indicates the function never completes.
    // That's why the assignment is unreachable
}


// Any vs Object
// Any is the default super type of all classes
// ( in same way unit is the default return type)
class Something
class Something2 : Any()

fun primitiveNiceties() {
    val pseudoPrimitives: Int = 1
    val anyField: Any = pseudoPrimitives // kotlin "primitives" are all considered sub types of Any
}

// Any Corresponds to java.lang.Object ( check the bytecode )
// but it's not the same as Object because it exposes only hashCode, equals, toString() methods
fun any() {
    val value: Any = Any()
    value.hashCode()
    value == "other"
    value.toString()

    // still have access to Object, but it's a warning
    (value as Object).notify()

    // As expected, any is a subtype of any?
    val any3: Any? = 3

    // But reverse doesn't work
//    val any4: Any = any3
}


// Void returns nothing
// Unit is an Object which return something but not anything meaningful
fun defaultReturn(): Unit {}
val thing: Unit = Unit


// Platform Types


private fun <T> ComponentActivity.collectLatestLifeCycleFlow(
    flow: Flow<T>,
    collect: suspend (T) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}

// Kotlin Just live Java has type erasure, which means at runtime you cannot query types
fun <T> genericCollectionFunc(list: List<T>) {

    // You cannot query type of list as it a Generic List
//    if (list is List<Int>) {
//
//    }

    // Star Projection
    // No information; about a generic argument
    if (list is List<*>) {
        val unsafe = list as List<Int> // Will succeed at runtime
        val mightCrash : Int = unsafe.first()


    }
}


// MutableList is invariant because, read + write so types must match exactly
//val modified : MutableList<Any> = mutableListOf<String>()


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTutTheme {
        Greeting("Android")
    }
}