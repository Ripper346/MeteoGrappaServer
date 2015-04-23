/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.regex.*;
import javax.servlet.ServletContext;

/**
 * This class elaborates the web page choosed for the parsing of the data and
 * putted on the database.
 *
 * @author Alessandro
 */
public class GrappaWeatherParser implements Runnable {

    //private SettingsLoader settings;
    private ServletContext settings;

    public GrappaWeatherParser(ServletContext settings) {
        this.settings = settings;
    }
    
    /**
     * Launch the downloader, then it extract the data and write it in the
     * database.
     */
    @Override
    public void run() {
        ResourceDownloader resource = new ResourceDownloader(settings.getInitParameter("GrappaWeatherParser_url"));
        WeatherData data = extractWeatherDataFromString(resource.getPageText());
        DerbyDBWriter database = new DerbyDBWriter();
        database.write(data);
        database.close();
    }

    /**
     * This method extract data from a text. It uses a regular expression for
     * extract the variables and them are combined in a WeatherData object.
     *
     * @param text string where extract data
     * @return WeatherData object that contains the data extract
     */
    public WeatherData extractWeatherDataFromString(String text) {
        Pattern pattern = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_pattern"));
        Matcher matches = pattern.matcher(text);
        WeatherData data = null;
        if (matches.find()) {
            data = new WeatherData(matches.group(4) + "-" + matches.group(3) + "-" + matches.group(2) + " " + matches.group(6),
                    matches.group(8) + ", " + matches.group(9), Double.parseDouble(matches.group(11)),
                    Integer.parseInt(matches.group(14)), Double.parseDouble(matches.group(16)),
                    matches.group(21), Double.parseDouble(matches.group(23)), Integer.parseInt(matches.group(26)),
                    Integer.parseInt(matches.group(28)), Double.parseDouble(matches.group(30)),
                    Double.parseDouble(matches.group(33)), Integer.parseInt(matches.group(36)),
                    Double.parseDouble(matches.group(39)), Integer.parseInt(matches.group(43)));
        }
        return data;
    }
}
