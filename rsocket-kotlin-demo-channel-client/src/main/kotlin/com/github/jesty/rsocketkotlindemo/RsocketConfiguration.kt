package com.github.jesty.rsocketkotlindemo

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import java.net.URI

@Configuration
class RsocketConfiguration {

    @Bean
    fun rSocketRequester(rsocketRequesterBuilder: RSocketRequester.Builder): RSocketRequester? {
        return rsocketRequesterBuilder.connectWebSocket(URI.create("ws://localhost:8080/rsocket")).block()
    }


}