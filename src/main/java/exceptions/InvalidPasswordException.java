package exceptions;

/**
 * Thrown when a password contains invalid characters according to application's
 * rules.
 * @author Renan Jesus
 */
public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = -5871865943911575595L;

	/**
	 * Constructs InvalidPasswordException with error message.
	 */
	public InvalidPasswordException() {
		super("Invalid password! Requires at least 6 characters.");
	}

}
