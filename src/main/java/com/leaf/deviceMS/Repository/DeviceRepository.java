package com.leaf.deviceMS.Repository;

import com.leaf.deviceMS.Entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
    DeviceEntity findByLocalIpAndMacAddress(String localIp, String macAddress);
}
