package variphy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreweryResponse {
	private String message;
	private List<Beer> beers;
	private String status;

	public BreweryResponse() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("data")
	public List<Beer> getBeers() {
		return beers;
	}

	public void setBeers(List<Beer> beers) {
		this.beers = beers;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
