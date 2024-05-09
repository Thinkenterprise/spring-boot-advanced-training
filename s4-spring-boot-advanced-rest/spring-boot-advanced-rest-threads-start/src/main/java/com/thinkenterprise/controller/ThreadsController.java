package com.thinkenterprise.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/threads")
public class ThreadsController {

	 private static final Logger log = LoggerFactory.getLogger(ThreadsController.class);
	    private final RestClient restClient;

	    public ThreadsController(RestClient.Builder restClientBuilder) {
	        restClient = restClientBuilder.baseUrl("http://httpbin.org/").build();
	    }

	    @GetMapping("/block/{seconds}")
	    public String delay(@PathVariable(name = "seconds") int seconds) {
	        ResponseEntity<Void> result = restClient.get()
	                .uri("/delay/" + seconds)
	                .retrieve()
	                .toBodilessEntity();

	        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());

	        return Thread.currentThread().toString();
	    }

}
