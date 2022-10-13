## Precondition
Der Rabbit MQ Message Broker wird über den folgenden Befehl gestartet. 


```
	docker compose -d
```

## Aufgabe 

Die Fluggesellschaft zeichnet das Tracking der Flüge auf. Die Tracking-Informationen werden von dem Tracking-Anbieter FlightAware als AMQP-Nachricht im JSON-Format an eine TopicExcange mit dem Namen FlightAwareTracking gesendet. Das Tracking besteht aus der Routenidentifikation, der Flugnummer, der Uhrzeit und einem Status. Erstellen Sie die Anwendung, welche die Nachrichten von der Queue ``SimpleFlightAwareQueueSimpleFlightAwareQueue`` empfängt. 


1. Erstellen Sie eine TopicExchange 
2. Empfangen Sie die JSON AMPQ-Message und geben Sie die auf der Konsole aus 


## AMQP Dependency prüfen  

```java
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-amqp</artifactId>
	</dependency>
```

## AMQP Route Configuration restellen   

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

## AMQP Consumer implementieren  
 

```java
@Component
public class AmqpReceiver  {
	
	private static final Logger logger = LoggerFactory.getLogger(AmqpReceiver.class);
	
	   
    @RabbitListener(queues=AmqpConfiguration.SIMPLE_QUEUE_NAME)
	public void onMessage(Tracking tracking) {
	   	logger.info(tracking.toString());
	}
   
  
}
```

## AMQP Consumer starten   

Starten Sie die Spring Boot Anwendung über die IDE. Es müssten zyklisch Nachrichten auf die Konsole ausgegeben werden. 