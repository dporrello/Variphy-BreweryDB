package variphy.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import variphy.model.Beer;
import variphy.model.BreweryResponse;
import variphy.service.BreweryService;
import variphy.util.IConstants;
import variphy.util.IConstants.SearchType;
import variphy.util.Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BreweryServiceImpl implements BreweryService {

	private Utilities utilities;

	public BreweryServiceImpl() throws Exception {
		utilities = new Utilities();
		utilities.setProperties();
	}

	@Override
	public List<Beer> searchByQuery(String query) throws IOException {
		List<Beer> beers = getBeersFromBrewery();

		beers = filterByQuery(beers, query);

		Collections.sort(beers);

		return beers;
	}

	@Override
	public List<Beer> searchByAbv(String minAbv, String maxAbv)
			throws IOException {
		List<Beer> beers = getBeersFromBrewery();

		beers = filterByAbv(beers, minAbv, maxAbv);

		Collections.sort(beers);

		return beers;
	}

	@Override
	public List<Beer> searchByAdvanced(String minAbv, String maxAbv,
			String query) throws IOException {
		List<Beer> beers = getBeersFromBrewery();

		beers = filterByAbv(beers, minAbv, maxAbv);
		beers = filterByQuery(beers, query);

		Collections.sort(beers);

		return beers;
	}

	/**
	 * Method used to filter through the list of Beer objects from specific
	 * brewery using String query. It checks both Name and Description. If there
	 * is no name or description available for the beer it uses the Style name
	 * and/or description.
	 * 
	 * @param beers
	 *            - List of beers to filter.
	 * @param query
	 *            - String used to filter beers.
	 * @return
	 */
	private List<Beer> filterByQuery(List<Beer> beers, String query) {
		if (query.equals(IConstants.SEARCH_ALL) || query.isEmpty())
			return beers;

		for (Iterator<Beer> it = beers.iterator(); it.hasNext();) {
			Beer beer = it.next();
			String beerName = (null != beer.getName() ? beer.getName()
					: (null != beer.getStyle() ? beer.getStyle().getName() : ""));
			String beerDescription = (null != beer.getDescription() ? beer
					.getDescription() : (null != beer.getStyle() ? beer
					.getStyle().getDescription() : ""));

			// Room for improvement here, if performance is an issue can look
			// into using regionMatches. Regex is more expensive time wise than
			// toLowerCase.
			if (!beerName.toLowerCase().contains(query.toLowerCase())
					&& !beerDescription.toLowerCase().contains(
							query.toLowerCase()))
				it.remove();
		}

		return beers;
	}

	/**
	 * Method used to filter through the list of Beer objects from specific
	 * brewery using min and/or max abv.
	 * 
	 * @param beers
	 *            - List of beers to filter.
	 * @param minAbv
	 *            - Min ABV used to filter (could be '-' to signify no value)
	 * @param maxAbv
	 *            - Max ABV used to filter (could be '-' to signify no value)
	 * @return
	 */
	private List<Beer> filterByAbv(List<Beer> beers, String minAbv,
			String maxAbv) {
		SearchType type = null;
		Double min = null;
		Double max = null;

		if (!minAbv.equals(IConstants.NO_ABV)
				&& !maxAbv.equals(IConstants.NO_ABV)) {
			type = SearchType.BOTH_ABV;
			min = Double.parseDouble(minAbv);
			max = Double.parseDouble(maxAbv);

			// If min is greater than max then return an empty list of beers.
			if (min > max)
				return new ArrayList<Beer>();

		} else if (!minAbv.equals(IConstants.NO_ABV)
				&& maxAbv.equals(IConstants.NO_ABV)) {
			type = SearchType.JUST_MIN;
			min = Double.parseDouble(minAbv);
		} else if (!maxAbv.equals(IConstants.NO_ABV)
				&& minAbv.equals(IConstants.NO_ABV)) {
			type = SearchType.JUST_MAX;
			max = Double.parseDouble(maxAbv);
		}

		// If type is null that means that no min or max were given so we will
		// return all beers from brewery.
		if (null == type)
			return beers;

		for (Iterator<Beer> it = beers.iterator(); it.hasNext();) {
			Beer beer = it.next();

			if (!beer.isAbvInRange(min, max, type))
				it.remove();
		}

		return beers;
	}

	/**
	 * Since the Brewery API does not have filtering beers from brewery side, we
	 * need to retrieve all beers associated with brewery and then do filtering
	 * ourselves.
	 * 
	 * @return - All beers associated with brewery ID in properties file.
	 * @throws IOException
	 */
	private List<Beer> getBeersFromBrewery() throws IOException {
		String url = String.format(utilities.getUrl(),
				utilities.getBreweryId(), utilities.getBeersKey(),
				utilities.getApiKey());

		return getRequest(url);
	}

	/**
	 * Method used to send HTTP Get Request to Brewery DB API.
	 * 
	 * @param url
	 *            - URL containing parameters to hit api.
	 * @return - returns list of POJO Beer objects using Jackson Core to parse
	 *         from JSON to POJO.
	 * @throws IOException
	 */
	private List<Beer> getRequest(String url) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(df);

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		BreweryResponse apiResponse = mapper.readValue(con.getInputStream(),
				BreweryResponse.class);

		if (null != apiResponse.getBeers())
			return apiResponse.getBeers();

		return new ArrayList<Beer>();
	}

}
