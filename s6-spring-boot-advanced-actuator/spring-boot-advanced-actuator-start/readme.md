## Aufgabe 

Für den bestehenden RouteService sollen Metriken eingeführt werden. Es sollen für die Business Methode businessFunction() die Anzahl der Aufrufe, die summierte Ausführungszeit und die maximale Ausführungszeit als Metrik bereitgestellt werden. 	
 
1. Erstellen Sie für die businessFunction() einen Meter vom Typ Timer. 
2. Übergeben Sie die Metrik an den Prometheus Metrik-Server.
3. Erstellen Sie über Grafana ein Dashboard zur Anzeige der Metriken.


Erstellen Sie nun noch einen eigenen Endpoint der den Route-Status zurückgibt.

1. Endpoint erstellen
2. Endpoint prüfen


Dieser soll zusätzlich ein reaktiver Endpoint für die gleiche Aufgabe bereitgestellt werden. 


1. Reactive Endpoint erstellen
2. Endpoint prüfen


# Metrik Implementieren 

## Timer Metrik implementieren 

Zunächst implmentieren Sie die Metrik über die Annotation  ``@Timer`` und fügen danach den ``TimerAspekt`` der Konfiguration hinzu. 


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

@Configuration
public class MetricConfiguration {

	@Bean
	TimedAspect timedAspect(MeterRegistry reg) {
		return new TimedAspect(reg);
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

