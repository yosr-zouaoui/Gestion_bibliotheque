package com.example;

import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class GestionBibliothequeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBibliothequeApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

	
	@Bean
	CommandLineRunner commanLinerunnerJdbcUsers (JdbcUserDetailsManager jdbcUserDetailsmanager)
	{
		return args -> {
			if (!jdbcUserDetailsmanager.userExists("user")) {
			jdbcUserDetailsmanager.createUser(
					User.withUsername("user").password(this.passwordEncoder().encode("user")).roles("USER").build());
			}
			if (!jdbcUserDetailsmanager.userExists("admin")) {
			jdbcUserDetailsmanager.createUser(
					User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("USER","ADMIN").build());
			}
		};
	}
	

}
