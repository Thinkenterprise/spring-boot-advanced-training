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

## Redis Embedded Server 
```java
	<dependency>
		<groupId>it.ozimov</groupId>
		<artifactId>embedded-redis</artifactId>
		<version>0.7.3</version>
	</dependency>
```

## Redis Properties in der application.properties setzen
```java
	spring.redis.server.port=6379
	spring.redis.server.host=localhost
```

## Redis Properties
```java
	@Configuration
	public class RedisProperties {

		private int port;
		private String host;

		public RedisProperties(@Value("${spring.redis.server.port}") int port,
				@Value("${spring.redis.server.host}") String host) {
			this.port = port;
			this.host = host;
		}
	
		public String getHost() {
			return host;
		}
	
		public int getPort() {
			return port;
		}
	}
```

## Redis Template und Serializer definieren 


```java
	@Configuration
	public class RedisClientConfiguration {
	
	@Bean
	public RedisTemplate<String, Route> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		
		RedisTemplate<String, Route> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
		return redisTemplate;
	}
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory(RedisProperties properties) {
		return new LettuceConnectionFactory(properties.getHost(), properties.getPort());
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
public class Application implements ApplicationRunner {

	@Autowired
	RedisTemplate<String, Route> redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(ApplicationArguments args) throws Exception {
		redisTemplate.opsForValue().set(String.valueOf(1L), new Route(1L, "LH7902", "MUC", "IAH"));
		redisTemplate.opsForValue().set(String.valueOf(2L), new Route(2L, "LH1602", "MUC", "IBZ"));
		redisTemplate.opsForValue().set(String.valueOf(3L), new Route(3L, "LH401", "FRA", "NYC"));
	}
}
```

## RedisServerConfiguration erstellen
```java
	@Configuration
	public class RedisServerConfiguration {
	
		private RedisServer server;
	
		public RedisServerConfiguration( RedisProperties properties) {
			server = new RedisServer(properties.getPort());
		}
	
		@PostConstruct
		public void postConstruct() {
			server.start();
		}
	
		@PreDestroy
		public void preDestroy() {
			server.stop();
		}
	}
```

## Test erstellen 
```java
	@ComponentScan(basePackageClasses = { Application.class })
	@DataRedisTest	
	public class RedisRouteRepositoryTest {

		@Autowired
		private RedisRouteRepository redisRouteRepository;
	
		@Test	
		public void testGetAll() {
			Assertions.assertEquals(3, redisRouteRepository.findAll().size());
		}
}

```