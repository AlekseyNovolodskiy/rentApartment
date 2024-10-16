package com.module.product_module.service;

import com.module.product_module.model.dto.ApartmentDto;
import com.module.product_module.model.dto.OsmComponents;

public interface GeoLocationService {
    OsmComponents geoLocationMethod(String latitude, String longitude);
}
