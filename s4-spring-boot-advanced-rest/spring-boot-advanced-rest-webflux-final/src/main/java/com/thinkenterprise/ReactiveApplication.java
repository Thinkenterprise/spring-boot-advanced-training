/*
 * Copyright (C) 2019 Thinkenterprise
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * @author Michael Schaefer
 */

package com.thinkenterprise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.service.client.RouteService;

@SpringBootApplication
public class ReactiveApplication implements ApplicationRunner {
	
	public final static Logger logger = LoggerFactory.getLogger(ReactiveApplication.class);
	
	@Autowired RouteService routeService;
	@Autowired WebClient webClient;
	
    public static void main(String[] args) {
    	SpringApplication.run(ReactiveApplication.class, args);
       
   }

	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		
		try {
			webClient.get()
		     		 .uri("/routes")
		     		 .retrieve()
		     		 .bodyToFlux(Route.class)
		     		 .log()
		     		 .blockLast();
			
		} catch (WebClientResponseException e) {
			ProblemDetail problemDetail= e.getResponseBodyAs(ProblemDetail.class);
			logger.info(problemDetail.getTitle());
		}
		
		
		routeService.routes().log().blockLast();
		
	}
}
