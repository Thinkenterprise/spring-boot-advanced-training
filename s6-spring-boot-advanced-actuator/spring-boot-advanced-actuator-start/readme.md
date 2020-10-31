## Aufgabe 

Es ist eine Erweiterung des Monitoring geplant. Es soll eine Metrik bereitgestellt werden, über die die Ausfallhäufigkeit des Route-Service gemessen werden kann. Die Metrik soll in das Monitoring System Prometheus eingespeist werden.

1. Erstellen Sie eine Metrik für die Ausfallhäufigkeit.
2. Rufen Sie die Metrik über den bestehenden Metrik Endpoint ab.
3. Stellen Sie die Metrik über Prometheus bereit.

Erstellen Sie nun noch einen eigenen Endpoint der den Route-Status zurückgibt.

1. Endpoint erstellen
2. Endpoint prüfen


Dieser soll zusätzlich ein reaktiver Endpoint für die gleiche Aufgabe bereitgestellt werden. 


1. Reactive Endpoint erstellen
2. Endpoint prüfen


## Metrik Implementieren 

```java
@Service
public class RouteService {

    private boolean serviceStatus;
    private boolean running = true;
  
    
    private MeterRegistry meterRegistry;
	private Counter routeRequestCount;
	
	@Autowired
	public RouteService(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
		this.initializeCounters();
	}
	
	void initializeCounters () {	
		routeRequestCount = Counter
				  .builder("routeServiceStateChangeCounter")
				  .description("counts number of state changes")
				  .tags("service", "states")
				  .register(this.meterRegistry);
	}
	

	public double incrementCounter() {
		routeRequestCount.increment();
		return routeRequestCount.count();
	}
        
   
   
}

```

## Anwendung starten 
Aus der IDE oder über die Konsole 
 
```
mvn clean package
java -jar <target/application>
```

## Metrik prüfen 

```
http://localhost:8080/actuator/metrics
```

## Prometheus einführen 

```
 <dependency>
	<groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
 </dependency>
```


## Anwendung starten 
Aus der IDE oder über die Konsole 
 
```
mvn clean package
java -jar <target/application>
```


## Prometheus starten 
 
Der absolute Pfad <absolute-path> kann über ``pwd`` in der Shell ermittelt und in das Kommando eingefügt werden.

```
docker run -d -p 9090:9090 --mount type=bind,source=<absolute-path>/prometheus.yml,target=/etc/prometheus/prometheus.yml prom/prometheus
```


## Metrik abrufen 
```
localhost:9090
```

## Eigenen Endpoint implementieren  

```java
@Component
@Endpoint(id="routeService")
public class RouteServiceEndpoint {

	@Autowired
	private RouteService routeService;
	

	@ReadOperation
	public Boolean getServiceStatus() {
		return routeService.getServiceStatus();
	}
	
}
```

## Endpoint aufrufen 

```
http://localhost:8080/actuator/routeService
```


## Endpoint parallel als reaktiven Endpoint bereitstellen   

```
@Component
@Endpoint(id="reactiveRouteService")
public class ReactiveRouteServiceEndpoint {

	@Autowired
	private RouteService routeService;
	
	
	@ReadOperation
	public Mono<Boolean> getServiceStatus() {
		return Mono.just(routeService.getServiceStatus());
	}
	
}

```

## Endpoint aufrufen 

```
http://localhost:8080/actuator/reactiveRouteService
```

