/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elasticsearch;

import java.io.File;
import org.json.simple.JSONArray;

/**
 *
 * @author Chen
 */
public class main {

    //private static final String path = "C:/Users/Chen/Desktop/App_WEB/dataset/";

    private static final String path = "C:/Users/an/Desktop/App_Web/Practica2/Datos/";
    private static final File norteJanuaryFolder = new File("C:/Users/an/Desktop/App_Web/Practica2/Datos/norte/mes1_06");
    private static final String norteJanuaryPath = "C:/Users/an/Desktop/App_Web/Practica2/Datos/norte/mes1_06/";
    private static final File norteFebruaryFolder = new File("C:/Users/an/Desktop/App_Web/Practica2/Datos/norte/mes2_06");
    private static final String norteFebruaryPath = "C:/Users/an/Desktop/App_Web/Practica2/Datos/norte/mes2_06/";
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
    private static final String norteJanuaryIndex = "http://localhost:9200/norte/january/_bulk";
    private static final String norteFebruaryIndex = "http://localhost:9200/norte/february/_bulk";
    private static final String norteDeleteIndex = "http://localhost:9200/norte";

    public static void main(String[] args) {
        connection = HostConnection.getInstance();
       //createWineIndex();
        //deleteWineIndex();
        //createInnIndex();
        //deleteInnIndex();
        //createMaratonIndex();
        //deleteInnIndex();
        //createNorteMonthIndex(norteJanuaryFolder,norteJanuaryPath,norteJanuaryIndex);
        createNorteMonthIndex(norteFebruaryFolder,norteFebruaryPath,norteFebruaryIndex);
    }

    /*To index wine document*/
    private static void createWineIndex() {

        String winePath = path.concat(wineFile);
        JSONArray jsonArray = ReadJSONFile.readJSONFile(winePath);

        connection.createIndex(wineIndex, jsonArray);
    }

     /*To delete wine document*/
    private static void deleteWineIndex() {

        connection.deleteIndex(wineDeleteIndex);
    }

     /*To index inn document*/
    private static void createInnIndex() {

        String innPath = path.concat(innFile);
        JSONArray jsonArray = ReadJSONFile.readJSONFile(innPath);

        connection.createIndex(innIndex, jsonArray);
    }
    
     /*To delete inn document*/
    private static void deleteInnIndex() {

        connection.deleteIndex(innDeleteIndex);
    }

     /*To index maraton document*/
    private static void createMaratonIndex() {

        String maratonPath = path.concat(maratonFile);
        JSONArray jsonArray = ReadJSONFile.readJSONFile(maratonPath);

        connection.createIndex(maratonIndex, jsonArray);
    }

     /*To delete maraton document*/
    private static void deleteMaratonIndex() {

        connection.deleteIndex(maratonDeleteIndex);
    }

     /*To index a month documents of norte*/
    private static void createNorteMonthIndex(File folder, String monthPath, String monthIndex) {
        // read all files of a month and convert its to a JSONArray
        JSONArray jsonArray = new JSONArray();
        for (File fileEntry : folder.listFiles()) {
            String dayPath = monthPath + fileEntry.getName();
            jsonArray.addAll(ReadJSONFile.readJSONFile(dayPath));
        }
        connection.createIndex(monthIndex, jsonArray);
    }
    
    private static void deleteNorteIndex(){
        connection.deleteIndex(norteDeleteIndex);
    }
}
