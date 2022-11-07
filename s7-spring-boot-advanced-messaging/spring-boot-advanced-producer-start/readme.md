

## Aufgabe 

Die Fluggesellschaft zeichnet das Tracking der Flüge auf. 
Die Tracking-Informationen werden von dem Tracking-Anbieter FlightAware als AMQP-Nachricht im JSON-Format an eine TopicExcange mit dem Namen FlightAwareTracking gesendet. 
Das Tracking besteht aus der Routenidentifikation, der Flugnummer, der Uhrzeit und einem Status. Erstellen Sie die Anwendung, die Zyklisch Tracking Infromationen an einen TopicExchange sendet. 

1. Setzen Sie den RabbitMQ Server auf. 
1. Erstellen Sie eine TopicExchange Route
2. Senden Sie die AMQP-Nachricht als JSON-Dokument an die Queue


## RabbitMQ Server 
Bitte wechseln Sie in das Verzeichnis ``infrastructure``. Der Rabbit MQ Message Broker wird über den folgenden Befehl gestartet. 


```
	docker compose up -d
```



## AMQP Dependency prüfen  

```java
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-amqp</artifactId>
	</dependency>
```

## AMQP Route Configuration erstellen    

```java
@Configuration
public class AmqpConfiguration {
   
    public static final String SIMPLE_QUEUE_NAME 	 = "SimpleFlightAwareQueue";
    public static final String SIMPLE_EXCHANGE_NAME = "SimpleFlightAwareExchange";
    public static final String SIMPLE_EXCHANGE_KEY = "SimpleFlightAwareKey";

    @Bean
    public Queue queue() {
        return new Queue(AmqpConfiguration.SIMPLE_QUEUE_NAME, false, false,true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(AmqpConfiguration.SIMPLE_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AmqpConfiguration.SIMPLE_EXCHANGE_KEY);
    }


}
```

## AMQP Converter erstellen 

```java
@Configuration
public class AmqpConfiguration {
   
    
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
}
```

## AMQP Producer implementieren  

```java
@Component
public class AmqpProducer {
	
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Tracking tracking) {
        rabbitTemplate.convertAndSend(AmqpConfiguration.SIMPLE_EXCHANGE_NAME,AmqpConfiguration.SIMPLE_EXCHANGE_KEY , tracking);
    }
}
```


## AMQP Producer einbinden  

```java
@Component
public class AmqProducerCallerScheduling {

	
	public static Long counter = Long.valueOf(0);

	@Autowired
	private AmqpProducer amqpProducer;

	@Scheduled(initialDelay = 5000, fixedDelay = 5000)
	public void sendTracking() {
		Tracking tracking = new Tracking();
		tracking.setRouteId(counter++);
		tracking.setFlightNumber("LH7902");
		tracking.setStatus(FlightStatus.DELAYED);
	
		amqpProducer.sendMessage(tracking);
	}

}	
```

## AMQP Producer starten   

Starten Sie die Spring Boot Anwendung über die IDE.


## Shutdown 

Wenn Sie mit der Übung fertig sind, dann stoppen Sie bitte den RabbitMQ Server. 

```
	docker compose down 
```


