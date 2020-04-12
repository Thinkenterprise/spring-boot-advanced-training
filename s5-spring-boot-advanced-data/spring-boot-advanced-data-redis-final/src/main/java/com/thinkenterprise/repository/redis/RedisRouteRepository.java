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

package com.thinkenterprise.repository.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.thinkenterprise.domain.route.Route;

@Repository
public class RedisRouteRepository implements RouteRepository {
	
	@Autowired
	RedisTemplate<String, Route> redisTemplate;
	
	public List<Route> findAll() {
		List<Route> routeList = new ArrayList<>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
        	
        	String value = it.next();
        	Long longValue = Integer.valueOf(value).longValue();
        	Route route = find(longValue);
        	routeList.add(route);
        }

        return routeList;
	}
	
	
	
	
	public Route find(Long id) {
		return redisTemplate.opsForValue().get(String.valueOf(id));
	}
	
	public Route save(Route route) {
		redisTemplate.opsForValue().set(String.valueOf(route.getId()), route);
		return route;
	}

	
}
