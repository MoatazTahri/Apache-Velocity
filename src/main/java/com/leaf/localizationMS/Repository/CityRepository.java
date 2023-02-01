package com.leaf.localizationMS.Repository;

import com.leaf.localizationMS.Entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String> {
    CityEntity findByCountry_CodeAndName(String countryCode, String cityName);
}
