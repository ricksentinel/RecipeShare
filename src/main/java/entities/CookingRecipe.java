package entities;

import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * A cooking recipe with its name, ingredients, instructions and portions
 * served.
 * @author Renan Jesus
 */
@Entity
@Table(name = "CookingRecipe")
public class CookingRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	private String name;
	private List<RecipeIngredient> allIngredients;
	private List<String> allInstructions;
	private int numOfPortions;
	private long numOfBooks;

	/**
	 * Constructs an empty CookingRecipe object with default values.
	 */
	public CookingRecipe() {
		this.name = "My Recipe";
		this.numOfPortions = 0;
		this.numOfBooks = 0;
	}

	/**
	 * @return CookingRecipe's ID.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return CookingRecipe's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets CookingRecipe's name.
	 * @param name Name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return CookingRecipe's list of ingredients.
	 */
	public List<RecipeIngredient> getIngredients() {
		return this.allIngredients;
	}

	/**
	 * @return CookingRecipe's list of instructions.
	 */
	public List<String> getInstructions() {
		return this.allInstructions;
	}

	/**
	 * @return Number of portions served by this CookingRecipe.
	 */
	public int getNumOfPortions() {
		return this.numOfPortions;
	}

	/**
	 * Sets number of portions served by this CookingRecipe.
	 * @param numOfPortions Number of portions to set.
	 */
	public void setNumOfPortions(int numOfPortions) {
		this.numOfPortions = numOfPortions;
	}

	/**
	 * @return Number of recipe books that have this recipe.
	 */
	public long getNumOfBooks() {
		return this.numOfBooks;
	}

	/**
	 * Adds all ingredients to CookingRecipe.
	 * @param allIngredients CookingRecipe's ingredients.
	 */
	public void addAllIngredients(Collection<RecipeIngredient> allIngredients) {
		for (RecipeIngredient ingredient : allIngredients) {
			addIngredient(ingredient);
		}
	}

	/**
	 * Add an ingredient to CookingRecipe.
	 * @param ingredient RecipeIngredient to add.
	 * @see MeasureUnit enum.
	 */
	private void addIngredient(RecipeIngredient ingredient) {
		boolean hasFound = false;

		// Search for ingredient's name and amount in the list.
		for (RecipeIngredient ingredientInList : getIngredients()) {
			// If found, add given amount to it.
			if (ingredientInList.getName().equals(ingredient.getName())
					&& (ingredientInList.getAmount() == ingredient.getAmount())) {
				ingredientInList.setAmount(ingredientInList.getAmount() + ingredient.getAmount());
				hasFound = true;
			}
		}

		// If it hasn't been found, add it as new.
		if (!hasFound) {
			getIngredients().add(ingredient);
		}
	}

	/**
	 * Add a instruction line to CookingRecipe.
	 * @param instructionLine Line of instruction to cook recipe.
	 */
	public void addInstruction(String instructionLine) {
		String instruction = instructionLine.substring(0, 1);
		instruction = instruction.toUpperCase() + instructionLine.substring(1);

		getInstructions().add(instruction);
	}

	/**
	 * Increment amount of books referring this recipe.
	 * @return Amount of books with this recipe.
	 */
	public long addBook() {
		this.numOfBooks++;

		// Return number of books referring it.
		return this.numOfBooks;
	}

	/**
	 * Decrement amount of books referring this recipe, saturating at zero.
	 * @return Amount of books with this recipe.
	 */
	public long removeBook() {
		if (getNumOfBooks() > 0) {
			this.numOfBooks--;
		}

		// Return number of recipe books referring it.
		return this.numOfBooks;
	}

}
