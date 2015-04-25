/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

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

    /**
     * Open the database or create a new one with default tables.
     */
    public DerbyDBWriter(String connection) {
        super();
        try {
            setConnection(DriverManager.getConnection("jdbc:derby:" + connection));
            this.createTables();
        } catch (SQLException ex) {
            String code = ex.getSQLState();
            if (ex.getSQLState().equals("08004")) {
                this.init("jdbc:derby://localhost/MeteoGrappa;create=true");
            }
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
