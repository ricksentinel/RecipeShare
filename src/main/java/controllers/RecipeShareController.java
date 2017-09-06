package controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import entities.CookingRecipe;
import entities.RecipeBook;
import entities.User;
import exceptions.ExistingUsernameException;
import exceptions.InvalidPasswordException;


/**
 * Spring MVC controller for RecipeShare web service.
 * @author Renan Jesus
 */
@Controller
public class RecipeShareController {

	private static AuthenticationManager loginManager;

	/**
	 * Constructs controller initializing UserRepository.
	 * @param repository Users accounts' repository.
	 */
	@Autowired
	public RecipeShareController(LoginManager manager) {
		RecipeShareController.loginManager = manager;
	}

	/**
	 * Goes to home page: GET mapping.
	 * @return View "home" (home page).
	 */
	@GetMapping("/")
	public String goToHomePage() {
		return "home";
	}

	/**
	 * Displays form to register new user: GET mapping.
	 * @return View "register".
	 */
	@GetMapping("/register")
	public String registerNewUser() {
		return "register";
	}

	/**
	 * Registers new user as long as username is available: POST mapping.
	 * @param username User's login name.
	 * @param password User's password.
	 * @return View "login".
	 */
	@PostMapping("/register")
	public String registerPost(	@RequestParam("username") String username,
								@RequestParam("realName") String realName,
								@RequestParam("password") String password) {
		try {
			// Register new user as long as its username is available.
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setRealName(realName);
			newUser.setPassword(password);

			((LoginManager) RecipeShareController.loginManager)
																.registerUser(newUser);
		} catch (ExistingUsernameException e) {
			return "redirect:/register?unavailableUsername";
		} catch (InvalidPasswordException e) {
			return "redirect:/register?invalidPassword";
		}

		return "redirect:/login";
	}

	/**
	 * Displays form to authenticate user: GET mapping.
	 * @return View "login".
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Allows user to access restricted pages if credentials are correct: POST
	 * mapping.
	 * @param username User's login name.
	 * @param password User's password.
	 * @return View "home".
	 */
	@PostMapping("/login")
	public String login(@RequestParam(name = "username") String username,
						@RequestParam(name = "password") String password,
						Model model) {
		try {
			// Attempt to authenticate user using its token.
			SecurityContextHolder
				.getContext()
				.setAuthentication(
				    RecipeShareController
				        .loginManager
						.authenticate(
						    new UsernamePasswordAuthenticationToken(username,password)
								  ));
		} catch (BadCredentialsException e) {
			return "redirect:/login?failed";
		}

		return "home";
	}

	/**
	 * Displays user's RecipeBook.
	 * @param model Spring MVC's model.
	 * @return View "my-recipe-book".
	 */
	@GetMapping("/my-recipe-book")
	public String showRecipeBook(Model model) {
		RecipeBook book = LoginManager
							.getUserRepository()
							.findOne(
								SecurityContextHolder
									.getContext()
									.getAuthentication()
									.getName()
									)
							.getBook();

		model.addAttribute("recipeBook", book);
		return "my-recipe-book";
	}

	/**
	 * Adds cooking recipe to current user's RecipeBook and shared repository: GET
	 * mapping.
	 * @return View "new-recipe".
	 */
	@GetMapping("/new-recipe")
	public String addRecipe() {
		return "new-recipe";
	}

	/**
	 * Adds cooking recipe to current user's RecipeBook and shared repository: POST
	 * mapping.
	 * @param recipe CookingRecipe to add.
	 * @return View "my-recipe-book".
	 */
	@PostMapping("new-recipe")
	public String addRecipe(@RequestParam(name = "recipe") CookingRecipe recipe) {
		LoginManager
			.getUserRepository()
			.findOne(
				SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getName()
					)
				.getBook()
					.add(recipe);

		return "redirect:/my-recipe-book";
	}

	/**
	 * Adds cooking recipe to user's RecipeBook: POST view.
	 * @param recipeId ID of recipe to add.
	 * @param recipeName Name of recipe to add.
	 * @return View "my-recipe-book".
	 */
	@PostMapping("/add-recipe")
	public String addRecipe(@RequestParam(name = "recipeId") long recipeId,
							@RequestParam(name = "recipeName") String recipeName) {
		LoginManager
			.getUserRepository()
			.findOne(
				SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getName()
					)
			.getBook()
				.add(recipeId, recipeName);

		return "redirect:/my-recipe-book";
	}

	/**
	 * Finds cooking recipe by name: GET view.
	 * @param name Name of recipe to find.
	 * @param model Spring MVC's model.
	 * @return View "search".
	 */
	@GetMapping("/search")
	public String findRecipe(@RequestParam(name = "name") String name, Model model) {
		List<CookingRecipe> recipesList = RecipeBook.getAllRecipes().findByName(name);

		model.addAttribute("recipesList", recipesList);
		return "search";
	}

	/**
	 * Removes recipe from user's RecipeBook: POST view.
	 * @param recipeId ID of recipe to remove.
	 * @return View "remove".
	 */
	@PostMapping("/remove")
	public String removeFromBook(@RequestParam(name = "recipeId") long recipeId) {
		LoginManager
			.getUserRepository()
			.findOne(
				SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getName()
					)
			.getBook()
				.remove(recipeId);

		return "redirect:/my-recipe-book";
	}

	/**
	 * Displays a CookingRecipe.
	 * @param recipe CookingRecipe to show.
	 * @param model Spring MVC's model.
	 * @return View "recipe".
	 */
	@GetMapping("/recipe")
	public String showRecipe(@ModelAttribute CookingRecipe recipe, Model model) {
		model.addAttribute(recipe);
		return "recipe";
	}

}
