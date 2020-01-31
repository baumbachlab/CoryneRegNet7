<%-- 
    Document   : quantitiesOfRegulatorAndRegulationTypes
    Created on : Jan 29, 2019, 3:25:18 PM
    Author     : doglas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CoryneRegNet 7.0</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/statistics.css">
        <link type="text/css" rel="stylesheet" href="css/qRRT.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <script src="//d3js.org/d3.v5.min.js"></script>
        <script src="js/d3-tip.js"></script>
    </head>

    <body style="background-image: url('images/background.png'); background-size: cover;" >


        <nav class="navbar navbar-expand-md navbar-inverse bg-dark fixed-top navbar-dark">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.htm"><span class="color logo-size">Coryne</span><span class="color-reg logo-size">Reg</span><span class="color logo-size">Net</span><span class="color-reg logo-size"> 7</span></a>
                </div>
                <div class="w-105 text-right">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse flex-grow-1 text-right" id="collapsibleNavbar">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link menu-item link-color flex-nowrap" href="databaseStatistics.htm">Statistics</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link menu-item link-color flex-nowrap" href="processToDownalod.htm">Download</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link menu-item link-color" href="docs&help.htm">Docs & Help</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link menu-item link-color flex-nowrap" href="workflow.htm">Workflow</a>
                        </li>
                        <li class="nav-item">
                            <a data-trigger="focus" class="nav-link menu-item link-color" href="requirements.htm">Requirements</a>
                        </li>
                    </ul>
                </div>  
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row font">
                <div class="col-sm-12 bottom-btn" style="padding-left: 0px;">
                    <a><button class="btn btn-primary btn-normal" onclick="goBack()">Go Back</button></a>
                </div>
            </div>
        </div>

        <script>
            var count = -1;
            var regulatorsMap = new Map;
            var regulationsMap = new Map;
            var organisms = [];
        </script>

        <c:if test="${empty organismsRegulations}">
            <span>No entries found.</span>
        </c:if>

        <div class="container-fluid badge badge-light shadow space-to-footer">
            <hr style="color: #eee; margin-bottom: 30px">
            <c:set var="count" value="-1" scope="page"/>
            <c:forEach items="${organismsRegulators}" var="regulators">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:set var="divName" value="regulator-type${count}" scope="page"/>
                <c:set var="divName2" value="regulation-type${count}" scope="page"/>
                <div class="container-fluid" style="margin-bottom: 80px;">
                    <div class="row font">
                        <c:choose>
                            <c:when test="${count==0}">
                                <div class="col-sm-12"><p class="title-size">Quantities of regulator and regulation types (${type} database)</p></div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-sm-12">
                                    <center>
                                        <span class="title-size">Quantities of regulator and regulation types for <a href="organismInfo.htm?id=${organismsId.get(regulators.key)}&type=${type}">${regulators.key}</a></span>
                                    </center>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="row" style="margin-top: 40px;">
                        <div class="col-sm-6">
                            <div id="${divName}" align="right" class="pie-chart-pos"></div>
                            <b><center><p class="sub-title-regulators">Quantities of regulator types</p></center></b>
                        </div>
                        <div class="col-sm-6">
                            <div id="${divName2}" class="pie-chart-pos"></div>
                            <b><p class="sub-title-regulations">Quantities of regulation types</p></b>
                        </div>
                    </div>
                </div>

                <script>
                    var key = '${regulators.key}';
                    var value = '${regulators.value}';
                    var regulatorsObject = {};
                    organisms.push(key);
                    //console.log(organisms);
                    //console.log("Regulators---------------------------------");
                    regulatorsObject = regulatorsObjectFunction(value);
                    //console.log(regulatorsObject);
                    regulatorsMap.set(key, regulatorsObject);
                    var operonRetriver = {};
                    operonRetriver = regulatorsMap.get(key);
                    //console.log(operonRetriver);
                    function regulatorsObjectFunction(value) {
                        var regulatorsAttributes = {};
                        var aux = [];
                        aux = value.split(", ");
                        var attributes = [];
                        var genesAux = [];
                        attributes = aux[0].split("[");
                        regulatorsAttributes.activators = attributes[1];
                        regulatorsAttributes.repressors = aux[2];
                        regulatorsAttributes.duals = aux[1];
                        attributes = aux[3].split("]");
                        regulatorsAttributes.total = attributes[0];
                        return regulatorsAttributes;
                    }
                </script>
            </c:forEach>

            <c:forEach items="${organismsRegulations}" var="regulations">

                <script>
                    var key = '${regulations.key}';
                    var value = '${regulations.value}';
                    var regulationsObject = {};
                    //console.log("Regulations---------------------------------");
                    regulationsObject = regulationsObjectFunction(value);
                    regulationsMap.set(key, regulationsObject);
                    var operonRetriver = {};
                    operonRetriver = regulationsMap.get(key);
                    //console.log(operonRetriver);
                    function regulationsObjectFunction(value) {
                        var regulationsAttributes = {};
                        var aux = [];
                        aux = value.split(", ");
                        var attributes = [];
                        var genesAux = [];
                        attributes = aux[0].split("[");
                        regulationsAttributes.activations = attributes[1];
                        regulationsAttributes.repressions = aux[1];
                        attributes = aux[2].split("]");
                        regulationsAttributes.total = attributes[0];
                        return regulationsAttributes;
                    }
                </script>
            </c:forEach>


            <script>

                //Pie Chart - Regulator types

                for (var i = 0; i < regulatorsMap.size; i++) {

                    count++;
                    var activators;
                    var total;
                    var repressors;
                    var duals;
                    activators = parseInt(regulatorsMap.get(organisms[i]).activators, 10);
                    total = regulatorsMap.get(organisms[i]).total;
                    repressors = parseInt(regulatorsMap.get(organisms[i]).repressors, 10);
                    duals = parseInt(regulatorsMap.get(organisms[i]).duals, 10);

                    var data = [repressors, activators, duals];

                    //console.log("data: ");
                    //console.log(data);

                    var chart_width = 450;
                    var chart_height = 300;
                    var color = d3.scaleOrdinal(['red', 'green', 'blue']);
                    // legend dimensions
                    var legendRectSize = 25; // defines the size of the colored squares in legend
                    var legendSpacing = 6; // defines spacing between squares

                    //Pie layout
                    var pie = d3.pie();
                    //Arc
                    var outer_radius = (chart_width - 150) / 2;
                    var inner_radius = 0;
                    var arc = d3.arc()
                            .innerRadius(inner_radius)
                            .outerRadius(outer_radius);
                    //Create SVG element
                    //console.log(divName);
                    var svg = d3.select("#regulator-type" + count)
                            .append("svg")
                            .attr("width", chart_width)
                            .attr("height", chart_height);

                    // Create Tooltips
                    var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                            .html(function (d) {
                                //console.log("hdhd");
                                //console.log(d);
                                //console.log(data);
                                var content = "<span style='margin-left: 2.5px;'>" + "Regulators: " + d.value + "</span><br>";
                                return content;
                            });

                    svg.call(tip);

                    //Groups
                    var arcs = svg.selectAll('g.arc')
                            .data(pie(data))
                            .enter()
                            .append('g')
                            .attr('class', 'arc')
                            .attr('transform',
                                    "translate(" + outer_radius + "," + chart_height / 2 + ")"
                                    );
                    //Arcs
                    arcs.append('path')
                            .attr('fill', function (d, i) {
                                return color(i);
                            })
                            .attr('d', arc)
                            .on('mouseover', tip.show)
                            .on('mouseout', tip.hide);

                    //Labels
                    arcs.append('text')
                            .attr('transform', function (d, i) {
                                return "translate(" + arc.centroid(d) + ")";
                            })
                            .attr('text-anchor', 'text-middle')
                            .text(function (d) {
                                var total = 0;
                                //console.log("calculate");
                                //console.log(data.length);
                                var aux = data;
                                total = aux[0] + aux[1] + aux[2];
                                //console.log(total);

                                //console.log(d.value);
                                if (d.value == 0) {
                                    return "";
                                }
                                return (d.value * 100 / total).toPrecision(3) + "%";
                            });
                    // define legend
                    var regulatorTypes = ["Activators", "Repressors", "Dual"];
                    var legend = svg.append("g")
                            .attr("transform", "translate(" + (chart_width - 145) +
                                    "," + (chart_height - 250) + ")");
                    regulatorTypes.forEach(function (regulatorType, i) {

                        var legendRow = legend.append("g")
                                .attr("transform", "translate(0, " + (i * 30) + ")");
                        legendRow.append("rect")
                                .attr("x", 130)
                                .attr("y", 0)
                                .attr("width", 15)
                                .attr("height", 15)
                                .attr("fill", function () {
                                    //console.log(regulatorType);
                                    if (regulatorType == "Activators") {
                                        return "green";
                                    } else if (regulatorType == "Repressors") {
                                        return "red";
                                    }
                                    return "blue";
                                });
                        legendRow.append("text")
                                .attr("x", 120)
                                .attr("y", 15)
                                .attr("text-anchor", "end")
                                .text(regulatorType)
                                .attr("fill", function () {
                                    if (regulatorType == "Activators") {
                                        return "green";
                                    } else if (regulatorType == "Repressors") {
                                        return "red";
                                    }
                                    return "blue";
                                })
                                .style("font-size", "20px");
                    });
                    // Pie Chart - Regulations

                    var activations = parseInt(regulationsMap.get(organisms[i]).activations, 10);
                    var repressions = parseInt(regulationsMap.get(organisms[i]).repressions, 10);

                    var data = [activations, repressions];
                    //console.log(data);
                    var chart_width = 450;
                    var chart_height = 300;
                    var color = d3.scaleOrdinal(['green', 'red']);
                    // legend dimensions
                    var legendRectSize = 25; // defines the size of the colored squares in legend
                    var legendSpacing = 6; // defines spacing between squares

                    //Pie layout
                    var pie = d3.pie();
                    //Arc
                    var outer_radius = (chart_width - 150) / 2;
                    var inner_radius = 0;
                    var arc = d3.arc()
                            .innerRadius(inner_radius)
                            .outerRadius(outer_radius);
                    //Create SVG element
                    var svg = d3.select("#regulation-type" + count)
                            .append("svg")
                            .attr("width", chart_width)
                            .attr("height", chart_height);

                    // Create Tooltips
                    var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                            .html(function (d) {
                                var content = "<span style='margin-left: 2.5px;'>" + "Regulations: " + d.value + "</span><br>";
                                return content;
                            });

                    svg.call(tip);
                    //Groups
                    var arcs = svg.selectAll('g.arc')
                            .data(pie(data))
                            .enter()
                            .append('g')
                            .attr('class', 'arc')
                            .attr('transform',
                                    "translate(" + outer_radius + "," + chart_height / 2 + ")");
                    //Arcs
                    arcs.append('path')
                            .attr('fill', function (d, i) {
                                return color(i);
                            })
                            .attr('d', arc)
                            .on('mouseover', tip.show)
                            .on('mouseout', tip.hide);

                    //Labels
                    arcs.append('text')
                            .attr('transform', function (d, i) {
                                return "translate(" + arc.centroid(d) + ")";
                            })
                            .attr('text-anchor', 'text-middle')
                            .text(function (d) {
                                var aux;
                                aux = data;
                                var total = 0;
                                //console.log("total: " + total);
                                total = aux[0] + aux[1];
                                //console.log(d.value);
                                if (d.value == 0) {
                                    return "";
                                }
                                //console.log("total: " + total);
                                return (d.value * 100 / total).toPrecision(3) + "%";
                            });
                    // define legend
                    var regulatorTypes = ["Repressions", "Activations"];
                    var legend = svg.append("g")
                            .attr("transform", "translate(" + (chart_width - 155) +
                                    "," + (chart_height - 250) + ")");
                    regulatorTypes.forEach(function (regulatorType, i) {
                        var legendRow = legend.append("g")
                                .attr("transform", "translate(0, " + (i * 30) + ")");
                        legendRow.append("rect")
                                .attr("width", 15)
                                .attr("height", 15)
                                .attr("fill", function () {
                                    //console.log(regulatorType);
                                    if (regulatorType == "Activations") {
                                        return "green";
                                    } else if (regulatorType == "Repressions") {
                                        return "red";
                                    }
                                });
                        legendRow.append("text")
                                .attr("x", 155)
                                .attr("y", 15)
                                .attr("text-anchor", "end")
                                .text(regulatorType)
                                .attr("fill", function () {
                                    if (regulatorType == "Activations") {
                                        return "green";
                                    } else if (regulatorType == "Repressions") {
                                        return "red";
                                    }
                                })
                                .style("font-size", "20px");
                    });
                }
            </script>
        </div>

        <div class="container-fluid font space-from-last">
            <div class="footer">
                <div class="row">
                    <div class="col-sm-12">
                        <hr>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Developed by: Mariana Parise, Doglas Parise, Josch Pauling, Vasco Azevedo and Jan Baumbach (2020) - CoryneRegNet 7.0</span>
                    </div>
                </div>
                <div id="div-footer" class="row">
                    <div class="col-sm-2">
                        <a href="https://www.tum.de/" target="_blank" class="center-block">
                            <img src="images/tum-logo.svg" alt="Lights" class="tum-logo">
                        </a>
                        <a href="https://ufmg.br/" target="_blank" class="center-block">
                            <img src="images/logo_ufmg3.png" alt="Lights" class="ufmg-logo">
                        </a>
                    </div>
                    <div class="col-sm-2">

                    </div>                
                    <div class="col-sm-8 ">
                        <div class="row text-position">
                            <div class="col-sm-12">
                                <a href="https://www.baumbachlab.net/" target="_blank">
                                    <span class="center-block">Experimental Bioinformatics - Baumbach Lab </span>
                                </a>
                            </div>
                        </div>
                        <div class="row text-position">
                            <div class="col-sm-12">
                                <a href="http://www.lgcm.icb.ufmg.br/site/" target="_blank">
                                    <span class="center-block">Laboratory of Cellular and Molecular Genetics - LGCM</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
