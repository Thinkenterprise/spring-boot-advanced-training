




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

## Schema und Daten in Datenbank laden 


```java
@Configuration
public class RouteDatabase {

	 	@Bean
	    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

	        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
	        initializer.setConnectionFactory(connectionFactory);

	        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
	        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
	        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
	        initializer.setDatabasePopulator(populator);

	        return initializer;
	    }
}

```


