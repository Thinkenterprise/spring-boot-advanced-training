package com.thinkenterprise.springboot.jpa2db;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


	public static void main( String[] args ) throws SQLException {
		 SpringApplication.run(Application.class, args);
		 Server.createWebServer( "-web", "-webAllowOthers", "-webPort", "8082" ).start();
		
	}


}
