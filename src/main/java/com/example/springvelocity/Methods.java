package com.example.springvelocity;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Methods {
    public static String readFileContent(File file) throws FileNotFoundException {
        Scanner scanner=new Scanner(file);
        String content="";
        while (scanner.hasNext()){
            content+=scanner.next();
        }
        scanner.close();
        return content;
    }
    public static JSONObject getJSON(String url) throws IOException, JSONException {
        URL response = new URL(url);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(response.openStream()));
        String jsonResult="";
        for(String line:br1.lines().collect(Collectors.toList())){
            jsonResult+=line;
        }
        JSONObject object=new JSONObject(jsonResult);
        return object;
    }
    public static String getAddressMac(InetAddress address) throws SocketException {
        NetworkInterface ni=NetworkInterface.getByInetAddress(address);
        byte[] hardwareAddress=ni.getHardwareAddress();
        String[] hexadecimal = new String[hardwareAddress.length];
        for (int i = 0; i < hardwareAddress.length; i++) {
            hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
        }
        String macAddress = String.join("-", hexadecimal);
        return macAddress;
    }
}
