package com.thinkenterprise.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.thinkenterprise.domain.route.Route;
import org.springframework.web.client.RestClient;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RouteControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;

    @Test
    public void routeQueryroutes() {

        var restClient = RestClient.builder().baseUrl("http://localhost:" + port).requestInterceptor(new AddHeaderInterceptor()).build();

        var routeResponse = restClient.get().uri("/routes").retrieve().toEntity(new ParameterizedTypeReference<Iterable<Route>>() {
        });

        Iterable<Route> iterable = routeResponse.getBody();

        assertEquals(HttpStatus.OK, routeResponse.getStatusCode());
        assertNotNull(routeResponse.getBody());
        assertNotNull(iterable.iterator().hasNext());

    }


    public class AddHeaderInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                throws IOException {
            request.getHeaders().add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJyZWFkIl0sImV4cCI6MjE0NDA4NjQ0MCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJfbmFtZSI6InRvbSIsImp0aSI6ImM4N2Q5NTNjLTZlZDAtNGRlMy1hZTJlLTMwZTcwOTYyNjExNyIsImNsaWVudF9pZCI6ImZvbyJ9.vOx3WIajVeaPelFuYttvSjvOSXw5POwzQiZPxQmH6eSQTVR_YCHHzd0vh2a00g3spZ0-S7fZfkiFuNF-QJogGS-GER-B8p4c6mMrazN0x-wytMVM6xZrQbner0Uqu_uuK1vQs-gm2-2BFpydQtq-ZYicss21RSJTLK7fuH5DzHQ");
            return execution.execute(request, body);
        }

    }

}