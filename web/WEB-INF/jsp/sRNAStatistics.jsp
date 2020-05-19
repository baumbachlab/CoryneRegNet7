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
        <script type="text/javascript" src="js/sRNARegAGene.js"></script>
        <script type="text/javascript" src="https://api.observablehq.com/@jashkenas/my-neat-notebook.js?v=3"></script>
    </head>

    <body style="background-image: url('images/background.png'); background-size: cover;" >

    <nav class="navbar navbar-expand-md navbar-inverse bg-dark fixed-top navbar-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.htm">
                    <span class="color logo-size">Coryne</span>
                    <span class="color-reg logo-size">Reg</span>
                    <span class="color logo-size">Net</span>
                    <span class="color-reg logo-size"> 7</span>
                </a>
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
            <div class="col-sm-12">
                <p>
                    <i class="fa fa-info" style='color:black;'></i>
                    &nbsp;To see statistics per organism click on the graphs below.
                </p>
            </div>
        </div>
        <div class="row" style="font-size: 20px">

            <div class="col-sm-6" style="cursor: pointer;" onclick="window.location = 'quantitiesOfRegulatorAndRegulationTypes.htm?type=${type}';">
                <div class="row">
                    <div class="col-sm-12" style="font-weight: bold">
                        <center>
                            Quantities of regulator types
                            <a tabindex="0" role="button" data-toggle="popover" data-trigger="hover" title="Quantities of sRNA types"
                               data-content="This part shows the quantities of sRNA types i.e. non coding RNA and functional non coding RNA.">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>
                        </center>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" style="padding-top: 10px">
                        <center>
                            <div id="sRNA-types" style="fill: white; font-weight: bold;"></div>
                        </center>
                    </div>
                </div>
            </div>
            <div class="col-sm-6" style="cursor: pointer;" onclick="window.location = 'tFsRegulatingAGene.htm?type=${type}';">
                <div class="row">
                    <div class="col-sm-12" style="font-weight: bold;">
                        <center>
                            Distribuition of sRNAs regulating a gene
                            <a tabindex="1" role="button" data-toggle="popover" data-trigger="hover" title="Distribuition of sRNAs regulating
                               a gene" data-content="This part shows the distribution of the number of sRNAs regulating a gene.">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>

                        </center>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12" style="padding-top: 10px">
                        <center>
                            <div id="sRNA0"></div>  
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
                            <a tabindex="2" role="button" data-toggle="popover" data-trigger="hover" title="Distribution of co-regulating TFs" 
                               data-content="This part shows the distribution of the number of co-regulating transcription factors.">
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
        </div>
    </div>

    <!-- Pie Chart - ncRNA types -->
    <script>

        //Data
        var ncRNA;
        var funcRNA;
        var ncRnaExperimental = parseInt('${ncRnaExperimental}');
        var ncRnaPredicted = parseInt('${ncRnaPredicted}');
        ncRNA = ncRnaExperimental + ncRnaPredicted;
        //console.log("ncRnaExperimental " + ncRnaExperimental);
        funcRnaExperimental = parseInt('${funcRnaExperimental}');
        funcRnaPredicted = parseInt('${funcRnaPredicted}');
        funcRNA = funcRnaExperimental + funcRnaPredicted;
        //console.log("funcRNA: " + funcRNA);
        var ncRNAType = new Map;
        ncRNAType.set(ncRNA, "ncRNA");
        ncRNAType.set(funcRNA, "functional ncRNA");
        var data = [ncRNA, funcRNA];
        var chart_width = 450;
        var chart_height = 300;
        var color = d3.scaleOrdinal(['red', 'blue']);
        //Pie layout
        var pie = d3.pie();
        //Arc
        var outer_radius = (chart_width - 150) / 2;
        var inner_radius = 0;
        var arc = d3.arc()
                .innerRadius(inner_radius)
                .outerRadius(outer_radius);
        //Create SVG element
        var svg = d3.select("#sRNA-types")
                .append("svg")
                .attr("width", chart_width)
                .attr("height", chart_height);
        // Create Tooltips
        var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
                .html(function (d) {
                    if (ncRNAType.get(d.value) == "ncRNA") {
                        var content = "<span style='margin-left: 2.5px;'> ncRNA experimental: " + ncRnaExperimental + "</span><br>\n\
                                       <span style='margin-left: 2.5px;'> ncRNA predicted: " + ncRnaPredicted + "</span><br>";
                    } else {
                        var content = "<span style='margin-left: 2.5px;'> Functional ncRNA experimental: " + funcRnaExperimental + "</span><br>\n\
                                       <span style='margin-left: 2.5px;'> Functional ncRNA predicted: " + funcRnaPredicted + "</span><br>";
                    }
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
                    total = aux[0] + aux[1];
                    //console.log(total);

                    //console.log(d.value);
                    if (d.value == 0) {
                        return "";
                    }
                    return (d.value * 100 / total).toPrecision(3) + "%";
                });
        // define legend
        var regulatorTypes = ["ncRNA", "func. RNA"];
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
                        if (regulatorType == "ncRNA") {
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
                        if (regulatorType == "ncRNA") {
                            return "red";
                        }
                        return "blue";
                    })
                    .style("font-size", "20px");
        });
    </script>

    <!-- Bar Chart - ncRNA reg a gene -->
    <script>
        var sRNARegAGeneShow = [];
        var atLeastEightsRNAs = 0;
    </script>
    <c:forEach items="${sRNARegAGene}" var="sRNA">
        <script>
            var coregulators = [];
            if ('${sRNA.numSrnas}' < 8) {
                if ('${sRNA.numGenes}'.length == 1) {
                    coregulators.genes = "000" + '${sRNA.numGenes}';
                } else if ('${sRNA.numGenes}'.length == 2) {
                    coregulators.genes = "00" + '${sRNA.numGenes}';
                } else if ('${sRNA.numGenes}'.length == 3) {
                    coregulators.genes = "0" + '${sRNA.numGenes}';
                } else {
                    coregulators.genes = '${sRNA.numGenes}';
                }
                coregulators.tfs = '${sRNA.numSrnas}';
                sRNARegAGeneShow.push(coregulators);
            } else {

                if (atLeastEightsRNAs == 0) {
                    atLeastEightsRNAs = '${sRNA.numSrnas}';
                } else {
                    atLeastEightsRNAs = parseInt(atLeastEightsRNAs) + parseInt('${sRNA.numSrnas}');
                }
            }

        </script>

    </c:forEach>
    <script>
        coregulators = [];
        if (atLeastEightsRNAs.length == 1) {
            coregulators.genes = "0000" + atLeastEightsRNAs;
        } else if (atLeastEightsRNAs.length == 2) {
            coregulators.genes = "000" + atLeastEightsRNAs;
        } else if (atLeastEightsRNAs.length == 3) {
            coregulators.genes = "00" + atLeastEightsRNAs;
        } else if (atLeastEightsRNAs.length == 4) {
            coregulators.genes = "0" + atLeastEightsRNAs;
        } else {
            coregulators.genes = atLeastEightsRNAs;
        }
        coregulators.tfs = ">=8";
        sRNARegAGeneShow.push(coregulators);
        console.log(sRNARegAGeneShow);
        var count = 0;
        var maxValue;
        for (var k = 0; k < sRNARegAGeneShow.length; k++) {
            if (k == 0) {
                maxValue = sRNARegAGeneShow[k].genes;
            } else {
                var aux = sRNARegAGeneShow[k].genes;
                maxValue = Math.max(maxValue, aux);
            }
        }

        //console.log(data);
        tfsRegAGene(sRNARegAGeneShow, count, maxValue);
        // Create Tooltips
    </script>

    <!-- Bar Chart - co-regulating TFs -->
    <script>
        var numOfCoregulatorsShow = [];
    </script>

    <c:forEach items="${numOfCoregulators}" var="numOfGeneCoregulators">
        <script>
            var coregulators = [];
            var aux = '${numOfGeneCoregulators}'.split("=");
            //console.log(aux);
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
                    var content = "<span style='margin-left: 2.5px;'>" + "Number of transcription factors: " + tfs + "<br>" +
                            "Number of co-regulators: " + d.coregulators + "</span><br>";
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
    <div class="container-fluid font" style="margin-top: 70px;">

    </div>
</body>
</html>