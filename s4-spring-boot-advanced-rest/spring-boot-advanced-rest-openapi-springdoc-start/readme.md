Die Fluggesellschaft möchte die Web-APIs des Route-Service über die OpenAPI 3Code-First dokumentieren. 
1. Führen Sie dazu SpringDoc in Spring Boot ein. 
2. Versehen Sie Code-First den bestehenden RouteController für die Methode getRoute mit den notwendigen OpenAPI 3 Swagger Annotationen, um diese zu dokumentieren. 
3. Rufen Sie die Spezifikation und die HTML-Dokumentation des laufenden Route-Service auf. 
4. Erstellen Sie die Server Stubs aus der beigelegten Open API Specification über das OpenAPI Generator Maven Plugin


## SpringDoc Dependencies einfügen  

```
     <dependency>
		<groupId>org.springdoc</groupId>
		<artifactId>springdoc-openapi-ui</artifactId>
		<version>1.4.6</version>
	</dependency>
```


## SpringDoc Bean Configuration erstellern    


```java
@Configuration
public class OpenAPIConfiguration {
		
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Aero API").version("1.0")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
	
	
}
```

## Controller Annotieren 

```java
@RestController
@RequestMapping("routes")
@Tag(name = "Route Controller")
@SecurityScheme(name = "OAuth2Security",type = SecuritySchemeType.OAUTH2,in = SecuritySchemeIn.HEADER,
				flows = @OAuthFlows(
				implicit = @OAuthFlow(authorizationUrl = "http://url.com/auth",
                scopes = @OAuthScope(name = "read:route", description = "Read routes"))))
public class RouteController {

	@Autowired
	RouteRepository routeRepository;

	@Operation(summary = "Get one Route", responses = {
			@ApiResponse(description = "The Route", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Route.class))),
			@ApiResponse(responseCode = "400", description = "User not found") })
	@SecurityRequirement(name = "Read routes", scopes = "read:route")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Route> get(
			@Parameter(description = "route itentifier", required = true) @PathVariable(value = "id") Long id) {
		return new ResponseEntity<Route>(routeRepository.findById(id).get(),HttpStatus.OK);

	}

} 
```

## Spring Doc testen 


Die **OpenAPI 3.0 Specification** rufen Sie auf indem Sie die URL des Service ermitteln und dann über **Postma** ``localhost:8080/oas/v3/spec `` eingeben. 


```
Postman : localhost:8080/oas/v3/spec

```

 

Die **Open API 3.0  HTML Documentation** rufen Sie auf, indem Sie die URL des Route Service ermitteln und dann über einen **Browser**  ``localhost:8080/oas/v3/ui `` eingeben. 


```
localhost:8080/oas/v3/ui
```



