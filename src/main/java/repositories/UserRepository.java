package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import entities.User;


/**
 * A repository for User objects.
 * @author Renan Jesus
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

	// Empty body - uses only inherited methods.

}
