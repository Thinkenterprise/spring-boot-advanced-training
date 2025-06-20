## Aufgabe

Die Fluggesellschaft plant die Umstellung der Relationalen Datenbank auf eine reaktive nicht blockierende Relationale Datenbank umzustellen, damit das reaktive Web API optimal unterstützt wird.

1. Führen Sie eine reaktive H2 Datenbank auf der Grundlage der R2DBC ein.
2. Stellen Sie ein reaktives Repository für den Zugriff auf die Routen in der Datenbank zur Verfügung.
3. Schreiben Sie einen Test um das Repository zu prüfen.


## R2DBC Spring Boot Starter hinzufügen 

```java
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-r2dbc</artifactId>
	</dependency>

```


## R2DBC H2 Driver hinzufügen 

```java
	<dependency>
		<groupId>io.r2dbc</groupId>
		<artifactId>r2dbc-h2</artifactId>
	</dependency>

```

## Fügen Sie der Route einen Identifier hinzu 


```java
	@Id
	private Long id;

```

## Erstellen Sie ein Repository

```java
	public interface ReactiveRouteRepository extends ReactiveCrudRepository<Route, Long>{
	
	@Query("SELECT * FROM route WHERE flight_number = :flightNumber")
    Flux<Route> findByFlightNumber(String flightNumber);

}
```

## Test schreiben 

```java
@DataR2dbcTest
public class RouteRepositoryTests {

    @Autowired
    private ReactiveRouteRepository reactiveRouteRepository;
    
    @Test
    public void findByFlightNumber() {
        	
    	reactiveRouteRepository.findById(101L)
    						   .as(StepVerifier::create)
    	                       .assertNext(route -> Assertions.assertTrue(route.getId().equals(101L)))
    	                       .verifyComplete();

    }
}
```



