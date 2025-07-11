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

package com.thinkenterprise.repository.jpa;

import com.thinkenterprise.domain.route.Route;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("jpa")
public class JpaRouteRepository implements RouteRepository {

	@Autowired
	private EntityManager entityManager;

	public List<Route> findAll() {
		TypedQuery<Route> query = entityManager.createQuery("select r from Route r",Route.class);
		return query.getResultList();
	}
	
	public Route find(Long id) {
		return entityManager.find(Route.class, id);
	}
	
	@Transactional
	public Route save(Route route) {
		 entityManager.persist(route);
		 return route;
	}
}
