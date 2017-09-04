package repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import entities.CookingRecipe;


/**
 * A repository for CookingRecipe objects.
 * @author Renan Jesus
 */
public interface CookingRecipeRepository extends CrudRepository<CookingRecipe, Long> {

	/**
	 * Finds recipe by its ID.
	 * @param id ID of recipe to find.
	 * @return CookingRecipe object if found, null otherwise.
	 */
	CookingRecipe findById(Long id);

	/**
	 * Finds list of recipes by name.
	 * @param name Name of recipes to find.
	 * @return List of CookingRecipe objects if found, null otherwise.
	 */
	List<CookingRecipe> findByName(String name);

}
