

## ExtendsWith 

```java

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestRouteController {

}

```

## BeforeAll & BeforeEach vs. BeforeCalss & BeforeEach

```java
	
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestRouteController {
	
	@BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }
 
    @BeforeEach
    static void beforeEcha() {
        System.out.println("After all test methods");
    }
	
}

```

## Display Name 

```java
@Test
@DisplayName("Checking if context is loaded with beans")
public void contextLoad() {
}

```


## Autowired 

```java
	@Test
	@DisplayName("Checking if repository is loaded with data by Autowire")
	public void checkRouteRepositoryWithAutowire(@Autowired TestRestTemplate restTemplate) {
		Iterable<Route> iterable = restTemplate.getForObject("/routes", Iterable.class);
		Assertions.assertTrue(iterable.iterator().hasNext());
	} 

```


## Qualifier 

```java
@Test
	@DisplayName("Checking if repository is loaded with data by @Qualifier")
	@DirtiesContext(methodMode=MethodMode.BEFORE_METHOD)
	public void checkRouteRepositoryWithQualifier(@Qualifier("routeRepository") RouteRepository routeRepository) {
		Assertions.assertTrue(routeRepository.count()>0);
	} 
```


## Properties   

```java
	@Test
	@DisplayName("Checking property route count == 4")
	@DirtiesContext(methodMode=MethodMode.BEFORE_METHOD)
	public void checkRouteRepositoryWithQualifier(@Value("${route.count}") float count) {
		Assertions.assertTrue(count == 4);
	} 

```

## Tag 
```java

	@Test
	@Tag("doNothing")
	public void checkExcludeTest() {
		System.out.println("doNoting");
		Assertions.assertTrue(true);
	} 
```

```java
<configuration>
	<excludes>
		<exclude>doNothing</exclude>
	</excludes>
</configuration>
```

## @EnabledOnOs 
Es gibt eine Menge mehr an Enabling Annotationen. Annotation im Source Code zeigen, dann sieht man die anderen 

```java
	@Test
	@EnabledOnOs(OS.MAC)
	public void checkMacOs() {
		Assertions.assertTrue(ture); // on Windows!!
	} 

```

## @Enableif 
Spring Annotation in der man eine SPEL verwenden kann. 

```java
@Test
	@EnabledIf(expression="false")
	public void checkConditionalTest() {
		Assertions.assertTrue(false);
	} 

```



