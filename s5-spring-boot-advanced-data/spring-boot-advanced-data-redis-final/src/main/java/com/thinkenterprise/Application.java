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
import org.springframework.data.redis.core.RedisTemplate;

import com.thinkenterprise.domain.route.Route;

@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	RedisTemplate<String, Route> redisTemplate;

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(final ApplicationArguments args) throws Exception {
		redisTemplate.opsForValue().set(String.valueOf(1L), new Route(1L, "LH7902", "MUC", "IAH"));
		redisTemplate.opsForValue().set(String.valueOf(2L), new Route(2L, "LH1602", "MUC", "IBZ"));
		redisTemplate.opsForValue().set(String.valueOf(3L), new Route(3L, "LH401", "FRA", "NYC"));
	}
}
