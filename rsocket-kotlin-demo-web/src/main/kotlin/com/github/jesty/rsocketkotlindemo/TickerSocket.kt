package com.github.jesty.rsocketkotlindemo

import TickerRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class TickerSocket {

    @MessageMapping("tick")
    fun tick(request: TickerRequest = TickerRequest(1)): Flow<Int> = flow {
        (1..Int.MAX_VALUE).forEach {
            delay(1000)
            emit(it)
        }
    }       .filter { it % request.module == 0 }
            .onCompletion { println("Flow with module ${request.module} completed.") }


}