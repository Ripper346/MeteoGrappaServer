/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBWriter for Derby databases.
 *
 * @author Alessandro
 */
public class DerbyDBWriter extends DBWriter {

    private Connection connection;

    /**
     * Open the database or create a new one with default tables.
     */
    public DerbyDBWriter() {
        connection = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            try {
                connection = DriverManager.getConnection("jdbc:derby:MeteoGrappa");
            } catch (SQLException ex) {
                this.init("jdbc:derby:MeteoGrappa;create=true");
            }
        } catch (ClassNotFoundException exClass) {
            Logger.getLogger(DBWriter.class.getName()).log(Level.SEVERE, null, exClass);
        }
    }

    /**
     * Close the connection with the database.
     */
    @Override
    public void close() {
        try {
            DriverManager.getConnection("jdbc:derby:MeteoGrappa;shutdown=true");
        } catch (SQLException ex) {
            if (!ex.getSQLState().equals("08006")) {
                Logger.getLogger(DBWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
