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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.thinkenterprise.service.RouteServiceMetricAPI;

@SpringBootApplication
@EnableScheduling
public class Application {
	
	private static final Log logger = LogFactory.getLog(Application.class);

	@Autowired
    private RouteServiceMetricAPI routeService;
	
	public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void changeStatus() {
    	logger.info("home() has been called");
        routeService.businessFunctionMeter();
    }
        
}
