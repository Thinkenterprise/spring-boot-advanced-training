Die Fluggesellschaft möchte den Route-Service auf ein reaktive Web API umstellen.
1. Erstellen Sie einen reaktiven Web Controller, der eine statische List von Routen zurückgibt.
3. Schreiben Sie einen Test für den Controller Ansatz der das neue Reactive API testet.

Das Umstellen der Controller auf Functional APIs kann zusätzlich implementiert werden, sofern Zeit vorhanden ist. 


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
vor dem Start über die **application.properties** gesetzt werden muss. 


```
spring:
  profiles:
    active: controller


```

### Anwendung starten und testen 
 

Die Anwendung kann auch über die IDE gestartet werden, hier wird der Start über die Console gezeigt. 

```
mvn clean package 
mvn spring-boot:run
curl localhost:8080/routes
```


## Controller Testen 


```

@ActiveProfiles("controller")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
public class ReactiveRouteControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void routes() {

		webTestClient.get()
		             .uri("/routes")
		             .exchange()
		             .expectBodyList(Route.class)
		             .hasSize(3);
	}
	

}

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




