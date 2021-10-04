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

import org.junit.Test
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.test.test
import java.math.BigDecimal
import java.math.BigInteger

/**
 * @author Simon Baslé
 */
class MathFluxExtensionsTests {

    data class User(val age: Int,val name: String)

    companion object {
        val userList = listOf(User(18, "bob"),
                User(80, "grandpa"),
                User(1, "baby"))

        val comparator: Comparator<User> = Comparator({ u1: User, u2:User -> u1.age - u2.age})

        val comparableList = listOf("AA", "A", "BB", "B", "AB")
    }

    //== New sum Just and Of ==

    @Test
    fun sumJustShorts() {
        shortArrayOf(15_000, 16_000)
            .toFlux()
            .sumJust()
            .test()
            .expectNext(31_000)
            .verifyComplete()
    }

    @Test
    fun sumJustInts() {
        intArrayOf(200_000_000, 200_000_000)
            .toFlux()
            .sumJust()
            .test()
            .expectNext(400_000_000)
            .verifyComplete()
    }

    @Test
    fun sumJustLongs() {
        longArrayOf(3_000_000_000, 2_000_000_000)
            .toFlux()
            .sumJust()
            .test()
            .expectNext(5_000_000_000)
            .verifyComplete()
    }

    @Test
    fun sumJustFloats() {
        floatArrayOf(3.6f, 1.5f)
            .toFlux()
            .sumJust()
            .test()
            .expectNext(5.1f)
            .verifyComplete()
    }

    @Test
    fun sumJustDoubles() {
        doubleArrayOf(3.5, 1.6)
            .toFlux()
            .sumJust()
            .test()
            .expectNext(5.1)
            .verifyComplete()
    }

    @Test
    fun sumOfMapped() {
        userList.toFlux()
            .sumOf { it.age }
            .test()
            .expectNext(99)
            .verifyComplete()
    }

    //== sum ==
    @Test
    fun sumShorts() {
        shortArrayOf(32_000, 8_000) //sum overflows a Short
                .toFlux()
                .sum()
                .test()
                .expectNext(40_000)
                .verifyComplete()
    }

    @Test
    fun sumInts() {
        intArrayOf(2_000_000_000, 200_000_000) //sum overflows an Int
                .toFlux()
                .sum()
                .test()
                .expectNext(2_200_000_000)
                .verifyComplete()
    }

    @Test
    fun sumLongs() {
        longArrayOf(3_000_000_000, 2_000_000_000)
                .toFlux()
                .sum()
                .test()
                .expectNext(5_000_000_000)
                .verifyComplete()
    }

    @Test
    fun sumFloatsPrecisionLoss() {
        floatArrayOf(3.5f, 1.5f)
                .toFlux()
                .sum()
                .test()
                .expectNext(4)
                .verifyComplete()
    }

    @Test
    fun sumDoublesPrecisionLoss() {
        doubleArrayOf(3.5, 1.5)
                .toFlux()
                .sum()
                .test()
                .expectNext(4)
                .verifyComplete()
    }

    @Test
    fun sumMapped() {
        userList.toFlux()
                .sum { it.age }
                .test()
                .expectNext(99)
                .verifyComplete()
    }

    //== sumInt ==

    @Test
    fun sumIntShorts() {
        shortArrayOf(32_000, 32_000) //sum overflows a Short
            .toFlux()
            .sumInt()
            .test()
            .expectNext(64_000)
            .verifyComplete()
    }

    @Test
    fun sumIntInts() {
        intArrayOf(300_000_000, 200_000_000)
            .toFlux()
            .sumInt()
            .test()
            .expectNext(500_000_000)
            .verifyComplete()
    }

    @Test
    fun sumIntLongs() {
        longArrayOf(300_000_000, 200_000_000)
            .toFlux()
            .sumInt()
            .test()
            .expectNext(500_000_000)
            .verifyComplete()
    }

