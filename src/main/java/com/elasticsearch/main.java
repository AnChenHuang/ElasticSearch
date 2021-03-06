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

    private static final String path = "C:/Users/Chen/Desktop/App_WEB/dataset/";
    private static final File norteFolder = new File("C:/Users/Chen/Desktop/App_WEB/dataset/norte");
    private static final String wineFile = "wine.json";
    private static final String innFile = "inn.json";
    private static final String maratonFile = "maraton.json";
    private static HostConnection connection;
    private static final String wineIndex = "http://localhost:9200/cellar/wines/_bulk";
    private static final String wineDeleteIndex = "http://localhost:9200/cellar";
    private static final String innIndex = "http://localhost:9200/inn/reservations/_bulk";
    private static final String innDeleteIndex = "http://localhost:9200/inn";
    private static final String maratonIndex = "http://localhost:9200/maraton/competitors/_bulk";
    private static final String maratonMapping = "http://localhost:9200/maraton";
    private static final String maratonDeleteIndex = "http://localhost:9200/maraton";
    private static final String norteIndex = "http://localhost:9200/norte/articles/_bulk";
    private static final String norteDeleteIndex = "http://localhost:9200/norte";
    private static final String norteMapping = "http://localhost:9200/norte";

    public static void main(String[] args) {
        connection = HostConnection.getInstance();
        deleteWineIndex();
        createWineIndex();

        deleteInnIndex();
        createInnIndex();
        
        //deleteMaratonIndex();
        //mappingMaraton();
        //createMaratonIndex();
        
        //deleteNorteIndex();
        //mappingNorte();
        //createNorteIndex();
        
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
    private static void createNorteIndex() {
        // read all files of a month and convert its to a JSONArray
        JSONArray jsonArray = new JSONArray();
        for (File fileEntry : norteFolder.listFiles()) {
            String monthPath = fileEntry.getAbsolutePath();
            File monthFolder = new File(monthPath);
            for (File dayFile : monthFolder.listFiles()) {
                String dayPath = dayFile.getAbsolutePath();
                jsonArray.addAll(ReadJSONFile.readJSONFile(dayPath));
            }
        }
        connection.createIndex(norteIndex, jsonArray);
    }
    /**
     * To delete norte document
     */
    private static void deleteNorteIndex() {
        connection.deleteIndex(norteDeleteIndex);
    }

    /**
     * mapping for maraton index
     */
    private static void mappingMaraton() {
        String mapping = "{\n"
                + "    \"mappings\": {\n"
                + "      \"competitors\": {\n"
                + "        \"properties\": {\n"
                + "          \"Age\": {\n"
                + "            \"type\": \"integer\"\n"
                + "          },\n"
                + "          \"BIBNumber\": {\n"
                + "            \"type\": \"integer\"\n"
                + "          },\n"
                + "          \"FirstName\": {\n"
                + "            \"type\": \"string\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"Group\": {\n"
                + "            \"type\": \"string\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"GroupPlace\": {\n"
                + "            \"type\": \"integer\"\n"
                + "          },\n"
                + "          \"LasName\": {\n"
                + "            \"type\": \"string\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"Pace\": {\n"
                + "            \"type\": \"string\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"Place\": {\n"
                + "            \"type\": \"integer\"\n"
                + "          },\n"
                + "          \"Sex\": {\n"
                + "            \"type\": \"string\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"State\": {\n"
                + "            \"type\": \"string\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"Time\": {\n"
                + "            \"type\": \"date\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            },\n"
                + "            \"format\": \"H:mm:ss\"\n"
                + "          },"
                + "          \"Town\": {\n"
                + "            \"type\": \"string\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }";
        connection.mappingIndex(maratonMapping, mapping);
    }

    /**
     * mapping for norte index
     */
    private static void mappingNorte() {
        String mapping = "{\n"
                + "    \"mappings\": {\n"
                + "      \"articles\": {\n"
                + "        \"properties\": {\n"
                + "          \"autor\": {\n"
                + "            \"type\": \"text\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"cuerpo\": {\n"
                + "            \"type\": \"text\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"docid\": {\n"
                + "            \"type\": \"text\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"fecha\": {\n"
                + "            \"type\": \"date\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            },\n"
                + "            \"format\" : \"dd-MM-yyyy\"\n"
                + "          },\n"
                + "          \"resumen\": {\n"
                + "            \"type\": \"text\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"seccion\": {\n"
                + "            \"type\": \"text\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          },\n"
                + "          \"titulo\": {\n"
                + "            \"type\": \"text\",\n"
                + "            \"fields\": {\n"
                + "              \"keyword\": {\n"
                + "                \"type\": \"keyword\",\n"
                + "                \"ignore_above\": 256\n"
                + "              }\n"
                + "            }\n"
                + "          }\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  }";
        connection.mappingIndex(norteMapping, mapping);
    }
}
