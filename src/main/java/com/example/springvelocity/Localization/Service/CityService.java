package com.example.springvelocity.Localization.Service;

import com.example.springvelocity.Localization.Entity.City;

public interface CityService {
    /**
     * Method to get specific City in specific country
     * @param cityName String
     * @param countryCode String
     * @return unique city result
     */
    City getCityByCountryAndName(String countryCode,String cityName);
}
