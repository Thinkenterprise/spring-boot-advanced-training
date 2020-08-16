## Aufgabe

Die Fluggesellschaft möchte die Implementierung des Route-Service auf einen direkten Zugriff auf die Daten über den EntityManager umstellen. Implementieren Sie das RouteRepository Interface für JPA. Sofern Sie das Unternehmen davon überzeugen können ein anderes Datenbankmodell zu verwenden können Sie auch auf einen Dokument- oder Key/Value Store umstellen.

1. Implementieren Sie das RouteRepository für JPA, Mongo oder Redis.
2. Führe Sie einen Test ein.

## Mongo Starter hinzufügen 

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-mongodb</artifactId>
	</dependency>

```

## Mongo Transaction Manager 

```java

@Configuration
public class MongoConfiguration {
	
	@Bean
	public MongoTransactionManager transactionManager(MongoDbFactory mongoDbFactory) {
		return new MongoTransactionManager(mongoDbFactory);
		
	}

}
```

## Mongo Database initialisieren 


```java


@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	MongoTransactionManager mongoTransactionManager;
	
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(ApplicationArguments args) throws Exception {
		
		mongoTemplate.insert(new Route("LH7902","MUC","IAH"));
		mongoTemplate.insert(new Route("LH1602","MUC","IBZ"));
		mongoTemplate.insert(new Route("LH401","FRA","NYC"));

	}
	
}
```


## Repository Implementieren 

```java
@Repository
@Transactional
public class MongoRouteRepository implements RouteRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Route> findAll() {
		return mongoTemplate.findAll(Route.class);
	}
	
	public Route find(String id) {
		return mongoTemplate.findById(id, Route.class);
	}
	
	public Route save(Route route) {
		return mongoTemplate.save(route);
	}
	
}

```




## Embedded Database hinzufügen  

```
	<dependency>
			<groupId>de.flapdoodle.embed</groupId>
			<artifactId>de.flapdoodle.embed.mongo</artifactId>
	</dependency>

```





## Test schreiben 



```java
@ComponentScan(basePackageClasses = {Application.class})
@DataMongoTest
public class MongoRouteRepositoryTest {

	@Autowired
	private MongoRouteRepository mongoRouteRepository;
	
	@Test
	public void testGetAll() {
		Assert.assertTrue(mongoRouteRepository.findAll().size()==3);
	}
	 
}

```
