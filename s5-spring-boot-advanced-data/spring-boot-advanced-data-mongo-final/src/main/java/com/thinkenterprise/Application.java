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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.thinkenterprise.domain.route.Route;

@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(ApplicationArguments args) throws Exception {
		
		mongoTemplate.insert(new Route("LH7902","MUC","IAH"));
		mongoTemplate.insert(new Route("LH1602","MUC","IBZ"));
		mongoTemplate.insert(new Route("LH401","FRA","NYC"));

	}
	
}
