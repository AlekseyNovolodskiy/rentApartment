package com.first.function_module.model.dto.geo_dto;

import java.util.List;

public class Components{
	private String continent;
	private String country;
	private String normalizedCity;
	private String city;
	private String type;
	private String postcode;
	private String iSO31661Alpha2;
	private String houseNumber;
	private String iSO31661Alpha3;
	private String countryCode;
	private String road;
	private String category;
	private String state;
	private List<String> iSO31662;
	private String cityDistrict;
	private String region;

	public String getContinent(){
		return continent;
	}

	public String getCountry(){
		return country;
	}

	public String getNormalizedCity(){
		return normalizedCity;
	}

	public String getCity(){
		return city;
	}

	public String getType(){
		return type;
	}

	public String getPostcode(){
		return postcode;
	}

	public String getISO31661Alpha2(){
		return iSO31661Alpha2;
	}

	public String getHouseNumber(){
		return houseNumber;
	}

	public String getISO31661Alpha3(){
		return iSO31661Alpha3;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getRoad(){
		return road;
	}

	public String getCategory(){
		return category;
	}

	public String getState(){
		return state;
	}

	public List<String> getISO31662(){
		return iSO31662;
	}

	public String getCityDistrict(){
		return cityDistrict;
	}

	public String getRegion(){
		return region;
	}
}