/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Timestamp;

/**
 * This class contains the data of weather from a page.
 *
 * @author Alessandro
 */
public class WeatherData {

    private Timestamp date;
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
    private double rain;
    private double feelTemperature;
    private int snow;

    public WeatherData() {
        this(null, "", 0.0, 0, 0.0, "", 0.0, 0, 0, 0.0, 0.0, 0.0, 0.0, 0);
    }

    public WeatherData(String date, String condition, double temperature, int humidity, double windSpeed, String windDirection, double pressure, int solar, int solarPercentage, double uv, double dewTemperature, double rain, double feelTemperature, int snow) {
        this.date = date == null ? null : Timestamp.valueOf(date);
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setDate(String date) throws IllegalArgumentException {
        this.setDate(Timestamp.valueOf(date));
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

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
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

    public void setTemperature(String temperature) {
        this.setTemperature(Double.parseDouble(temperature));
    }

    public void setHumidity(String humidity) {
        this.setHumidity(Integer.parseInt(humidity));
    }

    public void setWindSpeed(String windSpeed) {
        this.setWindSpeed(Double.parseDouble(windSpeed));
    }

    public void setPressure(String pressure) {
        this.setPressure(Double.parseDouble(pressure));
    }

    public void setSolar(String solar) {
        this.setSolar(Integer.parseInt(solar));
    }

    public void setSolarPercentage(String solarPercentage) {
        this.setSolarPercentage(Integer.parseInt(solarPercentage));
    }

    public void setUv(String uv) {
        this.setUv(Double.parseDouble(uv));
    }

    public void setDewTemperature(String dewTemperature) {
        this.setDewTemperature(Double.parseDouble(dewTemperature));
    }

    public void setRain(String rain) {
        this.setRain(Double.parseDouble(rain));
    }

    public void setFeelTemperature(String feelTemperature) {
        this.setFeelTemperature(Double.parseDouble(feelTemperature));
    }

    public void setSnow(String snow) {
        this.setSnow(Integer.parseInt(snow));
    }
}
