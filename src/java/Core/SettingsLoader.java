/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Load from settings.xml file the application settings. The xml file has to be
 * in the form below.
 * <p>
 * <code>&lt;Settings&gt;</code><br>
 * <code>&lt;url&gt;URL_WHERE_DOWNLOAD&lt;/url&gt;</code><br>
 * <code>&lt;patternRegex&gt;REGULAR_EXPRESSION_FOR_EXTRACT_DATA&lt;/patternRegex&gt;</code><br>
 * <code>&lt;/Settings&gt;</code>
 *
 * @author Alessandro
 */
public class SettingsLoader {

    private String url;
    private String patternRegex;

    public SettingsLoader() {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = builder.build(new File("settings.xml"));
            Element rootElement = document.getRootElement();
            this.url = rootElement.getAttributeValue("url");
            this.patternRegex = rootElement.getAttributeValue("patternRegex");
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getPattern() {
        return patternRegex;
    }

}
