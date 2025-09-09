# Reactor Kotlin Extensions

[![Join the chat at https://gitter.im/reactor/reactor](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/reactor/reactor?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Add various extensions and adapters for Kotlin programming language. While Reactor Core, Reactor Addons and other Reactor libraries 
are designed to be Kotlin friendly with the use of `@Nullable` and carefully picked signature types, Kotlin developers might 
want to adapt the core functional programming API further.

# Maven Artifacts

With Gradle from repo.spring.io or Maven Central repositories (stable releases only):

```groovy
    repositories {
        //maven { url 'https://repo.spring.io/snapshot' }
        maven { url 'https://repo.spring.io/milestone' }
        mavenCentral()
    }

    dependencies {
        //implementation "io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.5-SNAPSHOT"
        implementation "io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.4"
    }
```

With Maven from Maven Central repositories (stable releases):

```xml
<dependency>
    <groupId>io.projectreactor.kotlin</groupId>
    <artifactId>reactor-kotlin-extensions</artifactId>
    <version>1.2.4</version>
</dependency>
```

Or from repo.spring.io with access to SNAPSHOT: 

```xml
...
<dependencies>

    <dependency>
        <groupId>io.projectreactor.kotlin</groupId>
        <artifactId>reactor-kotlin-extensions</artifactId>
        <version>1.2.5-SNAPSHOT</version>
    </dependency>

</dependencies>
...
<repositories>
    <repository>
        <id>spring-milestones</id>
        <name>Spring Milestones</name>
        <url>https://repo.spring.io/milestone</url>
    </repository>
    <repository>
        <id>spring-snapshots</id>
        <name>Spring Snapshots</name>
        <url>https://repo.spring.io/snapshot</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>

```

# Quick Examples

Tuple destructure:
```kotlin
val (t1, t2, t3) = Tuples.of(O1, O2, O3)
assertEquals(t1, O1)
assertEquals(t2, O2)
assertEquals(t3, O3)
```

Convert, sum (*requires reactor-extra*) and test (*requires reactor-test*):
```kotlin
intArrayOf(2_000_000_000, 200_000_000) //sum overflows an Int
    .toFlux()
    .sum()
    .test()
    .expectNext(2_200_000_000)
    .verifyComplete()
```

Coordinating when two mono completes:
```kotlin
whenComplete("foo1".toMono(), "foo2".toMono())
    .test()
    .verifyComplete()
```

## License

Reactor is [Apache 2.0 licensed](https://www.apache.org/licenses/LICENSE-2.0.html).
