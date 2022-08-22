package com.example.springvelocity.Device.Service;

import com.example.springvelocity.Device.Entity.Device;
import com.example.springvelocity.Device.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    private DeviceRepository repository;
    @Override
    public Device getDeviceByIpAndMac(String localIp, String macAddress) {
        return repository.findByLocalIpAndMacAddress(localIp,macAddress);
    }

    @Override
    public Device addIfNotExist(Device device) {
        if(getDeviceByIpAndMac(device.getLocalIp(),device.getMacAddress())==null)
            return repository.save(device);
        return null;
    }
}
