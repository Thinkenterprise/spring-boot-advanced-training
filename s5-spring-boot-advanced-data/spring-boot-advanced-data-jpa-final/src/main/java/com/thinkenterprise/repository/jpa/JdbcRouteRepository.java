package com.thinkenterprise.repository.jpa;

import com.thinkenterprise.domain.route.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
@Profile("jdbc")
public class JdbcRouteRepository implements RouteRepository {

    @Autowired
    private JdbcClient jdbcClient;

    @Override
    public List<Route> findAll() {
        return jdbcClient.sql("select * from Route")
                .query(Route.class)
                .list();
    }

    @Override
    public Route find(Long id) {
        return jdbcClient.sql("SELECT * FROM Route WHERE id = ?")
                .param(id)
                .query(Route.class)
                .single();
    }

    @Override
    public Route save(Route route) {
        var generatedKeyHolder = new GeneratedKeyHolder();
        var updatedRows = jdbcClient.sql("insert into route(id, flightNumber, departure, destination) values(?,?,?,?)")
                .param(1, route.getId())
                .param(2, route.getFlightNumber())
                .param(3, route.getDeparture())
                .param(4, route.getDestination())
                .update(generatedKeyHolder);

        Assert.state(updatedRows == 1, "Failed to insert new route.");
        return route;
    }
}
