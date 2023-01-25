package com.leaf.DeviceMS.Repository;

import com.leaf.DeviceMS.Entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
    DeviceEntity findByLocalIpAndMacAddress(String localIp, String macAddress);
}
