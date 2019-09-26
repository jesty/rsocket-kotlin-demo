package com.github.jesty.rsocketkotlindemo

import io.rsocket.RSocket
import io.rsocket.RSocketFactory
import io.rsocket.frame.decoder.PayloadDecoder
import io.rsocket.transport.netty.client.WebsocketClientTransport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.util.MimeTypeUtils
import java.net.URI

@Configuration
class RsocketConfiguration {

    //@Bean
    fun rSocket(): RSocket? {
        return RSocketFactory
                .connect()
                .mimeType(MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(WebsocketClientTransport.create(URI.create("ws://localhost:8080/rsocket")))
                .start()
                .block()
    }

    @Bean
    fun rSocketRequester(rsocketRequesterBuilder: RSocketRequester.Builder): RSocketRequester? {
        return rsocketRequesterBuilder.connectWebSocket(URI.create("ws://localhost:8080/rsocket")).block();
        //return RSocketRequester.wrap(rSocket()!!, MimeTypeUtils.APPLICATION_JSON, MimeTypeUtils.parseMimeType("message/x.rsocket.routing.v0"), rSocketStrategies)
    }


}