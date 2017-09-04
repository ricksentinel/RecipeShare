package datastructures;

/**
 * A pair data structure. Allows pair of distinct types.
 * @author Renan Jesus
 */
public interface Pair<F, S> {

	/**
	 * @return Pair's first element.
	 */
	F getFirst();

	/**
	 * @return Pair's second element.
	 */
	S getSecond();

}
