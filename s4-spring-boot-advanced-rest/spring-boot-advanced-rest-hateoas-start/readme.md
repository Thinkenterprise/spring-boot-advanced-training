# Aufgabe 

Die CRUD-Operationen auf der Persistenz sollen nun über Spring Data REST bereitgestellt werden.
1. Aktivieren Sie Spring Data REST.
2. Führen Sie über das Spring Data REST API den Aufruf für die Anzeige aller Routen durch und ändern Sie eine Route.
3. Führen Sie für das Löschen von Routen einen Event Handler ein der auf der Konsole Text ausgibt.
4. Ändern sie den URI Path von Routes auf transfers und ebenfalls die dazugehörige Releation
5. Ändern die Sichtbarkeit der Methode findByDeparture so dass sie nicht über das API verfügbar ist


## Spring Data REST Bibliothek  

```java

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-rest</artifactId>
	</dependency>
```


## GET & POST with Spring Data REST   

Testen Sie das REST API über Postman. 

**GET**

```

OPERATION: GET
URL: http://localhost:8080
HEADER: Content-Type application/json	
```


**POST**

```

OPERATION: POST
URL: http://localhost:8080/routes
BODY: 
{
	"flightNumber":"FT123",
	"departure":"MUC",
	"destination":"FRA"
}

HEADER: Content-Type application/json	
```


## Delete Event Handler Erstellen  




```java

@RepositoryEventHandler(Route.class)
public class RouteEventHandler {
	
	protected static Logger logger = LoggerFactory.getLogger(RouteEventHandler.class);
	
	
	@HandleBeforeDelete
	public void beforeDelete(Route route) {
		logger.info("Before Delete from route with id: " + route.getId().toString());
		
	}
	

}
	
```

## Delete Event Handler Konfigurieren 


```java

@Configuration
public class HandlerConfiguration {
	
	@Bean
    public RouteEventHandler agentEvenHandler() {
        return new RouteEventHandler();
    }

}
	
```



## Customizing URI PATH and Link Relation


```java

@RepositoryRestResource(path = "transfers", collectionResourceRel = "transfers")
public interface RouteRepository extends PagingAndSortingRepository<Route, Long> {

    @Query("select r from Route r where r.departure = :departure")
    Iterable<Route> findByDeparture(@Param("departure") String departure);
}

```


## Spring Data REST HAL Bowser 


```java

	<dependency>
		<groupId>org.springframework.data</groupId>
		<artifactId>spring-data-rest-hal-explorer</artifactId>
	</dependency>
```

Der HAL Browser müsste danach über 


```
localhost:8080/explorer
```

aufrufbar sein.

