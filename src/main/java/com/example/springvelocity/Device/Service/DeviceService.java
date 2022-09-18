package com.example.springvelocity.Device.Service;

import com.example.springvelocity.Device.Entity.Device;

public interface DeviceService {

    /**
     * return a unique machin that have specific address mac and ip from database. Can be null
     * @param localIp address ip
     * @param macAddress address MAC
     * @return device
     */
    Device getDeviceByIpAndMac(String localIp,String macAddress);

    /**
     * add new device to database if is not exists
     * @param device {@link Device}
     * @return device added
     */
    Device addIfNotExist(Device device);
}
