/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Servlet for parsing the Grappa weather site.
 * 
 * @author Alessandro
 */
public class ApplicationContextListener implements ServletContextListener {

    private ScheduledExecutorService scheduler;
    private static ServletContext context;
    
    public ServletContext getServletContext() {
        return context;
    }

    @Override
    public void contextInitialized(ServletContextEvent settings) {
        context = settings.getServletContext();
        String interval = context.getInitParameter("GrappaWeatherParser_interval");
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
                new DataWorker(context),
                0,
                interval == null ? 30 : Integer.parseInt(interval),
                TimeUnit.MINUTES
        );
    }

    /**
     * It stops the servlet.
     * 
     * @param settings
     */
    @Override
    public void contextDestroyed(ServletContextEvent settings) {
        scheduler.shutdown();
    }
}
