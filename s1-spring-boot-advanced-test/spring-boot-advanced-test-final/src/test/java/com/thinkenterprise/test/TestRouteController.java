package com.thinkenterprise.test;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.RouteRepository;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestRouteController {
	
	
	@AfterAll
	static void afterAll() {
		System.out.println("After all test methods");
	}

	@BeforeAll
	static void beforeAll() {
		System.out.println("Before all test methods");
	}

	@Test
	@DisplayName("Checking if context is loaded with beans")
	public void contextLoad() {
	}

	@ParameterizedTest 
    @MethodSource("someRoutes")
    @DisplayName("Good Routes")
    void CheckRoutes(Route route) {
        
        Assertions.assertTrue(route.getFlightNumber().equals("LH123"));
    }

    static Stream<Route> someRoutes() {
        return Stream.of(
                new Route("LH123", "MUC", "BER"),
                new Route("LH123", "MUC", "BER")
                
        );
    }
	
	
	
	@Test
	@DisplayName("Checking if repository is loaded with data by Autowire")
	public void checkRestTemplateWithAutowire(@Autowired TestRestTemplate restTemplate) {
		Iterable<Route> iterable = restTemplate.getForObject("/routes", Iterable.class);
		Assertions.assertTrue(iterable.iterator().hasNext());
	}

	@Test
	@DisplayName("Checking if repository is loaded with data by @Qualifier")
	public void checkRouteRepositoryWithQualifier(@Qualifier("routeRepository") RouteRepository routeRepository) {
		Assertions.assertTrue(routeRepository.count() > 0);
	}

	@Test
	@DisplayName("Checking property route count == 4")
	public void checkProperties(@Value("${thinkenterprise.count}") float count) {
		Assertions.assertTrue(count == 4);
	}

	@Test
	@Tag("doNothing")
	public void checkExcludeTest() {
		System.out.println("doNoting");
		Assertions.assertTrue(true);
	}

	@Nested
	class ConditionalTests {

		@Test
		@EnabledOnOs(OS.WINDOWS)
		public void checkMacOs() {
			Assertions.assertTrue(true);
		}

		@Test
		@EnabledIf(expression = "false")
		public void checkConditional() {
			Assertions.assertTrue(false);
		}
	}
	
	
}
