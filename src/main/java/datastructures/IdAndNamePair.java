package datastructures;

/**
 * A pair of an object's ID and name.
 * @author Renan Jesus
 */

public class IdAndNamePair implements Pair<Long, String> {

	private Long id;
	private String name;

	/**
	 * Constructs a pair with default values.
	 */
	public IdAndNamePair() {
		this(((long) -1), null);
	}

	/**
	 * Constructs a pair with given values.
	 * @param id Pair's ID.
	 * @param name Pair's name.
	 */
	public IdAndNamePair(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return Pair's ID.
	 */
	@Override
	public Long getFirst() {
		return this.id;
	}

	/**
	 * Sets pair's ID.
	 * @param id ID to set.
	 */
	public void setFirst(Long id) {
		this.id = id;
	}

	/**
	 * @return Pair's name.
	 */
	@Override
	public String getSecond() {
		return this.name;
	}

	/**
	 * Sets pair's name.
	 * @param name Name to set.
	 */
	public void setSecond(String name) {
		this.name = name;
	}

}
