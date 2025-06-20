package com.thinkenterprise;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.jpa.JdbcRouteRepository;
import com.thinkenterprise.repository.jpa.RouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ComponentScan(basePackageClasses = {RouteRepository.class})
@DataJpaTest
public class JdbcRouteRepositoryTest {

    @Autowired
    private JdbcRouteRepository jdbcRouteRepository;

    @Test
    public void testGetAll() {
        assertEquals(3, jdbcRouteRepository.findAll().size());
    }

    @Test
    public void testGetById() {
        var route = jdbcRouteRepository.find(150l);
        assertNotNull(route);
    }

    @Test
    public void testSave() {
        var route = new Route();
        route.setId(8000L);
        route.setDeparture("departureTest");
        route.setDestination("destinationTest");
        route.setFlightNumber("flightNumberTest");

        var save = jdbcRouteRepository.save(route);

        assertEquals(route, save);
    }
}
