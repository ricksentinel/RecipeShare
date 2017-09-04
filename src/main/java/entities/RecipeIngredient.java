package entities;

/**
 * Ingredient's amount unit of measure.
 */
enum MeasureUnit {
	UNIT, GRAM, TEASPOON, TABLESPOON, CUP
}


/**
 * A recipe's ingredient, its amount and measure unit.
 * @author Renan Jesus
 */
public class RecipeIngredient {

	String name;
	int amount;
	MeasureUnit measure;
	
	/**
	 * Constructs RecipeIngredient with default values.
	 */
	RecipeIngredient() {
		name = null;
		measure = MeasureUnit.UNIT;
		amount = 0;
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
	 * @param amount
	 */
	void setAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * @return RecipeIngredient's unit of measure.
	 */
	MeasureUnit getMeasure() {
		return this.measure;
	}
	
	/**
	 * Sets RecipeIngredient's unit of measure.
	 */
	void setMeasure(MeasureUnit measure) {
		this.measure = measure;
	}
	
}
