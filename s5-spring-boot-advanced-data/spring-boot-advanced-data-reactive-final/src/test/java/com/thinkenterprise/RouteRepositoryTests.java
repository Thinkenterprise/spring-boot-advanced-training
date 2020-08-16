package com.thinkenterprise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.core.DatabaseClient;

import com.thinkenterprise.domain.route.ReactiveRouteRepository;

@DataR2dbcTest
@ComponentScan(basePackageClasses = {Application.class})
public class RouteRepositoryTests {

    @Autowired
    private ReactiveRouteRepository reactiveRouteRepository;
    
    @Autowired
    private DatabaseClient databaseClient;
    
    @Test
    public void findByFlightNumber() {
    
    	/*
    	Route route = new Route("LH7902","MUC","IAH");
        databaseClient.insert().into(Route.class).using(route).then().as(StepVerifier::create).verifyComplete();
    	
        Flux<Route> flightNumber = reactiveRouteRepository.findByFlightNumber("LH7902");
           
        flightNumber.as(StepVerifier::create)
        .assertNext(actual -> {
            assertThat(actual.getFlightNumber()).isEqualTo("LH7902");
     
        })
        .verifyComplete();
		*/

    }
}