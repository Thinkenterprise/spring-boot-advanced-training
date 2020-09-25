/*
 * Copyright (C) 2020 Thinkenterprise
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

package com.thinkenterprise.route.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinkenterprise.route.Route;
import com.thinkenterprise.route.RouteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("routes")
@Tag(name = "Route Controller")
@SecurityScheme(name = "OAuth2Security",type = SecuritySchemeType.OAUTH2,in = SecuritySchemeIn.HEADER,
				flows = @OAuthFlows(
				implicit = @OAuthFlow(authorizationUrl = "http://url.com/auth",
                scopes = @OAuthScope(name = "read:route", description = "Read routes"))))
public class RouteController {

	@Autowired
	RouteRepository routeRepository;

	@Operation(summary = "Get one Route", responses = {
			@ApiResponse(description = "The Route", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Route.class))),
			@ApiResponse(responseCode = "400", description = "User not found") })
	@SecurityRequirement(name = "Read routes", scopes = "read:route")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Route> get(
			@Parameter(description = "route itentifier", required = true) @PathVariable(value = "id") Long id) {
		return new ResponseEntity<Route>(routeRepository.findById(id).get(),HttpStatus.OK);

	}

} 
