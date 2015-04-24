/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;

/**
 * This class download the content of a web page.
 *
 * @author Alessandro
 */
public class ResourceDownloader {

    private String pageText;

    /**
     * It downloads the web page and store in a String variable.
     *
     * @param link url of the page to download
     */
    public ResourceDownloader(String link) {
        try {
            pageText = Jsoup.connect(link).get().html();
        } catch (IOException ex) {
            Logger.getLogger(ResourceDownloader.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public String getPageText() {
        return pageText;
    }

}
