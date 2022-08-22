package com.example.springvelocity.Device.Service;

import com.example.springvelocity.Device.Entity.Device;

public interface DeviceService {
    Device getDeviceByIpAndMac(String localIp,String macAddress);
    Device addIfNotExist(Device device);
}
