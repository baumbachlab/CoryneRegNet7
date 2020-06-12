<%--
    Document   : sRnaCoregulators
    Created on : Feb 19, 2019, 9:45:51 AM
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

        <!-- Bar Chart - co-regulating sRNAs -->
        <script>
            var numOfsRnaCoregulatorsShow = new Map;
            var count = -1;
            var organisms = [];
        </script>

        <div class="container-fluid badge badge-light shadow space-to-footer">
            <hr style="color: #eee; margin-bottom: 30px">
            <c:set var="count" value="-1" scope="page"/>
            <c:forEach items="${numOfsRnaCoregulators}" var="numOfGeneCoregulated">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:set var="divName" value="co-regulators${count}" scope="page"/>
                <div class="container-fluid" style="margin-bottom: 80px;">
                    <div class="row font">
                        <c:choose>
                            <c:when test="${count==0}">
                                <div class="col-sm-12"><p class="dist-info">Distribution of the number of co-regulating sRNAs (${type} database)</p></div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-sm-12"><center><span class="dist-info">Distribution of the number of co-regulating sRNAs for <a href="organismInfo.htm?id=${organismsId.get(numOfGeneCoregulated.key)}&type=${type}">${numOfGeneCoregulated.key}</a></span></center></div>
                                        </c:otherwise>
                                    </c:choose>
                    </div>
                    <div class="row" style="margin-top: 40px;">
                        <div class="col-sm-12">
                            <center><div id="${divName}"></div></center>
                        </div>
                    </div>
                </div>
                <script>
                    var key = '${numOfGeneCoregulated.key}';
                    var value = '${numOfGeneCoregulated.value}';
                    var coregulatorsObject = {};
                    organisms.push(key);
                    coregulatorsObject = coregulatorsObjectFunction(value);
                    //console.log(coregulatorsObject);
                    numOfsRnaCoregulatorsShow.set(key, coregulatorsObject);
                    var auxRetriver = {};
                    auxRetriver = numOfsRnaCoregulatorsShow.get(key);
                    //console.log(auxRetriver);
                    function coregulatorsObjectFunction(value) {
                        var coregulatorsAttributes = {};
                        var aux = [];
                        var tfs = [];
                        var coregulators = [];
                        var numOfCoregulators = 0;
                        var numOfTfs = 0;
                        aux = value.split(", ");
                        for (var i = 0; i < aux.length; i++) {
                            var attributes = aux[i].split("=");
                            //console.log(attributes[0]);
                            if (attributes[0].includes("{")) {
                                var attributesAux = attributes[0].split("{");
                                numOfCoregulators = attributesAux[1];
                                //coregulators.push(attributesAux[1]);
                            } else {
                                numOfCoregulators = attributes[0];
                            }

                            if (attributes[1].includes("}")) {
                                var attributesAux = attributes[1].split("}");
                                numOfTfs = attributesAux[0];
                            } else {
                                numOfTfs = attributes[1];
                            }

                            //coregulators
                            coregulators.push(numOfCoregulators);
                            //tfs
                            if (numOfTfs.length == 1) {
                                tfs.push("00" + numOfTfs);
                            } else if (numOfTfs.length == 2) {
                                tfs.push("0" + numOfTfs);
                            } else {
                                tfs.push(numOfTfs);
                            }
                        }
                        //coregulators.push(numOfCoregulators);
                        coregulatorsAttributes.tfs = tfs;
                        coregulatorsAttributes.coregulators = coregulators;
                        return coregulatorsAttributes;
                    }
                </script>

            </c:forEach>
            <script>
                for (var i = 0; i < numOfsRnaCoregulatorsShow.size; i++) {
                    count++;
                    //console.log(numOfCoregulatorsShow.get(organisms[i]));
                    var margin = {left: 80, right: 4.17, top: 6.17, bottom: 60};
                    var width = 500;
                    var height = 300 - margin.top - margin.bottom;
                    var g = d3.select('#co-regulators' + count)
                            .append("svg")
                            .attr("width", width + margin.left + margin.right)
                            .attr("height", height + margin.top + margin.bottom)
                            .append("g")
                            .attr("transform", "translate(" + margin.left + ", "
                                    + margin.top + ")");
                    var numOfCoregulators2 = [];
                    for (var k = 0; k < numOfsRnaCoregulatorsShow.get(organisms[i]).tfs.length; k++) {
                        var numOfCoregulatorsAux = [];
                        numOfCoregulatorsAux.coregulators = numOfsRnaCoregulatorsShow.get(organisms[i]).coregulators[k];
                        numOfCoregulatorsAux.tfs = numOfsRnaCoregulatorsShow.get(organisms[i]).tfs[k];
                        //console.log(numOfCoregulatorsAux);
                        numOfCoregulators2.push(numOfCoregulatorsAux);
                        //console.log(numOfCoregulators2);
                    }
                    var data = numOfCoregulators2;

                    // Create Tooltips
                    var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                            .html(function (d) {
                                var tfs = Number(d.tfs);
                                var content = "<span style='margin-left: 2.5px;'>" + "Number of sRNAs: " + tfs + "<br>" + "Number of co-regulators: " + d.coregulators + "</span><br>";
                                return content;
                            });
                    g.call(tip);
                    var x = d3.scaleBand()
                            .domain(data.map(function (d) {
                                return d.coregulators;
                            }
                            ))
                            .range([0, width])
                            .paddingInner(0.2)
                            .paddingOuter(0.2);
                    var y = d3.scaleLinear()
                            .domain([0, d3.max(data, function (d) {
                                    return d.tfs;
                                })])
                            .range([height, 0]);
                    var xAxisCall = d3.axisBottom(x);
                    g.append("g")
                            .attr("class", "x axis")
                            .attr("transform", "translate(0, " + height + ")")
                            .call(xAxisCall)
                            .selectAll("text");
                    var yAxisCall = d3.axisLeft(y)
                            .ticks(4)
                            .tickFormat(function (d) {
                                return d;
                            });
                    g.append("g")
                            .attr("class", "y-axis")
                            .call(yAxisCall);
                    //x label
                    g.append("text")
                            .attr("class", "x axis-label")
                            .attr("x", width / 2)
                            .attr("y", height + 40)
                            .attr("font-size", "20px")
                            .attr("text-anchor", "middle")
                            .text("Number of co-regulatos");
                    //y label
                    g.append("text")
                            .attr("class", "y axis-label")
                            .attr("x", -(height / 2))
                            .attr("y", -65)
                            .attr("font-size", "20px")
                            .attr("text-anchor", "middle")
                            .attr("transform", "rotate(-90)")
                            .text("Number of sRNAs");
                    var rectangle = g.selectAll("rect")
                            .data(data);
                    //Enter the elements present on the data
                    rectangle.enter()
                            .append("rect")
                            .attr("y", function (d) {
                                return y(d.tfs);
                            })
                            .attr("x", function (d) {
                                return x(d.coregulators);
                            })
                            .attr("width", x.bandwidth)
                            .attr("height", function (d) {
                                return height - y(d.tfs);
                            })
                            .attr("fill", "grey")
                            .on('mouseover', tip.show)
                            .on('mouseout', tip.hide);
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