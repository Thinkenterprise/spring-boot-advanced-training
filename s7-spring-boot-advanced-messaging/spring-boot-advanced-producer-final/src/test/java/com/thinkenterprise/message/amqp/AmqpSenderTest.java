package com.thinkenterprise.message.amqp;


import com.thinkenterprise.domain.tracking.FlightStatus;
import com.thinkenterprise.domain.tracking.Tracking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@Testcontainers
@SpringBootTest
class AmqpSenderTest {

    @Container
    @ServiceConnection
    static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3-management");

    @Autowired
    AmqpSender sender;

    @Test
    void containerStartintAndRunning() {
        assertThat(rabbitMQContainer.isCreated()).isTrue();
        assertThat(rabbitMQContainer.isRunning()).isTrue();
    }

    @Test
    void sendThrowsNoException() {
        var tracking = new Tracking();
        tracking.setId(1L);
        tracking.setStatus(FlightStatus.BOARDING);
        tracking.setFlightNumber("LH123");
        tracking.setRouteId(4L);

        assertThatNoException().isThrownBy(()->sender.sendMessage(tracking));
    }
}