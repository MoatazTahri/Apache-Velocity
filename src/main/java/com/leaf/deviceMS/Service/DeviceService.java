package com.leaf.deviceMS.Service;

import com.leaf.deviceMS.Entity.DeviceEntity;

public interface DeviceService {

    /**
     * find a unique machine by MAC address and IP in database. Can be null
     * @param localIp    ip address
     * @param macAddress address MAC
     * @return the found device
     */
    DeviceEntity getDeviceByIpAndMac(String localIp, String macAddress);

    /**
     * add new device to database if is not exists
     * @param device {@link DeviceEntity}
     * @return the added device
     */
    DeviceEntity addIfNotExist(DeviceEntity device);
}
