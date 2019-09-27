# Getting Started

This example is a RSocket client wrote in Kotlin that connect to the *rsocket-kotlin-demo-web* example using the request/stream (finite stream of many) and the channel (bi-directional streams) interaction style.

The core of the example is in TickerRestService.kt class, while the configuration is in RsocketConfiguration class.

To run the example move to this folder and run mvn spring-boot:run. Because it connects to the other example, you have to start *rsocket-kotlin-demo-web* example before it. 

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.M6/maven-plugin/)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/5.2.0.RC2/spring-framework-reference/languages.html#coroutines)
* [Spring Boot RSocket](https://docs.spring.io/spring-boot/docs/2.2.0.M6/reference/htmlsingle/#boot-features-rsocket)
