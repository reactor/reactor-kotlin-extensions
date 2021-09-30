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

package reactor.kotlin.extra.math

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.math.MathFlux
import java.math.BigDecimal
import java.math.BigInteger
import java.util.function.Function

/**
 * Extension to compute the [Long] sum of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Long].
 *
 * Note that summing decimal numbers with this method loses precision, see [sumDouble].
 *
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T: Number> Flux<T>.sum(): Mono<Long> = MathFlux.sumLong(this)

/**
 * Extension to compute the [Int] sum of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Int].
 *
 * Note that numbers will be mapped to [Int] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.sumInt(): Mono<Int> = MathFlux.sumInt(this)

/**
 * Extension to compute the [Long] sum of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Long].
 *
 * Note that numbers will be mapped to [Long] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.sumLong(): Mono<Long> = MathFlux.sumLong(this)

/**
 * Extension to compute the [Float] sum of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Float].
 *
 * Note that numbers will be mapped to [Float] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.sumFloat(): Mono<Float> = MathFlux.sumFloat(this)

/**
 * Extension to compute the [Double] sum of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Double].
 *
 * Note that since Double are more precise, some seemingly rounded Floats (e.g. 1.6f)
 * may convert to Doubles with more decimals (eg. 1.600000023841858), producing sometimes
 * unexpected sums.
 *
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T: Number> Flux<T>.sumDouble(): Mono<Double> = MathFlux.sumDouble(this)

/**
 * Extension to compute the [BigInteger] sum of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [BigInteger].
 *
 * Note that numbers will be mapped to [BigInteger] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.sumBigInt(): Mono<BigInteger> = MathFlux.sumBigInteger(this)

/**
 * Extension to compute the [BigDecimal] sum of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [BigDecimal].
 *
 * Note that numbers will be mapped to [BigDecimal] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.sumBigDecimal(): Mono<BigDecimal> = MathFlux.sumBigDecimal(this)

/**
 * Extension to compute the [Double] average of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Double].
 *
 * Note that since Double are more precise, some seemingly rounded Floats (e.g. 1.6f)
 * may convert to Doubles with more decimals (eg. 1.600000023841858), producing sometimes
 * unexpected averages.
 *
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T: Number> Flux<T>.average(): Mono<Double> = MathFlux.averageDouble(this)

/**
 * Extension to compute the [Float] average of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Float].
 *
 * Note that numbers will be mapped to [Float] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.averageFloat(): Mono<Float> = MathFlux.averageFloat(this)

/**
 * Extension to compute the [Double] average of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [Double].
 *
 * Note that numbers will be mapped to [Double] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.averageDouble(): Mono<Double> = MathFlux.averageDouble(this)

/**
 * Extension to compute the [BigInteger] average of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [BigInteger].
 *
 * Note that the result will be mapped to [BigInteger] and is likely to lose precision
 * during the calculation of the average
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.averageBigInt(): Mono<BigInteger> = MathFlux.averageBigInteger(this)

/**
 * Extension to compute the [BigDecimal] average of all values emitted by a [Flux] of [Number]
 * and return it as a [Mono] of [BigDecimal].
 *
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T: Number> Flux<T>.averageBigDecimal(): Mono<BigDecimal> = MathFlux.averageBigDecimal(this)

//min and max that work on any comparable
/**
 * Extension to find the lowest value in a [Flux] of [Comparable] values and return it
 * as a [Mono] of [T].
 *
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T: Comparable<T>> Flux<T>.min(): Mono<T> = MathFlux.min(this)

/**
 * Extension to find the highest value in a [Flux] of [Comparable] values and return it
 * as a [Mono] of [T].
 *
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T: Comparable<T>> Flux<T>.max(): Mono<T> = MathFlux.max(this)

//sum/sumDouble/average lambda versions where a converter is provided

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the sum of these
 * Numbers as a [Mono] of [Long].
 *
 * [Float] and [Double] are rounded to [Long] by [MathFlux], using Java standard
 * conversions.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T> Flux<T>.sum(mapper: (T) -> Number): Mono<Long>
        = MathFlux.sumLong(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the sum of these
 * Numbers as a [Mono] of [Int].
 *
 * Note that numbers will be mapped to [Int] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.sumInt(mapper: (T) -> Number): Mono<Int>
        = MathFlux.sumInt(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the sum of these
 * Numbers as a [Mono] of [Long].
 *
 * Note that numbers will be mapped to [Long] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.sumLong(mapper: (T) -> Number): Mono<Long>
        = MathFlux.sumLong(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the sum of these
 * Numbers as a [Mono] of [Float].
 *
 * Note that numbers will be mapped to [Float] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.sumFloat(mapper: (T) -> Number): Mono<Float>
        = MathFlux.sumFloat(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the sum of these
 * Numbers as a [Mono] of [Double], thus avoiding rounding
 * down to zero decimal places.
 *
 * Note that since [Double] are more precise than [Float], some seemingly rounded Floats
 * (e.g. 1.6f) may convert to Doubles with more decimals (eg. 1.600000023841858),
 * producing sometimes unexpected results.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T> Flux<T>.sumDouble(mapper: (T) -> Number): Mono<Double>
        = MathFlux.sumDouble(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the sum of these
 * Numbers as a [Mono] of [BigInteger]
 *
 * Note that numbers will be mapped to [BigInteger] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.sumBigInt(mapper: (T) -> Number): Mono<BigInteger>
        = MathFlux.sumBigInteger(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the sum of these
 * Numbers as a [Mono] of [BigInteger]
 *
 * Note that numbers will be mapped to [BigInteger] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.sumBigDecimal(mapper: (T) -> Number): Mono<BigDecimal>
        = MathFlux.sumBigDecimal(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the average of
 * these Numbers as a [Mono] of [Double].
 *
 * Note that since [Double] are more precise than [Float], some seemingly rounded Floats
 * (e.g. 1.6f) may convert to Doubles with more decimals (eg. 1.600000023841858),
 * producing sometimes unexpected results.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T> Flux<T>.average(mapper: (T) -> Number): Mono<Double>
        = MathFlux.averageDouble(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the average of
 * these Numbers as a [Mono] of [Float].
 *
 * Note that numbers will be mapped to [Float] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.averageFloat(mapper: (T) -> Number): Mono<Float>
        = MathFlux.averageFloat(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the average of
 * these Numbers as a [Mono] of [Double].
 *
 * Note that numbers will be mapped to [Double] using Java standard conversions
 * This may lead to arbitrary precision gain or loss if the source flux contains different types
 * Please choose an appropriate method based on the expected types in the source flux.
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.averageDouble(mapper: (T) -> Number): Mono<Double>
        = MathFlux.averageDouble(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the average of
 * these Numbers as a [Mono] of [BigInteger].
 *
 * Note that the result will be mapped to [BigInteger] and is likely to lose precision
 * during the calculation of the average
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.averageBigInt(mapper: (T) -> Number): Mono<BigInteger>
        = MathFlux.averageBigInteger(this, Function(mapper))

/**
 * Extension to map arbitrary values in a [Flux] to [Number]s and return the average of
 * these Numbers as a [Mono] of [BigDecimal].
 *
 * @param mapper a lambda converting values to [Number]
 * @author Mark Pruden
 * @since 1.1.5
 */
