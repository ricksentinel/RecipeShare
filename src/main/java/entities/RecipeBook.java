package entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import datastructures.IdAndNamePair;
import repositories.CookingRecipeRepository;


/**
 * A book of cooking recipes. All recipes added to repository are shared between
 * different RecipeBook objects.
 * @author Renan Jesus
 */
@Entity
@Table(name = "RecipeBook")
public class RecipeBook {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	private static CookingRecipeRepository allRecipes;

	private List<IdAndNamePair> bookRecipes;
	private String name;
	private String author;

	/**
	 * Constructs an empty RecipeBook with default values.
	 */
	public RecipeBook() {
		this.name = "My Recipe Book";
		this.author = null;
	}

	/**
	 * Constructs a RecipeBook with given values.
	 * @param bookName RecipeBook's name.
	 * @param authorName RecipeBook author's name.
	 */
	public RecipeBook(String bookName, String authorName) {
		this.name = bookName;
		this.author = authorName;
	}

	/**
	 * @return RecipeBook's ID.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return RecipeBook's name.
	 */
	public String getBookName() {
		return this.name;
	}

	/**
	 * Sets RecipeBook's name.
	 * @param name Name to set.
	 */
	public void setBookName(String name) {
		this.name = name;
	}

	/**
	 * @return RecipeBook author's name.
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * Sets RecipeBook author's name.
	 * @param authorName Author's name to set.
	 */
	public void setAuthor(String authorName) {
		this.author = authorName;
	}

	/**
	 * @return Repository of shared recipes.
	 */
	public static CookingRecipeRepository getAllRecipes() {
		return RecipeBook.allRecipes;
	}

	/**
	 * Adds a new recipe to shared repository and this RecipeBook.
	 * @param recipe CookingRecipe to add.
	 */
	public void add(CookingRecipe recipe) {
		RecipeBook.getAllRecipes().save(recipe);
		add(recipe.getId(), recipe.getName());
	}

	/**
	 * Adds a recipe to this book.
	 * @param recipeId ID of recipe to add.
	 * @param recipeName Name of recipe to add.
	 */
	public void add(long recipeId, String recipeName) {
		IdAndNamePair recipePair = new IdAndNamePair(recipeId, recipeName);

		if (!this.bookRecipes.contains(recipePair)) {
			RecipeBook.getAllRecipes().findById(recipeId).addBook();
			this.bookRecipes.add(recipePair);
		}
	}

	/**
	 * Removes recipe from this book. Also removes from shared repository if this is
	 * the last book that contains the recipe.
	 * @param recipeId ID of recipe to remove.
	 */
	public void remove(long recipeId) {
		// Find recipe in shared repository.
		CookingRecipe recipe = RecipeBook.getAllRecipes().findById(recipeId);

		// Remove recipe from RecipeBook.
		this.bookRecipes.remove(new IdAndNamePair(recipeId, recipe.getName()));

		// If recipe isn't in any other book, remove it from shared repository.
		if (recipe.removeBook() == 0) {
			RecipeBook.getAllRecipes().delete(recipe);
		}

	}

	/**
	 * Finds CookingRecipe by its ID if it's included in this book.
	 * @param recipeId ID of recipe to find.
	 * @return CookingRecipe if found, null otherwise.
	 */
	public CookingRecipe findById(long recipeId) {
		// Find recipe by ID.
		CookingRecipe recipe = RecipeBook.getAllRecipes().findById(recipeId);

		// If this book contains it, return the recipe.
		if (this.bookRecipes.contains(new IdAndNamePair(recipeId, recipe.getName()))) {
			return recipe;
		}

		// Otherwise, return null.
		return null;
	}

	/**
	 * Finds all CookingRecipe objects named 'recipeName' in this book.
	 * @param recipeName Name of recipe to find.
	 * @return CookingRecipe if found, null otherwise.
	 */
	public List<CookingRecipe> findByName(String recipeName) {
		// Find recipe by name.
		List<CookingRecipe> recipesList = RecipeBook.getAllRecipes().findByName(recipeName);

		// Remove from the list recipes that aren't in this book as well.
		for (int i = 0; i < recipesList.size(); ++i) {
			if (!this.bookRecipes.contains(new IdAndNamePair(recipesList.get(i).getId(), recipeName))) {
				recipesList.remove(i);
			}
		}

		// Return list of recipes in this book named 'recipeName'.
		return recipesList;
	}

}
