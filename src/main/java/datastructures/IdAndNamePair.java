package datastructures;

/**
 * A pair of an object's ID and name.
 * @author Renan Jesus
 */
public class IdAndNamePair implements Pair<Long, String> {

	private long id;
	private String name;

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
	public void setFirst(long id) {
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
