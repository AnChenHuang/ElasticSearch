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
    private static String post = "POST";
    private static String put = "PUT";
    private static String delete = "DELETE";

    private HostConnection() {
    }
    
    /**
     * Get singleton instance
     * @return 
     */
    public static HostConnection getInstance() {
        if (instance == null) {
            instance = new HostConnection();
        }
        return instance;
    }
    
    /**
     * Send repuest to host 
     * @param index
     * @param content
     * @param type 
     */
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
            /* Create a connection with speficied url*/
            URL url = new URL(index);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            
            /* Send the request*/
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            out.write(contentStr);
            out.close();
            
            /* Get response string*/
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
    
    /**
     * Send repuest to host 
     * @param index
     * @param content
     * @param type 
     */
    private void mappingRequest(String index, String content, String type) {
        
        try {
            /* Create a connection with speficied url*/
            URL url = new URL(index);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            
            /* Send the request*/
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            out.write(content);
            out.close();
            
            /* Get response string*/
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

    /**
     * To index content at the speficied url 
     * @param index
     * @param content 
     */
    public void createIndex(String index, JSONArray content) {
        sendRequest(index,content,post);
    }
    
    /**
     * To index content at the speficied url 
     * @param index
     * @param content 
     */
    public void mappingIndex(String index, String content) {
        mappingRequest(index,content,put);
    }

    /**
     * Remove the index content
     * @param index 
     */
    public void deleteIndex(String index) {
        sendRequest(index,null,delete);
    }
    
    
}
