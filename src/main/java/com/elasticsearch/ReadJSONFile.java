/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elasticsearch;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Chen
 */
public class ReadJSONFile {
    /**
     * Read JSONFile and convert it to JSONArray
     * @param path
     * @return JSONArray
     */
    public static JSONArray readJSONFile(String path) {
        JSONParser parser = new JSONParser();
        
        try {
            /* Read JSON file with format UTF-8*/
            return (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

}
