/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webside;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import core.WeatherData;
import java.util.ArrayList;

/**
 * Decorator class for WeatherData objects that decorate to JSON syntax using
 * gson library.
 * 
 * @author Alessandro
 */
public class GsonWeatherDataDecorator {

    /**
     * Nested class for generate the couple directions -> wind directions array.
     * 
     * @author Alessandro
     */
    private static class Direction {

        public String[] directions;

        public Direction(String[] directions) {
            this.directions = directions;
        }
    }

    /**
     * Convert a WeatherData object in JSON.
     * 
     * @param data WeatherData object to extract in JSON
     * @return A string in JSON syntax with the array of the wind directions and 
     * the informations of a WeatherData object.
     */
    public static String json(WeatherData data) {
        Gson gson = new Gson();
        ArrayList toGenerateJson = new ArrayList(0);
        toGenerateJson.add(new Direction(WeatherData.WINDDIRECTIONS));
        toGenerateJson.add(data);
        JsonElement jsonData = gson.toJsonTree(toGenerateJson);
        String ris = gson.toJson(jsonData);
        return ris;
    }

}
