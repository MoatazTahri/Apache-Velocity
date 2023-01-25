package com.leaf.DeviceMS.Service;

import com.leaf.DeviceMS.Entity.DeviceEntity;
import com.leaf.DeviceMS.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository repository;

    @Override
    @Transactional(readOnly = true)
    // Require
    // https://stackoverflow.com/questions/13051204/spring-transaction-required-vs-requires-new-rollback-transaction
    @Cacheable(value = "device")
    // keyGenerator
    public DeviceEntity getDeviceByIpAndMac(String localIp, String macAddress) {
        return repository.findByLocalIpAndMacAddress(localIp, macAddress);
    }

    @Override
    public DeviceEntity addIfNotExist(DeviceEntity device) {
        if (this.getDeviceByIpAndMac(device.getLocalIp(), device.getMacAddress()) == null)
            return repository.save(device);
        return null;
    }
}
