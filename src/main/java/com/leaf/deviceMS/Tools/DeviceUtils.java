package com.leaf.deviceMS.Tools;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DeviceUtils {

    private static final SystemInfo SYSTEM_INFO = new SystemInfo();

    private static final HardwareAbstractionLayer HARDWARE = SYSTEM_INFO.getHardware();

    private static final DeviceUtils DEVICE_UTILS = new DeviceUtils();

    public static DeviceUtils getInstance() {
        return DEVICE_UTILS;
    }

    public String getAddressMac() throws SocketException, UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        byte[] hardwareAddress = ni.getHardwareAddress();
        String[] hexadecimal = new String[hardwareAddress.length];
        for (int i = 0; i < hardwareAddress.length; i++) {
            hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
        }
        return String.join("-", hexadecimal);
    }

    public String getHostAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    public String getManufacturer() {
        return HARDWARE.getComputerSystem().getManufacturer();
    }

    public String getModel() {
        return HARDWARE.getComputerSystem().getModel();
    }

    public String getProcessor() {
        return HARDWARE.getProcessor().getProcessorIdentifier().getName();
    }

    public String getOperatingSystem() {
        OperatingSystem operatingSystem = SYSTEM_INFO.getOperatingSystem();
        return String.format("%s %s", operatingSystem.getFamily(), operatingSystem.getVersionInfo());
    }
}
