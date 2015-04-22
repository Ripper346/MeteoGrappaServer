/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 * This class contains the data of weather from a page.
 *
 * @author Alessandro
 */
public class WeatherData {

    private String date;
    private String condition;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private String windDirection;
    private double pressure;
    private int solar;
    private int solarPercentage;
    private double uv;
    private double dewTemperature;
    private int rain;
    private double feelTemperature;
    private int snow;

    public WeatherData() {
        this("", "", 0.0, 0, 0.0, "", 0.0, 0, 0, 0.0, 0.0, 0, 0.0, 0);
    }

    public WeatherData(String date, String condition, double temperature, int humidity, double windSpeed, String windDirection, double pressure, int solar, int solarPercentage, double uv, double dewTemperature, int rain, double feelTemperature, int snow) {
        this.date = date;
        this.condition = condition;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
        this.solar = solar;
        this.solarPercentage = solarPercentage;
        this.uv = uv;
        this.dewTemperature = dewTemperature;
        this.rain = rain;
        this.feelTemperature = feelTemperature;
        this.snow = snow;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getSolar() {
        return solar;
    }

    public void setSolar(int solar) {
        this.solar = solar;
    }

    public int getSolarPercentage() {
        return solarPercentage;
    }

    public void setSolarPercentage(int solarPercentage) {
        this.solarPercentage = solarPercentage;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public double getDewTemperature() {
        return dewTemperature;
    }

    public void setDewTemperature(double dewTemperature) {
        this.dewTemperature = dewTemperature;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public double getFeelTemperature() {
        return feelTemperature;
    }

    public void setFeelTemperature(double feelTemperature) {
        this.feelTemperature = feelTemperature;
    }

    public int getSnow() {
        return snow;
    }

    public void setSnow(int snow) {
        this.snow = snow;
    }

}