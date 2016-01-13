package variphy.service;

import java.io.IOException;
import java.util.List;

import variphy.model.Beer;

public interface BreweryService {

	/**
	 * Uses API to search by query.
	 * 
	 * @param query
	 *            - User input for searching.
	 * @return - List of Beer objects containing parsed JSON information.
	 * @throws IOException
	 *             - Throws exception if HTTP Get Request fails.
	 */
	public List<Beer> searchByQuery(String query) throws IOException;

	/**
	 * Uses API to search by abv.
	 * 
	 * @param minAbv
	 *            - User input for min ABV (could be '-', which signifies no
	 *            value)
	 * @param maxAbv
	 *            - User input for max ABV (could be '-', which signifies no
	 *            value)
	 * @return - List of Beer objects containing parsed JSON information.
	 * @throws IOException
	 *             - Throws exception if HTTP Get Request fails.
	 */
	public List<Beer> searchByAbv(String minAbv, String maxAbv)
			throws IOException;

	/**
	 * Uses API to search by query, and abv or a combination of the two.
	 * 
	 * @param minAbv
	 *            - User input for min ABV (could be '-', which signifies no
	 *            value)
	 * @param maxAbv
	 *            - User input for max ABV (could be '-', which signifies no
	 *            value)
	 * @param query
	 *            - User input for searching.
	 * @return - List of Beer objects containing parsed JSON information.
	 * @throws IOException
	 *             - Throws exception if HTTP Get Request fails.
	 */
	public List<Beer> searchByAdvanced(String minAbv, String maxAbv,
			String query) throws IOException;
}
