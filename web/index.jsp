<%-- 
    Document   : index
    Created on : 25-apr-2015, 15.10.33
    Author     : Alessandro
--%>

<%@page import="core.WeatherData"%>
<%@page import="webside.DBReader"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.sun.xml.rpc.processor.modeler.j2ee.xml.deweyVersionType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meteo del monte Grappa</title>
        <link href="css/blogPostStyle.css" rel="stylesheet" type="text/css">
        <!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
        <script>var __adobewebfontsappname__ = "dreamweaver"</script>
        <script src="http://use.edgefonts.net/montserrat:n4:default;source-sans-pro:n2:default.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            WeatherData data = DBReader.getLastData();
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(data.getDate().toString());
            String time = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date);
            // Set how to rotate the wind arrow of the direction
            String direction[] = WeatherData.WINDDIRECTIONS;
            int index = -1;
            do {
                index++;
            } while (!direction[index].equals(data.getWindDirection()));
            out.println("<style>"
                    + ".rotateWind {"
                    + "-webkit-transform: rotate(" + 22.5 * index + "deg);"
                    + "-moz-transform: rotate(" + 22.5 * index + "deg);"
                    + "-o-transform: rotate(" + 22.5 * index + "deg);"
                    + "-ms-transform: rotate(" + 22.5 * index + "deg);"
                    + "transform: rotate(" + 22.5 * index + "deg);"
                    + "}"
                    + "</style>");
        %>
        <div id="mainwrapper">
            <header>
                <!--**************************************************************************
                  Header starts here. It contains Logo and 3 navigation links.
                  ****************************************************************************-->
                <div id="logo" class="links"><!-- Company Logo text --><a href=index.jsp>Meteo del monte Grappa</a></div>
                <nav> <a href="grafici.jsp" title="Link">Grafici</a>  </nav>
            </header>
            <div id="content">
                <section id="mainContent">
                    <!--************************************************************************
                      Main Blog content starts here
                      ****************************************************************************-->
                    <h1><!-- Blog title -->Condizioni meteo attuali</h1>
                    <div id="bannerImage"><img src="img/Montegrappa.jpg" alt=""/></div>
                    <p>
                    <table class="actualCondition">
                        <tr>
                            <td colspan="2">
                        <center><b>Ultimo aggiornamento: <%= time%></b></center>
                        </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                        <center><%= data.getCondition()%></center>
                        </td>
                        </tr>
                        <tr>
                            <td>Temperatura</td>
                            <td><%= data.getTemperature()%>°C</td>
                        </tr>
                        <tr>
                            <td>Temperatura percepita</td>
                            <td><%= data.getFeelTemperature()%>°C</td>
                        </tr>
                        <tr>
                            <td>Umidità</td>
                            <td><%= data.getHumidity()%> %</td>
                        </tr>
                        <tr>
                            <td>Velocità del vento</td>
                            <td>
                                <%= data.getWindSpeed()%> km/h 
                                <img src="img/arrow.gif" class="rotateWind"/>
                                <%= data.getWindDirection()%>
                            </td>
                        </tr>
                        <tr>
                            <td>Pressione</td>
                            <td><%= data.getPressure()%> hPa</td>
                        </tr>
                        <tr>
                            <td>Radiazione solare</td>
                            <td><%= data.getSolar()%> W/m² <%= data.getSolarPercentage()%>%</td>
                        </tr>
                        <tr>
                            <td>Radiazione UV</td>
                            <td><%= data.getUv()%></td>
                        </tr>
                        <tr>
                            <td>Punto di rugiada</td>
                            <td><%= data.getDewTemperature()%>°C</td>
                        </tr>
                        <tr>
                            <td>Intesità pioggia</td>
                            <td><%= data.getRain()%> mm/h</td>
                        </tr>
                        <tr>
                            <td>Altezza neve</td>
                            <td><%= data.getSnow()%> cm</td>
                        </tr>
                    </table>
                    </p>
                </section>
                <div id="sidebar">
                    <!--************************************************************************
                    Sidebar starts here. It contains a searchbox, sample ad image and 6 links
                    ****************************************************************************-->
                    Webcam
                    <nav>
                        <ul>
                            <li><a href="http://www.skylinewebcams.com/webcam/italia/veneto/treviso/cima-grappa.html?w=210" title="Link">Sacrario</a></li>
                            <li><a href="http://www.cimagrappa.it/meteo/webcam2.php" title="Link">Sud-est</a></li>
                            <li><a href="http://www.cimagrappa.it/meteo/webcam4.php" title="Link">Est</a></li>
                            <li><a href="http://www.cimagrappa.it/meteo/webcam3.php" title="Link">Nord</a></li>
                        </ul>
                    </nav>
                </div>

                <footer>
                    <!--************************************************************************
                    Footer starts here
                    ****************************************************************************-->
                    <article>
                        <p><center>I dati sono estratti dal sito <a href="http://www.cimagrappa.it/meteo/">cimagrappa.it</a></center></p>
                    </article>
                </footer>
            </div>
            <div id="footerbar"><!-- Small footerbar at the bottom --></div>
        </div>
    </body>
</html>
