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

package com.thinkenterprise.domain.route;

import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteDatabase {

		/*
	 	@Bean
	    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

	        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
	        initializer.setConnectionFactory(connectionFactory);

	        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
	        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
	        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
	        initializer.setDatabasePopulator(populator);

	        return initializer;
	    }
	    */
}
