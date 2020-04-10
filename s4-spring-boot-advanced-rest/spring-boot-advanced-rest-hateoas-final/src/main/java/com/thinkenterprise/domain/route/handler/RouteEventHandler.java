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
package com.thinkenterprise.domain.route.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.thinkenterprise.domain.route.Route;

@RepositoryEventHandler(Route.class)
public class RouteEventHandler {
	
	protected static Logger logger = LoggerFactory.getLogger(RouteEventHandler.class);
	
	
	@HandleBeforeDelete
	public void beforeDelete(Route route) {
		logger.info("Before Delete from route with id: " + route.getId().toString());
		
	}
	

}
