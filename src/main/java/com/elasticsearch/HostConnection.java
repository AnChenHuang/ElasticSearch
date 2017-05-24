/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elasticsearch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Chen
 */
public class HostConnection {

    private static HostConnection instance;
    private static String put = "PUT";
    private static String delete = "DELETE";

    private HostConnection() {
    }

    public static HostConnection getInstance() {
        if (instance == null) {
            instance = new HostConnection();
        }
        return instance;
    }

    private void sendRequest(String index, JSONArray content, String type) {
        //Convert JSONArray to String
        String contentStr = "";
        if (content != null) {
            for (int i = 0; i < content.size(); i++) {
                JSONObject obj = (JSONObject) content.get(i);
                contentStr += "{\"index\":{\"_id\":" + i + "}}\n" + obj.toString() + "\n";
            }
        }
        try {
            URL url = new URL(index);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(contentStr);
            out.close();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void createIndex(String index, JSONArray content) {
        sendRequest(index,content,put);
    }

    public void deleteIndex(String index) {
        sendRequest(index,null,delete);
    }
}
