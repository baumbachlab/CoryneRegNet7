<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://d3js.org/d3.v5.min.js"></script>
        <script src="https://unpkg.com/viz.js@1.8.1/viz.js" type="javascript/worker"></script>
        <script src="https://unpkg.com/d3-graphviz@2.1.0/build/d3-graphviz.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CoryneRegNet 7.0</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
    </head>
    <body>

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

        <div class="row font">
            <div class="col-sm-6 bottom-btn" style="padding-left: 0px;">
                <a><button class="btn btn-primary btn-normal" onclick="goBack()">Go Back</button></a>
            </div>
            <div class="col-sm-6 bottom-btn" align="right">
                <a href="downloadFiles.htm?fileName=${cytoscapeFileName}">
                    <button class="btn btn-primary btn-normal">Download files</button>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 font title-network-vis">
                <center><p><i>${o.genera} ${o.species}</i> ${o.strain}</p></center>
            </div>
        </div>
        <div class="row">
            <div id="graph" style="text-align: center;"></div>
        </div>

        <div class="container-fluid font">
            <c:if test="${empty regulationsView}">
                <p class="centered-top">No entries found in the database.</p>
            </c:if>
            <script>
                var regInteractions = [];
                var geneInfoMap = new Map;
                var opInfos = new Map;
                var key;
                var value = {};
                var operons = new Map;
                var nodes = [];
            </script> 

            <c:forEach items="${operons}" var="op">
                <script>
                    //console.log("operons");
                    key = '${op.key}';
                    //console.log("key: " + key);
                    value = '${op.value}';
                    //console.log("value:");
                    //console.log(value);
                    var operonObject = {};
                    operonObject = operonObjectFunction(value);
                    operons.set(key, operonObject);
                    //console.log("operon: ");
                    //console.log(operons.get(key));
                    function operonObjectFunction(value) {
                        var operonAttributes = {locusTag: '', genes: ''};
                        var aux = [];
                        aux = value.split(", ");
                        //console.log("aux: ");
                        //console.log(aux);
                        var attributes = [];
                        var genesAux = [];
                        var locusTagAux = [];
                        var aux1 = [];
                        for (var x = 0; x < aux.length; x++) {
                            //console.log(aux[x]);
                            aux1 = aux[x].split("+");
                            if (x == 0) {
                                attributes = aux1[0].split("[");
                                locusTagAux.push(attributes[1]);
                                genesAux.push(aux1[1]);
                            } else if (x == aux.length - 1) {
                                attributes = aux1[1].split("]");
                                locusTagAux.push(aux1[0]);
                                genesAux.push(attributes[0]);
                            } else {
                                locusTagAux.push(aux1[0]);
                                genesAux.push(aux1[1]);
                            }
                        }
                        operonAttributes.locusTag = locusTagAux;
                        operonAttributes.genes = genesAux;
                        return operonAttributes;
                    }
                </script>
            </c:forEach>

            <c:forEach items="${geneOpInfo}" var="opInf">
                <script>
                    //console.log("opInf");
                    key = '${opInf.key}';
                    value = '${opInf.value}';
                    //console.log("Key: " + key);
                    //console.log("Value: " + value);
                    var opObject = {};
                    opObject = opObjectFunction(value);
                    //console.log("Save opInfos:")
                    //console.log("Key: " + key + " - value: " + opObject.operon);
                    opInfos.set(key, opObject.operon);
                    function opObjectFunction(value) {
                        var opAttributes = {operon: ""};
                        var aux = [];
                        //console.log(value);
                        aux = value.split(", ");
                        //console.log(aux);
                        var attributes = [];
                        //console.log("opAttributes[" + 0 + "]: " + aux[0]);
                        attributes = aux[0].split("[");
                        //console.log("opAttributes[" + 0 + "]: " + attributes[1]);
                        opAttributes.operon = attributes[1];
                        //console.log(opAttributes);
                        return opAttributes;
                    }
                </script>
            </c:forEach>

            <c:forEach items="${regulationsView}" var="ri">

                <script>
                    //Take transcription factor's name or locus_tag
                    //console.log("regulationsView");
                    var regInteraction = [];
                    regInteraction.id = '${ri.id}';
                    regInteraction.tgId = '${ri.targetGene.id}';
                    if ('${ri.targetGene.name}' != "") {
                        regInteraction.tgName = '${ri.targetGene.name}';
                        regInteraction.tgLocusTag = '${ri.targetGene.locusTag}';
                    } else {
                        regInteraction.tgLocusTag = '${ri.targetGene.locusTag}';
                        regInteraction.tgName = '${ri.targetGene.locusTag}';
                    }
                    //console.log("tgLocusTag: " + regInteraction.tgLocusTag);
                    //console.log("tgName: " + regInteraction.tgName);
                    regInteraction.tgAltLocusTag = '${ri.targetGene.alternativeLocusTag}';
                    regInteraction.tgProteinId = '${ri.targetGene.proteinId}';
                    regInteraction.tfId = '${ri.transcriptionFactor.id}';
                    if ('${ri.transcriptionFactor.name}' != "") {
                        regInteraction.tfName = '${ri.transcriptionFactor.name}';
                        regInteraction.tfLocusTag = '${ri.transcriptionFactor.locusTag}';
                    } else {
                        regInteraction.tfName = '${ri.transcriptionFactor.locusTag}';
                        regInteraction.tfLocusTag = '${ri.transcriptionFactor.locusTag}';
                    }

                    if (regInteraction.tfName == "-" || regInteraction.tgName == "-") {

                        //console.log("tfLocusTag: " + regInteraction.tfLocusTag);
                        //console.log("tfName: " + regInteraction.tfName);
                        //console.log("tgLocusTag: " + regInteraction.tgLocusTag);
                        //console.log("tgName: " + regInteraction.tgName);
                    }

                    regInteraction.tfAltLocusTag = '${ri.transcriptionFactor.alternativeLocusTag}';
                    regInteraction.tfProteinId = '${ri.transcriptionFactor.proteinId}';
                    //console.log('${ri.transcriptionFactor.role}');
                    regInteraction.tfRole = '${ri.transcriptionFactor.role}';
                    regInteraction.riRole = '${ri.role}';
                    regInteraction.pValue = '${ri.pValue}';
                    regInteraction.regulatorsDensity = '${ri.regulatorsDensity}';
                    regInteractions.push(regInteraction);
                    //console.log("-------------------------------------");
                </script> 
            </c:forEach>

            <c:forEach items="${nodes}" var="node">
                <script>
                    console.log("nodesView");
                    //console.log('${node}');
                    var nodeAux = [];
                    var aux;
                    var auxAux = [];
                    // console.log('${node}');
                    if ('${node.name}' != "") {
                        auxAux = '${node.name}';
                    } else {
                        auxAux = '${node.locusTag}';
                    }

                    if (auxAux.includes("-")) {
                        for (var j = 0; j < auxAux.length; j++) {
                            if (j == 0 && auxAux[j] != '-') {
                                aux = auxAux[j];
                            } else if (auxAux[j] != '-') {
                                if (aux.length == 0) {
                                    aux = auxAux[j];
                                } else {
                                    aux += auxAux[j];
                                }
                            }
                        }
                        //console.log("tg: " + tg);
                        //console.log("aux: " + aux);
                        auxAux = aux;
                        //console.log("nodeAux.name: " + nodeAux.name);
                        //console.log("tg : " + tg);
                    }

                    nodeAux.name = auxAux;

                    nodeAux.locusTag = '${node.locusTag}';
                    console.log(nodeAux.locusTag);
                    nodeAux.proteinId = '${node.proteinId}';
                    console.log(nodeAux.proteinId);

                    if ('${node.role}' == "") {
                        //console.log("nodeAux.name: " + nodeAux.name);
                        //console.log("target");
                        nodeAux.geneRole = "target";
                    } else if ('${node.role}' == "A") {
                        //console.log("nodeAux.name: " + nodeAux.name);
                        //console.log("Activator");
                        nodeAux.geneRole = "Activator";
                    } else if ('${node.role}' == "R") {
                        //console.log("nodeAux.name: " + nodeAux.name);
                        //console.log("Repressor");
                        nodeAux.geneRole = "Repressor";
                    } else {
                        //console.log("nodeAux.name: " + nodeAux.name);
                        nodeAux.geneRole = '${node.role}';
                        //console.log('${node.role}');
                    }

                    nodes.push(nodeAux);
                    console.log("-------------------------------------");
                </script> 
            </c:forEach>

            <c:set var="countGI" value="${genesInfo.size()}"/>
            <c:forEach var="gi" items="${genesInfo}">
                <script>
                    key = '${gi.key}';
                    value = '${gi.value}';
                    //console.log("Key: " + key);
                    //console.log("Value: " + value);
                    var geneObject = {};
                    geneObject = geneObjectFunction(value);
                    //console.log("Key: " + key + " - value: " + geneObject.locusTag);
                    geneInfoMap.set(key, geneObject);
                    var geneRetriver = {};
                    geneRetriver = geneInfoMap.get(key);
                    //console.log("=========================== " + geneRetriver.locusTag + " " + geneRetriver.name + " *******************");
                    function geneObjectFunction(value) {
                        var geneAttributes = {locusTag: '', name: '', proteinId: '', role: '', operon: ''};
                        var aux = [];
                        //console.log(value);
                        aux = value.split(", ");
                        //console.log(aux);
                        var attributes = [];
                        for (var x = 0; x < aux.length; x++) {
                            //console.log("geneAttributes[" + x + "]: " + aux[x]);
                            attributes = aux[x].split("=");
                            if (attributes[0] == "locusTag") {
                                //console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.locusTag = attributes[1];
                            } else if (attributes[0] == "name") {
                                geneAttributes.name = attributes[1];
                            } else if (attributes[0] == "proteinId") {
                                geneAttributes.proteinId = attributes[1];
                            } else if (attributes[0] == "role") {
                                if (attributes[1] != "null") {
                                    //console.log("NULLLLLLLLLLLLLLLLLLLLL");
                                    geneAttributes.role = attributes[1];
                                }
                            } else if (attributes[0] == "operon") {
                                geneAttributes.operon = attributes[1];
                            }
                        }

                        if (geneAttributes.name == '') {
                            geneAttributes.name = geneAttributes.locusTag;
                        }
                        //console.log(geneAttributes);
                        return geneAttributes;
                    }
                </script>
            </c:forEach>

            <script>

                var regInteraction = [];

                //console.log("nodes");
                //console.log(nodes);

                var tf;
                var tg;
                var edges = [];
                //console.log(regInteractions.length);
                for (i = 0; i < regInteractions.length; i++) {
                    //console.log("tfName: " + regInteractions[i].tfName);
                    //console.log("tgName: " + regInteractions[i].tgName);
                    //console.log("tfRole: " + regInteractions[i].tfRole);
                    tf = regInteractions[i].tfName;
                    tg = regInteractions[i].tgName;

                    //console.log("tfName: " + tf);
                    //console.log("tgName: " + tg);

                    if (tf.includes("-")) {
                        var aux;
                        for (var j = 0; j < tf.length; j++) {
                            if (j == 0 && tf[j] != '-') {
                                aux = tf[j];
                            } else if (tf[j] != '-') {
                                if (aux.length == 0) {
                                    aux = tf[j];
                                } else {
                                    aux += tf[j];
                                }
                            }
                        }
                        //console.log("tg: " + tg);
                        //console.log("aux: " + aux);
                        tf = aux;
                        //console.log("tg : " + tg);
                    }

                    if (tg.includes("-")) {
                        var aux;
                        for (var j = 0; j < tg.length; j++) {
                            if (j == 0 && tg[j] != '-') {
                                aux = tg[j];
                            } else if (tg[j] != '-') {
                                if (aux.length == 0) {
                                    aux = tg[j];
                                } else {
                                    aux += tg[j];
                                }
                            }
                        }
                        //console.log("tg: " + tg);
                        //console.log("aux: " + aux);
                        tg = aux;
                        //console.log("tg : " + tg);
                    }


                    var edgeInfo;
                    //console.log(regInteractions[i].riRole);
                    if (regInteractions[i].riRole == "A") {
                        edgeInfo = "Source: " + tf
                                + "\n" + "Target: " + tg
                                + "\n" + "Role: activator";
                        if (tf == tg) {
                            edges.push(tf + ' -> ' + tg + ' [color="#349834" arrowsize="3" tooltip="' + edgeInfo + '"]');
                        } else {
                            edges.push(tf + ' -> ' + tg + ' [color="#349834" minlen="5" arrowsize="3" tooltip="' + edgeInfo + '"]');
                        }

                    } else if (regInteractions[i].riRole == "R") {
                        edgeInfo = "Source: " + tf
                                + "\n" + "Target: " + tg
                                + "\n" + "Role: repressor";
                        if (tf == tg) {
                            edges.push(tf + ' -> ' + tg + ' [color="#ff6666" arrowsize="3" tooltip="' + edgeInfo + '"]');
                        } else {
                            edges.push(tf + ' -> ' + tg + ' [color="#ff6666" minlen="5" arrowsize="3" tooltip="' + edgeInfo + '"]');
                        }
                    } else {
                        edgeInfo = "Source: " + tf
                                + "\n" + "Target: " + tg
                                + "\n" + "Role: sigma";
                        if (tf == tg) {
                            edges.push(tf + ' -> ' + tg + ' [color="#00b3b3" arrowsize="3" tooltip="' + edgeInfo + '"]');
                        } else {
                            edges.push(tf + ' -> ' + tg + ' [color="#00b3b3" minlen="5" arrowsize="3" tooltip="' + edgeInfo + '"]');
                        }
                    }
                }
                //console.log(edges);
                var dots = [
                    [
                        'digraph "" {',
                        '   layout="neato"',
                        '   overlap="scale"',
                        '   nodesep="1.0"',
                        '   node [style="filled" fontcolor="black" fixedsize="true"]'
                    ]
                ];

                //console.log("nodes.length: " + nodes.length);
                var nodeInfo;
                for (var k = 0; k < nodes.length; k++) {
                    if (nodes[k].geneRole == "target") {
                        nodeInfo = "Locus tag: " + nodes[k].locusTag
                                + "\n" + "Gene name: " + nodes[k].name
                                + "\n" + "Protein id: " + nodes[k].proteinId;
                    } else {
                        nodeInfo = "Locus tag: " + nodes[k].locusTag
                                + "\n" + "Gene name: " + nodes[k].name
                                + "\n" + "Protein id: " + nodes[k].proteinId
                                + "\n" + "Role: " + nodes[k].geneRole;
                    }
                    if (nodes[k].geneRole == "target") {
                        dots[0].push(nodes[k].name + '[fillcolor="gray" fontsize="40" width="3" height="1.5" tooltip="' + nodeInfo + '"]');
                    } else if (nodes[k].geneRole == "Activator") {
                        dots[0].push(nodes[k].name + '[fillcolor="#349834" fontsize="70" width="5" height="3" tooltip="' + nodeInfo + '"]');
                    } else if (nodes[k].geneRole == "Repressor") {
                        dots[0].push(nodes[k].name + '[fillcolor="#ff6666" fontsize="70" width="5" height="3" tooltip="' + nodeInfo + '"]');
                    } else if (nodes[k].geneRole == "Dual") {
                        dots[0].push(nodes[k].name + '[fillcolor="#4d94ff" fontsize="70" width="5" height="3" tooltip="' + nodeInfo + '"]');
                    } else if (nodes[k].geneRole == "Sigma") {
                        dots[0].push(nodes[k].name + '[fillcolor="#00b3b3" fontsize="70" width="5" height="3" tooltip="' + nodeInfo + '"]');
                    } else {
                        console.log("Please check this: ");
                        console.log("k: " + k);
                        console.log(nodes[k]);
                    }
                }

                for (var i = 0; i < edges.length; i++) {
                    dots[0].push(edges[i]);
                }

                dots[0].push('}');
                //console.log(dots);
                var dotIndex = 0;
                var graphviz = d3.select("#graph").graphviz()
                        .attributer(attributer)
                        .transition(function () {
                            return d3.transition("graphviz")
                                    .ease(d3.easeLinear)
                                    .delay(40)
                                    .duration(2000);
                        })
                        .on("initEnd", render);
                function render() {
                    var dotLines = dots[dotIndex];
                    var dot = dotLines.join('');

                    transition1 = d3.transition()
                            .delay(100)
                            .duration(1000);

                    graphviz
                            .dot(dot)
                            .render()
                            .zoom(true)
                            .transition(transition1);

                    dotIndex = (dotIndex + 1) % dots.length;

                    transition1
                            .transition()
                            .duration(0)
                            .on("end", function () {
                                nodes = d3.selectAll('.node,.edge');
                                nodes
                                        .selectAll("g")
                                        .on("click", fieldClickHandler)
                                        .selectAll("a")
                                        // Remove the workaround attributes to avoid consuming the click events
                                        .attr("href", null)
                                        .attr("title", null);
                            });
                }
                function attributer(datum, index, nodes) {
                    var selection = d3.select(this);
                    if (datum.tag == "svg") {
                        var width = window.innerWidth;
                        var height = window.innerHeight;
                        datum.attributes.width = width + 80;
                        datum.attributes.height = height + 80;
                    }
                }

                function resetZoom() {
                    graphviz
                            .resetZoom(d3.transition().duration(1000));
                }
                function resizeSVG() {
                    var width = window.innerWidth;
                    var height = window.innerHeight;
                    var svg = d3.select("#graph").selectWithoutDataPropagation("svg");
                    svg
                            .transition()
                            .duration(700)
                            .attr("width", width + 80)
                            .attr("height", height + 80);
                    var d = svg.datum();
                    d.attributes['width'] = width + 80;
                    d.attributes['height'] = height + 80;
                }
                ;
                d3.select(window).on("resize", resizeSVG);
                d3.select(window).on("click", resetZoom);

                function fieldClickHandler() {
                    var node = d3.select(this);
                    alert("oi");
                }


            </script>
        </div>
    </body>
</html>