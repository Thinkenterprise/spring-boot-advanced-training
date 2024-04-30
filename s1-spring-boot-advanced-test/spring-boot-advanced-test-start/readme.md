

## ExtendsWith 

```java

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestRouteController {

}

```

## BeforeAll & BeforeEach vs. BeforeClass & BeforeEach

```java
	
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestRouteController {
	
	@BeforeAll
    public static void beforeAll() {
        System.out.println("Before all test methods");
    }
 
    @BeforeEach
	public void beforeEach() {
        System.out.println("Before each individual test method");
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

## Parameter 

```java
    @ParameterizedTest 
    @MethodSource("someRoutes")
    @DisplayName("Good Routes")
    void CheckRoutes(Route route) {
        
        Assertions.assertTrue(route.getFlightNumber().equals("LH123"));
    }

    static Stream<Route> someRoutes() {
        return Stream.of(
                new Route("LH123", "MUC", "BER"),
                new Route("LH123", "MUC", "BER")
                
        );
    }
```

## Autowired 

```java
    @Test
    @DisplayName("Checking if repository is loaded with data by @Autowire")
    public void checkRouteRepositoryCreation(@Autowired RouteRepository routeRepository) {
        Assertions.assertTrue(routeRepository.count() > 0);
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

## Show Nested Tests
```java
    @Nested
	class ConditionalTests {

		@Test
		@EnabledOnOs(OS.WINDOWS)
		public void checkMacOs() {
			Assertions.assertTrue(true);
		}

		@Test
		@EnabledIf(expression = "false")
		public void checkConditional() {
			Assertions.assertTrue(false);
		}
	}
```

