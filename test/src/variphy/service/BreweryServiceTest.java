package variphy.service;

import java.io.IOException;
import java.util.List;

import variphy.model.Beer;
import variphy.service.impl.BreweryServiceImpl;
import junit.framework.TestCase;

public class BreweryServiceTest extends TestCase {
	protected BreweryService breweryService;

	protected void setUp() throws Exception {
		breweryService = new BreweryServiceImpl();
	}

	public void testSearchByQueryWithResult() throws IOException {
		List<Beer> beers = breweryService.searchByQuery("black ipa");
		assertNotNull(beers);
		assertTrue(!beers.isEmpty());
	}

	public void testSearchByQueryEmptyResult() throws IOException {
		List<Beer> beers = breweryService
				.searchByQuery("testfasdfhasdjkhfalksdhf");
		assertNotNull(beers);
		assertTrue(beers.isEmpty());
	}

	public void testSearchByQueryEmptyInput() throws IOException {
		List<Beer> beers = breweryService.searchByQuery("");
		assertNotNull(beers);
		assertTrue(!beers.isEmpty());
	}

	public void testSearchByAbvMin() throws IOException {
		List<Beer> beers = breweryService.searchByAbv("1.6", "-");
		assertNotNull(beers);
		assertTrue(!beers.isEmpty());
	}

	public void testSearchByAbvMax() throws IOException {
		List<Beer> beers = breweryService.searchByAbv("-", "8");
		assertNotNull(beers);
		assertTrue(!beers.isEmpty());
	}

	public void testSearchByAbvBoth() throws IOException {
		List<Beer> beers = breweryService.searchByAbv("1.6", "5.5");
		assertNotNull(beers);
		assertTrue(!beers.isEmpty());
	}

	public void testSearchByAbvNeither() throws IOException {
		List<Beer> beers = breweryService.searchByAbv("-", "-");
		assertNotNull(beers);
		assertTrue(!beers.isEmpty());
	}

	public void testSearchByAbvEmptyResult() throws IOException {
		List<Beer> beers = breweryService.searchByAbv("200", "203");
		assertNotNull(beers);
		assertTrue(beers.isEmpty());
	}
}
