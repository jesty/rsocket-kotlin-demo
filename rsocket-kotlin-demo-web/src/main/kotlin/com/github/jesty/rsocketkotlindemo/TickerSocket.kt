package com.github.jesty.rsocketkotlindemo

import TickerRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import rem
import times

@Controller
class TickerSocket {

    @MessageMapping("tick")
    fun tick(request: TickerRequest = TickerRequest(1)): Flow<Int> = flow {
        (1..Int.MAX_VALUE).forEach {
            delay(1000)
            emit(it)
        }
    }.filter { it % request == 0 }
            .onCompletion { println("Flow with module ${request.value} completed.") }

    @MessageMapping("multiplicator")
    fun multiplicator(requestFlow: Flow<TickerRequest>): Flow<Int> = flow {
        requestFlow.collect { request ->
            (1..3).forEach {
                println("emitting $it * $request.module")
                delay(1000)
                emit(request * it)
            }
        }
    }


}