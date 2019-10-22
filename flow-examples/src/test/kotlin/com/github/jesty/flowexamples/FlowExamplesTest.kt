package com.github.jesty.flowexamples

import assertk.assertThat
import assertk.assertions.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.lang.Integer.parseInt
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class FlowExamplesTest {

    @Test
    fun `simple emit`() = runBlocking {
        val sb = StringBuilder()
        assertThat {
            flow {
                emit("Hello world!")
            }
                    .onEach { println("On each $it") }
                    .collect { sb.append(it) }
            sb.toString()
        }.isSuccess().isEqualTo("Hello world!")
    }


    @Test
    fun `basic test`() = runBlocking {
        assertThat {
            val collector = LinkedList<Int>();
            flow {
                (1..5).forEach { emit(it) }
            }
                    .onEach { println("On each $it") }
                    .collect { collector.add(it) }
            return@assertThat collector
        }.isSuccess().containsExactly(1, 2, 3, 4, 5)
    }

    @Test
    fun `basic test with a delay`() = runBlocking {
        assertThat {
            flow {
                (1..5).forEach {
                    delay(500)
                    emit(it)
                }
            }
                    .onEach { println("On each $it") }
                    .toList()
        }.isSuccess().containsExactly(1, 2, 3, 4, 5)
    }

    @Test
    fun `map value test`() = runBlocking {
        assertThat {
            flow {
                (1..5).forEach { emit(it) }
            }
                    .map { it * 2 }
                    .onEach { println("On each $it") }
                    .toList()
        }.isSuccess().containsExactly(2, 4, 6, 8, 10)
    }

    @Test
    fun `concat a flow for each element`() = runBlocking {
        assertThat {
            flow {
                (1..5).forEach { emit(it) }
            }
                    .flatMapConcat {
                        flow {
                            emit(it)
                            emit(it * it)
                        }
                    }
                    .onEach { println("On each $it") }
                    .toList()
        }.isSuccess().containsExactly(1, 1, 2, 4, 3, 9, 4, 16, 5, 25)
    }

    @Test
    fun `test flow lifecycle`() = runBlocking {
        assertThat {
            flow {
                (1..5).forEach {
                    emit(it)
                    if (it == 2) throw RuntimeException("Error on $it")
                }
            }
                    .onEach { println("On each $it") }
                    .onStart { println("Starting flow") }
                    .onCompletion { println("Flow completed") }
                    .catch { ex -> println("Exception message: ${ex.message}") }
                    .toList()
        }.isSuccess().containsExactly(1, 2)
    }

    @Test
    fun `test retry`() = runBlocking {
        val retry = AtomicBoolean(true);
        assertThat {
            (1..5).asFlow().onEach {
                if (it == 2 && retry.get()) throw RuntimeException("Error on $it")
            }.retryWhen { cause, attempt ->
                println("cause $cause attempt $attempt")
                if (attempt > 0) {
                    retry.set(false)
                }
                true
            }
                    .toList()
        }.isSuccess().containsExactly(1, 1, 1, 2, 3, 4, 5)
    }

    @Test
    fun `test retry and stop`() = runBlocking {
        assertThat {
            (1..5).asFlow().onEach {
                if (it == 3) throw RuntimeException("Error on $it")
            }.retryWhen { cause, attempt ->
                println("cause $cause attempt $attempt")
                attempt > 0
            }.toList()
        }.isFailure().hasMessage("Error on 3")
    }

    @Test
    fun `test catch and retry and stop`() = runBlocking {
        assertThat {
            (1..5).asFlow().onEach {
                if (it == 3) throw RuntimeException("$it")
            }.catch {
                emitAll((parseInt(it.message) + 1..5).asFlow())
            }.toList()
        }.isSuccess().containsExactly(1, 2, 4, 5)
    }

}

