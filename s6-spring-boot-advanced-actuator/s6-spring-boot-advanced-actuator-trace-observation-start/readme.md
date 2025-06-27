# Observation API

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

# Tracing 

Im ``RouteController`` folgende Implementierung einfügen 

```
	@RequestMapping("trace")
	public String getTrace() {
		logger.info("Hello Tracing");
		return "Hello Tracing";
	}

```

Danach z.B. über Postman einen Request ``trace`` absetzen und danach Zipkin ``localhost:9411`` aufrufen und **Trace** erklären.  


# Observation API 

Hier werden nun alle drei Aspekte **Trace**, **Metric** und **Log** anhnad des neuen Observation APIs erklärt, einmal deklarativ und einmal über das API.  

## Declarative  

```
	@RequestMapping("observability")
	@Observed(name = "observability", contextualName = "observability", lowCardinalityKeyValues = {"A", "B"})
	public String getObservability() {
		return "Hello Tracing";
	}
```

Danach z.B. über Postman einen Request ``observability`` absetzen und danach Promethues ``localhost:9411`` aufrufen und **Trace**, **Metric** und **Log** erklären.  


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


## Erweiterungen 
Es gibt seit Spring 3.2 weitere Untersützung der folgenden Annotationen, die verwendet werdeen können. @Observed haben wir gezeigt weitere sind möglich. 

You can now use Micrometer’s @Timed, @Counted, @NewSpan, @ContinueSpan and @Observed annotations. The aspects for them are now auto-configured if you have AspectJ on the classpath.



