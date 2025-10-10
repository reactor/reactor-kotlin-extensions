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

package reactor.kotlin.extra.math

import org.junit.Test
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

        val comparator: Comparator<User> = Comparator { u1: User, u2: User -> u1.age - u2.age }

        val comparableList = listOf("AA", "A", "BB", "B", "AB")
    }

    //== New sum Just and Of ==

    @Test
    fun sumJustShorts() {
        shortArrayOf(15_000, 16_000)
            .toFlux()
            .sumAll()
            .test()
            .expectNext(31_000)
            .verifyComplete()
    }

    @Test
    fun sumJustInts() {
        intArrayOf(200_000_000, 200_000_000)
            .toFlux()
            .sumAll()
            .test()
            .expectNext(400_000_000)
            .verifyComplete()
    }

    @Test
    fun sumJustLongs() {
        longArrayOf(3_000_000_000, 2_000_000_000)
            .toFlux()
            .sumAll()
            .test()
            .expectNext(5_000_000_000)
            .verifyComplete()
    }

    @Test
    fun sumJustFloats() {
        floatArrayOf(3.6f, 1.5f)
            .toFlux()
            .sumAll()
            .test()
            .expectNext(5.1f)
            .verifyComplete()
    }

    @Test
    fun sumJustDoubles() {
        doubleArrayOf(3.5, 1.6)
            .toFlux()
            .sumAll()
            .test()
            .expectNext(5.1)
            .verifyComplete()
    }

    @Test
    fun sumOfMapped() {
        userList.toFlux()
            .sumAll { it.age }
            .test()
            .expectNext(99)
            .verifyComplete()
    }

    //== sumInt ==

    @Test
    fun sumIntShorts() {
        shortArrayOf(32_000, 32_000) //sum overflows a Short
            .toFlux()
            .sumAsInt()
            .test()
            .expectNext(64_000)
            .verifyComplete()
    }

    @Test
    fun sumIntInts() {
        intArrayOf(300_000_000, 200_000_000)
            .toFlux()
            .sumAsInt()
            .test()
            .expectNext(500_000_000)
            .verifyComplete()
    }

    @Test
    fun sumIntLongs() {
        longArrayOf(300_000_000, 200_000_000)
            .toFlux()
            .sumAsInt()
            .test()
            .expectNext(500_000_000)
            .verifyComplete()
    }

    @Test
    fun sumIntFloats() {
        floatArrayOf(3.5f, 1.9f)
            .toFlux()
            .sumAsInt()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    @Test
    fun sumIntDoubles() {
        doubleArrayOf(3.5, 1.5)
            .toFlux()
            .sumAsInt()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    //== sumLong ==

    @Test
    fun sumLongShorts() {
        shortArrayOf(32_000, 32_000) //sum overflows a Short
            .toFlux()
            .sumAsLong()
            .test()
            .expectNext(64_000)
            .verifyComplete()
    }

    @Test
    fun sumLongInts() {
        intArrayOf(2_000_000_000, 2_000_000_000) //sum overflows an Int
            .toFlux()
            .sumAsLong()
            .test()
            .expectNext(4_000_000_000)
            .verifyComplete()
    }

    @Test
    fun sumLongLongs() {
        longArrayOf(300_000_000_000, 200_000_000_000)
            .toFlux()
            .sumAsLong()
            .test()
            .expectNext(500_000_000_000)
            .verifyComplete()
    }

    @Test
    fun sumLongFloats() {
        floatArrayOf(3.5f, 1.9f)
            .toFlux()
            .sumAsLong()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    @Test
    fun sumLongDoubles() {
        doubleArrayOf(3.5, 1.5)
            .toFlux()
            .sumAsLong()
            .test()
            .expectNext(4)
            .verifyComplete()
    }

    //== sumFloat ==

    @Test
    fun sumFloatShorts() {
        shortArrayOf(32_000, 32_000) //sum overflows a Short
            .toFlux()
            .sumAsFloat()
            .test()
            .expectNext(64000F)
            .verifyComplete()
    }

    @Test
    fun sumFloatInts() {
        intArrayOf(2_000_000_000, 2_000_000_000) //sum overflows an Int
            .toFlux()
            .sumAsFloat()
            .test()
            .expectNext(4E9F)
            .verifyComplete()
    }

    @Test
    fun sumFloatLongs() {
        longArrayOf(30_000_000_000, 20_000_000_000) // There is some precision loss in these conversions.
            .toFlux()
            .sumAsFloat()
            .test()
            .expectNext(5.0000003E10F)
            .verifyComplete()
    }

    @Test
    fun sumFloatFloats() {
        floatArrayOf(3.5f, 1.9f)
            .toFlux()
            .sumAsFloat()
            .test()
            .expectNext(5.4F)
            .verifyComplete()
    }

    @Test
    fun sumFloatDoubles() {
        doubleArrayOf(3.5, 1.9)
            .toFlux()
            .sumAsFloat()
            .test()
            .expectNext(5.4F)
            .verifyComplete()
    }

    //== sumAsDouble ==
    @Test
    fun sumAsDoubleShorts() {
        shortArrayOf(32_000, 8_000) //sum overflows a Short
            .toFlux()
            .sumAsDouble()
            .test()
            .expectNext(40_000.0)
            .verifyComplete()
    }

    @Test
    fun sumAsDoubleInts() {
        intArrayOf(2_000_000_000, 200_000_000) //sum overflows an Int
            .toFlux()
            .sumAsDouble()
            .test()
            .expectNext(2_200_000_000.0)
            .verifyComplete()
    }

    @Test
    fun sumAsDoubleLongs() {
        longArrayOf(3_000_000_000, 2_000_000_000)
            .toFlux()
            .sumAsDouble()
            .test()
            .expectNext(5_000_000_000.0)
            .verifyComplete()
    }

    @Test
    fun sumAsDoubleFloats() {
        floatArrayOf(3.5f, 1.5f)
            .toFlux()
            .sumAsDouble()
            .test()
            .expectNext(5.0)
            .verifyComplete()
    }

    @Test
    fun sumAsDoubleDoubles() {
        doubleArrayOf(3.5, 1.5)
            .toFlux()
            .sumAsDouble()
            .test()
            .expectNext(5.0)
            .verifyComplete()
    }

    //== sumBigInt ==

    @Test
    fun sumBigIntegerShorts() {
        shortArrayOf(32_000, 32_000)
            .toFlux()
            .sumAsBigInt()
            .test()
            .expectNext(BigInteger("64000"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerInts() {
        intArrayOf(2_000_000_000, 2_000_000_000)
            .toFlux()
            .sumAsBigInt()
            .test()
            .expectNext(BigInteger("4000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerLongs() {
        longArrayOf(30_000_000_000, 20_000_000_000)
            .toFlux()
            .sumAsBigInt()
            .test()
            .expectNext(BigInteger("50000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerFloats() {
        floatArrayOf(3.9f, 1.9f)
            .toFlux()
            .sumAsBigInt()
            .test()
            // 5.8 which now gets rounded down to 5 as sum drops the fractional part
            .expectNext(BigInteger("5"))
            .verifyComplete()
    }

    @Test
    fun sumBigIntegerDoubles() {
        doubleArrayOf(3.9, 1.9)
            .toFlux()
            .sumAsBigInt()
            .test()
            // == 5.8 which now gets rounded down to 5 as sum drops the fractional part
            .expectNext(BigInteger("5"))
            .verifyComplete()
    }

    //== sumBigDecimal ==

    @Test
    fun sumBigDecimalShorts() {
        shortArrayOf(32_000, 32_000)
            .toFlux()
            .sumAsBigDecimal()
            .test()
            .expectNext(BigDecimal("64000"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalInts() {
        intArrayOf(2_000_000_000, 2_000_000_000)
            .toFlux()
            .sumAsBigDecimal()
            .test()
            .expectNext(BigDecimal("4000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalLongs() {
        longArrayOf(30_000_000_000, 20_000_000_000)
            .toFlux()
            .sumAsBigDecimal()
            .test()
            .expectNext(BigDecimal("50000000000"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalFloats() {
        floatArrayOf(3.5f, 1.99f)
            .toFlux()
            .sumAsBigDecimal()
            .test()
            .expectNext(BigDecimal("5.49"))
            .verifyComplete()
    }

    @Test
    fun sumBigDecimalDoubles() {
        doubleArrayOf(3.5, 1.99)
            .toFlux()
            .sumAsBigDecimal()
            .test()
            .expectNext(BigDecimal("5.49"))
            .verifyComplete()
    }

    //== New average Just and Of ==

    @Test
    fun averageJustShorts() {
        shortArrayOf(10, 11, 13, 14)
            .toFlux()
            .averageAll()
            .test()
            .expectNext(12)
            .verifyComplete()
    }

    @Test
    fun averageJustInts() {
        intArrayOf(10, 11, 13, 14)
            .toFlux()
            .averageAll()
            .test()
            .expectNext(12)
            .verifyComplete()
    }

    @Test
    fun averageJustLongs() {
        longArrayOf(10, 11, 13, 14)
            .toFlux()
            .averageAll()
            .test()
            .expectNext(12)
            .verifyComplete()
    }

    @Test
    fun averageJustFloats() {
        floatArrayOf(10f, 11f)
            .toFlux()
            .averageAll()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageJustDoubles() {
        doubleArrayOf(10.0, 11.0)
            .toFlux()
            .averageAll()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageOfMapped() {
        userList.toFlux()
            .averageAll { it.age }
            .test()
            .expectNext(33)
            .verifyComplete()
    }

    //== averageFloat ==

    @Test
    fun averageFloatShorts() {
        shortArrayOf(10, 11)
            .toFlux()
            .averageAsFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatInts() {
        intArrayOf(10, 11)
            .toFlux()
            .averageAsFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatLongs() {
        longArrayOf(10, 11)
            .toFlux()
            .averageAsFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatFloats() {
        floatArrayOf(10f, 11f)
            .toFlux()
            .averageAsFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    @Test
    fun averageFloatDoubles() {
        doubleArrayOf(10.0, 11.0)
            .toFlux()
            .averageAsFloat()
            .test()
            .expectNext(10.5f)
            .verifyComplete()
    }

    //== averageDouble ==

    @Test
    fun averageDoubleShorts() {
        shortArrayOf(10, 11)
            .toFlux()
            .averageAsDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleInts() {
        intArrayOf(10, 11)
            .toFlux()
            .averageAsDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleLongs() {
        longArrayOf(10, 11)
            .toFlux()
            .averageAsDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleFloats() {
        floatArrayOf(10f, 11f)
            .toFlux()
            .averageAsDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    @Test
    fun averageDoubleDoubles() {
        doubleArrayOf(10.0, 11.0)
            .toFlux()
            .averageAsDouble()
            .test()
            .expectNext(10.5)
            .verifyComplete()
    }

    //== averageBigInt ==

    @Test
    fun averageBigIntShorts() {
        shortArrayOf(10, 11, 12)
            .toFlux()
            .averageAsBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntInts() {
        intArrayOf(10, 11, 12)
            .toFlux()
            .averageAsBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntLongs() {
        longArrayOf(10, 11, 12)
            .toFlux()
            .averageAsBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntFloats() {
        floatArrayOf(10f, 11f, 12f)
            .toFlux()
            .averageAsBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    @Test
    fun averageBigIntDoubles() {
        doubleArrayOf(10.0, 11.0, 12.0)
            .toFlux()
            .averageAsBigInt()
            .test()
            .expectNext(BigInteger("11"))
            .verifyComplete()
    }

    //== averageBigDecimal ==

    @Test
    fun averageBigDecimalShorts() {
        shortArrayOf(10, 11, 11, 11)
            .toFlux()
            .averageAsBigDecimal()
            .test()
            .expectNext(BigDecimal("10.75"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalInts() {
        intArrayOf(10, 11, 11, 11)
            .toFlux()
            .averageAsBigDecimal()
            .test()
            .expectNext(BigDecimal("10.75"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalLongs() {
        longArrayOf(10, 11, 11, 11)
            .toFlux()
            .averageAsBigDecimal()
            .test()
            .expectNext(BigDecimal("10.75"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalFloats() {
        floatArrayOf(10f, 11f, 12f, 13.5f)
            .toFlux()
            .averageAsBigDecimal()
            .test()
            .expectNext(BigDecimal("11.625"))
            .verifyComplete()
    }

    @Test
    fun averageBigDecimalDoubles() {
        doubleArrayOf(10.0, 11.0, 12.0, 13.5)
            .toFlux()
            .averageAsBigDecimal()
            .test()
            .expectNext(BigDecimal("11.625"))
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
}