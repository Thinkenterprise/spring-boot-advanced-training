Die Fluggesellschaft möchte den Route-Service auf ein reaktive Web API umstellen.
1. Erstellen Sie einen reaktiven Web Controller, der eine statische List von Routen zurückgibt.
2. Die Fluggesellschaft möchte zukünftig mehr funktional programmieren. Versuchen Sie das gleiche über Functional APIs zu realieren.
3. Schreiben Sie einen Test für den Controller Ansatz der das neue Reactive API testet.




## Webflux Starter hinzufügen 

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-webflux</artifactId>
	</dependency>      


```


## WebFlux Controller erstellen  




```
@RestController
@RequestMapping("routes")
@Profile("controller")
public class ReactiveRouteController {

	
	@RequestMapping
	public Flux<Route> routes() {
		return Flux.just(new Route("LH7902","MUC","IAH"), 
				         new Route("LH1602","MUC","IBZ"), 
				         new Route("LH401","FRA","NYC"));
	}
	 
}   


```

Damit der Controller von anderen noch kommenden Reactive Implementierungen unterscheiden werden kann ordnen wir ihn dem Profile **controller** zu, das 
vr dem Start über die **application.properties** gesetzt werden muss. 


```
spring:
  profiles:
    active: controller


```


### Anwendung starten und testen 
 

Die ANwendung kann auch über die IDE gestartet werden, hier wird der Start über die Console gezeigt. 

```
mvn clean package 
mvn spring-boot:run
curl localhost:8080/routes
```



### Anwendung auf Function API umstellen  

```java
@Configuration
@Profile("functional")
public class ReativeFunctionalConfiguration {
	
	@Bean
	public RouterFunction<ServerResponse> trackingsFunctional() {
	
		return RouterFunctions.route(RequestPredicates.GET("/routes"), 
				                     request -> ServerResponse.ok().body(Flux.just(new Route("LH7902","MUC","IAH"), 
				    				         									   new Route("LH1602","MUC","IBZ"), 
				    				         									   new Route("LH401","FRA","NYC")), 
				                     Route.class));
				
	}


}
    
```



```
spring:
  profiles:
    active: functional


```



    
### Anwendung starten und testen 
 

Die ANwendung kann auch über die IDE gestartet werden, hier wird der Start über die Console gezeigt. 

```
mvn clean package 
mvn spring-boot:run
curl localhost:8080/routes
```



