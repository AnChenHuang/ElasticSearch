/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elasticsearch;

import org.json.simple.JSONArray;

/**
 *
 * @author Chen
 */
public class main {
    private static final String path = "C:/Users/Chen/Desktop/App_WEB/dataset/";
    private static final String wineFile = "wine.json";
    private static final String innFile = "inn.json";
    private static final String maratonFile = "maraton.json";
    private static HostConnection connection;
    private static final String wineIndex = "http://localhost:9200/cellar/wines/_bulk";
    private static final String wineDeleteIndex = "http://localhost:9200/cellar";
    private static final String innIndex = "http://localhost:9200/inn/reservations/_bulk";
    private static final String innDeleteIndex = "http://localhost:9200/inn";
    private static final String maratonIndex = "http://localhost:9200/maraton/competitors/_bulk";
    private static final String maratonDeleteIndex = "http://localhost:9200/maraton";
    public static void main(String[] args) {
       connection = HostConnection.getInstance();
       //createWineIndex();
       //deleteWineIndex();
       //createInnIndex();
       //deleteInnIndex();
       createMaratonIndex();
       //deleteInnIndex();
    }
    
    private static void createWineIndex(){
        
        String winesPath = path.concat(wineFile);
        JSONArray jsonArray = ReadJSONFile.readJSONFile(winesPath);
        
        connection.createIndex(wineIndex,jsonArray);
    }
    
    private static void deleteWineIndex(){

        connection.deleteIndex(wineDeleteIndex);
    }
    
    private static void createInnIndex(){
        
        String winesPath = path.concat(innFile);
        JSONArray jsonArray = ReadJSONFile.readJSONFile(winesPath);
        
        connection.createIndex(innIndex,jsonArray);
    }
    
    private static void deleteInnIndex(){

        connection.deleteIndex(innDeleteIndex);
    }
    
    private static void createMaratonIndex(){
        
        String winesPath = path.concat(maratonFile);
        JSONArray jsonArray = ReadJSONFile.readJSONFile(winesPath);
        
        connection.createIndex(maratonIndex,jsonArray);
    }
    
    private static void deleteMaratonIndex(){

        connection.deleteIndex(maratonDeleteIndex);
    }
    
    
}
