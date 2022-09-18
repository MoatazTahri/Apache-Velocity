package com.example.springvelocity.Localization.Service;

import com.example.springvelocity.Localization.Entity.City;

public interface CityService {

    /**
     * return unique city that have specific name in a country. Can be null
     * @param countryCode alpha 2 country code
     * @param cityName city name
     * @return {@link City} city
     */
    City getCityByCountryAndName(String countryCode,String cityName);
}
