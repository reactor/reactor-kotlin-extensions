package reactor.kotlin.core.util

import reactor.util.function.*

/**
 * base class for importing TupleExtensions implicitly
 *
 * @author Jongho Jeon
 */
open class TupleExtBase {
    /**
     * @see reactor.kotlin.core.util.function.component1
     */
    operator fun <T> Tuple2<T, *>.component1(): T = t1
    /**
     * @see reactor.kotlin.core.util.function.component2
     */
    operator fun <T> Tuple2<*, T>.component2(): T = t2
    /**
     * @see reactor.kotlin.core.util.function.component3
     */
    operator fun <T> Tuple3<*, *, T>.component3(): T = t3
    /**
     * @see reactor.kotlin.core.util.function.component4
     */
    operator fun <T> Tuple4<*, *, *, T>.component4(): T = t4
    /**
     * @see reactor.kotlin.core.util.function.component5
     */
    operator fun <T> Tuple5<*, *, *, *, T>.component5(): T = t5
    /**
     * @see reactor.kotlin.core.util.function.component6
     */
    operator fun <T> Tuple6<*, *, *, *, *, T>.component6(): T = t6
    /**
     * @see reactor.kotlin.core.util.function.component7
     */
    operator fun <T> Tuple7<*, *, *, *, *, *, T>.component7(): T = t7
    /**
     * @see reactor.kotlin.core.util.function.component8
     */
    operator fun <T> Tuple8<*, *, *, *, *, *, *, T>.component8(): T = t8
}
