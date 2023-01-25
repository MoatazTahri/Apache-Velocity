package com.leaf.DeviceMS.Service;

import com.leaf.DeviceMS.Entity.DeviceEntity;

public interface DeviceService {

    /**
     * return a unique machin that have specific address mac and ip from database. Can be null
     *
     * @param localIp    address ip
     * @param macAddress address MAC
     * @return device
     */
    DeviceEntity getDeviceByIpAndMac(String localIp, String macAddress);

    /**
     * add new device to database if is not exists
     *
     * @param device {@link DeviceEntity}
     * @return device added
     */
    DeviceEntity addIfNotExist(DeviceEntity device);
}
