/*
 * Copyright (c) 2011-2025 VMware Inc. or its affiliates, All Rights Reserved.
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

package reactor.kotlin.test

import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import reactor.test.StepVerifier.Assertions
import reactor.test.StepVerifier.LastStep
import reactor.test.StepVerifierOptions
import reactor.test.scheduler.VirtualTimeScheduler
import java.time.Duration
import kotlin.reflect.KClass


/**
 * Extension for [StepVerifier.LastStep.expectError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun LastStep.expectError(kClass: KClass<out Throwable>): StepVerifier = expectError(kClass.java)

/**
 * Extension for [StepVerifier.LastStep.expectError] providing a `expectError<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> LastStep.expectError(): StepVerifier = expectError(T::class.java)

/**
 * Extension for [StepVerifier.LastStep.verifyError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun LastStep.verifyError(kClass: KClass<out Throwable>): Duration = verifyError(kClass.java)

/**
 * Extension for [StepVerifier.LastStep.verifyError] providing a `verifyError<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> LastStep.verifyError(): Duration = verifyError(T::class.java)

/**
 * Extension for [StepVerifier.Assertions.hasDroppedErrorOfType] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun Assertions.hasDroppedErrorOfType(kClass: KClass<out Throwable>): Assertions = hasDroppedErrorOfType(kClass.java)

/**
 * Extension for [StepVerifier.Assertions.hasDroppedErrorOfType] providing a `hasDroppedErrorOfType<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> Assertions.hasDroppedErrorOfType(): Assertions = hasDroppedErrorOfType(T::class.java)

/**
 * Extension for [StepVerifier.Assertions.hasOperatorErrorOfType] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun Assertions.hasOperatorErrorOfType(kClass: KClass<out Throwable>): Assertions = hasOperatorErrorOfType(kClass.java)

/**
 * Extension for [StepVerifier.Assertions.hasOperatorErrorOfType] providing a `hasOperatorErrorOfType<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> Assertions.hasOperatorErrorOfType(): Assertions = hasOperatorErrorOfType(T::class.java)

/**
 * Extension for testing [Flux] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any> Flux<T>.test(): StepVerifier.FirstStep<T> = StepVerifier.create(this)

/**
 * Extension for testing [Flux] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any> Flux<T>.test(n: Long): StepVerifier.FirstStep<T> =
    StepVerifier.create(this, n)

/**
 * Extension for testing [Flux] with [StepVerifier] API.
 *
 * @author Cristian Romero
 */
fun <T : Any> Flux<T>.test(options: StepVerifierOptions): StepVerifier.FirstStep<T> =
    StepVerifier.create(this, options)

/**
 * Extension for testing [Mono] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any> Mono<T>.test(): StepVerifier.FirstStep<T> = StepVerifier.create(this)

/**
 * Extension for testing [Mono] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any> Mono<T>.test(n: Long): StepVerifier.FirstStep<T> =
    StepVerifier.create(this, n)

/**
 * Extension for testing [Mono] with [StepVerifier] API.
 *
 * @author Cristian Romero
 */
fun <T : Any> Mono<T>.test(options: StepVerifierOptions): StepVerifier.FirstStep<T> =
    StepVerifier.create(this, options)

/**
 * Extension for testing the supplied [Publisher] with [StepVerifier] API, using a [VirtualTimeScheduler].
 *
 * @see [StepVerifier.withVirtualTime]
 * @author Mikael Elm
 */
fun <T : Any> (() -> Publisher<T>).testUsingVirtualTime(): StepVerifier.FirstStep<T> =
    StepVerifier.withVirtualTime { invoke() }

/**
 * Extension for testing the supplied [Publisher] with [StepVerifier] API, using a [VirtualTimeScheduler].
 *
 * @see [StepVerifier.withVirtualTime]
 * @author Mikael Elm
 */
fun <T : Any> (() -> Publisher<T>).testUsingVirtualTime(n: Long): StepVerifier.FirstStep<T> =
    StepVerifier.withVirtualTime({ invoke() }, n)

/**
 * Extension for testing a [Publisher] with [StepVerifier] API, using a [VirtualTimeScheduler].
 *
 * @see [StepVerifier.withVirtualTime]
 * @author Mikael Elm
 */
fun <T : Any> Publisher<T>.testUsingVirtualTime(vtsLookup: () -> VirtualTimeScheduler, n: Long): StepVerifier.FirstStep<T> =
    StepVerifier.withVirtualTime({ this }, vtsLookup, n)

/**
 * Extension for testing a [Publisher] with [StepVerifier] API, using a [VirtualTimeScheduler].
 *
 * @see [StepVerifier.withVirtualTime]
 * @author Mikael Elm
 */
fun <T : Any> Publisher<T>.testUsingVirtualTime(options: StepVerifierOptions): StepVerifier.FirstStep<T> =
    StepVerifier.withVirtualTime({ this }, options)
