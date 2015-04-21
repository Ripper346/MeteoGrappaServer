/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * This class download the content of a web page.
 *
 * @author Alessandro
 */
public class ResourceDownloader {

    private String pageText;

    /**
     * Constructor: it downloads the web page and store in a String variable.
     *
     * @param link url of the page to download
     */
    public ResourceDownloader(String link) {
        String line;
        BufferedReader siteBuffer = null;
        try {
            URL site = new URL(link);
            pageText = "";
            siteBuffer = new BufferedReader(new InputStreamReader(site.openStream()));
            while ((line = siteBuffer.readLine()) != null) {
                pageText += line;
            }
        } catch (IOException ex) {
            // nothing to do
        } finally {
            try {
                siteBuffer.close();
            } catch (NullPointerException | IOException ex) {
                // nothing to do
            }
        }
    }
}
