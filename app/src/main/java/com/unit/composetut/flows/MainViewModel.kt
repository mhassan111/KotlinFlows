package com.unit.composetut.flows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow("Hello World")
    val stateFlow: MutableStateFlow<String> = _stateFlow

    /** Conflate flow example **/
    private val flow = flow {
        for (i in 1..30) {
            delay(100)
            emit(i)
        }
    }

    private val _testSharedFlow =
        MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    val testSharedFlow: SharedFlow<Int> = _testSharedFlow

    private val _mutableStateFlow = MutableStateFlow(0)
    val immutableStateFlow = _mutableStateFlow

    private val _sharedFlow = MutableSharedFlow<Int>(replay = 5)
    private val sharedFlow = _sharedFlow.asSharedFlow()

    private fun squareNumber(number: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }

    private fun incrementTestSharedFlow() {
        viewModelScope.launch {
            _testSharedFlow.emit(0)
        }
    }

    private fun incrementCounter() {
//        _mutableStateFlow.value = _mutableStateFlow.value + 1
        _mutableStateFlow.value = 0
    }

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        updateMutableSharedFlow()


//        updateMutableStateFlow()
//        collectFlow()
//        collectFlowWithConflate()
//        updateStateFlow()
//
//        squareNumber(4)
//        squareNumber(5)
//
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(2000)
//                println("FIRST FLOW: The received number is $it")
//            }
//        }
//
//        viewModelScope.launch {
//            sharedFlow.collect {
//                delay(3000)
//                println("SECOND FLOW: The received number is $it")
//            }
//        }
//        squareNumber(3)
    }

    private fun updateMutableStateFlow() {
        viewModelScope.launch {
            delay(3000)
            incrementCounter()
            delay(3000)
            incrementCounter()
            delay(3000)
            incrementCounter()
        }
    }

    private fun updateMutableSharedFlow() {
        viewModelScope.launch {
            delay(3000)
            incrementTestSharedFlow()
            delay(3000)
            incrementTestSharedFlow()
            delay(3000)
            incrementTestSharedFlow()
        }
    }

    private fun updateStateFlow() {
        viewModelScope.launch {
            delay(4000)
            _stateFlow.value = "abc"
        }
    }

    private fun collectFlowWithConflate() {
        viewModelScope.launch {
            val result = flow.conflate().onEach { delay(1000) }.toList()
            println("conflate list: $result")
        }
    }

//    private fun collectFlow() {
//        viewModelScope.launch {
//            countDownFlow.collect { time ->
//                delay(1500L)
//                println("The current time is: $time")
//            }
//        }
//    }

    // Flow operators
    private fun collectFlow() {

        val restaurantFlow = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main Dish")
            delay(100L)
            emit("Dessert")
        }

        viewModelScope.launch {
            flow.onEach {
                print("Flow: $it is delivered")
            }.collect {
                print("Flow: Now eating $it")
                delay(1500L)
                println("Flow: Finished eating $it")
            }
        }

        viewModelScope.launch {
            flow.buffer().collect {
                print("Flow: Now eating $it")
                delay(1500L)
                println("Flow: Finished eating $it")
            }
        }

        viewModelScope.launch {
            flow.conflate().collect {
                print("Flow: Now eating $it")
                delay(1500L)
                println("Flow: Finished eating $it")
            }
        }

        viewModelScope.launch {
            flow.collectLatest {
                print("Flow: Now eating $it")
                delay(1500L)
                println("Flow: Finished eating $it")
            }
        }

        // Flatten flows
        val flow1 = flow {
            emit(1)
            delay(500)
            emit(2)
        }

        viewModelScope.launch {
            flow1.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(500)
                    emit(value + 2)
                }
            }.collect { value: Int ->
                println(value)
            }
        }

        // Reduce operator
        viewModelScope.launch {
            val reduceResult = countDownFlow.reduce { accumulator, value ->
                accumulator + value
            }
            println("The count is after reduce = $reduceResult")
        }

        // Fold operator
        viewModelScope.launch {
            val reduceResult = countDownFlow.fold(100) { accumulator, value ->
                accumulator + value
            }
            println("Tne count is after fold = $reduceResult")
        }

        // Count operator.. terminal flow operator
        val count = viewModelScope.launch {
            countDownFlow
                .filter { time -> time % 2 == 0 }
                .map { time -> time * time }
                .onEach { time -> println(time) }
                .count { time ->
                    time % 2 == 0
                }
        }
        println("The count is = $count")

        countDownFlow.onEach { println(it) }.launchIn(viewModelScope)

        viewModelScope.launch {
            countDownFlow
                .filter { time -> time % 2 == 0 }
                .map { time -> time * time }
                .onEach { time -> println(time) }
                .collect { time ->
                    println("The current time is: $time")
                }
        }
    }

}