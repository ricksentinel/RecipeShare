package controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC configuration class.
 * @author Renan Jesus
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    
	/**
	 * Defines which View controllers are tied to which Views.
	 * @param registry Views and controllers' registry.
	 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/new-recipe").setViewName("new-recipe");
        registry.addViewController("/search").setViewName("search");
        registry.addViewController("/remove").setViewName("remove");
    }

}
