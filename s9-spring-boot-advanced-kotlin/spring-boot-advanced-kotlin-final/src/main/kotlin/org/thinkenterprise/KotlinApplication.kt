package org.thinkenterprise

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.thinkenterprise.repository.RouteRepository
import org.springframework.boot.SpringBootConfiguration

@EnableAutoConfiguration
@ComponentScan
@SpringBootConfiguration
class KotlinApplication 
		
	fun main(args: Array<String>) {
		runApplication<KotlinApplication>(*args)
	}
	
 

