package entities;

/**
 * Ingredient's amount unit of measure.
 */
enum MeasureUnit {
	UNIT, GRAM, KILOGRAM, TEASPOON, TABLESPOON, CUP, MILLILITER, LITER
}


/**
 * A recipe's ingredient, its amount and measure unit.
 * @author Renan Jesus
 */
public class RecipeIngredient {

	private String name;
	private int amount;
	private MeasureUnit unit;

	/**
	 * Constructs RecipeIngredient with default values.
	 */
	public RecipeIngredient() {
		this(null, 0, MeasureUnit.UNIT);
	}

	/**
	 * Constructs RecipeIngredient with given values.
	 * @param name Ingredient's name.
	 * @param amount Ingredient's amount in recipe.
	 * @param measure Amount's unit of measure.
	 */
	public RecipeIngredient(String name, int amount, MeasureUnit unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}

	/**
	 * @return RecipeIngredient's name.
	 */
	String getName() {
		return this.name;
	}

	/**
	 * Sets RecipeIngredient's name.
	 * @param name Name to set.
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * @return RecipeIngredient's amount.
	 */
	int getAmount() {
		return this.amount;
	}

	/**
	 * Sets RecipeIngredient's amount.
	 * @param amount Amount to set.
	 */
	void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return RecipeIngredient's unit of measure.
	 */
	MeasureUnit getMeasure() {
		return this.unit;
	}

	/**
	 * Sets RecipeIngredient's unit of measure.
	 * @param unit Ingredient amount's unit of measure.
	 */
	void setMeasure(MeasureUnit unit) {
		this.unit = unit;
	}

}
