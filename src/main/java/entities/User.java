package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import exceptions.InvalidPasswordException;


/**
 * An user of RecipeShare web service.
 * @author Renan Jesus
 */
@Entity
@Table(name = "User")
public class User {

	@Id
	private String username;

	private String realName;
	private String password;
	@OneToOne
	private RecipeBook book;

	/**
	 * Constructs an user with default values (null).
	 */
	public User() {
		this.username = null;
		this.realName = null;
		this.password = null;
	}

	/**
	 * @return User's login name.
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets user's login name.
	 * @param username Username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return User's real name.
	 */
	public String getRealName() {
		return this.realName;
	}

	/**
	 * Sets user's real name.
	 * @param realName Name to set.
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return User's password.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets user's password.
	 * @param User's password.
	 * @throws InvalidPasswordException
	 */
	public void setPassword(String password) throws InvalidPasswordException {
		// Assert that password has at least 6 characters.
		if (password.length() < 6) {
			throw (new InvalidPasswordException());
		}

		// Since password is valid, set it.
		this.password = password;
	}

	/**
	 * @return User's RecipeBook.
	 */
	public RecipeBook getBook() {
		return this.book;
	}

}
