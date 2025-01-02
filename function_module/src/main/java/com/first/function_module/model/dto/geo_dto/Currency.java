package com.first.function_module.model.dto.geo_dto;

import java.util.List;

public class Currency{
	private String thousandsSeparator;
	private String symbol;
	private List<String> alternateSymbols;
	private int symbolFirst;
	private int subunitToUnit;
	private String format;
	private String htmlEntity;
	private String subunit;
	private int smallestDenomination;
	private String decimalMark;
	private String isoNumeric;
	private String name;
	private String isoCode;

	public String getThousandsSeparator(){
		return thousandsSeparator;
	}

	public String getSymbol(){
		return symbol;
	}

	public List<String> getAlternateSymbols(){
		return alternateSymbols;
	}

	public int getSymbolFirst(){
		return symbolFirst;
	}

	public int getSubunitToUnit(){
		return subunitToUnit;
	}

	public String getFormat(){
		return format;
	}

	public String getHtmlEntity(){
		return htmlEntity;
	}

	public String getSubunit(){
		return subunit;
	}

	public int getSmallestDenomination(){
		return smallestDenomination;
	}

	public String getDecimalMark(){
		return decimalMark;
	}

	public String getIsoNumeric(){
		return isoNumeric;
	}

	public String getName(){
		return name;
	}

	public String getIsoCode(){
		return isoCode;
	}
}