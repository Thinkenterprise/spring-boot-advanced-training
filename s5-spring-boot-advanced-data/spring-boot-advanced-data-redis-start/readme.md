## Aufgabe

Die Fluggesellschaft möchte die Implementierung des Route-Service auf einen direkten Zugriff auf die Daten über den EntityManager umstellen. Implementieren Sie das RouteRepository Interface für JPA. Sofern Sie das Unternehmen davon überzeugen können ein anderes Datenbankmodell zu verwenden können Sie auch auf einen Dokument- oder Key/Value Store umstellen.

1. Implementieren Sie das RouteRepository für JPA, Mongo oder Redis.
2. Führe Sie einen Test ein.


## Redis Starter einbinden  

```java
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-redis</artifactId>
	</dependency>
		
		
```


## Redis In Memory Driver 
```java
	<dependency>
		<groupId>com.github.kstyrc</groupId>
		<artifactId>embedded-redis</artifactId>
		<version>0.6</version>
	</dependency>
```

## Redis Template und Serializer definieren 


```java
	@Configuration
	public class RedisConfiguration {
	
	@Bean
	public RedisTemplate<String, Route> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		
		RedisTemplate<String, Route> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
		return redisTemplate;
		
	}
	
	
	@Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Route> jackson2JsonRedisSerializer() {
    	Jackson2JsonRedisSerializer<Route> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Route.class);
        return jackson2JsonRedisSerializer;
    }
   
}

```

## Erstellen Sie ein Redis Repository

```java
@Repository
public class RedisRouteRepository implements RouteRepository {
	
	@Autowired
	RedisTemplate<String, Route> redisTemplate;
	
	public List<Route> findAll() {
		List<Route> routeList = new ArrayList<>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
        	
        	String value = it.next();
        	Long longValue = Integer.valueOf(value).longValue();
        	Route route = find(longValue);
        	routeList.add(route);
        }

        return routeList;
	}
	
	
	
	
	public Route find(Long id) {
		return redisTemplate.opsForValue().get(String.valueOf(id));
	}
	
	public Route save(Route route) {
		redisTemplate.opsForValue().set(String.valueOf(route.getId()), route);
		return route;
	}

	
}
```

## Datenbak initialisieren 


```java
@SpringBootApplication
public class Application implements ApplicationRunner, ApplicationListener<ApplicationEvent> {

	@Autowired
	RedisTemplate<String, Route> redisTemplate;
	
	static RedisServer redisServer;
	
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       
    }

	public void run(ApplicationArguments args) throws Exception {
		
		redisServer = new RedisServer(6379); 
		redisServer.start();
			
		redisTemplate.opsForValue().set(String.valueOf(1L), new Route(1L,"LH7902","MUC","IAH"));
		redisTemplate.opsForValue().set(String.valueOf(2L), new Route(2L,"LH1602","MUC","IBZ"));
		redisTemplate.opsForValue().set(String.valueOf(3L), new Route(3L,"LH401","FRA","NYC"));
		
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		 
		if(event instanceof  ContextStoppedEvent)
			redisServer.stop();
		
	}
	
}

```

## Test erstellen 

```java
	@ComponentScan(basePackageClasses = {Application.class})
	@DataRedisTest	
	public class RedisRouteRepositoryTest {

		@Autowired
		private RedisRouteRepository redisRouteRepository;
	
		@Test	
		public void testGetAll() {
			Assert.assertTrue(redisRouteRepository.findAll().size()==3);
		}
	 
}

```



