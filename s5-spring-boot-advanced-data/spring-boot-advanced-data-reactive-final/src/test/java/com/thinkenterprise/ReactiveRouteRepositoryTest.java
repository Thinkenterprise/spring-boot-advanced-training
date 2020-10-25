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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.ComponentScan;

import com.thinkenterprise.domain.route.ReactiveRouteRepository;

import reactor.test.StepVerifier;

@DataR2dbcTest
@ComponentScan(basePackageClasses = {Application.class})
public class ReactiveRouteRepositoryTest {

    @Autowired
    private ReactiveRouteRepository reactiveRouteRepository;
    
    
    @Test
    public void findByFlightNumber() {
        	
    	reactiveRouteRepository.findById(101L)
    						   .as(StepVerifier::create)
    	                       .assertNext(route -> Assertions.assertTrue(route.getId().equals(101L)))
    	                       .verifyComplete();

    }
}