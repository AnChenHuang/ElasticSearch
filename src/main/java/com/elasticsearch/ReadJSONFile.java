/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elasticsearch;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author Chen
 */
public class ReadJSONFile {
    public static JSONArray readJSONFile(String path) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONArray) parser.parse(new FileReader(path));
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

}
