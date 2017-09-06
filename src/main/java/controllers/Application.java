package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Start RecipeShare web service.
 * @author Renan Jesus
 */
@SpringBootApplication
@ComponentScan({"controllers"})
@EntityScan("entities")
@EnableJpaRepositories("repositories")
public class Application {

	/**
	 * Runs SpringBoot application.
	 * @param args Default arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
