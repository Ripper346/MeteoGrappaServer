/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workers;

import core.GrappaWeatherParser;
import javax.servlet.ServletContext;

/**
 * This class launch the thread for download the page and write the data parsed
 * on the database.
 *
 * @author Alessandro
 */
public class DataWorker implements Runnable {

    protected ServletContext servletContext;
    /**
     * Constructor 
     * 
     * @param servletContext 
     */
    public DataWorker(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    
    /**
     * Runner method for store the data of the weather in the database.
     */
    @Override
    public void run() {
        new Thread(new GrappaWeatherParser(servletContext)).start();
    }
}
