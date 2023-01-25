package com.leaf.LocalizationMS.Service;

import com.leaf.LocalizationMS.Entity.CityEntity;

public interface CityService {

    /**
     * return unique city that have specific name in a country. Can be null
     *
     * @param countryCode alpha 2 country code
     * @param cityName    city name
     * @return {@link CityEntity} city
     */
    CityEntity getCityByCountryAndName(String countryCode, String cityName);
}
