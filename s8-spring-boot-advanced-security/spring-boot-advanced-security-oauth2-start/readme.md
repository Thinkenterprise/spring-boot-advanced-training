## Aufgabe

Die Fluggesellschaft stellt einen Route-Service bereit, über den die Routen der Fluggesellschaft bearbeitet werden können. Für den Route-Service soll nun eine Authentifizierung und ein Autorisierung über OAuth2 und JWT-Token eingeführt werden.

1. Schützen Sie den Service über einen signierten JWT, indem Sie die Signierung mit dem bereitgestellten Public Key, prüfen.
2. Schützen Sie über Method Based Authorization die Abfrage aller Routen so, dass nur ein Token mit Scope „read“ Zugriff auf die Routen hat.
3. Schreiben Sie einen Integration Test um die eingeführte Authentifizierung und Autorisierung zu prüfen.
 


## OAuth2/JWT Security Starter hinzufügen 

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
	</dependency>  


```

## Public Key bereitstellen 

```
  security:
    oauth2:
      resourceserver:
        jwt: 
          public-key-location: classpath:META-INF/resources/public-key.txt
```

Der Bereich `security` befindet sich nach der Anpassung "unterhalb" der mit dem Schlüsselwort `spring` eingeleiteten Hierarchie!

## OAuth2/JWT Security Configuration für Authorization 

```
@Configuration
@EnableMethodSecurity
@Profile("nonReactive")
public class OAuth2JWTSecurityConfiguration  {
      
}

```

## JWT Authorization am Controller abfragen 

```
@RestController
@RequestMapping("/routes")
@Profile("nonReactive")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @RequestMapping
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<Iterable<Route>> findAll() {
        return ResponseEntity.ok(routeRepository.findAll());
    }

   
}
    

```

## Profile setzen  

```
 active:
    profiles: nonReactive
    

```

## Token Zugriff testen 

```
curl -X GET 'http://localhost:8080/routes' -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIl0sImV4cCI6MjE0NDA4NjQ0MCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJfbmFtZSI6InRvbSIsImp0aSI6ImM4N2Q5NTNjLTZlZDAtNGRlMy1hZTJlLTMwZTcwOTYyNjExNyIsImNsaWVudF9pZCI6ImZvbyJ9.vOx3WIajVeaPelFuYttvSjvOSXw5POwzQiZPxQmH6eSQTVR_YCHHzd0vh2a00g3spZ0-S7fZfkiFuNF-QJogGS-GER-B8p4c6mMrazN0x-wytMVM6xZrQbner0Uqu_uuK1vQs-gm2-2BFpydQtq-ZYicss21RSJTLK7fuH5DzHQ' -H 'Content-Type: application/json'
    
```


