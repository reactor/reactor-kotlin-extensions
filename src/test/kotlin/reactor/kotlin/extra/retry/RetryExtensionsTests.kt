/*
 * Copyright (c) 2019-2021 VMware Inc. or its affiliates, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.kotlin.extra.retry

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.test.test
import reactor.retry.RetryExhaustedException
import reactor.test.StepVerifier
import java.io.IOException
import java.time.Duration

class RetryExtensionsTests {

    @Test
    fun fluxRetryExponentialBackoff() {
        val retries = mutableListOf<Long>()

        Flux.concat(Flux.range(0, 2), Flux.error(IOException()))
                .retryExponentialBackoff(4, Duration.ofMillis(100),
                        Duration.ofMillis(500)) { retries.add(it.backoff().toMillis()) }
                .test()
                .expectNext(0, 1)
                .expectNoEvent(Duration.ofMillis(50))  // delay=100
                .expectNext(0, 1)
                .expectNoEvent(Duration.ofMillis(150)) // delay=200
                .expectNext(0, 1)
                .expectNoEvent(Duration.ofMillis(250)) // delay=400
                .expectNext(0, 1)
                .expectNoEvent(Duration.ofMillis(450)) // delay=500
                .expectNext(0, 1)
                .verifyError(RetryExhaustedException::class.java)

        assertThat(retries).containsExactly(100, 200, 400, 500)
    }

    @Test
    fun monoRetryExponentialBackoff() {
        val retries = mutableListOf<Long>()

        val mono: Mono<Any> = Mono.error<Any>(IOException())
                .retryExponentialBackoff(4, Duration.ofMillis(100),
                        Duration.ofMillis(500)) { retries.add(it.backoff().toMillis()) }

        StepVerifier.withVirtualTime { mono }
                .expectSubscription()
                .thenAwait(Duration.ofMillis(100))
                .thenAwait(Duration.ofMillis(200))
                .thenAwait(Duration.ofMillis(400))
                .thenAwait(Duration.ofMillis(500))
                .verifyError(RetryExhaustedException::class.java)

        assertThat(retries).containsExactly(100L, 200L, 400L, 500L)
    }

    @Test
    fun fluxRetryRandomBackoff() {
        val retries = mutableListOf<Long>()

        Flux.concat(Flux.range(0, 2), Flux.error(IOException()))
                .retryRandomBackoff(4, Duration.ofMillis(100),
                        Duration.ofMillis(2000)) { retries.add(it.backoff().toMillis()) }
                .test()
                .expectNext(0, 1, 0, 1, 0, 1, 0, 1, 0, 1)
                .verifyError(RetryExhaustedException::class.java)

        //we'll leave it to java tests to test the retry backoff behavior
        assertThat(retries).hasSize(4)
    }

    @Test
    fun monoRetryRandomBackoff() {
        val retries = mutableListOf<Long>()

        val mono: Mono<Any> = Mono.error<Any>(IOException())
                .retryRandomBackoff(4, Duration.ofMillis(100),
                        Duration.ofMillis(2000)) { retries.add(it.backoff().toMillis()) }

        StepVerifier.withVirtualTime { mono }
                .expectSubscription()
                .thenAwait(Duration.ofMillis(100))
                .thenAwait(Duration.ofMillis(2000))
                .thenAwait(Duration.ofMillis(2000))
                .thenAwait(Duration.ofMillis(2000))
                .verifyError(RetryExhaustedException::class.java)

        //we'll leave it to java tests to test the retry backoff behavior
        assertThat(retries).hasSize(4)
    }
}