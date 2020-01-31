<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <script src="//d3js.org/d3.v5.min.js"></script>
        <script src="js/d3-tip.js"></script>
        <script type="text/javascript" src="js/tfsRegAGene.js"></script>
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
                        <a class="nav-link menu-item link-color flex-nowrap" href="#">Statistics</a>
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
                <div class="title-size text-center">Main statistics (${type} database)</div>
            </div>
        </div>
    </div>
    <div class="container-fluid badge badge-light shadow space-to-footer">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="row" style="font-size: 20px">
            <div class="col-sm-12"><p> <i class="fa fa-info" style='color:black;'></i>&nbsp;To see statistics per organism click on the graphs below.</p></div>
        </div>
        <div class="row" style="font-size: 20px">
            <div class="col-sm-6" style="cursor: pointer;" onclick="window.location = 'quantitiesOfRegulatorAndRegulationTypes.htm?type=${type}';">
                <div class="row">
                    <div class="col-sm-12" style="font-weight: bold">
                        <center>
                            Quantities of regulator types
                            <a tabindex="0" role="button" data-toggle="popover" data-trigger="hover" title="Quantities of regulator types" data-content="This part shows the quantities of regulators i.e. activators, repressors and dual regulators, and also the quantities of regulation types i.e. activations and repressions.">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>
                        </center>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" style="padding-top: 10px">
                        <center>
                            <div id="regulator-types" style="fill: white; font-weight: bold;"></div>
                        </center>
                    </div>
                </div>
            </div>
            <div class="col-sm-6" style="cursor: pointer;" onclick="window.location = 'tFsRegulatingAGene.htm?type=${type}';">
                <div class="row">
                    <div class="col-sm-12" style="font-weight: bold;">
                        <center>
                            Distribuition of TFs regulating a gene
                            <a tabindex="1" role="button" data-toggle="popover" data-trigger="hover" title="Distribuition of TFs regulating a gene" data-content="This part shows the distribution of the number of transcription factors regulating a gene.">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>

                        </center>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" style="padding-top: 10px">
                        <center>
                            <div id="regulators0"></div>  
                        </center>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="font-size: 20px; margin: 50px 0px 50px 0px">
            <div class="col-sm-6" style="cursor: pointer;" onclick="window.location = 'coregulatorsStatistics.htm?type=${type}';">
                <div class="row">
                    <div class="col-sm-12" style="font-weight: bold">
                        <center>
                            Distribution of co-regulating TFs
                            <a tabindex="2" role="button" data-toggle="popover" data-trigger="hover" title="Distribution of co-regulating TFs" data-content="This part shows the distribution of the number of co-regulating transcription factors.">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>
                        </center>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" style="padding-top: 10px">
                        <center>
                            <div id="co-regulators"></div>
                        </center>
                    </div>
                </div>
            </div>
            <div class="col-sm-6" style="cursor: pointer;" onclick="window.location = 'hmmProfLenStatistics.htm?type=${type}';">
                <div class="row">
                    <div class="col-sm-12" style="font-weight: bold;">
                        <center>
                            Distribution of HMM profiles lengths
                            <a tabindex="3" role="button" data-toggle="popover" data-trigger="hover" title="Distribution of HMM profiles lengths" data-content="This part shows the distribution of HMM profiles lengths.">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>
                        </center>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" style="padding-top: 10px">
                        <center>
                            <div id="profile-len"></div>  
                        </center>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--        <div class="row" style="font-size: 20px; margin: 50px 0px 120px 0px">
                <div class="col-sm-6" style="cursor: pointer;" onclick="window.location = 'distanceFromStartGene.htm?type=${type}';">
                    <div class="row">
                        <div class="col-sm-12" style="font-weight: bold">
                            Distribution of TFBS distance from gene start
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-5 col-sm-6 col-8">
                            <div id="distences"></div>
                        </div>
                        <div id="distances-text" class="col-lg-7 col-sm-6 col-4 statistics-padding">
                            <p>This part shows the distribution of transcription factors binding site distances from gene start. It is represented in form of a diagram and two histograms that show activator and repressor binding site distances separate.</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="row">
                        <div class="col-sm-12" style="font-weight: bold;">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-5 col-sm-6 col-8">
                            <div id="waiting-graph"></div>  
                        </div>
                        <div id="waiting-graph-text" class="col-md-7 col-sm-6 col-4 statistics-padding">
                            <p></p>
                        </div>
                    </div>
                </div>
            </div>
