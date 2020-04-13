package com.thinkenterprise.springboot.jpa2pc;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	

	private static final Logger LOGGER = LoggerFactory.getLogger( Application.class );

	
	public static void main( String[] args ) throws SQLException {
		new SpringApplication( Application.class ).run( args );
		Server.createWebServer( "-web", "-webAllowOthers", "-webPort", "8082" ).start();
		LOGGER.info( "Call <http://localhost:8082> to take a look into the DB instances:" );
		LOGGER.info( "--> Domain-DB URL = jdbc:h2:mem:domain-db" );
		LOGGER.info( "--> Analytics-DB URL = jdbc:h2:mem:analytics-db" );
	}



}
