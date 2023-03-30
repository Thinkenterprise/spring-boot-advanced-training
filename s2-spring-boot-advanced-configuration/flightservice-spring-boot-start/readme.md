# Aufgabe 

Die Fluggesellschaft stellt einen Service bereit, der für die Routenverwaltung verantwortlich ist. Der Service soll um eine Funktion erweitert werden, die es ermöglicht die Gesamteinnahmen aller durchgeführten Flüge unter Berücksichtigung eine konfigurierbaren Steuerfaktors zu berechnen. Dazu wurde bereits eine Bibliothek Flightservice.jar implementiert. Die Bibliothek soll nun über einen Starter bereitgestellt und in den Service eingebunden werden.

1. Binden Sie den Starter in den Service ein und bieten Sie die neue Funktion über eine Controller an.



## FlightService Starter einbinden 

```java
	<dependency>
		<groupId>com.thinkenterprise</groupId>
   		<artifactId>s2-flightservice-spring-boot-starter</artifactId>
   		<version>${flightservice.version}</version>
	</dependency>


```



## Flight Entity erweitern 
 

```java
@Entity
public class Flight extends AbstractEntity implements FlightPrice {
}
```


## Flight Service Controller 

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

Das Testen kann auch über die IDE erfolgen. 

```
java -jar target/<Application>.jar 
    
```
