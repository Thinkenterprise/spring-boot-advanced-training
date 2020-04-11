# Aufgabe 

## Kotlin vorstellen

* Dokumentation von Kotlin vorstellen 
* Playground vorstellen 


## Build Management vorstellen 

* Dokumentation von Kotlin vorstellen 
* Playground vorstellen 


## Kotlin Applikation erstellen  

```java
package org.thinkenterprise

	
fun main(args: Array<String>) {
	 System.out.print("Hello World");
}
	 
```

## Extentions erstellen

* Spring Koline Extentions
* Spring Framework Extentions 
* Spring Boot Extentions 


## Spring Boot Applikation erstellen 


```java

import org.springframework.boot.runApplication

@EnableAutoConfiguration
@ComponentScan
class KotlinApplication 
		
	fun main(args: Array<String>) {
		runApplication<KotlinApplication>(*args)
	}

```

## Persistence vorstellen 

## Controller implementieren 


```java

@RestController
class RouteController (private val repository: RouteRepository){
	
	@GetMapping("routes")
	fun findAll() = repository.findAll();
	
}
 
```





