
<%-- 
    Document   : distanceFromStartGene
    Created on : Apr 1, 2019, 2:10:34 PM
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
        <link type="text/css" rel="stylesheet" href="css/distanceFromStartGene.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <script src="//d3js.org/d3.v3.min.js"></script>
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

        <!-- Bar Chart - distribution of TFBS distances from gene start. -->
        <script>
            var distancesFromStartSiteShow = new Map;
            var distancesFromStartSiteActivationsShow = new Map;
            var distancesFromStartSiteRepressionsShow = new Map;
            var count = -1;
            var organisms = [];
        </script>

        <c:set var="count" value="-1" scope="page"/>
        <c:forEach items="${distancesFromStartSite}" var="distanceFromStartSite">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <c:set var="divName" value="distance${count}" scope="page"/>
            <c:set var="activations" value="distanceActivations${count}" scope="page"/>
            <c:set var="represions" value="distanceRepressions${count}" scope="page"/>
            <div class="container-fluid" style="margin-bottom: 80px;">
                <div class="row font">
                    <c:choose>
                        <c:when test="${count==0}">
                            <div class="col-sm-12"><p class="dist-info">Distribution of transcription factors binding site distances from gene start (${type} database)</p></div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-sm-12"><center><span class="dist-info">Distribution of transcription factors binding site distances from gene start for <a href="organismInfo.htm?id=${organismsId.get(distanceFromStartSite.key)}&type=${type}">${distanceFromStartSite.key}</a></span></center></div>
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
                var key = '${distanceFromStartSite.key}';
                var value = '${distanceFromStartSite.value}';
                var distancesFromStartSiteObject = {};
                organisms.push(key);
                //console.log(organisms);
                //console.log("Regulators---------------------------------");
                //console.log(value);
                distancesFromStartSiteObject = distanceFromStartSiteObjectFunction(value);
                //console.log(distanceFromStartSiteObject);
                distancesFromStartSiteShow.set(key, distancesFromStartSiteObject);
                var auxRetriver = {};
                auxRetriver = distancesFromStartSiteShow.get(key);
                //console.log(auxRetriver);
                function distanceFromStartSiteObjectFunction(value) {
                    var distanceFromStartSiteAttributes = {};
                    var aux = [];
                    var ranges = [];
                    var values = [];
                    var valuesAux = [];
                    aux = value.split(", ");
                    for (var i = 0; i < aux.length; i++) {
                        var attributes = aux[i].split("=");
                        //console.log(attributes[0]);
                        if (attributes[0].includes("{")) {
                            //console.log("Etrei no {")
                            var attributesAux = attributes[0].split("{");
                            //console.log("attributesAux: " + attributesAux[1]);
                            attributes[0] = attributesAux[1];

                            //console.log("ranges[" + i + "]: " + ranges[i])
                        }
                        //console.log("Etrei no else")
                        //console.log("attributes: " + attributes[0]);
                        if (attributes[0] == 0) {
                            ranges.push("-20-37");
                        } else if (attributes[0] == 1) {
                            ranges.push("38-95");
                        } else if (attributes[0] == 2) {
                            ranges.push("96-153");
                        } else if (attributes[0] == 3) {
                            ranges.push("154-211");
                        } else if (attributes[0] == 4) {
                            ranges.push("212-269");
                        } else if (attributes[0] == 5) {
                            ranges.push("270-327");
                        } else if (attributes[0] == 6) {
                            ranges.push("328-385");
                        } else if (attributes[0] == 7) {
                            ranges.push("386-443");
                        } else if (attributes[0] == 8) {
                            ranges.push("444-501");
                        } else if (attributes[0] == 9) {
                            ranges.push("502-560");
                        }
                        //console.log("ranges[i]: " + ranges[i])
                        //console.log(attributes[1]);

                        if (attributes[1].includes("}")) {
                            var attributesAux = attributes[1].split("}");
                            attributes[1] = attributesAux[0];
                        }

                        if (attributes[1].length == 1) {
                            valuesAux.push("00" + attributes[1]);
                        } else if (attributes[1].length == 2) {
                            valuesAux.push("0" + attributes[1]);
                        } else {
                            valuesAux.push(attributes[1]);
                        }
                    }

                    values.value = valuesAux;
                    distanceFromStartSiteAttributes.ranges = ranges;
                    distanceFromStartSiteAttributes.values = values;
                    return distanceFromStartSiteAttributes;
                }
            </script>
        </c:forEach>

        <c:forEach items="${distancesFromStartSiteActivator}" var="distanceFromStartSiteActivator">
            <script>
                var key = '${distanceFromStartSiteActivator.key}';
                var value = '${distanceFromStartSiteActivator.value}';
                var distancesFromStartSiteObject = {};
                organisms.push(key);
                //console.log(organisms);
                //console.log("Regulators---------------------------------");
                console.log(value);
                distancesFromStartSiteObject = distanceFromStartSiteObjectFunction(value);
                //console.log(distanceFromStartSiteObject);
                distancesFromStartSiteActivationsShow.set(key, distancesFromStartSiteObject);
                var auxRetriver = {};
                auxRetriver = distancesFromStartSiteActivationsShow.get(key);
                console.log("key: " + key);
                console.log("value: ");
                console.log(auxRetriver);
                function distanceFromStartSiteObjectFunction(value) {
                    var distanceFromStartSiteAttributes = {};
                    var aux = [];
                    var ranges = [];
                    var values = [];
                    var valuesAux = [];
                    aux = value.split(", ");
                    for (var i = 0; i < aux.length; i++) {
                        var attributes = aux[i].split("=");
                        console.log(attributes[0]);
                        if (attributes[0].includes("{")) {
                            console.log("Etrei no {")
                            var attributesAux = attributes[0].split("{");
                            console.log("attributesAux: " + attributesAux[1]);
                            attributes[0] = attributesAux[1];

                            console.log("ranges[" + i + "]: " + ranges[i])
                        }
                        console.log("Etrei no else")
                        console.log("attributes: " + attributes[0]);
                        if (attributes[0] == 0) {
                            ranges.push("-20-37");
                        } else if (attributes[0] == 1) {
                            ranges.push("38-95");
                        } else if (attributes[0] == 2) {
                            ranges.push("96-153");
                        } else if (attributes[0] == 3) {
                            ranges.push("154-211");
                        } else if (attributes[0] == 4) {
                            ranges.push("212-269");
                        } else if (attributes[0] == 5) {
                            ranges.push("270-327");
                        } else if (attributes[0] == 6) {
                            ranges.push("328-385");
                        } else if (attributes[0] == 7) {
                            ranges.push("386-443");
                        } else if (attributes[0] == 8) {
                            ranges.push("444-501");
                        } else if (attributes[0] == 9) {
                            ranges.push("502-560");
                        }
                        console.log("ranges[i]: " + ranges[i]);
                        console.log("value: " + attributes[1]);

                        if (attributes[1].includes("}")) {
                            var attributesAux = attributes[1].split("}");
                            attributes[1] = attributesAux[0];
                        }

                        if (attributes[1].length == 1) {
                            valuesAux.push("00" + attributes[1]);
                        } else if (attributes[1].length == 2) {
                            valuesAux.push("0" + attributes[1]);
                        } else {
                            valuesAux.push(attributes[1]);
                        }
                        console.log("valuesAux[i]: " + valuesAux[i]);
                    }

                    values.value = valuesAux;
                    distanceFromStartSiteAttributes.ranges = ranges;
                    distanceFromStartSiteAttributes.values = values;
                    return distanceFromStartSiteAttributes;
                }
            </script>
        </c:forEach>

        <c:forEach items="${distancesFromStartSiteRepressor}" var="distanceFromStartSiteRepressor">
            <script>
                var key = '${distanceFromStartSiteRepressor.key}';
                var value = '${distanceFromStartSiteRepressor.value}';
                var distancesFromStartSiteObject = {};
                organisms.push(key);
                //console.log(organisms);
                //console.log("Regulators---------------------------------");
                //console.log(value);
                distancesFromStartSiteObject = distanceFromStartSiteObjectFunction(value);
                //console.log(distanceFromStartSiteObject);
                distancesFromStartSiteRepressionsShow.set(key, distancesFromStartSiteObject);
                var auxRetriver = {};
                auxRetriver = distancesFromStartSiteRepressionsShow.get(key);
                //console.log(auxRetriver);
                function distanceFromStartSiteObjectFunction(value) {
                    var distanceFromStartSiteAttributes = {};
                    var aux = [];
                    var ranges = [];
                    var values = [];
                    var valuesAux = [];
                    aux = value.split(", ");
                    for (var i = 0; i < aux.length; i++) {
                        var attributes = aux[i].split("=");
                        //console.log(attributes[0]);
                        if (attributes[0].includes("{")) {
                            //console.log("Etrei no {")
                            var attributesAux = attributes[0].split("{");
                            //console.log("attributesAux: " + attributesAux[1]);
                            attributes[0] = attributesAux[1];

                            //console.log("ranges[" + i + "]: " + ranges[i])
                        }
                        //console.log("Etrei no else")
                        //console.log("attributes: " + attributes[0]);
                        if (attributes[0] == 0) {
                            ranges.push("-20-37");
                        } else if (attributes[0] == 1) {
                            ranges.push("38-95");
                        } else if (attributes[0] == 2) {
                            ranges.push("96-153");
                        } else if (attributes[0] == 3) {
                            ranges.push("154-211");
                        } else if (attributes[0] == 4) {
                            ranges.push("212-269");
                        } else if (attributes[0] == 5) {
                            ranges.push("270-327");
                        } else if (attributes[0] == 6) {
                            ranges.push("328-385");
                        } else if (attributes[0] == 7) {
                            ranges.push("386-443");
                        } else if (attributes[0] == 8) {
                            ranges.push("444-501");
                        } else if (attributes[0] == 9) {
                            ranges.push("502-560");
                        }
                        //console.log("ranges[i]: " + ranges[i])
                        //console.log(attributes[1]);

                        if (attributes[1].includes("}")) {
                            var attributesAux = attributes[1].split("}");
                            attributes[1] = attributesAux[0];
                        }

                        if (attributes[1].length == 1) {
                            valuesAux.push("00" + attributes[1]);
                        } else if (attributes[1].length == 2) {
                            valuesAux.push("0" + attributes[1]);
                        } else {
                            valuesAux.push(attributes[1]);
                        }
                    }

                    values.value = valuesAux;
                    distanceFromStartSiteAttributes.ranges = ranges;
                    distanceFromStartSiteAttributes.values = values;
                    return distanceFromStartSiteAttributes;
                }
            </script>
        </c:forEach>

        <script>
            count = -1;
            for (var i = 0; i < distancesFromStartSiteShow.size; i++) {
                // console.log("distancesFromStartSiteShow.size: " + distancesFromStartSiteShow.size);
                //console.log("distancesFromStartSiteActivationsShow.size: " + distancesFromStartSiteActivationsShow.size);
                //console.log("distancesFromStartSiteRepressionsShow.size: " + distancesFromStartSiteRepressionsShow.size);
                //console.log("i: " + i);
                //console.log("distancesFromStartSiteShow: ");
                //console.log(distancesFromStartSiteShow.get(organisms[i]));
                //console.log("distancesFromStartSiteActivationsShow: ");
                //console.log(distancesFromStartSiteActivationsShow.get(organisms[i]));
                //console.log("distancesFromStartSiteRepressionsShow: ");
                //console.log(distancesFromStartSiteRepressionsShow.get(organisms[i]));

                var distances2 = [];
                //console.log(distancesFromStartSiteShow.get(organisms[i]).ranges.length);

                //console.log("Fun starts!!!!!!!!!!!!!!!!");
                var data = [];
                var values = [];
                var nActivations;
                var nRepressions;
                var nSum = [];
                for (k = 0; k < distancesFromStartSiteShow.get(organisms[i]).values.value.length; k++) {
                    nSum = 0;
                    nActivations = 0;
                    nRepressions = 0;
                    console.log("Activator ranges: " + distancesFromStartSiteActivationsShow.get(organisms[i]).ranges[k]);
                    console.log("Activator value: " + distancesFromStartSiteActivationsShow.get(organisms[i]).values.value[k]);
                    nActivations = distancesFromStartSiteActivationsShow.get(organisms[i]).values.value[k];
                    console.log("Repressor ranges: " + distancesFromStartSiteRepressionsShow.get(organisms[i]).ranges[k]);
                    console.log("Repressor value: " + distancesFromStartSiteRepressionsShow.get(organisms[i]).values.value[k]);
                    nRepressions = distancesFromStartSiteRepressionsShow.get(organisms[i]).values.value[k];
                    console.log("Sum ranges (from DB): " + distancesFromStartSiteShow.get(organisms[i]).ranges[k]);
                    console.log("Sum value (from DB): " + distancesFromStartSiteShow.get(organisms[i]).values.value[k]);
                    nSum = parseInt(nActivations) + parseInt(nRepressions);
                    console.log("Sum value: " + nSum);
                    data.push({"ranges": distancesFromStartSiteShow.get(organisms[i]).ranges[k], "values": [{"value": nActivations, "regulationType": "Activations"}, {"value": nRepressions, "regulationType": "Repressions"}, {"value": nSum, "regulationType": "Sum"}]});
                }

                // Create Tooltips
                //   var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                //         .html(function (d) {
                //           var freq = Number(d.freq);
                //        var content = "<span style='margin-left: 2.5px;'>" + "Frequency: " + freq + "<br>" + "HMM Profiles Length: " + d.hmmProfLength + "</span><br>";
                //        return content;
                //  });
                //g.call(tip);

                count++;
                var margin = {top: 20, right: 20, bottom: 30, left: 55},
                        width = 960 - margin.left - margin.right,
                        height = 500 - margin.top - margin.bottom;
                var x0 = d3.scale.ordinal()
                        .rangeRoundBands([0, width], .1);
                var x1 = d3.scale.ordinal();
                var y = d3.scale.linear()
                        .range([height, 0]);
                var xAxis = d3.svg.axis()
                        .scale(x0)
                        .tickSize(0)
                        .orient("bottom");
                var yAxis = d3.svg.axis()
                        .scale(y)
                        .orient("left");
                var color = d3.scale.ordinal()
                        .range(["#339966", "#cc0000", "#b3b3b3"]);
                var svg = d3.select("#distance" + count)
                        .append("svg")
                        .attr("width", width + margin.left + margin.right)
                        .attr("height", height + margin.top + margin.bottom)
                        .append("g")
                        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

                // Create Tooltips
                var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                        .html(function (d) {
                            //console.log(d);
                            var freq = Number(d.value);
                            var content = "<span style='margin-left: 2.5px;'>" + d.regulationType + "</br>" + "Frequency: " + freq + "</span><br>";
                            return content;
                        });
                svg.call(tip);

                var rangesNames = data.map(function (d) {
                    return d.ranges;
                });
                var regulationTypeNames = data[0].values.map(function (d) {
                    return d.regulationType;
                });
                x0.domain(rangesNames);
                x1.domain(regulationTypeNames).rangeRoundBands([0, x0.rangeBand()]);
                y.domain([0, d3.max(data, function (ranges) {
                        return d3.max(ranges.values, function (d) {
                            //console.log(d.value);
                            return d.value;
                        });
                    })]);
                svg.append("g")
                        .attr("class", "x axis")
                        .attr("transform", "translate(0," + height + ")")
                        .call(xAxis);
                svg.append("g")
                        .attr("class", "y axis")
                        .style('opacity', '0')
                        .call(yAxis)
                        .append("text")
                        .attr("transform", "rotate(-90)")
                        .attr("y", -52)
                        .attr("x", 0 - (height / 2))
                        .attr("dy", ".71em")
                        .style("text-anchor", "begin")
                        .style('font-weight', 'bold')
                        .text("Frequency");

                svg.select('.y').transition().duration(500).delay(1300).style('opacity', '1');
                var slice = svg.selectAll(".slice")
                        .data(data)
                        .enter().append("g")
                        .attr("class", "g")
                        .attr("transform", function (d) {
                            return "translate(" + x0(d.ranges) + ",0)";
                        });
                slice.selectAll("rect")
                        .data(function (d) {
                            //console.log(d.values);
                            return d.values;
                        })
                        .enter().append("rect")
                        .attr("width", x1.rangeBand())
                        .attr("x", function (d) {
                            return x1(d.regulationType);
                        })
                        .style("fill", function (d) {
                            return color(d.regulationType)
                        })
                        .attr("y", function (d) {
                            return y(0);
                        })
                        .attr("height", function (d) {
                            return height - y(0);
                        })
                        .on("mouseover", function (d) {
                            d3.select(this).style("fill", d3.rgb(color(d.regulationType)).darker(2));
                        })
                        .on("mouseout", function (d) {
                            d3.select(this).style("fill", color(d.regulationType));
                        })
                        .on('mouseover', tip.show)
                        .on('mouseout', tip.hide);
                slice.selectAll("rect")
                        .transition()
                        .delay(function (d) {
                            return Math.random() * 1000;
                        })
                        .duration(1000)
                        .attr("y", function (d) {
                            // console.log(d.value);
                            return y(d.value);
                        })
                        .attr("height", function (d) {
                            // console.log(d.value);
                            return height - y(d.value);
                        });
                //Legend
                var legend = svg.selectAll(".legend")
                        .data(data[0].values.map(function (d) {
                            return d.regulationType;
                        }).reverse())
                        .enter().append("g")
                        .attr("class", "legend")
                        .attr("transform", function (d, i) {
                            return "translate(0," + i * 20 + ")";
                        })
                        .style("opacity", "0");
                legend.append("rect")
                        .attr("x", width - 18)
                        .attr("width", 18)
                        .attr("height", 18)
                        .style("fill", function (d) {
                            return color(d);
                        });
                legend.append("text")
                        .attr("x", width - 24)
                        .attr("y", 9)
                        .attr("dy", ".35em")
                        .style("text-anchor", "end")
                        .text(function (d) {
                            return d;
                        });
                legend.transition().duration(500).delay(function (d, i) {
                    return 1300 + 100 * i;
                }).style("opacity", "1");
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
