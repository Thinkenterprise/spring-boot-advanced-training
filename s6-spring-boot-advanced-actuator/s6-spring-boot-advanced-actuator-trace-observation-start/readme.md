## 

Hier die notwendigen Dependnecies für **OTEL**

```
<dependency>
	<groupId>io.micrometer</groupId>
	<artifactId>micrometer-tracing-bridge-otel</artifactId>
</dependency>
<dependency>
	<groupId>io.opentelemetry</groupId>
	<artifactId>opentelemetry-exporter-zipkin</artifactId>
</dependency>
```

## Tracing 

Im ``RouteController`` folgende Implementierung einfügen 

```
	@RequestMapping("trace")
	public String getTrace() {
		logger.info("Hello Tracing");
		return "Hello Tracing";
	}

```

Danach z.B. über Postman einen Request ``trace`` absetzen und danach Zipkin ``localhost:9411`` aufrufen und **Trace** erklären.  


## Observation API 

Hier werden nun alle drei Aspekte **Trace**, **Metric** und **Log** anhnad des neuen Observation APIs erklärt, einmal deklarativ und einmal über das API.  

### Declarative  

```
	@RequestMapping("observability")
	@Observed(name = "observability", contextualName = "observability", lowCardinalityKeyValues = {"A", "B"})
	public String getObservability() {
		return "Hello Tracing";
	}
```

Danach z.B. über Postman einen Request ``trace`` absetzen und danach Zipkin ``localhost:9411`` aufrufen und **Trace**, **Metric** und **Log** erklären.  


### API

Hier nochmal zeigen, wie das über das API geht und welche Methoden aufgerufen werden müssen. 


```
	@RequestMapping("observabilityApi")
	public String getObservabilityApi() {
		
		Integer randomValue = random.nextInt(1000);
		
		Observation businessFunctionObservation= Observation.createNotStarted("businessFunctionTimerObservation", this.observationRegistry);
		
		// Metrik (Timer)  
		businessFunctionObservation.start();
		
		// Log 
		businessFunctionObservation.event(Observation.Event.of("my.Event"));
		
		
		try {
			Thread.sleep(Math.abs(randomValue));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		businessFunctionObservation.stop();
		
		return "Hello Tracing";
	}
```















```
@Service
public class RouteService {

private Random random = new Random();

	@Timed(value = "businessFunctionTimer", extraTags = { "business",
			"service" }, description = "Execution time of business function")
	public Integer businessFunction() {

		Integer randomValue = random.nextInt(1000);

		try {
			Thread.sleep(Math.abs(randomValue));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	
		return randomValue;
	}

}

```

## Anwendung starten 
Aus der IDE oder über die Konsole 


```
mvn clean package
java -jar <target/application>
```

## Counter Metrik prüfen 

```
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/metrics/businessFunctionTimer
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


## Prometheus Metriken abfragen 

```
http://localhost:8080/actuator/prometheus
```

## Prometheus & Grafana starten 


### Über Docker Compose Support von Spring Boot 

In dem Verzeichnis ``infrastructure`` liegt ein Dokcer Kompose File, dass automatisch beim Start von Spring Boot aufgerufen werden soll.  

Dazu sind zwei Schritte notwendig. Im ersten Schritt ist die folgende Dependency hinzuzufügen. 


```
 <dependency>
	<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-docker-compose</artifactId>
		<optional>true</optional>
	</dependency>
```

Im zweiten Schritt muss in der Spring Boot Konfiguration ``application.properties`` der Ort des Docker Compose Files angegeben werden. 

```
spring.docker.compose.file=./infrastructure/docker-compose.yml
```

### Manuell 

Nun soll die von Spring Boot bereitgestellten Metriken über Prometheus, Grafan und Altermanager erfasst 
und angezeigt werden. Starten Sie dazu die benötigte Infrastruktur. 

Wechseln Sie in das Verzeichnis ``infrastructure`` und starten Sie die Infrastruktur über 


```
docker compose up -d 
```


## Metrik über Prometheus abrufen 

Über die URL können Sie Prometheus aufrufen und dort die Metriken anzeigen lassen. 


```
http://localhost:9090/
```

## Metrik über Grafana abrufen 

Prometheus ist für das Erfassen und Speicher der Metriken verantwortlich. Prometheus 
besitzt zwar eine Anzeige, die wird aber in der Regel nicht für das Monitoring verwendet. 
Grafana ist spezialisiert für die Anzeige von Metriken aus verschiedenen Quellen und 
bietet umfassende Möglichkeiten Monitoring Dashboards zur Verfügung zu setellen. 

Die Voristallierte Version von Grafana besitzt bereits eine konfigurierte Datenquelle 
zu Prometheus. Legen Sie über Dashboard einen Graphen an, der eine der Metriken anzeigt. 


```
http://localhost:3000/
```

Die Zugangsdaten sind **admin** und **foobar**



## Shutdown 

Fahren Sie bitte mit dem folgenden Befehl die Infrastruktur wieder herunter. 

```
docker compose down 
```





# Eigenen Endpoint implementieren  

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