    @Test
    fun sumIntFloats() {
        floatArrayOf(3.5f, 1.9f)
            .toFlux()
            .sumInt()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    @Test
    fun sumIntDoubles() {
        doubleArrayOf(3.5, 1.5)
            .toFlux()
            .sumInt()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    @Test
    fun sumIntMapped() {
        userList.toFlux()
            .sumInt { it.age }
            .test()
            .expectNext(99)
            .verifyComplete()
    }

    //== sumLong ==

    @Test
    fun sumLongShorts() {
        shortArrayOf(32_000, 32_000) //sum overflows a Short
            .toFlux()
            .sumLong()
            .test()
            .expectNext(64_000)
            .verifyComplete()
    }

    @Test
    fun sumLongInts() {
        intArrayOf(2_000_000_000, 2_000_000_000) //sum overflows an Int
            .toFlux()
            .sumLong()
            .test()
            .expectNext(4_000_000_000)
            .verifyComplete()
    }

    @Test
    fun sumLongLongs() {
        longArrayOf(300_000_000_000, 200_000_000_000)
            .toFlux()
            .sumLong()
            .test()
            .expectNext(500_000_000_000)
            .verifyComplete()
    }

    @Test
    fun sumLongFloats() {
        floatArrayOf(3.5f, 1.9f)
            .toFlux()
            .sumLong()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    @Test
    fun sumLongDoubles() {
        doubleArrayOf(3.5, 1.5)
            .toFlux()
            .sumLong()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    @Test
    fun sumLongMapped() {
        userList.toFlux()
            .sumLong { it.age }
            .test()
            .expectNext(99)
            .verifyComplete()
    }

    //== sumFloat ==

    @Test
    fun sumFloatShorts() {
        shortArrayOf(32_000, 32_000) //sum overflows a Short
            .toFlux()
            .sumFloat()
            .test()
            .expectNext(64000F)
            .verifyComplete()
    }

    @Test
    fun sumFloatInts() {
        intArrayOf(2_000_000_000, 2_000_000_000) //sum overflows an Int
            .toFlux()
            .sumFloat()
            .test()
            .expectNext(4E9F)
            .verifyComplete()
    }

    @Test
    fun sumFloatLongs() {
        longArrayOf(30_000_000_000, 20_000_000_000) // There is some precision loss in these conversions.
            .toFlux()
            .sumFloat()
            .test()
            .expectNext(5.0000003E10F)
            .verifyComplete()
    }

    @Test
    fun sumFloatFloats() {
        floatArrayOf(3.5f, 1.9f)
            .toFlux()
            .sumFloat()
            .test()
            .expectNext(5.4F)
            .verifyComplete()
    }

    @Test
    fun sumFloatDoubles() {
        doubleArrayOf(3.5, 1.9)
            .toFlux()
            .sumFloat()
            .test()
            .expectNext(5.4F)
            .verifyComplete()
    }

    @Test
    fun sumFloatMapped() {
        userList.toFlux()
            .sumFloat { it.age }
            .test()
            .expectNext(99.0F)
            .verifyComplete()
    }

    //== sumDouble ==
    @Test
    fun sumDoubleShorts() {
        shortArrayOf(32_000, 8_000) //sum overflows a Short
                .toFlux()
                .sumDouble()
                .test()
                .expectNext(40_000.0)
                .verifyComplete()
    }

    @Test
    fun sumDoubleInts() {
        intArrayOf(2_000_000_000, 200_000_000) //sum overflows an Int
                .toFlux()
                .sumDouble()
                .test()
                .expectNext(2_200_000_000.0)
                .verifyComplete()
    }

    @Test
    fun sumDoubleLongs() {
        longArrayOf(3_000_000_000, 2_000_000_000)
                .toFlux()
                .sumDouble()
                .test()
                .expectNext(5_000_000_000.0)
                .verifyComplete()
    }

    @Test
    fun sumDoubleFloats() {
        floatArrayOf(3.5f, 1.5f)
                .toFlux()
                .sumDouble()
                .test()
                .expectNext(5.0)
                .verifyComplete()
    }

    @Test
    fun sumDoubleDoubles() {
        doubleArrayOf(3.5, 1.5)
                .toFlux()
                .sumDouble()
                .test()
                .expectNext(5.0)
                .verifyComplete()
    }

    @Test
    fun sumDoubleMapped() {
        userList.toFlux()
                .sumDouble { it.age }
                .test()
                .expectNext(99.0)
                .verifyComplete()
    }

    //== sumBigInt ==

    @Test
    fun sumBigIntegerShorts() {
        shortArrayOf(32_000, 32_000)
            .toFlux()
            .sumBigInt()
            .test()
            .expectNext(BigInteger("64000"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerInts() {
        intArrayOf(2_000_000_000, 2_000_000_000)
            .toFlux()
            .sumBigInt()
            .test()
            .expectNext(BigInteger("4000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerLongs() {
        longArrayOf(30_000_000_000, 20_000_000_000)
            .toFlux()
            .sumBigInt()
            .test()
            .expectNext(BigInteger("50000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerFloats() {
        floatArrayOf(3.9f, 1.9f)
            .toFlux()
            .sumBigInt()
            .test()
            .expectNext(BigInteger("4"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerDoubles() {
        doubleArrayOf(3.9, 1.9)
            .toFlux()
            .sumBigInt()
            .test()
            .expectNext(BigInteger("4"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerMapped() {
        userList.toFlux()
            .sumBigInt { it.age }
            .test()
            .expectNext(BigInteger("99"))
            .verifyComplete()
    }

    //== sumBigDecimal ==

    @Test
    fun sumBigDecimalShorts() {
        shortArrayOf(32_000, 32_000)
            .toFlux()
            .sumBigDecimal()
            .test()
            .expectNext(BigDecimal("64000"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalInts() {
        intArrayOf(2_000_000_000, 2_000_000_000)
            .toFlux()
            .sumBigDecimal()
            .test()
            .expectNext(BigDecimal("4000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalLongs() {
        longArrayOf(30_000_000_000, 20_000_000_000)
            .toFlux()
            .sumBigDecimal()
            .test()
            .expectNext(BigDecimal("50000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalFloats() {
        floatArrayOf(3.5f, 1.99f)
            .toFlux()
            .sumBigDecimal()
            .test()
            .expectNext(BigDecimal("5.49"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalDoubles() {
        doubleArrayOf(3.5, 1.99)
            .toFlux()
            .sumBigDecimal()
            .test()
            .expectNext(BigDecimal("5.49"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalMapped() {
        userList.toFlux()
            .sumBigDecimal { it.age }
            .test()
            .expectNext(BigDecimal("99"))
            .verifyComplete()
    }

    //== New average Just and Of ==

    @Test
    fun averageJustShorts() {
        shortArrayOf(10, 11, 13, 14)
            .toFlux()
            .averageJust()
            .test()
            .expectNext(12)
            .verifyComplete()
    }

    @Test
    fun averageJustInts() {
        intArrayOf(10, 11, 13, 14)
            .toFlux()
            .averageJust()
            .test()
            .expectNext(12)
            .verifyComplete()
    }

    @Test
    fun averageJustLongs() {
        longArrayOf(10, 11, 13, 14)
            .toFlux()
            .averageJust()
            .test()
            .expectNext(12)
            .verifyComplete()
    }

    @Test
    fun averageJustFloats() {
        floatArrayOf(10f, 11f)
            .toFlux()
            .averageJust()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageJustDoubles() {
        doubleArrayOf(10.0, 11.0)
            .toFlux()
            .averageJust()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageOfMapped() {
        userList.toFlux()
            .averageOf { it.age }
            .test()
            .expectNext(33)
            .verifyComplete()
    }

    //== average ==

    @Test
    fun averageShorts() {
        shortArrayOf(10, 11)
                .toFlux()
                .average()
                .test()
                .expectNext(10.5)
                .verifyComplete()
    }

    @Test
    fun averageInts() {
        intArrayOf(10, 11)
                .toFlux()
                .average()
                .test()
                .expectNext(10.5)
                .verifyComplete()
    }

    @Test
    fun averageLongs() {
        longArrayOf(10, 11)
                .toFlux()
                .average()
                .test()
                .expectNext(10.5)
                .verifyComplete()
    }

    @Test
    fun averageFloats() {
        floatArrayOf(10f, 11f)
                .toFlux()
                .average()
                .test()
                .expectNext(10.5)
                .verifyComplete()
    }

    @Test
    fun averageDoubles() {
        doubleArrayOf(10.0, 11.0)
                .toFlux()
                .average()
                .test()
                .expectNext(10.5)
                .verifyComplete()
    }

    @Test
    fun averageMapped() {
        userList.toFlux()
                .average { it.age }
                .test()
                .expectNext(33.0)
                .verifyComplete()
    }

    //== averageFloat ==

    @Test
    fun averageFloatShorts() {
        shortArrayOf(10, 11)
            .toFlux()
            .averageFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatInts() {
        intArrayOf(10, 11)
            .toFlux()
            .averageFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatLongs() {
        longArrayOf(10, 11)
            .toFlux()
            .averageFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatFloats() {
        floatArrayOf(10f, 11f)
            .toFlux()
            .averageFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatDoubles() {
        doubleArrayOf(10.0, 11.0)
            .toFlux()
            .averageFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatMapped() {
        userList.toFlux()
            .averageFloat { it.age }
            .test()
            .expectNext(33.0f)
            .verifyComplete()
    }

    //== averageDouble ==

    @Test
    fun averageDoubleShorts() {
        shortArrayOf(10, 11)
            .toFlux()
            .averageDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleInts() {
        intArrayOf(10, 11)
            .toFlux()
            .averageDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleLongs() {
        longArrayOf(10, 11)
            .toFlux()
            .averageDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleFloats() {
        floatArrayOf(10f, 11f)
            .toFlux()
            .averageDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleDoubles() {
        doubleArrayOf(10.0, 11.0)
            .toFlux()
            .averageDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleMapped() {
        userList.toFlux()
            .averageDouble { it.age }
            .test()
            .expectNext(33.0)
            .verifyComplete()
    }

    //== averageBigInt ==

    @Test
    fun averageBigIntShorts() {
        shortArrayOf(10, 11, 12)
            .toFlux()
            .averageBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntInts() {
        intArrayOf(10, 11, 12)
            .toFlux()
            .averageBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntLongs() {
        longArrayOf(10, 11, 12)
            .toFlux()
            .averageBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntFloats() {
        floatArrayOf(10f, 11f, 12f)
            .toFlux()
            .averageBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntDoubles() {
        doubleArrayOf(10.0, 11.0, 12.0)
            .toFlux()
            .averageBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntMapped() {
        userList.toFlux()
            .averageBigInt { it.age }
            .test()
            .expectNext(BigInteger("33"))
            .verifyComplete()
    }

    //== averageBigDecimal ==

    @Test
    fun averageBigDecimalShorts() {
        shortArrayOf(10, 11, 11, 11)
            .toFlux()
            .averageBigDecimal()
            .test()
            .expectNext(BigDecimal("10.75"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalInts() {
        intArrayOf(10, 11, 11, 11)
            .toFlux()
            .averageBigDecimal()
            .test()
            .expectNext(BigDecimal("10.75"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalLongs() {
        longArrayOf(10, 11, 11, 11)
            .toFlux()
            .averageBigDecimal()
            .test()
            .expectNext(BigDecimal("10.75"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalFloats() {
        floatArrayOf(10f, 11f, 12f, 13.5f)
            .toFlux()
            .averageBigDecimal()
            .test()
            .expectNext(BigDecimal("11.625"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalDoubles() {
        doubleArrayOf(10.0, 11.0, 12.0, 13.5)
            .toFlux()
            .averageBigDecimal()
            .test()
            .expectNext(BigDecimal("11.625"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalMapped() {
        userList.toFlux()
            .averageBigDecimal { it.age }
            .test()
            .expectNext(BigDecimal("33"))
            .verifyComplete()
    }

    //== min ==
    @Test
    fun minShorts() {
        shortArrayOf(12, 8, 16)
                .toFlux()
                .min()
                .test()
                .expectNext(8)
                .verifyComplete()
    }

    @Test
    fun minInts() {
        intArrayOf(12, 8, 16)
                .toFlux()
                .min()
                .test()
                .expectNext(8)
                .verifyComplete()
    }

    @Test
    fun minLongs() {
        longArrayOf(12, 8, 16)
                .toFlux()
                .min()
                .test()
                .expectNext(8)
                .verifyComplete()
    }

    @Test
    fun minFloats() {
        floatArrayOf(12f, 8.1f, 16f, 8.2f)
                .toFlux()
                .min()
                .test()
                .expectNext(8.1f)
                .verifyComplete()
    }

    @Test
    fun minDoubles() {
        doubleArrayOf(12.0, 8.1, 16.0, 8.2)
                .toFlux()
                .min()
                .test()
                .expectNext(8.1)
                .verifyComplete()
    }

    @Test
    fun minComparables() {
        comparableList.toFlux()
                .min()
                .test()
                .expectNext("A")
                .verifyComplete()
    }

    @Test
    fun minComparator() {
        userList.toFlux()
                .min(comparator)
                .map { it.name }
                .test()
                .expectNext("baby")
                .verifyComplete()
    }

    @Test
    fun minComparatorLambdaInverted() {
        userList.toFlux()
                .min { a, b -> b.age - a.age }
                .map { it.name }
                .test()
                .expectNext("grandpa")
                .verifyComplete()
    }

    //== max ==
    @Test
    fun maxShorts() {
        shortArrayOf(12, 8, 16)
                .toFlux()
                .max()
                .test()
                .expectNext(16)
                .verifyComplete()
    }

    @Test
    fun maxInts() {
        intArrayOf(12, 8, 16)
                .toFlux()
                .max()
                .test()
                .expectNext(16)
                .verifyComplete()
    }

    @Test
    fun maxLongs() {
        longArrayOf(12, 8, 16)
                .toFlux()
                .max()
                .test()
                .expectNext(16)
                .verifyComplete()
    }

    @Test
    fun maxFloats() {
        floatArrayOf(12f, 8.1f, 16f, 8.2f)
                .toFlux()
                .max()
                .test()
                .expectNext(16f)
                .verifyComplete()
    }

    @Test
    fun maxDoubles() {
        doubleArrayOf(12.0, 8.1, 16.0, 8.2)
                .toFlux()
                .max()
                .test()
                .expectNext(16.0)
                .verifyComplete()
    }

    @Test
    fun maxComparables() {
        comparableList.toFlux()
                .max()
                .test()
                .expectNext("BB")
                .verifyComplete()
    }

    @Test
    fun maxComparator() {
        userList.toFlux()
                .max(comparator)
                .map { it.name }
                .test()
                .expectNext("grandpa")
                .verifyComplete()
    }

    @Test
    fun maxComparatorLambdaInverted() {
        userList.toFlux()
                .max { a, b -> b.age - a.age }
                .map { it.name }
                .test()
                .expectNext("baby")
                .verifyComplete()
    }

//// == collection of numbers ==

    @Test
    fun numberCollectionSum() {
        val longs: List<Long> = listOf(1L, 2L, 3L)
        val floats: List<Float> = listOf(1.5f, 2.5f)
        val doubles: List<Double> = listOf(1.6, 2.6)

        Flux.concat(
                longs.toFlux().sum(),
                floats.toFlux().sum(),
                doubles.toFlux().sum())
                .test()
                .expectNext(6L)
                .expectNext(3L).`as`("floats rounded down")
                .expectNext(3L).`as`("doubles rounded down")
                .verifyComplete()
    }

    @Test
    fun numberCollectionDoubleSum() {
        val longs: List<Long> = listOf(1L, 2L, 3L)
        //avoid weird 1.6f == 1.600000023841858 precision artifacts
        val floats: List<Float> = listOf(1.5f, 2.5f)
        val doubles: List<Double> = listOf(1.6, 2.6)

        Flux.concat(
                longs.toFlux().sumDouble(),
                floats.toFlux().sumDouble(),
                doubles.toFlux().sumDouble())
                .test()
                .expectNext(6.0)
                .expectNext(4.0).`as`("floats")
                .expectNext(4.2).`as`("doubles")
                .verifyComplete()
    }

}