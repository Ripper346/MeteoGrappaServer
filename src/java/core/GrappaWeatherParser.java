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
        Matcher matches;
        WeatherData data = new WeatherData();
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexTimestamp")).matcher(text);
        if (matches.find()) {
            data.setDate("20" + matches.group(4) + "-" + matches.group(3) + "-" + matches.group(2) + " " + (matches.group(7).length() == 5 ? matches.group(7) + ":00" : matches.group(7)));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexCondition")).matcher(text);
        if (matches.find()) {
            data.setCondition(matches.group(1) + ", " + matches.group(2));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexTemperature")).matcher(text);
        if (matches.find()) {
            data.setTemperature(matches.group(1));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexHumidity")).matcher(text);
        if (matches.find()) {
            data.setHumidity(matches.group(1));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexWind")).matcher(text);
        if (matches.find()) {
            data.setWindSpeed(matches.group(1));
            data.setWindDirection(matches.group(4));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexPressure")).matcher(text);
        if (matches.find()) {
            data.setPressure(matches.group(1));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexSolar")).matcher(text);
        if (matches.find()) {
            data.setSolar(matches.group(1));
            data.setSolarPercentage(matches.group(3));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexUv")).matcher(text);
        if (matches.find()) {
            data.setUv(matches.group(1));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexDew")).matcher(text);
        if (matches.find()) {
            data.setDewTemperature(matches.group(2));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexRain")).matcher(text);
        if (matches.find()) {
            data.setRain(matches.group(2));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexFeelTemperature")).matcher(text);
        if (matches.find()) {
            data.setFeelTemperature(matches.group(1));
        }
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexSnow")).matcher(text);
        if (matches.find()) {
            data.setSnow(matches.group(2));
        }
        return data;
    }
}
