package variphy.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import variphy.util.IConstants;
import variphy.util.IConstants.SearchType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Beer implements Comparable<Beer> {
	private String id;
	private String name;
	private String description;
	private Double abv;
	private Double ibu;
	private Date createDate;
	private BeerStyle style;

	public Beer() {

	}

	/**
	 * This function is used to determine if this beer object is valid within
	 * the filtering options provided by the user. It uses the ABV values.
	 * 
	 * @param minAbv
	 *            - Min value of ABV
	 * @param maxAbv
	 *            - Max value of ABV
	 * @param type
	 *            - SearchType that helps determine which properties are given.
	 * @return - False if the beer is not within range and true if it is within
	 *         range.
	 */
	public boolean isAbvInRange(Double minAbv, Double maxAbv, SearchType type) {

		boolean foundBeer = false;

		if (null != this.getAbv()) {
			switch (type) {
			case BOTH_ABV:
				if (this.getAbv() >= minAbv && this.getAbv() <= maxAbv)
					foundBeer = true;
				break;
			case JUST_MIN:
				if (this.getAbv() >= minAbv)
					foundBeer = true;
				break;
			case JUST_MAX:
				if (this.getAbv() <= maxAbv)
					foundBeer = true;
				break;
			default:
				break;
			}

		} else {
			// If there is a Style associated with this beer and no ABV
			// associated to it then we need to compare the values in the
			// style.
			if (null != this.getStyle()) {
				switch (type) {
				case BOTH_ABV:
					if (null != this.getStyle().getAbvMin()
							&& null != this.getStyle().getAbvMax()) {

						if (minAbv >= this.getStyle().getAbvMin()
								&& maxAbv <= this.getStyle().getAbvMax())
							foundBeer = true;
					} else if (null != this.getStyle().getAbvMin()
							&& null == this.getStyle().getAbvMax()) {

						if (minAbv >= this.getStyle().getAbvMin())
							foundBeer = true;
					} else if (null != this.getStyle().getAbvMax()
							&& null == this.getStyle().getAbvMin()) {

						if (maxAbv <= this.getStyle().getAbvMax())
							foundBeer = true;
					}
					break;
				case JUST_MIN:
					if (null != this.getStyle().getAbvMax()) {

						if (minAbv <= this.getStyle().getAbvMax())
							foundBeer = true;
					}
					break;
				case JUST_MAX:
					if (null != this.getStyle().getAbvMin()) {

						if (maxAbv >= this.getStyle().getAbvMin())
							foundBeer = true;
					}
					break;
				}
			}
		}

		return foundBeer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAbv() {
		return abv;
	}

	public void setAbv(Double abv) {
		this.abv = abv;
	}

	public Double getIbu() {
		return ibu;
	}

	public void setIbu(Double ibu) {
		this.ibu = ibu;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BeerStyle getStyle() {
		return style;
	}

	public void setStyle(BeerStyle style) {
		this.style = style;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(abv);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		temp = Double.doubleToLongBits(ibu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((style == null) ? 0 : style.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		if (Double.doubleToLongBits(abv) != Double.doubleToLongBits(other.abv))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(ibu) != Double.doubleToLongBits(other.ibu))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (style == null) {
			if (other.style != null)
				return false;
		} else if (!style.equals(other.style))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		if (null != style) {
			String abvMinMax = null;
			if (null != style.getAbvMin() && null != style.getAbvMax())
				abvMinMax = style.getAbvMin() + " - " + style.getAbvMax();
			else if (null != style.getAbvMin())
				abvMinMax = String.valueOf(style.getAbvMin());
			else if (null != style.getAbvMax())
				abvMinMax = String.valueOf(style.getAbvMax());

			String ibuMinMax = null;
			if (null != style.getIbuMin() && null != style.getIbuMax())
				ibuMinMax = style.getIbuMin() + " - " + style.getIbuMax();
			else if (null != style.getIbuMin())
				ibuMinMax = style.getIbuMin();
			else if (null != style.getIbuMax())
				ibuMinMax = style.getIbuMax();

			return "-----------------------------------------------------"
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_NAME
					+ ((null != name) ? name : style.getName())
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_ID
					+ ((null != id) ? id : String.valueOf(style.getId()))
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_DESCRIPTION
					+ ((null != description) ? description : style
							.getDescription())
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_ABV
					+ ((null != abv) ? abv : abvMinMax)
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_IBU
					+ ((null != ibu) ? ibu : ibuMinMax)
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_CREATE_DATE
					+ ((null != createDate) ? dateFormat.format(createDate)
							: dateFormat.format(style.getCreateDate()))
					+ System.getProperty(IConstants.LINE_SEPARATOR);
		} else
			return "-----------------------------------------------------"
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_NAME + name
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_ID + id
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_DESCRIPTION + description
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_ABV + abv
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_IBU + ibu
					+ System.getProperty(IConstants.LINE_SEPARATOR)
					+ IConstants.BEER_CREATE_DATE
					+ dateFormat.format(createDate)
					+ System.getProperty(IConstants.LINE_SEPARATOR);
	}

	@Override
	public int compareTo(Beer beer) {
		return beer.getCreateDate().compareTo(this.getCreateDate());
	}
}
