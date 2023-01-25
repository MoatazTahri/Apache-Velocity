package com.leaf.DeviceMS.Tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

public class DeviceUtils {

    public static String getAddressMac(InetAddress address) throws SocketException {
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        byte[] hardwareAddress = ni.getHardwareAddress();
        String[] hexadecimal = new String[hardwareAddress.length];
        for (int i = 0; i < hardwareAddress.length; i++) {
            hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
        }
        return String.join("-", hexadecimal);
    }
}
