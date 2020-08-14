

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
		<excludedGroups>doNothing</excludedGroups>
	</configuration>
	
	>mvn clean package -DexcludedGroups="slow"
	
	Eclipse run Configuration 
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



