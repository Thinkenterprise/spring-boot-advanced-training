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
package com.thinkenterprise.actuator.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import com.thinkenterprise.service.RouteServiceMetricAPI;

@Component
@Endpoint(id="routeService")
public class RouteServiceEndpoint {

	@Autowired
	private RouteServiceMetricAPI routeService;
	

	@ReadOperation
	public Boolean getServiceStatus() {
		return routeService.getServiceStatus();
	}
	
	// Its only working if the compiler store parameter infos see here 
	// https://github.com/spring-projects/spring-boot/issues/13261
	@ReadOperation
	public Boolean getServiceStatus(@Selector Boolean parameter) {
		return parameter;
	}
	
	// I Did`nt found information about Query Parameter It works with @RequestParam but that comes from the mvc module :-(
	
	
	
	
	
}
