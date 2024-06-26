/*
 * Copyright (c) 2011-2021 VMware Inc. or its affiliates, All Rights Reserved.
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

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.retry.Jitter
import reactor.retry.Retry
import reactor.retry.RetryContext
import java.time.Duration
import java.util.function.Consumer

/**
 * Extension to add a [Flux.retry] variant to [Flux] that uses an exponential backoff,
 * as enabled by reactor-extra's [Retry] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param jitter optional flag to activate random jitter in the backoffs
 * @param doOnRetry optional [Consumer]-like lambda that will receive a [RetryContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra retry features have been replaced by a core alternative and will be removed.
 *   If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra retry features have been replaced by a core alternative and will be removed. " +
        "If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.")
fun <T> Flux<T>.retryExponentialBackoff(times: Long, first: Duration, max: Duration? = null,
                                        jitter: Boolean = false,
                                        doOnRetry: ((RetryContext<T>) -> Unit)? = null): Flux<T> {
    val retry = Retry.any<T>()
            .retryMax(times)
            .exponentialBackoff(first, max)
            .jitter(if (jitter) Jitter.random() else Jitter.noJitter())

    return if (doOnRetry == null)
        retry.apply(this)
    else
        retry.doOnRetry(doOnRetry).apply(this)
}

/**
 * Extension to add a [Flux.retry] variant to [Flux] that uses a random backoff,
 * as enabled by reactor-extra's [Retry] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param doOnRetry optional [Consumer]-like lambda that will receive a [RetryContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra retry features have been replaced by a core alternative and will be removed.
 *   If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra retry features have been replaced by a core alternative and will be removed. " +
        "If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.")
fun <T> Flux<T>.retryRandomBackoff(times: Long, first: Duration, max: Duration? = null,
                                   doOnRetry: ((RetryContext<T>) -> Unit)? = null): Flux<T> {
    val retry = Retry.any<T>()
            .retryMax(times)
            .randomBackoff(first, max)

    return if (doOnRetry == null)
        retry.apply(this)
    else
        retry.doOnRetry(doOnRetry).apply(this)
}

/**
 * Extension to add a [Mono.retry] variant to [Mono] that uses an exponential backoff,
 * as enabled by reactor-extra's [Retry] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param jitter optional flag to activate random jitter in the backoffs
 * @param doOnRetry optional [Consumer]-like lambda that will receive a [RetryContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra retry features have been replaced by a core alternative and will be removed.
 *   If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra retry features have been replaced by a core alternative and will be removed. " +
        "If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.")
fun <T> Mono<T>.retryExponentialBackoff(times: Long, first: Duration, max: Duration? = null,
                                        jitter: Boolean = false,
                                        doOnRetry: ((RetryContext<T>) -> Unit)? = null): Mono<T> {
    val retry = Retry.any<T>()
            .retryMax(times)
            .exponentialBackoff(first, max)
            .jitter(if (jitter) Jitter.random() else Jitter.noJitter())

    return if (doOnRetry == null)
        this.retryWhen(reactor.util.retry.Retry.withThrowable(retry))
    else
        this.retryWhen(reactor.util.retry.Retry.withThrowable(retry.doOnRetry(doOnRetry)))
}

/**
 * Extension to add a [Mono.retry] variant to [Mono] that uses a random backoff,
 * as enabled by reactor-extra's [Retry] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param doOnRetry optional [Consumer]-like lambda that will receive a [RetryContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra retry features have been replaced by a core alternative and will be removed.
 *   If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra retry features have been replaced by a core alternative and will be removed. " +
        "If you still need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.")
fun <T> Mono<T>.retryRandomBackoff(times: Long, first: Duration, max: Duration? = null,
                                   doOnRetry: ((RetryContext<T>) -> Unit)? = null): Mono<T> {
    val retry = Retry.any<T>()
            .retryMax(times)
            .randomBackoff(first, max)

    return if (doOnRetry == null)
        this.retryWhen(reactor.util.retry.Retry.withThrowable(retry))
    else
        this.retryWhen(reactor.util.retry.Retry.withThrowable(retry.doOnRetry(doOnRetry)))
}

