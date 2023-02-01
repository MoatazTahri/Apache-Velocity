package com.leaf.deviceMS.Service;

import com.leaf.deviceMS.Entity.DeviceEntity;
import com.leaf.deviceMS.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository repository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Cacheable(value = "device")
    public DeviceEntity getDeviceByIpAndMac(String localIp, String macAddress) {
        return repository.findByLocalIpAndMacAddress(localIp, macAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DeviceEntity addIfNotExist(DeviceEntity device) {
        if (this.getDeviceByIpAndMac(device.getLocalIp(), device.getMacAddress()) == null)
            return repository.save(device);
        return null;
    }
}
