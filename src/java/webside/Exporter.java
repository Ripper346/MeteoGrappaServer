/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webside;

import core.WeatherData;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Export data in a file using a codification class
 *
 * @author Alessandro
 */
public class Exporter {

    /**
     * Export the weather data taken once in a JSON file.
     *
     * @param data WeatherData object where are stored all the information to
     * export
     * @param path Path where saves the file
     */
    public static void GsonWeatherDataExport(WeatherData data, String path) {
        String jsonData = GsonWeatherDataDecorator.json(data);
        try (PrintWriter output = new PrintWriter(path + System.getProperty("file.separator") + "lastData.json")) {
            output.print(jsonData);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
