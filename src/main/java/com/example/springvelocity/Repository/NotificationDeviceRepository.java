package com.example.springvelocity.Repository;

import com.example.springvelocity.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDeviceRepository extends JpaRepository<Device,Integer> {
}
