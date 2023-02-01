package com.leaf.tools;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class JsonUtils {

    public static boolean isValidJSON(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static JSONObject getJSON(String url) throws IOException, JSONException {
        URL response = new URL(url);
        BufferedReader br1 = new BufferedReader(new InputStreamReader(response.openStream()));
        StringBuilder jsonResult = new StringBuilder();
        for (String line : br1.lines().collect(Collectors.toList())) {
            jsonResult.append(line);
        }
        return new JSONObject(jsonResult.toString());
    }


}
