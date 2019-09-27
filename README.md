# RSocket Kotlin Demo

This example includes two application developed to show how to use RSocket with Spring Boot, Kotlin and Coroutines:

* [rsocket-kotlin-demo-web](/rsocket-kotlin-demo-web): A Spring Boot application that exposes two RSocket endpoints using the request/stream (finite stream of many) and the channel (bi-directional streams) interaction style.
* [rsocket-kotlin-demo-channel-client](/rsocket-kotlin-demo-channel-client): a Kotlin client that connect to the endpoints exposed in rsocket-kotlin-demo-web example.

To develop this example I used:

* Kotlin 1.3.50
* Kotlin coroutine 1.3.0
* RSocket Kotlin 0.9.8
* Spring Boot 2.2.0 M6

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.M6/maven-plugin/)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.0.RC2/spring-framework-reference/languages.html#coroutines)
* [Spring Boot RSocket](https://docs.spring.io/spring-boot/docs/2.2.0.M6/reference/htmlsingle/#boot-features-rsocket)
