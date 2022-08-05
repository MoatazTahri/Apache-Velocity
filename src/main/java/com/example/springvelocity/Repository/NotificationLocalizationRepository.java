package com.example.springvelocity.Repository;

import com.example.springvelocity.Entity.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationLocalizationRepository extends JpaRepository<Localization,Integer> {
}
