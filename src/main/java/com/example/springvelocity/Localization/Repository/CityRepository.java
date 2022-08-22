package com.example.springvelocity.Localization.Repository;

import com.example.springvelocity.Localization.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,String> {

    /**
     *
     * @param countryCode
     * @param cityName
     * @return
     */
    City findByCountry_CodeAndName(String countryCode,String cityName);

}
