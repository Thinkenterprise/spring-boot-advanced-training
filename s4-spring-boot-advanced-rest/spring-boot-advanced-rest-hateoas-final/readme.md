# Aufgabe 




## Spring Data REST Bibliothek  

```java

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-rest</artifactId>
	</dependency>
```


## GET & POST with Spring Data REST   

**GET**

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


## Delete Event verwenden 




```java

@Configuration
public class HandlerConfiguration {
	
	@Bean
    public RouteEventHandler agentEvenHandler() {
        return new RouteEventHandler();
    }

}
	
```

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


## Customizing URI PATH and Link Relation


```java

@RepositoryRestResource(path = "transfers", collectionResourceRel = "transfers")
public interface RouteRepository extends PagingAndSortingRepository<Route, Long> {

    @Cacheable("routesByDeparture")
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


	

### Events einbinden 
 

```java
@Entity
public class Flight extends AbstractEntity implements FlightPrice {
}
```


### Flight Service Controller 

```java
@RestController
public class FlightServiceController {

	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightRepository flightRepository;
	
	
	@RequestMapping("/flightService")
    public String flightService() {
		List<Flight> flights = flightRepository.findAll();
       	List<FlightPrice> flightPrices  = flights.stream().map(flight -> (FlightPrice)flight).collect(Collectors.toList());
       	return String.valueOf(flightService.totalPrice(flightPrices));
    }
}
    
```
    
## Flight Service konfigurieren 

```
	flightservice:
  		taxRate: 1.5 
```



## Flight Service Anwendung bauen  


```java
	mvn clean package 
```


## Flight Service Anwendung testen   

```
java -jar target/<Application>.jar 
    
```
