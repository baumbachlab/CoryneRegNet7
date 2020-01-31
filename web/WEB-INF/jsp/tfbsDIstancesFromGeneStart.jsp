<%-- 
    Document   : tfbsDIstancesFromGeneStart
    Created on : Apr 1, 2019, 10:25:41 AM
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

        <!-- Bar Chart - HMM Profiles Lengths -->
        <script>
            var hmmProfilesLenShow = new Map;
            var count = -1;
            var organisms = [];
        </script>

        <c:set var="count" value="-1" scope="page"/>
        <c:forEach items="${hmmProfilesLen}" var="hmmProfileLen">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <c:set var="divName" value="hmmProfilesLen${count}" scope="page"/>
            <div class="container-fluid" style="margin-bottom: 80px;">
                <div class="row font">
                    <c:choose>
                        <c:when test="${count==0}">
                            <div class="col-sm-12"><p class="dist-info">Distribution of HMM profiles lengths (${type} database)</p></div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-sm-12"><center><span class="dist-info">Distribution of HMM profiles lengths for <a href="organismInfo.htm?id=${organismsId.get(hmmProfileLen.key)}&type=${type}">${hmmProfileLen.key}</a></span></center></div>
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
                var key = '${hmmProfileLen.key}';
                var value = '${hmmProfileLen.value}';
                var hmmProfileLenObject = {};
                organisms.push(key);
                //console.log(organisms);
                //console.log("Regulators---------------------------------");
                //console.log(value);
                hmmProfileLenObject = hmmProfileLenObjectFunction(value);
                //console.log(hmmProfileLenObject);
                hmmProfilesLenShow.set(key, hmmProfileLenObject);
                var auxRetriver = {};
                auxRetriver = hmmProfilesLenShow.get(key);
                //console.log(auxRetriver);
                function hmmProfileLenObjectFunction(value) {
                    var hmmProfileLenAttributes = {};
                    var aux = [];
                    var freq = [];
                    var hmmProfLength = [];
                    aux = value.split(", ");
                    for (var i = 0; i < aux.length; i++) {
                        var attributes = aux[i].split("=");
                        //console.log(attributes[0]);
                        if (attributes[0].includes("{")) {
                            //console.log("Etrei no {")
                            var attributesAux = attributes[0].split("{");
                            //console.log("attributesAux: " + attributesAux[1]);
                            if (attributesAux[1] == 0) {
                                hmmProfLength.push("5-12");
                            } else if (attributesAux[1] == 1) {
                                hmmProfLength.push("13-20");
                            } else if (attributesAux[1] == 2) {
                                hmmProfLength.push("21-28");
                            } else if (attributesAux[1] == 3) {
                                hmmProfLength.push("29-36");
                            } else if (attributesAux[1] == 4) {
                                hmmProfLength.push("37-45");
                            } else if (attributesAux[1] == 5) {
                                hmmProfLength.push("46-59");
                            } else {
                                hmmProfLength.push(">=60");
                            }

                            //console.log("hmmProfLength[i]: " + hmmProfLength[i])
                        } else {
                            //console.log("Etrei no else")
                            //console.log("attributes: " + attributes[0]);
                            if (attributes[0] == 0) {
                                hmmProfLength.push("5-12");
                            } else if (attributes[0] == 1) {
                                hmmProfLength.push("13-20");
                            } else if (attributes[0] == 2) {
                                hmmProfLength.push("21-28");
                            } else if (attributes[0] == 3) {
                                hmmProfLength.push("29-36");
                            } else if (attributes[0] == 4) {
                                hmmProfLength.push("37-45");
                            } else if (attributes[0] == 5) {
                                hmmProfLength.push("46-59");
                            } else {
                                hmmProfLength.push(">=60");
                            }
                            //console.log("hmmProfLength[i]: " + hmmProfLength[i])
                        }
                        //console.log(attributes[1]);

                        if (attributes[1].includes("}")) {
                            var attributesAux = attributes[1].split("}");
                            if (attributesAux[0].length == 1) {
                                freq.push("00" + attributesAux[0]);
                            } else if (attributesAux[0].length == 2) {
                                freq.push("0" + attributesAux[0]);
                            } else {
                                freq.push(attributesAux[0]);
                            }
                        } else {
                            if (attributes[1].length == 1) {
                                freq.push("00" + attributes[1]);
                            } else if (attributes[1].length == 2) {
                                freq.push("0" + attributes[1]);
                            } else {
                                freq.push(attributes[1]);
                            }
                        }
                    }

                    hmmProfileLenAttributes.freq = freq;
                    hmmProfileLenAttributes.hmmProfLength = hmmProfLength;
                    return hmmProfileLenAttributes;
                }
            </script>

        </c:forEach>
        <script>
            for (var i = 0; i < hmmProfilesLenShow.size; i++) {
                count++;
                //console.log(hmmProfilesLen.get(organisms[i]));
                var margin = {left: 50, right: 4.17, top: 6.17, bottom: 60};
                var width = 300 - margin.left - margin.right;
                var height = 300 - margin.top - margin.bottom;

                var g = d3.select('#hmmProfilesLen' + count)
                        .append("svg")
                        .attr("width", width + margin.left + margin.right)
                        .attr("height", height + margin.top + margin.bottom)
                        .append("g")
                        .attr("transform", "translate(" + margin.left + ", "
                                + margin.top + ")");

                var hmmProfilesLen2 = [];
                for (var k = 0; k < hmmProfilesLenShow.get(organisms[i]).freq.length; k++) {
                    //console.log("hmmProfilesLenShow.get(organisms[i]).hmmProfLength[k]: " + hmmProfilesLenShow.get(organisms[i]).hmmProfLength[k]);
                    //console.log("hmmProfilesLenShow.get(organisms[i]).freq[k]: " + hmmProfilesLenShow.get(organisms[i]).freq[k]);
                    var hmmProfilesLenAux = [];
                    hmmProfilesLenAux.hmmProfLength = hmmProfilesLenShow.get(organisms[i]).hmmProfLength[k];
                    hmmProfilesLenAux.freq = hmmProfilesLenShow.get(organisms[i]).freq[k];
                    //console.log(hmmProfilesLenAux);
                    hmmProfilesLen2.push(hmmProfilesLenAux);
                    //console.log(hmmProfilesLen2);
                }

                //console.log("----------------------------------");
                //console.log(hmmProfilesLen2);

                var data = hmmProfilesLen2;
                //console.log(data);

                // Create Tooltips
                var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                        .html(function (d) {
                            var freq = Number(d.freq);
                            var content = "<span style='margin-left: 2.5px;'>" + "Frequency: " + freq + "<br>" + "HMM Profiles Length range: " + d.hmmProfLength + "</span><br>";
                            return content;
                        });
                g.call(tip);


                var x = d3.scaleBand()
                        .domain(data.map(function (d) {
                            return d.hmmProfLength;
                        }
                        ))
                        .range([0, width])
                        .paddingInner(0.2)
                        .paddingOuter(0.2);
                var y = d3.scaleLinear()
                        .domain([0, d3.max(data, function (d) {
                                return d.freq;
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
                        .attr("y", -35)
                        .attr("font-size", "20px")
                        .attr("text-anchor", "middle")
                        .attr("transform", "rotate(-90)")
                        .text("Frequency");
                var rectangle = g.selectAll("rect")
                        .data(data);

//Enter the elements present on the data
                rectangle.enter()
                        .append("rect")
                        .attr("y", function (d) {
                            return y(d.freq);
                        })
                        .attr("x", function (d) {
                            return x(d.hmmProfLength);
                        })
                        .attr("width", x.bandwidth)
                        .attr("height", function (d) {
                            return height - y(d.freq);
                        })
                        .attr("fill", "grey")
                        .on('mouseover', tip.show)
                        .on('mouseout', tip.hide);
            }
        </script>

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
