package com.unit.composetut.flows

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowViewModel : ViewModel() {

    private val integersFlow = flow {
        for (i in 1..30) {
            delay(100)
            emit(i)
        }
    }

    private val _intStateFlow = MutableStateFlow<Int>(0)
    val intStateFlow: StateFlow<Int> = _intStateFlow.asStateFlow()

    private fun incrementIntStateFlow() {
        _intStateFlow.value += 1
    }

    private fun updateIntStateFlow() {
        viewModelScope.launch {
            incrementIntStateFlow()
            delay(3000)
            incrementIntStateFlow()
            delay(3000)
            incrementIntStateFlow()
        }
    }

    private val _stringStateFlow = MutableStateFlow<String>("")
    val stringStateFlow: StateFlow<String> = _stringStateFlow

    private fun updateStringStateFlow(value: String) {
        viewModelScope.launch {
            delay(2000L)
            _stringStateFlow.value = value
        }
    }

    /**
     * Restaurant Dinner Flow, Returns the Dishes after a delay
     * Required for each dish to get prepared
     */
    private val dinnerMenuFlow = flow<String> {
        delay(250L)
        emit("Appetizer")
        delay(100L)
        emit("Main Dish")
        delay(100L)
        emit("Dessert")
        delay(100L)
        emit("Chocolates")
    }

    val countDownFlow = flow<Int> {
        val startingValue = 3
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    private val mFlow = flow {
        emit(1)
        delay(500)
        emit(2)
    }

    init {
//        collectIntegersFlow()
        collectDinnerFlow()
//        collectCountDownFlow()
//        flowOperators()

        updateStringStateFlow("Hello World 2")
        updateIntStateFlow()
    }


    private fun flowOperators() {

        viewModelScope.launch {

            // flatMapConcat
//            mFlow.flatMapConcat { value ->
//                flow {
//                    emit(value + 1)
//                    delay(500)
//                    emit(value + 2)
//                }
//            }.collect { value ->
//                println("flatMap: value = $value")
//            }
//
            // Reduce Operator
            val reduceResult = countDownFlow.reduce { accumulator, value ->
                accumulator + value
            }
            println("The count is after reduce = $reduceResult")

            // Fold Operator
            val foldResult = countDownFlow.fold(initial = 100) { acc, value ->
                acc + value
            }
            println("The count is after fold = $foldResult")

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

    /* Collect Latest will only print the last value */
    private fun collectCountDownFlow() {
        viewModelScope.launch {
            countDownFlow.collectLatest {
                delay(1500L)
                println("countDownFlow: value = $it")
            }
        }
    }

    private fun collectIntegersFlow() {
        viewModelScope.launch {
            integersFlow.collect {
                delay(1500L)
                println("integers flow = $it")
            }
        }
    }

    /**
     * Flow [buffer] will run the collect in a separate coroutine and will take the incoming emissions
     * with the same delay they are emitting and buffer them.
     * Once the collector Coroutine finish collecting the last emission, it will take the next emission from the Buffer
     */
    private fun collectDinnerFlow() {
        viewModelScope.launch {
            dinnerMenuFlow.collectLatest {
                println("DinnerFlow: Now eating $it")
                delay(1500L)
                println("DinnerFlow: Finished eating $it")
            }
        }

//        // Collector Flow simply runs in the same coroutine as Emitter Flow
//        viewModelScope.launch {
//            dinnerMenuFlow.onEach {
//                println("DinnerFlow: is delivered $it")
//            }.collect {
//                println("DinnerFlow: Now eating $it")
//                delay(1500L)
//                println("DinnerFlow: finished eating $it")
//            }
//        }
//
//        // Buffered Flow runs in a separate coroutine and buffers the incoming emissions
//        viewModelScope.launch {
//            dinnerMenuFlow.onEach {
//                println("DinnerFlow: is delivered $it")
//            }.buffer().collect {
//                println("DinnerFlow: Now eating $it")
//                delay(1500L)
//                println("DinnerFlow: finished eating $it")
//            }
//        }

        // Conflate will skip the buffered emissions, and will only collect the last buffered emission
//        viewModelScope.launch {
//            dinnerMenuFlow.onEach {
//                println("DinnerFlow: is delivered $it")
//            }.conflate().collect {
//                println("DinnerFlow: Now eating $it")
//                delay(1500L)
//                println("DinnerFlow: finished eating $it")
//            }
//        }

    }
}