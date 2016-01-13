package variphy.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Utilities {
	static ResourceBundle configBundle;

	private String url;
	private String breweryId;
	private String beersKey;
	private String apiKey;

	public Utilities() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBreweryId() {
		return breweryId;
	}

	public void setBreweryId(String breweryId) {
		this.breweryId = breweryId;
	}

	public String getBeersKey() {
		return beersKey;
	}

	public void setBeersKey(String beersKey) {
		this.beersKey = beersKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setProperties() throws Exception {
		try {
			configBundle = ResourceBundle
					.getBundle("Brewery", new Locale("es"));

			setUrl(configBundle.getString("api.url"));
			setBreweryId(configBundle.getString("api.breweryId"));
			setBeersKey(configBundle.getString("api.beers"));
			setApiKey(configBundle.getString("api.key"));

		} catch (Exception exception) {
			throw new Exception("Error parsing properties file.");
		}
	}
}