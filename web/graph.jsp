<%-- 
    Document   : index
    Created on : 25-apr-2015, 15.10.33
    Author     : Alessandro
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="webside.DBReader"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
        <link type="text/css" rel="stylesheet" href="graph/graph.css">
        <link type="text/css" rel="stylesheet" href="graph/detail.css">
        <link type="text/css" rel="stylesheet" href="graph/lines.css">

        <script src="graph/d3.v3.js"></script>
        <script src="graph/rickshaw.js"></script>
        <script type="text/javascript">
            function createGraph(data, axis, chart, colours, names) {
                var graph, max, min, point, scales, series, scales;
                width = $(window).width() > 100 ? $(window).width() - 60 : 100;
                height = $(window).height() - 20;
                min = Number.MAX_VALUE;
                max = Number.MIN_VALUE;
                for (var i = 0; i < data.length; i++) {
                    series = data[i];
                    for (var j = 0; j < series.length; j++) {
                        point = series[j];
                        min = Math.min(min, point.y);
                        max = Math.max(max, point.y);
                    }
                }
                scales = d3.scale.linear().domain([min, max]).nice();
                series = [];
                for (var i = 0; i < data.length; i++) {
                    series.push({color: colours[i], data: data[i], name: names[i], scale: scales});
                }

                graph = new Rickshaw.Graph({
                    element: document.getElementById(chart),
                    renderer: 'line',
                    width: width,
                    height: height,
                    strokeWidth: 1,
                    series: series
                });
                new Rickshaw.Graph.Axis.Y.Scaled({
                    element: document.getElementById(axis),
                    graph: graph,
                    orientation: 'left',
                    height: height,
                    scale: scales,
                    tickFormat: Rickshaw.Fixtures.Number.formatKMBT
                });
                new Rickshaw.Graph.Axis.Time({
                    graph: graph,
                    height: height
                });
                new Rickshaw.Graph.HoverDetail({
                    graph: graph
                });

                graph.render();
            }

        </script>


        <style type="text/css">
            .axis0 {
                position: absolute;
                width: 40px;
            }
            #axis1 {
                position: absolute;
                left: 1050px;
                width: 40px;
            }
            .chart {
                position: relative;
                left: 50px;
            }
        </style>
    </head>

    <body>
        <div id="graph" class="graph">
            <div id="axis0" class="axis0"></div>
            <div id="chart" class="chart"></div>
        </div>
        <%
            String clause = "";
            int type = 0;
            try {
                int each = Integer.parseInt(request.getParameter("each"));
                Calendar cal = Calendar.getInstance();
                String time;
                switch (each) {
                    case 24:
                        // last 24 hours
                        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
                        time = new Timestamp(cal.getTimeInMillis()).toString();
                        clause = "datetime > '" + time + "'";
                        break;
                    case 7:
                        // last week
                        cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR) - 1);
                        time = new Timestamp(cal.getTimeInMillis()).toString();
                        clause = "datetime > '" + time + "'";
                        break;
                    case 15:
                        // current month
                        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
                        time = new Timestamp(cal.getTimeInMillis()).toString();
                        clause = "datetime > '" + time + "'";
                        break;
                    case 30:
                        // last month
                        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
                        time = new Timestamp(cal.getTimeInMillis()).toString();
                        clause = "datetime > '" + time + "'";
                        break;
                }
            } catch (NumberFormatException ex) {
                // do nothing: type not set
            }
            try {
                type = Integer.parseInt(request.getParameter("type"));
            } catch (NumberFormatException ex) {
                // do nothing: type not set
            }
            String[][] graph = new String[4][3];
            graph[0][0] = "temperature, feel_temperature, deaf_temperature";
            graph[1][0] = "humidity";
            graph[2][0] = "wind_speed";
            graph[3][0] = "rain";
            graph[0][1] = "var color = ['red', 'blue', 'grey'];";
            graph[1][1] = "var color = ['red'];";
            graph[2][1] = "var color = ['green'];";
            graph[3][1] = "var color = ['blue'];";
            graph[0][2] = "var legend = ['Temperatura', 'Temperatura percepita', 'Punto di rugiada'];";
            graph[1][2] = "var legend = ['Umidità'];";
            graph[2][2] = "var legend = ['Velocità'];";
            graph[3][2] = "var legend = ['Pioggia'];";
            out.println("<script type=\"text/javascript\">" + DBReader.readDataGraph("graph", graph[type][0], clause, "datetime")
                    + graph[type][1] + graph[type][2] + "\n</script>");
        %>
        <script type="text/javascript">
            createGraph(graph, "axis0", "chart", color, legend);

            window.onresize = function () {
                $("#axis0").html("");
                $("#chart").html("");
                createGraph(graph, "axis0", "chart", color, legend);
            }
        </script>
    </body>
</html>