</div>-->

    <!-- Pie Chart - Regulator types -->
    <script>

        //Data
        var activators;
        var repressors;
        var duals;
        activators = parseInt('${numOfActivators}');
        repressors = parseInt('${numOfRepressors}');
        duals = parseInt('${numOfDuals}');
        var regulatorType = new Map;

        regulatorType.set(repressors, "Repressors");
        regulatorType.set(activators, "Activators");
        regulatorType.set(duals, "Dual regulators");
        var data = [repressors, activators, duals];

        //var perOfActivators = ('${numOfActivators}' * 100) / '${totalNumOfTfs}';
        //var perOfRepressors = ('${numOfRepressors}' * 100) / '${totalNumOfTfs}';
        //var perOfDuals = ('${numOfDuals}' * 100) / '${totalNumOfTfs}';
        //var data = [perOfActivators, perOfRepressors, perOfDuals];
        var chart_width = 450;
        var chart_height = 300;
        var color = d3.scaleOrdinal(['red', 'green', 'blue']);
        //Pie layout
        var pie = d3.pie();
        //Arc
        var outer_radius = (chart_width - 150) / 2;
        var inner_radius = 0;
        var arc = d3.arc()
                .innerRadius(inner_radius)
                .outerRadius(outer_radius);
        //Create SVG element
        var svg = d3.select("#regulator-types")
                .append("svg")
                .attr("width", chart_width)
                .attr("height", chart_height);

        // Create Tooltips
        var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                .html(function (d) {
                    //console.log("hdhd");
                    //console.log(d);
                    //console.log(data);
                    var content = "<span style='margin-left: 2.5px;'>" + regulatorType.get(d.value) + ": " + d.value + "</span><br>";
                    return content;
                });

        svg.call(tip);

        //Groups
        var arcs = svg.selectAll('g.arc')
                .data(pie(data))
                .enter()
                .append('g')
                .attr('class', 'arc')
                .attr(
                        'transform',
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

    </script>

    <!-- Bar Chart - TFs reg a gene -->
    <script>
        var tfsRegAGeneShow = [];
        var atLeastSixTFs = 0;
    </script>

    <c:forEach items="${tfsRegAGene}" var="tfsRegGene">
        <script>
            //console.log("inicio")
            //console.log('${tfsRegGene}');
            var coregulators = [];
            var aux = '${tfsRegGene}'.split("=");
            //console.log(aux)
            if (aux[0] < 6) {
                if (aux[1].length == 1) {
                    coregulators.genes = "0000" + aux[1];
                } else if (aux[1].length == 2) {
                    coregulators.genes = "000" + aux[1];
                } else if (aux[1].length == 3) {
                    coregulators.genes = "00" + aux[1];
                } else if (aux[1].length == 4) {
                    coregulators.genes = "0" + aux[1];
                } else {
                    coregulators.genes = aux[1];
                }
                coregulators.tfs = aux[0];
                //console.log(coregulators);
                tfsRegAGeneShow.push(coregulators);
                //console.log(tfsRegAGeneShow);
            } else {

                //console.log("--------------------------");
                //console.log("atLeastSixTFs: " + atLeastSixTFs + " + aux[1]:" + aux[1]);
                if (atLeastSixTFs == 0) {
                    atLeastSixTFs = aux[1];
                } else {
                    atLeastSixTFs = parseInt(atLeastSixTFs) + parseInt(aux[1]);
                }
                //console.log("atLeastSixTFs: " + atLeastSixTFs);
                //console.log("--------------------------");
            }
        </script>

    </c:forEach>
    <script>
        coregulators = [];

        if (atLeastSixTFs.length == 1) {
            coregulators.genes = "0000" + atLeastSixTFs;
        } else if (atLeastSixTFs.length == 2) {
            coregulators.genes = "000" + atLeastSixTFs;
        } else if (atLeastSixTFs.length == 3) {
            coregulators.genes = "00" + atLeastSixTFs;
        } else if (atLeastSixTFs.length == 4) {
            coregulators.genes = "0" + atLeastSixTFs;
        } else {
            coregulators.genes = atLeastSixTFs;
        }
        //coregulators.genes = atLeastSixTFs;
        coregulators.tfs = ">=6";
        tfsRegAGeneShow.push(coregulators);
        //console.log(tfsRegAGeneShow);
        //console.log(":)");
        //console.log(tfsRegAGeneShow);
        var count = 0;
        //console.log("count: " + count);
        // console.log("Bar chart start!!!!!!");
        // console.log(tfsRegAGeneShow.get(organisms[i]));

        var maxValue;
        //var tfsRegAGene2 = [];
        //console.log(tfsRegAGeneShow.length);
        for (var k = 0; k < tfsRegAGeneShow.length; k++) {
            //console.log("k: " + k);
            //console.log("tfsRegAGeneShow[" + k + "].genes: " + tfsRegAGeneShow[k].genes);
            //console.log("tfsRegAGeneShow[" + k + "].tfs: " + tfsRegAGeneShow[k].tfs);
            //var tfsRegAGeneAux = [];

            if (k == 0) {
                maxValue = tfsRegAGeneShow[k].genes;
                //console.log("maxValue: " + maxValue);
            } else {
                var aux = tfsRegAGeneShow[k].genes;
                //console.log("maxValue < aux? " + maxValue + " < " + aux);
                maxValue = Math.max(maxValue, aux);
                //console.log("new maxValue: " + maxValue);
            }
            //tfsRegAGeneAux.genes = tfsRegAGeneShow[k].genes;
            //tfsRegAGeneAux.tfs = tfsRegAGeneShow[k].tfs;
            //console.log(tfsRegAGeneAux);
            //tfsRegAGene2.push(tfsRegAGeneAux);
            //console.log(tfsRegAGene2);
        }

        //console.log("maxValue: " + maxValue);

        //var data = tfsRegAGeneShow;

        //for (var k = 0; k < tfsRegAGeneShow.length; k++) {
        // console.log("k: " + k);
        //console.log("tfsRegAGeneShow[" + k + "].genes: " + tfsRegAGeneShow[k].genes);
        //  console.log("tfsRegAGeneShow[" + k + "].tfs: " + tfsRegAGeneShow[k].tfs);
        //}


        //console.log(data);
        tfsRegAGene(tfsRegAGeneShow, count, maxValue);
        // Create Tooltips
    </script>

    <!-- Bar Chart - co-regulating TFs -->
    <script>
        var numOfCoregulatorsShow = [];
    </script>

    <c:forEach items="${numOfCoregulators}" var="numOfGeneCoregulators">
        <script>
            //  console.log("inicio")
            //console.log('${numOfGeneCoregulators}');
            var coregulators = [];
            var aux = '${numOfGeneCoregulators}'.split("=");
            console.log(aux);

            if (aux[0] == 0) {
                coregulators.coregulators = "0-9";
            } else if (aux[0] == 1) {
                coregulators.coregulators = "10-19";
            } else if (aux[0] == 2) {
                coregulators.coregulators = "20-29";
            } else if (aux[0] == 3) {
                coregulators.coregulators = "30-39";
            } else if (aux[0] == 4) {
                coregulators.coregulators = "40-49";
            } else if (aux[0] == 5) {
                coregulators.coregulators = "50-59";
            } else if (aux[0] == 6) {
                coregulators.coregulators = "60-69";
            } else {
                coregulators.coregulators = ">=70"
            }

            if (aux[1].length == 1) {
                coregulators.tfs = "00" + aux[1];
            } else if (aux[1].length == 2) {
                coregulators.tfs = "0" + aux[1];
            } else {
                coregulators.tfs = aux[1];
            }
            //coregulators.tfs = aux[1];

            //console.log(coregulators);
            numOfCoregulatorsShow.push(coregulators);
            //console.log(numOfCoregulatorsShow);
        </script>
    </c:forEach>

    <script>
//        console.log(numOfCoregulatorsShow);
        var margin = {left: 80, right: 4.17, top: 6.17, bottom: 60};
        var width = 500;
        var height = 300 - margin.top - margin.bottom;
        var g = d3.select('#co-regulators')
                .append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + ", "
                        + margin.top + ")");

        var data = numOfCoregulatorsShow;
        //console.log(data);

        // Create Tooltips
        var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                .html(function (d) {
                    var tfs = Number(d.tfs);
                    var content = "<span style='margin-left: 2.5px;'>" + "Number of transcription factors: " + tfs + "<br>" + "Number of co-regulators: " + d.coregulators + "</span><br>";
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
                .ticks(5)
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
                .text("Number of TFs");
        var rectangle = g.selectAll("rect")
                .data(data);
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
    </script>

    <!-- Bar Chart - HMM profiles lengths -->
    <script>
        var profileLenShow = [];
    </script>

    <c:forEach items="${hmmProfilesLen}" var="hmmProfileLen">
        <script>
            var profileLen = [];
            var aux = '${hmmProfileLen}'.split("=");
            console.log("hmmProfLen:");
            console.log(aux);
            if (aux[1].length == 1) {
                profileLen.freq = "00" + aux[1];
            } else if (aux[1].length == 2) {
                profileLen.freq = "0" + aux[1];
            } else {
                profileLen.freq = aux[1];
            }
            if (aux[0] == 0) {
                profileLen.profileLen = "5-12";
            } else if (aux[0] == 1) {
                profileLen.profileLen = "13-20";
            } else if (aux[0] == 2) {
                profileLen.profileLen = "21-28";
            } else if (aux[0] == 3) {
                profileLen.profileLen = "29-36";
            } else if (aux[0] == 4) {
                profileLen.profileLen = "37-45";
            } else if (aux[0] == 5) {
                profileLen.profileLen = "46-59";
            } else {
                profileLen.profileLen = ">=60"
            }

            //    console.log(profileLen);
            profileLenShow.push(profileLen);
            //  console.log(profileLenShow);
        </script>
    </c:forEach>

    <script>
//        console.log(profileLenShow);
        var margin = {left: 80, right: 4.17, top: 6.17, bottom: 60};
        var width = 500;
        var height = 300 - margin.top - margin.bottom;
        var g = d3.select('#profile-len')
                .append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + ", "
                        + margin.top + ")");

        var data = profileLenShow;

        // Create Tooltips
        var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                .html(function (d) {
                    var freq = Number(d.freq);
                    var content = "<span style='margin-left: 2.5px;'>" + "Frequency: " + freq + "<br>" + "HMM Profiles Length: " + d.profileLen + "</span><br>";
                    return content;
                });
        g.call(tip);


        var x = d3.scaleBand()
                .domain(data.map(function (d) {
                    return d.profileLen;
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
                .ticks(5)
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
                .text("HMM profiles lengths");
        //y label
        g.append("text")
                .attr("class", "y axis-label")
                .attr("x", -(height / 2))
                .attr("y", -65)
                .attr("font-size", "20px")
                .attr("text-anchor", "middle")
                .attr("transform", "rotate(-90)")
                .text("Frequency");
        var rectangle = g.selectAll("rect")
                .data(data);
        rectangle.enter()
                .append("rect")
                .attr("y", function (d) {
                    return y(d.freq);
                })
                .attr("x", function (d) {
                    return x(d.profileLen);
                })
                .attr("width", x.bandwidth)
                .attr("height", function (d) {
                    return height - y(d.freq);
                })
                .attr("fill", "grey")
                .on('mouseover', tip.show)
                .on('mouseout', tip.hide);
    </script>

    <!-- Bar Chart - distribution of TFBS distances from gene start. -->
    <script>
        var distanceShow = [];
    </script>

    <c:forEach items="${distancesFromStartSite}" var="distanceFromStartSite">
        <script>
            //          console.log("distanceFromStartSite - inicio")
            //        console.log('${distanceFromStartSite}');
            var distance = [];
            var aux = '${distanceFromStartSite}'.split("=");
            //      console.log(aux);
            if (aux[1].length == 1) {
                distance.freq = "00" + aux[1];
            } else if (aux[1].length == 2) {
                distance.freq = "0" + aux[1];
            } else {
                distance.freq = aux[1];
            }
            if (aux[0] <= 1) {
                distance.distance = "-20-95";
            } else if (aux[0] <= 3) {
                distance.distance = "96-211";
            } else if (aux[0] <= 5) {
                distance.distance = "212-327";
            } else if (aux[0] <= 7) {
                distance.distance = "328-443";
            } else if (aux[0] <= 9) {
                distance.distance = "444-560";
            }

            //    console.log(distance);
            distanceShow.push(distance);
            //  console.log(distanceShow);
        </script>
    </c:forEach>

    <script>
//        console.log(distanceShow);
        var margin = {left: 50, right: 4.17, top: 6.17, bottom: 60};
        var width = 300 - margin.left - margin.right;
        var height = 300 - margin.top - margin.bottom;
        var g = d3.select('#distences')
                .append("svg")
                .attr("width", width + margin.left + margin.right)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + ", "
                        + margin.top + ")");

        var data = distanceShow;


        var x = d3.scaleBand()
                .domain(data.map(function (d) {
                    return d.distance;
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
                .ticks(5)
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
                .text("Distance from gene start");
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
        rectangle.enter()
                .append("rect")
                .attr("y", function (d) {
                    return y(d.freq);
                })
                .attr("x", function (d) {
                    return x(d.distance);
                })
                .attr("width", x.bandwidth)
                .attr("height", function (d) {
                    return height - y(d.freq);
                })
                .attr("fill", "grey");
    </script>

    <div class="container-fluid font" style="margin-top: 70px;">
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