fun <T> Flux<T>.averageBigDecimal(mapper: (T) -> Number): Mono<BigDecimal>
        = MathFlux.averageBigDecimal(this, Function(mapper))

//min/max lambda versions where a comparator or equivalent function is provided
/**
 * Extension to find the lowest value in a [Flux] and return it as a [Mono]. The lowest
 * value is defined by comparisons made using a provided [Comparator].
 *
 * @param comp The [Comparator] to use
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T> Flux<T>.min(comp: Comparator<T>): Mono<T> = MathFlux.min(this, comp)
/**
 * Extension to find the lowest value in a [Flux] and return it as a [Mono]. The lowest
 * value is defined by comparisons made using a provided function that behaves like a
 * [Comparator].
 *
 * @param comp The comparison function to use (similar to a [Comparator])
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T> Flux<T>.min(comp: (T, T) -> Int): Mono<T> = MathFlux.min(this, Comparator(comp))
/**
 * Extension to find the highest value in a [Flux] and return it as a [Mono]. The highest
 * value is defined by comparisons made using a provided [Comparator].
 *
 * @param comp The [Comparator] to use
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T> Flux<T>.max(comp: Comparator<T>): Mono<T> = MathFlux.max(this, comp)
/**
 * Extension to find the highest value in a [Flux] and return it as a [Mono]. The highest
 * value is defined by comparisons made using a provided function that behaves like a
 * [Comparator].
 *
 * @param comp The comparison function to use (similar to a [Comparator])
 * @author Simon Baslé
 * @since 3.1.1
 */
fun <T> Flux<T>.max(comp: (T, T) -> Int): Mono<T> = MathFlux.max(this, Comparator(comp))