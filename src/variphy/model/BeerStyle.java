package variphy.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerStyle {
	private int id;
	private String name;
	private String description;
	private Date createDate;
	private String ibuMin;
	private String ibuMax;
	private Double abvMin;
	private Double abvMax;

	public BeerStyle() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIbuMin() {
		return ibuMin;
	}

	public void setIbuMin(String ibuMin) {
		this.ibuMin = ibuMin;
	}

	public String getIbuMax() {
		return ibuMax;
	}

	public void setIbuMax(String ibuMax) {
		this.ibuMax = ibuMax;
	}

	public Double getAbvMin() {
		return abvMin;
	}

	public void setAbvMin(Double abvMin) {
		this.abvMin = abvMin;
	}

	public Double getAbvMax() {
		return abvMax;
	}

	public void setAbvMax(Double abvMax) {
		this.abvMax = abvMax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abvMax == null) ? 0 : abvMax.hashCode());
		result = prime * result + ((abvMin == null) ? 0 : abvMin.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((ibuMax == null) ? 0 : ibuMax.hashCode());
		result = prime * result + ((ibuMin == null) ? 0 : ibuMin.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BeerStyle other = (BeerStyle) obj;
		if (abvMax == null) {
			if (other.abvMax != null)
				return false;
		} else if (!abvMax.equals(other.abvMax))
			return false;
		if (abvMin == null) {
			if (other.abvMin != null)
				return false;
		} else if (!abvMin.equals(other.abvMin))
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
		if (ibuMax == null) {
			if (other.ibuMax != null)
				return false;
		} else if (!ibuMax.equals(other.ibuMax))
			return false;
		if (ibuMin == null) {
			if (other.ibuMin != null)
				return false;
		} else if (!ibuMin.equals(other.ibuMin))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
