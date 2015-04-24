/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operate between the database and the data.
 *
 * @author Alessandro
 */
public abstract class DBWriter {

    private Connection connection;

    /**
     * Initialize the database if it's empty. It creates a table in the database
     * MeteoGrappa named MeteoGrappa_data where there will be stored all the
     * information when the main class download the data. The sql for creating
     * table is:
     * <p>
     * <code>CREATE TABLE `MeteoGrappa_Data` (`datetime` TIMESTAMP NOT NULL,
     * `condition` VARCHAR(30), `temperature` REAL, `humidity` INTEGER,
     * `wind_speed` REAL,`wind_direction` VARCHAR(10), `pressure` REAL,
     * `solar_radiation` INTEGER,`solar_percentage` INTEGER, `uv` REAL,
     * `deaf_temperature` REAL, `rain` REAL, `feel_temperature` REAL,
     * `snow` INTEGER, PRIMARY KEY (`datetime`));
     * </code>
     *
     * @param jdbc odbc string for connect and create the database
     */
    protected void init(String jdbc) {
        try {
            connection = DriverManager.getConnection(jdbc);
            connection.createStatement().executeUpdate("CREATE TABLE `MeteoGrappa_Data` (`datetime` TIMESTAMP NOT NULL, `condition` VARCHAR(30), `temperature` REAL, `humidity` INTEGER, `wind_speed` REAL, `wind_direction` VARCHAR(10), `pressure` REAL, `solar_radiation` INTEGER, `solar_percentage` INTEGER, `uv` REAL, `deaf_temperature` REAL, `rain` REAL, `feel_temperature` REAL, `snow` INTEGER, PRIMARY KEY (`datetime`));");
        } catch (SQLException ex) {
            Logger.getLogger(DBWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write the data stored in a WeatherData object.
     *
     * @param data WeatherData object to use for give the data to be written on
     * the database
     */
    protected void write(WeatherData data) {
        try {
            PreparedStatement query = connection.prepareStatement("INSERT INTO `MeteoGrappa_Data` (`datetime`, `condition`, `temperature`, `humidity`, `wind_speed`, `wind_direction`, `pressure`, `solar_radiation`, `solar_percentage`, `uv`, `deaf_temperature`, `rain`, `feel_temperature`, `snow`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            query.setTimestamp(1, data.getDate());
            query.setString(2, data.getCondition());
            query.setDouble(3, data.getTemperature());
            query.setInt(4, data.getHumidity());
            query.setDouble(5, data.getWindSpeed());
            query.setString(6, data.getWindDirection());
            query.setDouble(7, data.getPressure());
            query.setInt(8, data.getSolar());
            query.setInt(9, data.getSolarPercentage());
            query.setDouble(10, data.getUv());
            query.setDouble(11, data.getDewTemperature());
            query.setDouble(12, data.getRain());
            query.setDouble(13, data.getFeelTemperature());
            query.setInt(14, data.getSnow());
            query.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Close the connection with the database.
     */
    public abstract void close();
}
