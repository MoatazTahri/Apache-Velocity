package com.leaf.networkMS.Tools;

import com.leaf.tools.APILinks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class NetworkUtils {

    public static boolean internetIsConnected() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(1000);
        } catch (IOException e) {
            return false;
        }
    }

    public static String getPublicIp() throws IOException {
        URL myIpURL = new URL(APILinks.Amazon_check_ip); //find public IP
        InputStreamReader stream = new InputStreamReader(myIpURL.openStream());
        BufferedReader br = new BufferedReader(stream);
        String publicIp = br.readLine();
        br.close();
        return publicIp;
    }
}
