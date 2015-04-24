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
    private final ServletContext settings;
    
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
        ResourceDownloader rawData = new ResourceDownloader(settings.getInitParameter("GrappaWeatherParser_urlRaw"));
        String[] rawDataArray = rawData.getPageText().split(" ");
        WeatherData data = extractWeatherDataFromString(resource.getPageHtml(), rawDataArray);
        DerbyDBWriter database = new DerbyDBWriter();
        database.write(data);
        database.close();
    }

    /**
     * This method extract data from a text. It uses a regular expression for
     * extract the variables and them are combined in a WeatherData object.
     *
     * @param text string of the web page where extract data
     * @param rawDataArray contains the second document downloaded by the page with raw data
     * @return WeatherData object that contains the data extract
     */
    public WeatherData extractWeatherDataFromString(String text, String[] rawDataArray) {
        Matcher matches;
        WeatherData data = new WeatherData();
        String[] date = rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexDate"))].split("/");
        data.setDate(date[2] + "-" + date[1] + "-" + date[0] + " " + rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexTime"))].substring(1));
        String condition = rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexCondition"))].replaceAll("_", " ").toLowerCase();
        condition = condition.substring(0, 1).toUpperCase() + condition.substring(1);
        data.setCondition(condition.replaceAll("/", ", "));
        data.setTemperature(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexTemperature"))]);
        data.setHumidity(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexHumidity"))]);
        data.setWindSpeed(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexWindSpeed"))]);
        String windDirection = WeatherData.WINDDIRECTIONS[(int) Math.round(((Double.parseDouble(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexWindDirection"))]) + 11) / 22.5) % 16)];
        data.setWindDirection(windDirection);
        data.setPressure(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexPressure"))]);
        data.setSolar((int)Double.parseDouble(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexSolar"))]));
        data.setSolarPercentage(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexSolarPercentage"))]);
        data.setUv(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexUv"))]);
        data.setDewTemperature(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexDew"))]);
        data.setRain(Double.parseDouble(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexRain"))])*60);
        data.setFeelTemperature(rawDataArray[Integer.parseInt(settings.getInitParameter("GrappaWeatherParser_indexFeelTemperature"))]);
        matches = Pattern.compile(settings.getInitParameter("GrappaWeatherParser_regexSnow")).matcher(text);
        if (matches.find()) {
            data.setSnow(matches.group(2));
        }
        return data;
    }
}
