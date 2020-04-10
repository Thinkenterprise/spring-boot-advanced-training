# Aufgabe 




## FlightService Starter einbinden 

```java
	<dependency>
		<groupId>com.thinkenterprise</groupId>
   		<artifactId>flightservice-spring-boot-starter-start</artifactId>
   		<version>${flightservice.version}</version>
	</dependency>


```


## FlightService benutzen 


### Flight Entity erweitern 
 

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
