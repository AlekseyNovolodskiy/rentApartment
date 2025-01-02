package com.first.function_module.model.dto.geo_dto;

public class Timezone{
	private String name;
	private int nowInDst;
	private String shortName;
	private int offsetSec;
	private String offsetString;

	public String getName(){
		return name;
	}

	public int getNowInDst(){
		return nowInDst;
	}

	public String getShortName(){
		return shortName;
	}

	public int getOffsetSec(){
		return offsetSec;
	}

	public String getOffsetString(){
		return offsetString;
	}
}
