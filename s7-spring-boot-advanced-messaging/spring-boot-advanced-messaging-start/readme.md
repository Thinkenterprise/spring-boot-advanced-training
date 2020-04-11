## Aufgabe 

Die Fluggesellschaft zeichnet das Tracking der Flüge auf. Die Tracking-Informationen werden von dem Tracking-Anbieter FlightAware als AMQP-Nachricht im JSON-Format an eine Message Topic mit dem Namen FlightAwareTracking gesendet. Das Tracking besteht aus der Routenidentifikation, der Flugnummer, der Uhrzeit und einem Status. Die AMQP-Nachricht soll weiter an eine ... gesendet werden.

1. Erstellen Sie eine TopicExchange Route
2. Senden Sie die AMQP-Nachricht als JSON-Dokument an die Queue
3. Empfangen Sie die AMQP-Nachricht und konvertieren Sie die in ein Tracking-Objekt
4. Geben Sie den Inhalt des Tracking Objektes auf der Konsole aus.


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

## AMQP Sender implementieren  

```java
@Component
public class AmqpSender {
	
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Tracking tracking) {
        rabbitTemplate.convertAndSend(AmqpConfiguration.SIMPLE_EXCHANGE_NAME,AmqpConfiguration.SIMPLE_EXCHANGE_KEY , tracking);
    }
}
```

## AMQP Receiver implementieren  

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


## AMQP Sender einbinden  

```java

@Component
public class AmqSenderCallerScheduling implements BeanFactoryAware {

	public static Long counter = new Long(0);

	private static BeanFactory context;

	@Scheduled(initialDelay = 5000, fixedDelay = 5000)
	public void sendTracking() {
	...
		AmqpSender sender = context.getBean(AmqpSender.class);
		sender.sendMessage(tracking);
	}

}

	
```


## AMQP Server starten   

```java
	
```