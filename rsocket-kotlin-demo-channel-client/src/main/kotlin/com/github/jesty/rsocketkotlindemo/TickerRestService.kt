package com.github.jesty.rsocketkotlindemo

import TickerRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class TickerRestService(val rSocketRequester: RSocketRequester) {

    @RequestMapping(value = ["/ticker"], produces = ["text/event-stream"], method = [RequestMethod.GET])
    fun requestStream(): Flow<Int> {
        return rSocketRequester
                .route("tick")
                .data(TickerRequest(2))
                .retrieveFlow<Int>()
                .onEach { println("Ticker: $it") }
                .onCompletion { println("On completition")}
    }

}