package com.leaf.localizationMS.Service;

import com.leaf.localizationMS.Entity.CityEntity;
import com.leaf.localizationMS.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable("city")
    public CityEntity getCityByCountryAndName(String countryCode, String cityName) {
        return cityRepository.findByCountry_CodeAndName(countryCode, cityName);
    }
}
