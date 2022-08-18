Die Fluggesellschaft möchte die Web-APIs des Route-Service über die Swagger Spezifikation 2 Code First dokumentieren.
1. Führen Sie dazu SpringFox in Spring Boot ein.
2. Versehen Sie Code First den bestehenden RouteController für die Methode routes mit den
notwendigen Swagger Annotationen, um diese zu dokumentieren.
3. Rufen Sie die Spezifikation und die HTML-Dokumentation des laufenden Route-Service auf.

## SpringFox Dependencies einfügen  

```
         <dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>3.0.0</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>3.0.0</version>
		</dependency>
```


## SpringFox Dependencies konfigurieren   


```java
@Configuration
@EnableSwagger2
public class OpenAPIConfiguration {
		
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors
				.basePackage("com.thinkenterprise.route"))
				.paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Aearo").description("Route API").version("1.0").build();
	}

}
```

## Controller Annotieren 

```java
@RestController
@RequestMapping("routes")
@Api("routes")
public class RouteController {


	@Autowired
	RouteRepository routeRepository;
	
	@ApiOperation(value="Get all Routes", notes= "Read the list of routes from the repository", nickname="getAll", response=Route.class, responseContainer="List", code=200, tags={"Routes"}, produces="application/json")
	@ApiResponses(value = {@ApiResponse(code=204, message="empty list"), @ApiResponse(code=400, message="can't access routes ",response=Error.class)})
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Route>> getAll() {
		return new ResponseEntity<Iterable<Route>>(routeRepository.findAll(),HttpStatus.OK);
	}
```

## SpringFox testen 


Die **Swagger 2 Specification** rufen Sie auf indem Sie die URL des Service ermitteln und dann über **Postma** ``v2/api-docs `` eingeben. 


```
cf apps
Postman : http://<Service-url>/v2/api-docs

```

 

Die **Swagger 2 HTML Documentation** rufen Sie auf indem Sie die URL des Route Service ermitteln und dann über einen **Browser** eingeben ``swagger-ui.html `` eingeben. 


```
cf apps
Postman : http://<Service-url>/swagger-ui.html
```



