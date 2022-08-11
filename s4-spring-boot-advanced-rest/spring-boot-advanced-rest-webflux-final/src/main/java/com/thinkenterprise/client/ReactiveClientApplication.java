/*
 * Copyright (C) 2020 Thinkenterprise
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

package com.thinkenterprise.client;

import java.io.IOException;

import org.springframework.web.reactive.function.client.WebClient;

import com.thinkenterprise.domain.route.Route;

public class ReactiveClientApplication {
	
	  public static void main(String[] args) throws IOException {
		  
	    	WebClient client = WebClient.create("http://localhost:8080");
	    	
	    	client
	    	.get()
	    	.uri("/routes")
	    	.exchangeToFlux(response -> response.bodyToFlux(Route.class))
	    	.log()
	    	.blockLast();
	    	
	   }

}
