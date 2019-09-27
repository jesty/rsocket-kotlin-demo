# Getting Started

This example is a RSocket client wrote in Kotlin that expose two RSocket endpoints using the request/stream (finite stream of many) and the channel (bi-directional streams) interaction style.

Moreover, there is a Javascript client (app.js) that will connect to the request/stream example.

The transport protocol is Websocket.

To run the example move to this folder and run mvn spring-boot:run. 

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.M6/maven-plugin/)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.0.RC2/spring-framework-reference/languages.html#coroutines)
* [Spring Boot RSocket](https://docs.spring.io/spring-boot/docs/2.2.0.M6/reference/htmlsingle/#boot-features-rsocket)