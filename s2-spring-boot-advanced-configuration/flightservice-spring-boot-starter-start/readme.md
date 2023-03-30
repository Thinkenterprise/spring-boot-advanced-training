# Aufgabe 


Die Fluggesellschaft stellt einen Service bereit, der für die Routenverwaltung verantwortlich ist. Der Service soll um eine Funktion erweitert werden, die es ermöglicht die Gesamteinnahmen aller durchgeführten Flüge unter Berücksichtigung eine konfigurierbaren Steuerfaktors zu berechnen. Dazu wurde bereits eine Bibliothek Flightservice.jar implementiert. Die Bibliothek soll nun über einen Starter bereitgestellt und in den Service eingebunden werden.

1. Erstellen Sie einen Starter, der die Autoconfiguration und Properties einbindet und einer
Anwendung zur Verfügung stellt.


## Autoconfiguration hinzufügen 

```java
 <dependency>
       <groupId>com.thinkenterprise</groupId>
       <artifactId>s2-flightservice-spring-boot-autoconfigure</artifactId>
       <version>${flightservice.version}</version>
 </dependency>

```

### Starter installieren 
 

```java
mvn clean install 
```


