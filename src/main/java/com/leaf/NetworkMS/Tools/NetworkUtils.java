package com.leaf.NetworkMS.Tools;

import com.leaf.APILinks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NetworkUtils {

    public static String getPublicIp() throws IOException {
        URL myIpURL = new URL(APILinks.Amazon_check_ip); //find public IP
        BufferedReader br = new BufferedReader(new InputStreamReader(myIpURL.openStream()));
        String publicIp = br.readLine();
        br.close();
        return publicIp;
    }
}
