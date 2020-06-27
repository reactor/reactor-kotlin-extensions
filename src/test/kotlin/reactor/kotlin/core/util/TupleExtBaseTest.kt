package reactor.kotlin.core.util

import org.junit.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.test.test
import reactor.util.function.Tuple2
import reactor.util.function.Tuples

object O1; object O2; object O3; object O4

class TupleExtBaseImpl : TupleExtBase() {

    fun destructureTuple2WithMonoMap(): Mono<Tuple2<O3, O4>> =
        Mono.zip(Mono.just(O1), Mono.just(O2))
            .map { (a, b) ->
                Tuples.of(O3, O4)
            }

    fun destructureTuple2WithMonoFlatMap(): Mono<Tuple2<O3, O4>> =
        Mono.zip(Mono.just(O1), Mono.just(O2))
            .flatMap { (a, b) ->
                Mono.zip(Mono.just(O3), Mono.just(O4))
            }

    fun destructureTuple2WithFluxFlatMap(): Flux<Tuple2<O3, O4>> =
        Flux.zip(Flux.just(O1), Flux.just(O2))
            .flatMap { (a, b) ->
                Flux.zip(Flux.just(O3), Flux.just(O4))
            }
}

internal class TupleExtBaseTest {

    @Test
    fun `destructure Tuple2 with Mono#map`() {
        TupleExtBaseImpl().destructureTuple2WithMonoMap()
            .test()
            .expectNext(Tuples.of(O3, O4))
    }

    @Test
    fun `destructure Tuple2 with Mono#flatMap`() {
        TupleExtBaseImpl().destructureTuple2WithMonoFlatMap()
            .test()
            .expectNext(Tuples.of(O3, O4))
    }

    @Test
    fun `destructure Tuple2 with Flux#flatMap`() {
        TupleExtBaseImpl().destructureTuple2WithFluxFlatMap()
            .test()
            .expectNext(Tuples.of(O3, O4))
    }
}
