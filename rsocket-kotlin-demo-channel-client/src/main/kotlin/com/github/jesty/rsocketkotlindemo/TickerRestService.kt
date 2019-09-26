package com.github.jesty.rsocketkotlindemo

import TickerRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class TickerRestService(val rSocketRequester: RSocketRequester) {

    @RequestMapping(value = ["/tick"], produces = ["text/event-stream"], method = [RequestMethod.GET])
    fun requestStream(): Flow<Int> {
        return rSocketRequester
                .route("tick")
                .data(TickerRequest(2))
                .retrieveFlow<Int>()
                .retry()
                .onEach { println("Ticker: $it") }
                .onCompletion { println("On completition") }
    }

    @RequestMapping(value = ["/multiplicator"], produces = ["text/event-stream"], method = [RequestMethod.GET])
    fun requestStreamWithFlow(): Flow<Int> {
        return rSocketRequester
                .route("multiplicator")
                .data((2..5)
                        .asFlow()
                        .map { TickerRequest(it) }
                        .onEach { delay(5000) })
                .retrieveFlow<Int>()
                .onEach { println("Moltiplicator: $it") }
                .onCompletion { println("On completition") }
    }

}