package com.first.function_module.model.dto.geo_dto;

public class ResultsItem{
	private Components components;
	private DistanceFromQ distanceFromQ;
	private String formatted;
	private int confidence;
	private Bounds bounds;
	private Annotations annotations;
	private Geometry geometry;

	public Components getComponents(){
		return components;
	}

	public DistanceFromQ getDistanceFromQ(){
		return distanceFromQ;
	}

	public String getFormatted(){
		return formatted;
	}

	public int getConfidence(){
		return confidence;
	}

	public Bounds getBounds(){
		return bounds;
	}

	public Annotations getAnnotations(){
		return annotations;
	}

	public Geometry getGeometry(){
		return geometry;
	}
}
