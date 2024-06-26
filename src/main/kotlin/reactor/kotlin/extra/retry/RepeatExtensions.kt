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
import reactor.retry.Repeat
import reactor.retry.RepeatContext
import java.time.Duration
import java.util.function.Consumer

/**
 * Extension to add a [Flux.repeat] variant to [Flux] that uses an exponential backoff,
 * as enabled by reactor-extra's [Repeat] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param jitter optional flag to activate random jitter in the backoffs
 * @param doOnRepeat optional [Consumer]-like lambda that will receive a [RepeatContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra repeat features are being phased out. If you still
 *   need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra repeat features are being phased out. " +
        "If you still need to call this method, io.projectreactor:reactor-extras " +
        "runtime dependency is needed.")
fun <T> Flux<T>.repeatExponentialBackoff(times: Long, first: Duration, max: Duration? = null,
                                         jitter: Boolean = false,
                                         doOnRepeat: ((RepeatContext<T>) -> Unit)? = null): Flux<T> {
    val repeat = Repeat.times<T>(times)
            .exponentialBackoff(first, max)
            .jitter(if (jitter) Jitter.random() else Jitter.noJitter())

    return if (doOnRepeat == null)
        repeat.apply(this)
    else
        repeat.doOnRepeat(doOnRepeat).apply(this)
}

/**
 * Extension to add a [Flux.repeat] variant to [Flux] that uses a randomized backoff,
 * as enabled by reactor-extra's [Repeat] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param doOnRepeat optional [Consumer]-like lambda that will receive a [RepeatContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra repeat features are being phased out. If you still
 *   need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra repeat features are being phased out. " +
        "If you still need to call this method, io.projectreactor:reactor-extras " +
        "runtime dependency is needed.")
fun <T> Flux<T>.repeatRandomBackoff(times: Long, first: Duration, max: Duration? = null,
                                    doOnRepeat: ((RepeatContext<T>) -> Unit)? = null): Flux<T> {
    val repeat = Repeat.times<T>(times)
            .randomBackoff(first, max)

    return if (doOnRepeat == null)
        repeat.apply(this)
    else
        repeat.doOnRepeat(doOnRepeat).apply(this)
}


/**
 * Extension to add a [Mono.repeat] variant to [Mono] that uses an exponential backoff,
 * as enabled by reactor-extra's [Repeat] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param jitter optional flag to activate random jitter in the backoffs
 * @param doOnRepeat optional [Consumer]-like lambda that will receive a [RepeatContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra repeat features are being phased out. If you still
 *   need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra repeat features are being phased out. " +
        "If you still need to call this method, io.projectreactor:reactor-extras " +
        "runtime dependency is needed.")
fun <T> Mono<T>.repeatExponentialBackoff(times: Long, first: Duration, max: Duration? = null,
                                         jitter: Boolean = false,
                                         doOnRepeat: ((RepeatContext<T>) -> Unit)? = null): Flux<T> {
    val repeat = Repeat.times<T>(times)
            .exponentialBackoff(first, max)
            .jitter(if (jitter) Jitter.random() else Jitter.noJitter())

    return if (doOnRepeat == null)
        repeat.apply(this)
    else
        repeat.doOnRepeat(doOnRepeat).apply(this)
}

/**
 * Extension to add a [Mono.repeat] variant to [Mono] that uses a randomized backoff,
 * as enabled by reactor-extra's [Repeat] builder.
 *
 * @param times the maximum number of retry attempts
 * @param first the initial delay in retrying
 * @param max the optional upper limit the delay can reach after subsequent backoffs
 * @param doOnRepeat optional [Consumer]-like lambda that will receive a [RepeatContext]
 *   each time an attempt is made.
 *
 * @author Simon Baslé
 * @since 3.1.1
 * @deprecated Reactor-Extra repeat features are being phased out. If you still
 *   need to call this method, io.projectreactor:reactor-extras runtime dependency is needed.
 *   To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "Reactor-Extra repeat features are being phased out. " +
        "If you still need to call this method, io.projectreactor:reactor-extras " +
        "runtime dependency is needed.")
fun <T> Mono<T>.repeatRandomBackoff(times: Long, first: Duration, max: Duration? = null,
                                    doOnRepeat: ((RepeatContext<T>) -> Unit)? = null): Flux<T> {
    val repeat = Repeat.times<T>(times)
            .randomBackoff(first, max)

    return if (doOnRepeat == null)
        repeat.apply(this)
    else
        repeat.doOnRepeat(doOnRepeat).apply(this)
}