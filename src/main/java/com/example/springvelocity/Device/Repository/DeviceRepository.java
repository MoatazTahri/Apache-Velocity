package com.example.springvelocity.Device.Repository;

import com.example.springvelocity.Device.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Integer> {
    Device findByLocalIpAndMacAddress(String localIp,String macAddress);
}
