# Aufgabe 

Die Fluggesellschaft stellt einen Service bereit, der für die Routenverwaltung verantwortlich ist. Der Service soll um eine Funktion erweitert werden, die es ermöglicht die Gesamteinnahmen aller durchgeführten Flüge unter Berücksichtigung eine konfigurierbaren Steuerfaktors zu berechnen. Dazu wurde bereits eine Bibliothek Flightservice.jar implementiert. Die Bibliothek soll nun über einen Starter bereitgestellt und in den Service eingebunden werden.

1. Erstellen Sie eine Autoconfiguration mit dem Properties tax für den konfigurierbaren Steuerfaktor.


## Bibliothek hinzufügen 

```java
	<dependency>
		<groupId>com.thinkenterprise</groupId>
		<artifactId>s2-flightservice</artifactId>
		<version>${flightservice.version}</version>
	</dependency>

```


## Properties erstellen 

```java
@ConfigurationProperties(prefix = "flightservice")
public class FlightServiceConfigurationProperties {

	
	private float taxRate;

	public float getTaxRate() {
		return this.taxRate;
	}
	
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	
}
    
```


## Autoconfiguration erstellen 

```java
@AutoConfiguration
@ConditionalOnClass(FlightService.class)
@EnableConfigurationProperties(FlightServiceConfigurationProperties.class)
public class FlightServiceAutoConfiguration {


	@Autowired
	FlightServiceConfigurationProperties fsProperties;
	
	
    @Bean
    @ConditionalOnMissingBean
    public FlightService flightService(){

        FlightService flightService = new FlightServiceImpl();
        flightService.setTaxRate(fsProperties.getTaxRate());
        return flightService;
    }
    
    
```
    
## Factory File erstellen 

META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports

```java
	com.thinkenterprise.flightservice.autoconfiguration.FlightServiceAutoConfiguration
```



## Prüfe Processor Bibliotheken 


```java

	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
	</dependency>
		
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-autoconfigure-processor</artifactId>
		<optional>true</optional>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-configuration-processor</artifactId>
		<optional>true</optional>
	</dependency>
    
```


## Bibliothek installieren 

```
mvn clean install 		
    
```
