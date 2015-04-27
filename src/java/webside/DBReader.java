/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webside;

import core.WeatherData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class DBReader {

    private final static String CONNECTION = "jdbc:derby://localhost/MeteoGrappa";

    /**
     * Makes a query to the database and extract results.
     *
     * @param columns Columns of the table to extract
     * @param clause Where statement for the query
     * @param order Order statement for the query
     * @return A javascript matrix. Each element has a couple x, y where x is
     * the date in unix format and y is the value of the columns.
     */
    public static String readDataGraph(String javascriptVariableName, String columns, String clause, String order) {
        if (clause != null && !clause.equals("")) {
            clause = " WHERE " + clause;
        }
        if (order != null && !order.equals("")) {
            order = " ORDER BY " + order;
        }
        String output = "";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTION);
            ResultSet rs = conn.createStatement().executeQuery("SELECT datetime, " + columns + " FROM MeteoGrappa_Data" + clause + order);
            String dataDictionary[] = new String[3];
            for (int i = 0; i < dataDictionary.length; i++) {
                dataDictionary[i] = "[";
            }
            while (rs.next()) {
                String date = rs.getString(1);
                DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long unixTime = dfm.parse(date).getTime() / 1000;
                for (int i = 0; i < dataDictionary.length; i++) {
                    dataDictionary[i] += "{x: " + unixTime + ", y: " + rs.getString(i + 2) + "},";
                }
            }
            for (int i = 0; i < dataDictionary.length; i++) {
                dataDictionary[i] = dataDictionary[i].substring(0, dataDictionary[i].length() - 1) + "]";
            }
            output = "var " + javascriptVariableName + " = [";
            for (String dataDictionary1 : dataDictionary) {
                output += dataDictionary1 + ",";
            }
            return output + "];\n";
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(DBReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return output;
    }

    /**
     * Makes a query for extract the last record inserted.
     *
     * @return A WeatherData object where are stored all the information extracted
     * from the last record.
     */
    public static WeatherData getLastData() {
        Connection conn = null;
        WeatherData data = new WeatherData();
        try {
            conn = DriverManager.getConnection(CONNECTION);
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM MeteoGrappa_data WHERE datetime IN (SELECT MAX(datetime) FROM MeteoGrappa_data)");
            if (rs.next()) {
                data.setDate(rs.getString(1));
                data.setCondition(rs.getString(2));
                data.setTemperature(rs.getString(3));
                data.setHumidity(rs.getString(4));
                data.setWindSpeed(rs.getString(5));
                data.setWindDirection(rs.getString(6));
                data.setPressure(rs.getString(7));
                data.setSolar(rs.getString(8));
                data.setSolarPercentage(rs.getString(9));
                data.setUv(rs.getString(10));
                data.setDewTemperature(rs.getString(11));
                data.setRain(rs.getString(12));
                data.setFeelTemperature(rs.getString(13));
                data.setSnow(rs.getString(14));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }
}
