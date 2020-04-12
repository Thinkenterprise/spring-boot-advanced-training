

## Just a Flux

```java
public class ReactiveTests {

	protected static Logger log = LoggerFactory.getLogger(ReactiveTests.class);

	@Test
	public void createFluxWithJust() {
		@SuppressWarnings("unused")
		Flux<String> flux = Flux.just("Hello", "world");
	}

}
```

## Empty Flux 

```java
@Test
	public void createFluxWithEmpty() {

		@SuppressWarnings("unused")
		Flux<String> flux = Flux.empty();
	}
```

## Flux from Array

```java
	@Test
	public void createFluxWithfromArray() {

		@SuppressWarnings("unused")
		Flux<Integer> flux = Flux.fromArray(new Integer[] { 1, 2, 3 });
	}
```

## Flux from Range

```java
@Test
	public void createFluxWithfromRange() {

		@SuppressWarnings("unused")
		Flux<Integer> flux = Flux.range(1, 500);
	}
```

## Just a Mono 

```java
@Test
	public void createMonoWithJust() {

		@SuppressWarnings("unused")
		Mono<String> mono = Mono.just("One");
	}
```

## Empty Mono

```java
	@Test
	public void createMonoWithJustOrEmpty() {

		@SuppressWarnings("unused")
		Mono<String> stream = Mono.justOrEmpty(Optional.empty());
	}
```

## Flux Range with Subscription

```java
@Test
	public void createFluxWithConsumerAsSubscription() {

		Flux.range(1, 500).subscribe(i -> System.out.println(i));
	}

```

## Flux Range with Customer Subscription

```java
@Test
	public void createFluxWithCustomerSubscription() {

		Flux.range(1, 500).subscribe(new CustomerSubscription<Integer>());
	}
```

## Flux Range with Block

```java
@Test
	public void createFluxWithFrameworkBlockSubcription() {

		Flux.range(1, 500).doOnNext(e -> log.info("{}" + e)).blockLast();
	}
```

## Flux with Map Operator

```java
@Test
	public void createFluxWithMapOperator() {

		Flux.range(1, 500).map(e -> String.valueOf(e)).subscribe(i -> System.out.println(i));
	}
```

## Flux with Operator Thread

```java
	@Test
	public void createFluxWithFilterAndSchedulerOperator() {

		Flux.range(1, 500).publishOn(Schedulers.elastic()).filter(i -> i < 20).subscribe(i -> System.out.println(i));
	}
```

## Flux with Programmatic Generator

```java
	@Test
	public void createFluxProgrammaticWithGenerate() {

		Flux<String> flux = Flux.generate(() -> 0, (state, sink) -> {
			sink.next("3 x " + state + " = " + 3 * state);
			if (state == 10)
				sink.complete();
			return state + 1;
		});
		
		flux.subscribe(e -> System.out.println(e));

	}
```

## Verifier

```java
	@Test
	public void testMonoWithStepVerifier() {	
			Mono<String> mono = Mono.just("One");		
			StepVerifier.create(mono).expectNext("One").verifyComplete();
	}
```






