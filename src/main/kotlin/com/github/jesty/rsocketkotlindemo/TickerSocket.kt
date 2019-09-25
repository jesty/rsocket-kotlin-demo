package com.github.jesty.rsocketkotlindemo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class TickerSocket {

    @MessageMapping("tick")
    fun tick(): Flow<Int> = flow {
        (1..Int.MAX_VALUE).forEach {
            delay(1000)
            emit(it)
        }
    }

}