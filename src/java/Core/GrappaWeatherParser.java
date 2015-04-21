/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 * This class elaborates the web page choosed for the parsing of the data and
 * putted on the database.
 *
 * @author Alessandro
 */
public class GrappaWeatherParser implements Runnable {

    public final String URL = "http://www.metocimagrappa.it/wd/tabella.html";

    @Override
    public void run() {
        ResourceDownloader resource = new ResourceDownloader(URL);
        WeatherData data = new WeatherData();
        data.extract(resource.getpage());
        DBWriter database = new DBWriter();
        database.write(data);
        database.close();
    }
}
