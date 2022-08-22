package com.example.springvelocity.Localization.Service;

import com.example.springvelocity.Localization.Entity.City;
import com.example.springvelocity.Localization.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City getCityByCountryAndName(String countryCode,String cityName) {
        return cityRepository.findByCountry_CodeAndName(countryCode,cityName);
    }
}
