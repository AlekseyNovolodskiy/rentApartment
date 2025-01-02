package com.first.function_module.model.dto.geo_dto;

public class Annotations{
	private String flag;
	private Mercator mercator;
	private Timezone timezone;
	private What3words what3words;
	private OSM oSM;
	private DMS dMS;
	private Sun sun;
	private UNM49 uNM49;
	private String maidenhead;
	private int callingcode;
	private Object qibla;
	private String geohash;
	private Currency currency;
	private Roadinfo roadinfo;
	private String mGRS;

	public String getFlag(){
		return flag;
	}

	public Mercator getMercator(){
		return mercator;
	}

	public Timezone getTimezone(){
		return timezone;
	}

	public What3words getWhat3words(){
		return what3words;
	}

	public OSM getOSM(){
		return oSM;
	}

	public DMS getDMS(){
		return dMS;
	}

	public Sun getSun(){
		return sun;
	}

	public UNM49 getUNM49(){
		return uNM49;
	}

	public String getMaidenhead(){
		return maidenhead;
	}

	public int getCallingcode(){
		return callingcode;
	}

	public Object getQibla(){
		return qibla;
	}

	public String getGeohash(){
		return geohash;
	}

	public Currency getCurrency(){
		return currency;
	}

	public Roadinfo getRoadinfo(){
		return roadinfo;
	}

	public String getMGRS(){
		return mGRS;
	}
}
