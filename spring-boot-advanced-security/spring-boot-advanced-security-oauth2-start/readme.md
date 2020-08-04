## OAuth2/JWT Security Starter hinzufügen 

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
	</dependency>  


```



## OAuth2/JWT Security Configuration für Authorization 

```
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled=true)
    public class OAuth2JWTSecurityConfiguration extends GlobalMethodSecurityConfiguration  {  
    }

```

## JWT Authorization am Controller abfragen 

```
  @RequestMapping
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<Iterable<Route>> findAll() {
        return ResponseEntity.ok(routeRepository.findAll());
    }
    

```





## Token Zugriff testen 

```
curl -X GET 'http://localhost:8080/routes' -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIl0sImV4cCI6MjE0NDA4NjQ0MCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJfbmFtZSI6InRvbSIsImp0aSI6ImM4N2Q5NTNjLTZlZDAtNGRlMy1hZTJlLTMwZTcwOTYyNjExNyIsImNsaWVudF9pZCI6ImZvbyJ9.vOx3WIajVeaPelFuYttvSjvOSXw5POwzQiZPxQmH6eSQTVR_YCHHzd0vh2a00g3spZ0-S7fZfkiFuNF-QJogGS-GER-B8p4c6mMrazN0x-wytMVM6xZrQbner0Uqu_uuK1vQs-gm2-2BFpydQtq-ZYicss21RSJTLK7fuH5DzHQ' -H 'Content-Type: application/json'
    
```
