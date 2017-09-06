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
	 * Constructs RecipeIngredient with given values. Requires [amount > 0].
	 * @param name Ingredient's name.
	 * @param amount Ingredient's amount in recipe.
	 * @param measure Amount's unit of measure.
	 */
	public RecipeIngredient(String name, int amount, MeasureUnit unit) {
		this.name = name;
		this.unit = unit;

		// Saturate amount in one.
		if (amount > 0) {
			this.amount = amount;
		}
		else {
			this.amount = 1;
		}
	}

	/**
	 * @return RecipeIngredient's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets RecipeIngredient's name.
	 * @param name Name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return RecipeIngredient's amount.
	 */
	public int getAmount() {
		return this.amount;
	}

	/**
	 * Sets RecipeIngredient's amount. Saturates lowest values in one.
	 * @param amount Amount to set.
	 */
	public void setAmount(int amount) {
		if (amount > 0) {
			this.amount = amount;
		}
		else {
			this.amount = 1;
		}
	}

	/**
	 * @return RecipeIngredient's unit of measure.
	 */
	public MeasureUnit getMeasure() {
		return this.unit;
	}

	/**
	 * Sets RecipeIngredient's unit of measure.
	 * @param unit Ingredient amount's unit of measure.
	 */
	public void setMeasure(MeasureUnit unit) {
		this.unit = unit;
	}

}
