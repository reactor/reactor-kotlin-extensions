/*
 * Copyright (c) 2011-2022 VMware Inc. or its affiliates, All Rights Reserved.
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

package reactor.kotlin.adapter.rxjava

import io.reactivex.*
import reactor.adapter.rxjava.RxJava2Adapter
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


/**
 * Wraps a Flowable instance into a Flux instance, composing the micro-fusion
 * properties of the Flowable through.
 * @param <T> the value type
 * @return the new Flux instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Flowable<T>.toFlux(): Flux<T> {
    return RxJava2Adapter.flowableToFlux<T>(this)
}

/**
 * Wraps a Flux instance into a Flowable instance, composing the micro-fusion
 * properties of the Flux through.
 * @param <T> the value type
 * @return the new Flux instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Flux<T>.toFlowable(): Flowable<T> {
    return RxJava2Adapter.fluxToFlowable(this)
}

/**
 * Wraps a Mono instance into a Flowable instance, composing the micro-fusion
 * properties of the Flux through.
 * @param <T> the value type
 * @return the new Flux instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Mono<T>.toFlowable(): Flowable<T> {
    return RxJava2Adapter.monoToFlowable<T>(this)
}

/**
 * Wraps a void-Mono instance into a RxJava Completable.
 * @return the new Completable instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun Mono<*>.toCompletable(): Completable {
    return RxJava2Adapter.monoToCompletable(this)
}

/**
 * Wraps a RxJava Completable into a Mono instance.
 * @return the new Mono instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun Completable.toMono(): Mono<Void> {
    return RxJava2Adapter.completableToMono(this)
}

/**
 * Wraps a Mono instance into a RxJava Single.
 *
 * If the Mono is empty, the single will signal a
 * [NoSuchElementException].
 * @param <T> the value type
 * @return the new Single instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Mono<T>.toSingle(): Single<T> {
    return RxJava2Adapter.monoToSingle(this)
}

/**
 * Wraps a RxJava Single into a Mono instance.
 * @param <T> the value type
 * @return the new Mono instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Single<T>.toMono(): Mono<T> {
    return RxJava2Adapter.singleToMono<T>(this)
}

/**
 * Wraps an RxJava Observable and applies the given backpressure strategy.
 * @param <T> the value type
 * @param strategy the back-pressure strategy, default is BUFFER
 * @return the new Flux instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Observable<T>.toFlux(strategy: BackpressureStrategy = BackpressureStrategy.BUFFER): Flux<T> {
    return RxJava2Adapter.observableToFlux(this, strategy)
}

/**
 * Wraps a Flux instance into a RxJava Observable.
 * @param <T> the value type
 * @return the new Observable instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Flux<T>.toObservable(): Observable<T> {
    return RxJava2Adapter.fluxToFlowable(this).toObservable()
}

/**
 * Wraps an RxJava Maybe into a Mono instance.
 * @param <T> the value type
 * @return the new Mono instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Maybe<T>.toMono(): Mono<T> {
    return RxJava2Adapter.maybeToMono(this)
}

/**
 * WRaps Mono instance into an RxJava Maybe.
 * @param <T> the value type
 * @return the new Maybe instance
 * @deprecated RxJava adapters are being removed from the scope of extensions.
 * To be removed at the earliest in 1.3.0.
 */
@Deprecated(message = "RxJava adapters are being removed from the scope of extensions.")
fun <T> Mono<T>.toMaybe(): Maybe<T> {
    return RxJava2Adapter.monoToMaybe(this)
}
