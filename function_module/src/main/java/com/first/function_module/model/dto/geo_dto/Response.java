package com.first.function_module.model.dto.geo_dto;

import java.util.List;

public class Response{
	private String thanks;
	private List<LicensesItem> licenses;
	private Rate rate;
	private String documentation;
	private StayInformed stayInformed;
	private List<ResultsItem> results;
	private Status status;
	private Timestamp timestamp;
	private int totalResults;

	public String getThanks(){
		return thanks;
	}

	public List<LicensesItem> getLicenses(){
		return licenses;
	}

	public Rate getRate(){
		return rate;
	}

	public String getDocumentation(){
		return documentation;
	}

	public StayInformed getStayInformed(){
		return stayInformed;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public Status getStatus(){
		return status;
	}

	public Timestamp getTimestamp(){
		return timestamp;
	}

	public int getTotalResults(){
		return totalResults;
	}
}