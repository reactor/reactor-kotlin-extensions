/*
 * Copyright (c) 2011-2019 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.kotlin.test

import org.junit.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifierOptions
import reactor.test.scheduler.VirtualTimeScheduler
import java.time.Duration

class StepVerifierExtensionsTests {

    @Test
    fun `expectError() with KClass parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .verifyError(IllegalStateException::class)
    }

    @Test
    fun `expectError() with generic parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .verifyError<IllegalStateException>()
    }

    @Test
    fun `verifyError() with KClass parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .verifyError(IllegalStateException::class)
    }

    @Test
    fun `verifyError() with generic parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .verifyError<IllegalStateException>()
    }

    @Test
    fun `testUsingVirtualTime()`() {
        { Mono.just("foo").delayElement(Duration.ofDays(1)) }
            .testUsingVirtualTime()
            .thenAwait(Duration.ofDays(1))
            .expectNext("foo")
            .verifyComplete()
    }

    @Test
    fun `testUsingVirtualTime() requesting n elements`() {
        { Flux.just("foo", "bar").delayElements(Duration.ofDays(1)) }
            .testUsingVirtualTime(1)
            .thenAwait(Duration.ofDays(1))
            .expectNext("foo")
            .expectNoEvent(Duration.ofDays(7))
            .thenRequest(1)
            .thenAwait(Duration.ofDays(1))
            .expectNext("bar")
            .verifyComplete()
    }

    @Test
    fun `testUsingVirtualTime() passing scheduler and requesting n elements`() {
        val vts = VirtualTimeScheduler.create()
        Flux.just("foo", "bar").delayElements(Duration.ofDays(1), vts)
            .testUsingVirtualTime({vts}, 1)
            .thenAwait(Duration.ofDays(1))
            .expectNext("foo")
            .expectNoEvent(Duration.ofDays(7))
            .thenRequest(1)
            .thenAwait(Duration.ofDays(1))
            .expectNext("bar")
            .verifyComplete()
    }

    @Test
    fun `testUsingVirtualTime() passing options`() {
        val vts = VirtualTimeScheduler.create()
        Mono.just("foo").delayElement(Duration.ofDays(1), vts)
            .testUsingVirtualTime(StepVerifierOptions.create().virtualTimeSchedulerSupplier { vts })
            .thenAwait(Duration.ofDays(1))
            .expectNext("foo")
            .verifyComplete()
    }
}
