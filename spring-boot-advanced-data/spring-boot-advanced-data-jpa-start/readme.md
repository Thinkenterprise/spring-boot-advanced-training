

## Repository Implementieren 

```java
@Repository
public class JpaRouteRepository implements RouteRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	public List<Route> findAll() {
		
		TypedQuery<Route> query = entityManager.createQuery("select r from Route r",Route.class);
		return query.getResultList();
	}
	
	public Route find(Long id) {
		return entityManager.find(Route.class, id);
	}
	
	public Route save(Route route) {
		 entityManager.persist(route);
		 return route;
	}

	
}

```




## Embedded Database hinzufügen  

```
	<dependency>
		<groupId>com.h2database</groupId>
		<artifactId>h2</artifactId>
	</dependency>

```

## Test schreiben 




```java
@ComponentScan(basePackageClasses = {RouteRepository.class})
@DataJpaTest
public class JpaRouteRepositoryTest {

	@Autowired
	private JpaRouteRepository jdbcRouteRepository;
	
	@Test
	public void testGetAll() {
		Assert.assertTrue(jdbcRouteRepository.findAll().size()==3);
	}
	 
}

```
