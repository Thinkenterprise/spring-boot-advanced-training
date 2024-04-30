package com.thinkenterprise.test;

import com.thinkenterprise.domain.route.Route;
import com.thinkenterprise.repository.RouteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.util.stream.Stream;

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
	@DisplayName("Checking if repository is loaded with data by @Autowire")
	public void checkRouteRepositoryCreation(@Autowired RouteRepository routeRepository) {
		Assertions.assertTrue(routeRepository.count() > 0);
	}

	@Test
	@DisplayName("Checking if repository is loaded with data by @Qualifier")
	public void checkRouteRepositoryWithQualifier(@Qualifier("routeRepository") RouteRepository routeRepository) {
		Assertions.assertTrue(routeRepository.count() > 0);
	}

	@Test
	@DisplayName("Checking property route count == 4")
	public void checkProperties(@Value("${route.count}") float count) {
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
