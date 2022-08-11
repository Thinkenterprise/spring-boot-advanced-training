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

package com.thinkenterprise.flightservice.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.thinkenterprise.flightservice.service.FlightService;
import com.thinkenterprise.flightservice.service.FlightServiceImpl;

@AutoConfiguration
@ConditionalOnClass(FlightService.class)
@EnableConfigurationProperties(FlightServiceConfigurationProperties.class)
public class FlightServiceAutoConfiguration {


	@Autowired
	FlightServiceConfigurationProperties fsProperties;
	 
	
    @Bean
    @ConditionalOnMissingBean
    public FlightService flightService(){

        FlightService flightService = new FlightServiceImpl();
        flightService.setTaxRate(fsProperties.getTaxRate());
        return flightService;
    }
    
}
