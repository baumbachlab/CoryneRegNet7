<%-- 
    Document   : visualizationNetworkWithCombinedOperons
    Created on : Feb 8, 2019, 11:59:53 AM
    Author     : doglas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CoryneRegNet 7.0</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/networkVisualization.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vis/4.21.0/vis.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/vis/4.21.0/vis.css" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
        <style>
            .loader {
                border: 7px dotted #333;
                border-radius: 50%;
                border-top: 7px dotted #333;
                width: 50px;
                height: 50px;
                -webkit-animation: spin 4s linear infinite; /* Safari */
                animation: spin 4s linear infinite;
            }

            /* Safari */
            @-webkit-keyframes spin {
                0% { -webkit-transform: rotate(0deg); }
                100% { -webkit-transform: rotate(360deg); }
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>

        <style type="text/css">
            #loadingBar {
                position:absolute;
                top:0px;
                margin: auto;
                -webkit-transition: all 0.5s ease;
                -moz-transition: all 0.5s ease;
                -ms-transition: all 0.5s ease;
                -o-transition: all 0.5s ease;
                transition: all 0.5s ease;
                opacity:1;
            }
            #wrapper {
                position:relative;
            }
            #text {
                position:absolute;
                top:8px;
                left:530px;
                width:30px;
                height:50px;
                margin:auto auto auto auto;
                font-size:22px;
                color: #000000;
            }
            div.outerBorder {
                position:relative;
                top:250px;
                width:600px;
                height:58px;
                margin:auto auto auto auto;
                border:8px solid rgba(0,0,0,0.1);
                background: rgb(252,252,252); /* Old browsers */
                background: -moz-linear-gradient(top,  rgba(252,252,252,1) 0%, rgba(237,237,237,1) 100%); /* FF3.6+ */
                background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(252,252,252,1)), color-stop(100%,rgba(237,237,237,1))); /* Chrome,Safari4+ */
                background: -webkit-linear-gradient(top,  rgba(252,252,252,1) 0%,rgba(237,237,237,1) 100%); /* Chrome10+,Safari5.1+ */
                background: -o-linear-gradient(top,  rgba(252,252,252,1) 0%,rgba(237,237,237,1) 100%); /* Opera 11.10+ */
                background: -ms-linear-gradient(top,  rgba(252,252,252,1) 0%,rgba(237,237,237,1) 100%); /* IE10+ */
                background: linear-gradient(to bottom,  rgba(252,252,252,1) 0%,rgba(237,237,237,1) 100%); /* W3C */
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fcfcfc', endColorstr='#ededed',GradientType=0 ); /* IE6-9 */
                border-radius:72px;
                box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
            }
            #border {
                position:absolute;
                top:10px;
                left:10px;
                width:500px;
                height:23px;
                margin:auto auto auto auto;
                box-shadow: 0px 0px 4px rgba(0,0,0,0.2);
                border-radius:10px;
            }
            #bar {
                position:absolute;
                top:0px;
                left:0px;
                width:20px;
                height:20px;
                margin:auto auto auto auto;
                border-radius:11px;
                border:2px solid rgba(30,30,30,0.05);
                background: rgb(0, 173, 246); /* Old browsers */
                box-shadow: 2px 0px 4px rgba(0,0,0,0.4);
            }
        </style>
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

        <div id="first-div" class="row font">
            <div class="col-sm-12 bottom-btn" style="padding-left: 0px;">
                <c:choose>
                    <c:when test="${typeOfGoBack eq 'normal'}">
                        <c:choose>
                            <c:when test="${goBackTo eq 'experimental'}">
                                <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">Go Back</button></a>
                            </c:when>
                            <c:when test="${goBackTo eq 'predicted'}">
                                <a href="searchPredictedData.htm"><button type="button" class="btn btn-primary btn-normal">Go Back</button></a>
                            </c:when>
                            <c:when test="${goBackTo eq 'organism'}">
                                <a href="organismInfo.htm?id=${o.id}&type=${type}"><button type="button" class="btn btn-primary btn-normal">Go Back</button></a>
                            </c:when>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${type eq 'experimental'}">
                                <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                            </c:when>
                            <c:otherwise>
                                <a href="searchPredictedData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <a href="downloadFiles.htm?fileName=${cytoscapeFileName}">
                    <button class="btn btn-primary btn-normal">Download network file</button>
                </a>
            </div>
        </div>

        <div id="second-div" class="row">
            <div class="col-md-12 font title-network-vis">
                <center>
                    <span id="organism-name"><i>${o.genera} ${o.species}</i> ${o.strain}</span>
                </center>
            </div>
        </div>

        <div id="wrapper" class="row" style="margin-top: 20px; padding-left: 0px; padding-right: 0px;">
            <div id="side-div" class="col-md-2">
                <div class="col-md-12" style="font-size: 22px;">
                    <span>How to explore the network&nbsp;</span>
                    <a href="#" data-toggle="modal" data-target="#helpModal">
                        <i class="fa fa-question-circle" style="color:black; text-rendering: optimizeLegibility;"></i>
                    </a>
                </div>
                <div class="col-md-12" style="font-size: 22px; margin-top: 20px;">
                    <span>Network Layout:</span>&nbsp;
                    <a href="#" data-toggle="modal" data-target="#helpLayoutModal">
                        <i class="fa fa-question-circle" style="color:black; text-rendering: optimizeLegibility;"></i>
                    </a>

                    <br>
                    <input type="radio" name="op" id="noOP" value="0" onchange="noOperon()" checked> Genes<br>
                    <input type="radio" name="op" id="opCo" value="1" onchange="operonsCombined()"> Operons<br>
                </div>
                <div class="col-md-12 font" style="margin-top: 20px;">
                    <button id="stop-layout-button" class="btn btn-primary btn-normal" onclick="layoutStatus()">Improve Layout</button>
                </div>
            </div>
            <div id="net-div" class="col-md-10">
                <div id="no-operons"></div>
                <div id="operons-combined" style="display: none;"></div>
                <div id="loadingBar">
                    <div id="loader-id" class="outerBorder">
                        <div id="text">0%</div>
                        <div id="border">
                            <div id="bar"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            var screenWidth = $(window).width();
            //var loaderPosition = (screenWidth / 2) -300; To be in the center of the entire screen
            //           var sideDivWidth = document.getElementById('side-div').clientWidth;
            //         var netDivWidth = document.getElementById('net-div').clientWidth;
            if (screenWidth > 1500) {
                var loaderPosition = (screenWidth / 2) - 600;
            } else {
                var loaderPosition = (screenWidth / 2) - 450;
            }
//            console.log("sideDivWidth: " + sideDivWidth);
            //           console.log("netDivWidth: " + netDivWidth);
            //         console.log("screenWidth: " + screenWidth);
            //       console.log("loaderPosition: " + loaderPosition);
            document.getElementById('loader-id').style.marginLeft = loaderPosition + "px";
        </script>

        <!-- ModalHelp Layout--  helpLayoutModal  -->
        <div class="modal fade" id="helpLayoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="exampleModalLabel">Network layout types:</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body" style="font-size: 22px;">

                        <ul>
                            <li><b>Genes:</b> It shows the genes individually</li>


                            <li><b>Operons:</b> It shows the genes that belongs to an operon in a unique node in yellow</li>


                        </ul>

                        <img class="img-fluid" src="images/Layouts.png" alt="Layouts"> 

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <!-- Modal - Help -->
        <div class="modal fade" id="helpModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="exampleModalLabel">How to explore the network?</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body" style="font-size: 22px;">
                        <p><b>Zoom:</b> scroll mouse</p>
                        <p><b>Network Layout:</b></p>
                        <ul>
                            <li><b>Genes:</b> It shows the genes individually</li>

                            <li><b>Operons:</b> It shows the genes that belongs to an operon in a unique node in yellow</li>

                        </ul>
                        <p><b>Show node & edge information:</b></p>
                        <ul>
                            <li><b>A gene or an operon:</b> click in the correspondent node. It shows the locus tag (gene id), name, protein id, regulator role
                                (if a TF) or the genes that belong the operon (if it is an operon). Additionally, it shows a "Network Visualization" button, 
                                it opens a new tab showing the regulations of the selected gene or operon.</li>

                            <li><b>A regulation:</b> click in the correspondent link. It shows the source (TF) or operon, target (TG) or operon and the regulation role</li>
                        </ul>
                        <p><b>Improve Layout:</b> It automatically try to improve the layout</p>
                        <p><b>Stop Layouting:</b> It stops layouting and the user can manually modify the layout</p>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="geneModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="exampleModalLabel">Gene Information</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="gene-modal-content" style="font-size: 22px;">
                        <p>Something went wrong. No info to show!</p>
                    </div>
                    <div class="modal-footer">
                        <form action="networkDinamicVisualizationOperons.htm" target="_blank">
                            <input type="hidden" id="locus-for-network" name="interestGenes" value="">
                            <input type="hidden" id="organism-for-network" name="organism" value="${o.id}">
                            <input type="hidden" id="search-type" name="searchType" value="${type}">
                            <input type="submit" class="btn btn-secondary left" value="Network Visualization" />

                        </form>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="riModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="exampleModalLabel">Regulatory Interaction Information</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="ri-modal-content" style="font-size: 22px;">
                        <p>Something went wrong. No info to show!</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid font">
            <c:if test="${empty regulationsView}">
                <p class="centered-top">No entries found in the database.</p>
            </c:if>
            <script>
                var regInteractions = [];
                var geneInfoMap = new Map;
                var regInteractionsCombined = [];
                //var createdOperons = [];
                var geneInfoMapCombined = new Map;
                var opInfos = new Map;
                var key;
                var value = {};
                var operons = new Map;
            </script> 

            <c:forEach items="${operons}" var="op">
                <script>
                    //console.log("operons");
                    key = '${op.key}';
                    //console.log("key: " + key);
                    value = '${op.value}';
                    //console.log("value:");
                    //console.log(value);
                    //console.log("-----------------------------------");
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
                            //console.log("operons - aux:");                
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

            <c:forEach var="gi" items="${genesInfo}">
                <script>
                    //console.log("genesInfo");
                    key = '${gi.key}';
                    value = '${gi.value}';
                    //console.log("Key: " + key);
                    //console.log("Value: " + value);
                    //console.log("<br>");

                    //GeneInfo for gene based layout
                    var geneObjectGeneVis = {};
                    geneObjectGeneVis = geneObjectGeneVisFunction(value);
                    //console.log("Key: " + key + " - value: " + geneObject.locusTag);
                    geneInfoMap.set(key, geneObjectGeneVis);
                    //var geneRetriver = {};
                    //geneRetriver = geneInfoMap.get(key);
                    //console.log("=========================== " + geneRetriver.locusTag + " " + geneRetriver.name + " *******************");
                    function geneObjectGeneVisFunction(value) {
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
                                //console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.name = attributes[1];
                            } else if (attributes[0] == "proteinId") {
                                //console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.proteinId = attributes[1];
                            } else if (attributes[0] == "role") {
                                if (attributes[1] != "null") {
                                    if (attributes[1] != "") {
                                        //console.log("role: " + attributes[1]);
                                        geneAttributes.role = attributes[1];
                                    } else {
                                        //console.log("Role is empty!!!!!!!!!");
                                        geneAttributes.role = "unknown";
                                    }
                                }
                            } else if (attributes[0] == "operon") {
                                //console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.operon = attributes[1];
                            }
                        }

                        if (geneAttributes.name == '') {
                            geneAttributes.name = geneAttributes.locusTag;
                        }
                        //console.log(geneAttributes);
                        return geneAttributes;
                    }
                    //FIM gene based layout

                    //GeneInfo for operon based layout
                    var geneObjectOpVis = {};
                    geneObjectOpVis = geneObjectOperonsVisualizationFunction(value);
                    if (geneObjectOpVis.operon != "") {
                        opInfos.set(key, geneObjectOpVis.operon);
                    }
                    //console.log("geneObject:");
                    //console.log(geneObject);
                    if (!geneInfoMapCombined.get(geneObjectOpVis.operon)) {
                        //console.log("Não está em um operon ou é um novg.o operon:");
                        //console.log(geneInfoMapCombined.get(geneObject.operon));
                        if (geneObjectOpVis.pos == "0") {
                            //console.log("Não está em um operon:");
                            //console.log("Key: " + key + " - value: " + geneObject.name);
                            geneInfoMapCombined.set(key, geneObjectOpVis);
                        } else {
                            //console.log("-----------------");
                            //console.log(geneObjectOpVis);
                            //console.log(geneObjectOpVis.operon);
                            var belongToOperon = operons.get(geneObjectOpVis.operon);
                            //console.log(belongToOperon);
                            //for (var i = 0; i < belongToOperon.genes.length; i++) {
                            //console.log("gene: " + belongToOperon.genes[i]);
                            //}
                            //console.log("geneObject:");
                            //console.log(geneObject);
                            geneObjectOpVis.locusTag = geneObjectOpVis.operon;
                            geneObjectOpVis.name = geneObjectOpVis.operon;
                            geneObjectOpVis.proteinId = "-";
                            geneObjectOpVis.operon = "operon";
                            geneObjectOpVis.genes = belongToOperon.genes;
                            var newkey = geneObjectOpVis.name;
                            //console.log("Está em um operon:");
                            //console.log("Key: " + key + " - value: " + geneObject.locusTag);
                            //console.log(geneObject.genes);
                            //createdOperons.push(geneObject.operon);

                            geneInfoMapCombined.set(newkey, geneObjectOpVis);
                        }
                    }

                    //console.log("----------------------------");

                    //console.log("=========================== " + geneRecover.locusTag + " " + geneRecover.name + " *******************");
                    function geneObjectOperonsVisualizationFunction(value) {
                        var geneAttributes = {locusTag: '', name: '', proteinId: '', role: '', operon: '', pos: '', genes: '-'};
                        var aux = [];
                        //console.log(value);
                        aux = value.split(", ");
                        //console.log(aux);
                        var attributes = [];
                        for (var x = 0; x < aux.length; x++) {
                            //console.log("geneAttributes[" + x + "]: " + aux[x]);
                            attributes = aux[x].split("=");
                            //console.log("geneAttributes[" + x + "]: " + attributes[0]);
                            //console.log("geneAttributes[" + x + "]: " + attributes[1]);
                            if (attributes[0] == "locusTag") {
                                //  //console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.locusTag = attributes[1];
                            } else if (attributes[0] == "name") {
                                //console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.name = attributes[1];
                            } else if (attributes[0] == "proteinId") {
                                //console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.proteinId = attributes[1];
                            } else if (attributes[0] == "role") {
                                if (attributes[1] != "null") {
                                    //console.log("NULLLLLLLLLLLLLLLLLLLLL");
                                    if (attributes[1] != "") {
                                        //console.log("role: " + attributes[1]);
                                        geneAttributes.role = attributes[1];
                                    } else {
                                        //console.log("Role is empty!!!!!!!!!");
                                        geneAttributes.role = "unknown";
                                    }
                                }
                            } else if (attributes[0] == "operon") {
                                // console.log(attributes[0] + " = " + attributes[1]);
                                geneAttributes.operon = attributes[1];
                            } else if (attributes[0] == "pos") {
                                var pos = attributes[1].split("}");
                                geneAttributes.pos = pos[0];
                            }
                        }

                        if (geneAttributes.name == "") {
                            //    console.log(geneAttributes.locusTag);
                            geneAttributes.name = geneAttributes.locusTag;
                        }
                        //console.log(geneAttributes);
                        return geneAttributes;
                    }
                    //FIM operon based layout
                </script>
            </c:forEach>

            <c:forEach var="srnai" items="${srnasInfo}">
                <script>
                    //console.log("genesInfo");
                    key = '${srnai.key}';
                    value = '${srnai.value}';
                    console.log("Key: " + key);
                    //console.log("Value: " + value);
                    //console.log("<br>");

                    //GeneInfo for gene based layout
                    var geneObjectGeneVis = {};
                    geneObjectGeneVis = geneObjectGeneVisFunction(value);
                    //console.log("Key: " + key + " - value: " + geneObject.locusTag);
                    var srnaAttributes = {locusTag: key, name: key, proteinId: '', role: 'X', operon: ''};
                    //console.log("srnaAttributes.locusTag: " + srnaAttributes.locusTag)
                    geneInfoMap.set(key, srnaAttributes);
                    //FIM gene based layout

                </script>
            </c:forEach>

            <c:forEach items="${regulationsView}" var="ri">
                <script>
                    //Take transcription factor's name or locus_tag
                    //console.log("ricomb");
                    var regInteraction = [];
                    var tg = "";
                    var tf = "";
                    if ('${ri.targetGene.name}' != "") {
                        tg = opInfos.get('${ri.targetGene.name}');
                        //if (tg != null) {
                        //  console.log("Tg: " + '${ri.targetGene.name}');
                        //console.log("Tg: " + tg);
                        //console.log("opInfosTg");
                        //console.log(opInfos.get('${ri.targetGene.name}'));
                        //} else {
                        //  console.log("<br>");
                        //console.log("Tg: " + '${ri.targetGene.name}');
                        // console.log("Non tem operon");
                        //}
                    } else {
                        tg = opInfos.get('${ri.targetGene.locusTag}');
                        //if (tg != null) {
                        //console.log("Tg: " + '${ri.targetGene.locusTag}');
                        //console.log("Tg: " + tg);
                        //console.log("opInfos");
                        //console.log(opInfos.get('${ri.targetGene.locusTag}'));
                        //} else {
                        //console.log("<br>");
                        //console.log("Tg: " + '${ri.targetGene.locusTag}');
                        //console.log("Non tem operon");
                        //}
                    }

                    if ('${ri.transcriptionFactor.name}' != "") {
                        tf = opInfos.get('${ri.transcriptionFactor.name}');
                        //if (tf != null) {
                        //console.log("Tf: " + '${ri.transcriptionFactor.name}');
                        //console.log("Tf: " + tf);
                        //console.log("opInfos");
                        //console.log(opInfos.get('${ri.transcriptionFactor.name}'));
                        //} else {
                        //  console.log("<br>");
                        //console.log("Tf: " + '${ri.transcriptionFactor.name}');
                        // console.log("Non tem operon");
                        //}
                    } else {
                        tf = opInfos.get('${ri.transcriptionFactor.locusTag}');
                        //if (tf != null) {
                        //  console.log("Tf: " + '${ri.transcriptionFactor.locusTag}');
                        //  console.log("Tf: " + tf);
                        //  console.log("opInfos");
                        //  console.log(opInfos.get('${ri.transcriptionFactor.locusTag}'));
                        //} else {
                        //  console.log("<br>");
                        //  console.log('${ri.transcriptionFactor.locusTag}');
                        //  console.log("Non tem operon");
                        //}
                    }

                    regInteraction.id = '${ri.id}';
                    if (tg != null) {
                        regInteraction.tgName = tg;
                        regInteraction.tgLocusTag = tg;
                    } else {
                        if ('${ri.targetGene.name}' != "") {
                            regInteraction.tgName = '${ri.targetGene.name}';
                            regInteraction.tgLocusTag = '${ri.targetGene.locusTag}';
                        } else {
                            regInteraction.tgLocusTag = '${ri.targetGene.locusTag}';
                            regInteraction.tgName = '${ri.targetGene.locusTag}';
                        }
                    }
                    //                    console.log("tgName: " + regInteraction.tgName);
                    //                  console.log(geneInfoMapCombined.get(regInteraction.tgName).name);

                    if (tf != null) {
                        //console.log("source: " + tf);
                        regInteraction.tfName = tf;
                        regInteraction.tfLocusTag = tf;
                    } else {
                        if ('${ri.transcriptionFactor.name}' != "") {
                            //console.log("sourceName: " + '${ri.transcriptionFactor.name}');
                            regInteraction.tfName = '${ri.transcriptionFactor.name}';
                            //console.log("targetLocusTag: " + '${ri.transcriptionFactor.locusTag}');
                            regInteraction.tfLocusTag = '${ri.transcriptionFactor.locusTag}';
                        } else {
                            //console.log("sourceName: " + '${ri.transcriptionFactor.name}');
                            //console.log("targetLocusTag: " + '${ri.transcriptionFactor.locusTag}');
                            regInteraction.tfName = '${ri.transcriptionFactor.locusTag}';
                            regInteraction.tfLocusTag = '${ri.transcriptionFactor.locusTag}';
                        }
                    }
                    //console.log("regInteraction.tfName: " + regInteraction.tfName);
                    //console.log("geneInfoMapCombined.get(regInteraction.tfName).name: " + geneInfoMapCombined.get(regInteraction.tfName).name);
                    regInteraction.tgProteinId = '${ri.targetGene.proteinId}';
                    regInteraction.tfProteinId = '${ri.transcriptionFactor.proteinId}';
                    if (tf != null) {
                        regInteraction.tfRole = 'operon';
                    } else {
                        //console.log('${ri.transcriptionFactor.role}');
                        if ('${empty ri.transcriptionFactor.role}') {
                            regInteraction.tfRole = "unknown";
                        } else {
                            regInteraction.tfRole = '${ri.transcriptionFactor.role}';
                        }
                    }
                    //console.log('${ri.role}');
                    regInteraction.riRole = '${ri.role}';
                    regInteraction.pValue = '${ri.pValue}';
                    //           console.log("Save: ");
                    //console.log(regInteraction);
                    var repetedRegulation = false;
                    for (var i = 0; i < regInteractionsCombined.length; i++) {
                        if (regInteractionsCombined[i].tfName == regInteraction.tfName) {
                            if (regInteractionsCombined[i].tgName == regInteraction.tgName) {
                                if (regInteractionsCombined[i].riRole == regInteraction.riRole) {
                                    repetedRegulation = true;
                                }
                            }
                        }
                    }
                    if (!repetedRegulation) {
                        regInteractionsCombined.push(regInteraction);
                    }
                    //       console.log("------------------");
                    //     console.log();
                </script>

                <script>
                    //Take transcription factor's name or locus_tag
                    //console.log("regulationsView");
                    var regInteraction = [];
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
                    //regInteraction.tfId = '${ri.transcriptionFactor.id}';
                    if ('${ri.transcriptionFactor.name}' != "") {
                        regInteraction.tfName = '${ri.transcriptionFactor.name}';
                        regInteraction.tfLocusTag = '${ri.transcriptionFactor.locusTag}';
                    } else {
                        regInteraction.tfName = '${ri.transcriptionFactor.locusTag}';
                        regInteraction.tfLocusTag = '${ri.transcriptionFactor.locusTag}';
                    }
                    //                    console.log("tfLocusTag: " + regInteraction.tfLocusTag);
                    //                  console.log("tfName: " + regInteraction.tfName);

                    //regInteraction.tfAltLocusTag = '${ri.transcriptionFactor.alternativeLocusTag}';
                    regInteraction.tfProteinId = '${ri.transcriptionFactor.proteinId}';
                    //console.log('${ri.transcriptionFactor.role}');
                    regInteraction.tfRole = '${ri.transcriptionFactor.role}';
                    //console.log('${ri.role}');
                    regInteraction.riRole = '${ri.role}';
                    regInteraction.pValue = '${ri.pValue}';
                    regInteractions.push(regInteraction);
                    //console.log("-------------------------------------");
                </script> 
            </c:forEach>

            <c:forEach items="${rnaRegList}" var="rnaRL">
                <script>
                    //Take transcription factor's name or locus_tag
                    //console.log("rnaRegList");

                    var regInteraction = [];
                    regInteraction.tfName = '${rnaRL.srna.locusTag}';
                    //console.log(regInteraction.tfName);
                    regInteraction.tfLocusTag = '${rnaRL.srna.locusTag}';
                    //console.log(regInteraction.tfLocusTag);

                    if ('${rnaRL.tg.name}' != "") {
                        regInteraction.tgName = '${rnaRL.tg.name}';
                        //console.log(regInteraction.tgName);
                    } else {
                        regInteraction.tgName = '${rnaRL.tg.locusTag}';
                        //console.log("regInteraction.tgName: " + regInteraction.tgName);
                    }
                    regInteraction.tgLocusTag = '${rnaRL.tg.locusTag}';
                    //console.log("tg: " + regInteraction.tgLocusTag);

                    regInteraction.tfProteinId = '-';
                    regInteraction.tfRole = '${rnaRL.role}';
                    //console.log(regInteraction.tfRole);
                    regInteraction.riRole = '${rnaRL.role}';
                    regInteraction.pValue = '${rnaRL.pvalue}';
                    //console.log("RNA reg info:");
                    //console.log(regInteraction);
                    regInteractions.push(regInteraction);
                </script> 
            </c:forEach>

            <script>
                //console.log("------------------------END----------------------------");
                //console.log("------------------------Lets start baby!!!!!!!!!");
                //console.log("links:");
                //console.log(regInteractions);

                //window.alert("Am I fast enough?");
                var network;
                var screenHeightString;
                //console.log("geneInfoMap.size: " + geneInfoMap.size);
                //console.log("regInteractions.length: " + regInteractions.length);
                if (geneInfoMap.size < 50) {
                    var firstDivHeight = document.getElementById('first-div').clientHeight;
                    //console.log("firstDivHeight: " + firstDivHeight);
                    var secondDivHeight = document.getElementById('second-div').clientHeight;
                    //console.log("secondDivHeight: " + secondDivHeight);
                    //var thirdDivHeight = document.getElementById('third-div').clientHeight;
                    //console.log("thirdDivHeight: " + thirdDivHeight);

                    //console.log("window.height(): " + $(window).height());
                    //var screenHeight = ($(window).height() - (firstDivHeight + secondDivHeight + thirdDivHeight));
                    var screenHeight = ($(window).height() - (firstDivHeight + secondDivHeight));
                    //console.log("screenHeight: " + screenHeight);
                    screenHeightString = screenHeight.toString(10) + "px";
                    //console.log("screenHeightString: " + screenHeightString);
                    //console.log("regInteractions.length: " + regInteractions.length);
                } else {
                    var screenHeight = $(window).height();
                    //.log("screenHeight: " + screenHeight);
                    screenHeightString = screenHeight.toString(10) + "px";
                }

                noOperons();
                function  noOperons() {
                    console.log("Starting noOperons");
                    var createEdges = {id: "", label: ""};
                    var mapEdges = new Array();
                    var createNodes = {id: "", label: ""};
                    var mapNodes = new Set();
                    var edgeColor;
                    var riRole = "";
                    var nodes;
                    var edges;
                    var container;
                    for (var i = 0; i < regInteractions.length; i++) {
                        //console.log("from: " + regInteractions[i].tfName);
                        //console.log("to: " + regInteractions[i].tgName);
                        //console.log("role: " + regInteractions[i].riRole);
                        if ((regInteractions[i].riRole == "R") || (regInteractions[i].riRole == "Repressor")) {
                            edgeColor = '#ff6666';
                            riRole = "Repressor";
                        } else if ((regInteractions[i].riRole == "A") || (regInteractions[i].riRole == "Activator")) {
                            edgeColor = '#349834';
                            riRole = "Activator";
                        } else if ((regInteractions[i].riRole == "unknown") || (regInteractions[i].riRole == "")) {
                            edgeColor = '#00b3b3';
                            if (regInteractions[i].tfName.startsWith("sig")) {
                                riRole = "Sigma";
                            } else {
                                riRole = "";
                            }
                        } else if ((regInteractions[i].riRole == "Dual") || (regInteractions[i].riRole == "D")) {
                            edgeColor = "#0080ff";
                            riRole = "Dual";
                        } else if (regInteractions[i].riRole == "X") {
                            edgeColor = "#f5b042";
                            riRole = "X";
                        }

                        //console.log("edgeColor: " + edgeColor);

                        createEdges = {from: regInteractions[i].tfName, to: regInteractions[i].tgName, role: riRole, pValue: regInteractions[i].pValue, color: {color: edgeColor, highlight: edgeColor, hover: edgeColor}};
                        mapEdges.push(createEdges);
                        mapNodes.add(regInteractions[i].tfName);
                    }

                    for (var i = 0; i < regInteractions.length; i++) {
                        //console.log("from: " + regInteractions[i].tfName);
                        //console.log("to: "+ regInteractions[i].tgName);
                        mapNodes.add(regInteractions[i].tgName);
                    }

                    // create an array with edges
                    edges = new vis.DataSet(mapEdges);
                    //console.log("edges: ");
                    //console.log(edges);

                    // create an array with nodes
                    //console.log("nodes: ");
                    //console.log(mapNodes);
                    var mapNodesToNodes = new Array();
                    //console.log("mapNodes.size: " + mapNodes.size);
                    var count = 0;
                    var nodeColor;
                    var geneInfo;
                    mapNodes.forEach(mapNode => {
                        console.log(mapNode);
                        console.log("geneInfoMap.get(mapNode):");
                        console.log(geneInfoMap.get(mapNode));
                        geneInfo = geneInfoMap.get(mapNode);
                        nodeColor = "#b3b3b3";
                        console.log("mapNode: ");
                        console.log(geneInfo);
                        //console.log("mapNode.role: " + geneInfo.role);
                        if ((geneInfo.role == "Repressor") || (geneInfo.role == "R")) {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#ff8080";
                        } else if ((geneInfo.role == "Activator") || (geneInfo.role == "A")) {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#67cb67";
                        } else if ((geneInfo.role == "Dual") || (geneInfo.role == "D")) {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#80b3ff";
                        } else if (geneInfo.role == "unknown") {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#66d9ff";
                        } else if (geneInfo.role == "X") {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#f5b042";
                        }
                        //console.log("mapNode: " + mapNode);

                        //if (mapNode == "CA40472_RS08255" || mapNode == "clpP" || mapNode == "acnA") {
                        //    createNodes = {id: mapNode, label: mapNode, color: nodeColor, shape: "database", hidden: true};
                        //} else {
                        createNodes = {id: mapNode, label: mapNode, color: nodeColor};
                        //}
                        mapNodesToNodes.push(createNodes);
                        count++;
                    });
                    //console.log(mapNodesToNodes);
                    nodes = new vis.DataSet(mapNodesToNodes);
                    // create a network
                    container = document.getElementById('no-operons');
                    var data = {
                        nodes: nodes,
                        edges: edges
                    };
                    // initialize your network!
                    network = new vis.Network(container, data, options);
                    var options = {
                        autoResize: true,
                        height: screenHeightString,
                        width: '100%',
                        nodes: {
                            shape: 'ellipse',
                            shapeProperties: {
                                interpolation: false    // 'true' for intensive zooming
                            }
                        },
                        edges: {
                            arrows: {
                                to: {enabled: true, scaleFactor: 1, type: 'arrow'}
                            },
                            smooth: {
                                enabled: false
                            }
                        },
                        physics: {
                            adaptiveTimestep: true,
                            solver: 'forceAtlas2Based',
                            forceAtlas2Based: {
                                gravitationalConstant: -50,
                                centralGravity: 0.01,
                                springConstant: 0.08,
                                springLength: 100,
                                damping: 0.4,
                                avoidOverlap: 0
                            }
                        },
                        layout: {improvedLayout: false}
                    }

                    network.setOptions(options);
                    network.on("stabilizationProgress", function (params) {
                        document.getElementById('loadingBar').style.opacity = 1;
                        var maxWidth = 496;
                        var minWidth = 20;
                        var widthFactor = params.iterations / params.total;
                        var width = Math.max(minWidth, maxWidth * widthFactor);
                        document.getElementById('bar').style.width = width + 'px';
                        document.getElementById('text').innerHTML = Math.round(widthFactor * 100) + '%';
                    });
                    network.on("stabilizationIterationsDone", function () {
                        document.getElementById('text').innerHTML = '100%';
                        document.getElementById('bar').style.width = '496px';
                        document.getElementById('loadingBar').style.opacity = 0;
                    });
                    //NetWork on Zoom
                    network.on("zoom", function () {
                        pos = [];
                        pos = network.getViewPosition();
                        if (network.getScale() >= 2.00) {

                            network.moveTo({
                                position: {x: pos.x, y: pos.y},
                                scale: 2.00,
                            });
                        }
                    });
                    var toggle = false;
                    network.on('click', function (properties) {

                        //console.log("Starting onClick");
                        var ids = properties.nodes;
                        var clickedNodes = nodes.get(ids);
                        //console.log('clicked nodes:', clickedNodes);
                        if (clickedNodes.length == 1) {
                            //console.log("clickedNodes[0].label: ");
                            //console.log(clickedNodes);
                            var nodeAux = geneInfoMap.get(clickedNodes[0].id);
                            //console.log(nodeAux);
                            var nodeInfo = "<b>Locus_Tag:</b> " + '<span><a href="geneInfo.htm?locusTag=' + nodeAux.locusTag + '&type=${type}" target="_blank">' + nodeAux.locusTag + '</a><span>'
                                    + "<br>" + "<b>Name:</b> " + nodeAux.name
                                    + "<br>" + "<b>Protein id:</b> " + '<span><a href="https://www.ncbi.nlm.nih.gov/protein/?term=' + nodeAux.proteinId + '" target="_blank">' + nodeAux.proteinId + '</a><span>';
                            if (nodeAux.role != "") {
                                //console.log("nodeAux.role.length: " + nodeAux.role.length);
                                if (nodeAux.role.length == 1) {
                                    if (nodeAux.role == "A") {
                                        nodeInfo += "<br>" + "<b>Role:</b> Activator";
                                    } else if (nodeAux.role == "R") {
                                        nodeInfo += "<br>" + "<b>Role:</b> Repressor";
                                    } else if (nodeAux.role == "D") {
                                        nodeInfo += "<br>" + "<b>Role:</b> Dual";
                                    } else if (nodeAux.role == "X") {
                                        nodeInfo += "<br>" + "<b>Role:</b> -";
                                    }
                                } else {
                                    if (nodeAux.role == "unknown") {
                                        //console.log("nodeAux.name: " + nodeAux.name);
                                        //var isASigma = nodeAux.name.startsWith("sig");
                                        if (nodeAux.name.startsWith("sig")) {
                                            nodeInfo += "<br>" + "<b>Role:</b> Sigma";
                                        } else {
                                            nodeInfo += "<br>" + "<b>Role:</b>";
                                        }
                                    } else {
                                        nodeInfo += "<br>" + "<b>Role:</b> " + nodeAux.role;
                                    }
                                }
                            }

                            if (nodeAux.operon != "") {
                                //console.log(operons.get(nodeAux.operon));
                                nodeInfo += "<br>" + "<b>Operon:</b> " + '<span><a href="operonInfo.htm?name=' + nodeAux.operon + '&type=${type}" target="_blank">' + nodeAux.operon + '</a><span>';
                                var geneOperon = operons.get(nodeAux.operon);
                                for (var i = 0; i < geneOperon.locusTag.length; i++) {
                                    if (geneOperon.locusTag[i] != geneOperon.genes[i]) {
                                        nodeInfo += "<br>&nbsp;&nbsp;&nbsp;" + (i + 1) + ":</b> " + '<span><a href="geneInfo.htm?locusTag=' + geneOperon.locusTag[i] + '&type=${type}" target="_blank">' + geneOperon.locusTag[i] + '</a><span> (' + geneOperon.genes[i] + ')';
                                    } else {
                                        nodeInfo += "<br>&nbsp;&nbsp;&nbsp;" + (i + 1) + ":</b> " + '<span><a href="geneInfo.htm?locusTag=' + geneOperon.locusTag[i] + '&type=${type}" target="_blank">' + geneOperon.locusTag[i] + '</a><span>';
                                    }
                                }
                            }
                            $('#gene-modal-content').html(nodeInfo);
                            $('#locus-for-network').val(nodeAux.locusTag);
                            $('#geneModal').modal('show');
                        } else {
                            var edgeIds = properties.edges;
                            var clickedEdge = edges.get(edgeIds);
                            //console.log(clickedEdge);
                            if (clickedEdge.length == 1) {
                                //console.log("clickedNodes[0].label: " + clickedNodes[0].label);
                                var sourceNode = geneInfoMap.get(clickedEdge[0].from);
                                var targetNode = geneInfoMap.get(clickedEdge[0].to);
                                //console.log("sourceNode:");
                                //console.log(sourceNode);
                                //console.log("targetNode:");
                                //console.log(targetNode);
                                var edgeInfo;
                                if (sourceNode.locusTag != sourceNode.name) {
                                    edgeInfo = "<b>Source:</b> " + '<span><a href="geneInfo.htm?locusTag=' + sourceNode.locusTag + '&type=${type}" target="_blank">' + sourceNode.locusTag + '</a><span>' + '(' + sourceNode.name + ")";
                                } else {
                                    edgeInfo = "<b>Source:</b> " + '<span><a href="geneInfo.htm?locusTag=' + sourceNode.locusTag + '&type=${type}" target="_blank">' + sourceNode.locusTag + '</a><span>';
                                }

                                if (targetNode.locusTag != targetNode.name) {
                                    edgeInfo += "<br><b>Target:</b> " + '<span><a href="geneInfo.htm?locusTag=' + targetNode.locusTag + '&type=${type}" target="_blank">' + targetNode.locusTag + '</a><span>' + '(' + targetNode.name + ")";
                                } else {
                                    edgeInfo += "<br><b>Target:</b> " + '<span><a href="geneInfo.htm?locusTag=' + targetNode.locusTag + '&type=${type}" target="_blank">' + targetNode.locusTag + '</a><span>';
                                }

                                edgeInfo += "<br>" + "<b>Role:</b> " + clickedEdge[0].role;
                                if (clickedEdge[0].pValue != -1) {
                                    edgeInfo += "<br>" + "<b>P-value:</b> " + clickedEdge[0].pValue;
                                }

                                $('#ri-modal-content').html(edgeInfo);
                                $('#riModal').modal('show');
                            }


                        }
                        //var nodeInfo = "Name: " + d.data.name
                        //var nodeInfo = "Name: " + d.data.name
                        //      + "<br>" + "Colname: " + d.data.colname;

                        //console.log("Finishing onClick");
                    });
                    network.setOptions({
                        physics: {enabled: false},
                        edges: {
                            smooth: {
                                enabled: false, //setting to true enables curved lines
                                //type: "dynamic",
                                //roundness: 0.5
                            },
                        }
                    });
                    network.stabilize(1000); // 100 works for me, YMMV
                    console.log("End noOperons");
                }

                //console.log("Gene based layout DONE!");
                function operonsCombined2() {
                    console.log("Starting operonsCombined2");
                    var createEdges = {id: "", label: ""};
                    var mapEdges = new Array();
                    var createNodes = {id: "", label: ""};
                    var mapNodes = new Set();
                    var edgeColor;
                    var riRole = "";
                    var nodes;
                    var edges;
                    var container;
                    for (var i = 0; i < regInteractionsCombined.length; i++) {
                        //console.log("from: " + regInteractionsCombined[i].tfName);
                        //console.log("to: " + regInteractionsCombined[i].tgName);
                        //console.log("role: " + regInteractionsCombined[i].riRole);


                        if ((regInteractionsCombined[i].riRole == "R") || (regInteractionsCombined[i].riRole == "Repressor")) {
                            edgeColor = '#ff6666';
                            riRole = "Repressor";
                        } else if ((regInteractionsCombined[i].riRole == "A") || (regInteractionsCombined[i].riRole == "Activator")) {
                            edgeColor = '#349834';
                            riRole = "Activator";
                        } else if ((regInteractionsCombined[i].riRole == "unknown") || (regInteractionsCombined[i].riRole == "")) {
                            edgeColor = '#00b3b3';
                            if (regInteractionsCombined[i].tfName.startsWith("sig")) {
                                riRole = "Sigma";
                            } else {
                                riRole = "";
                            }
                        } else if ((regInteractionsCombined[i].riRole == "Dual") || (regInteractionsCombined[i].riRole == "D")) {
                            edgeColor = '#0080ff';
                            riRole = "Dual";
                        }

                        //console.log("edgeColor: " + edgeColor);

                        createEdges = {from: regInteractionsCombined[i].tfName, to: regInteractionsCombined[i].tgName, role: riRole, pValue: regInteractionsCombined[i].pValue, color: {color: edgeColor, highlight: edgeColor, hover: edgeColor}};
                        mapEdges.push(createEdges);
                        //console.log("regInteractionsCombined[i].tfName: " + regInteractionsCombined[i].tfName);
                        mapNodes.add(regInteractionsCombined[i].tfName);
                    }

                    for (var i = 0; i < regInteractionsCombined.length; i++) {
                        //console.log("from: " + regInteractionsCombined[i].tfName);
                        //console.log("to: " + regInteractionsCombined[i].tgName);
                        //console.log("regInteractionsCombined[i].tgName: " + regInteractionsCombined[i].tgName);
                        mapNodes.add(regInteractionsCombined[i].tgName);
                    }

                    // create an array with edges
                    edges = new vis.DataSet(mapEdges);
                    //console.log("edges: ");
                    //console.log(edges);

                    // create an array with nodes
                    //console.log("nodes: ");
                    //console.log(mapNodes);
                    var mapNodesToNodes = new Array();
                    //console.log("mapNodes.size: " + mapNodes.size);
                    var count = 0;
                    var nodeColor;
                    var geneInfo;
                    mapNodes.forEach(mapNode => {
                        //console.log("mapNode: " + mapNode);
                        //console.log("geneInfoMapCombined.get(mapNode):");
                        //console.log(geneInfoMapCombined.get(mapNode));
                        geneInfo = geneInfoMapCombined.get(mapNode);
                        nodeColor = "#b3b3b3";
                        //console.log("mapNode.role: " + geneInfo.role);


                        if ((geneInfo.role == "Repressor") || (geneInfo.role == "R")) {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#ff8080";
                        } else if ((geneInfo.role == "Activator") || (geneInfo.role == "A")) {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#67cb67";
                        } else if ((geneInfo.role == "Dual") || (geneInfo.role == "D")) {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#80b3ff";
                        } else if (geneInfo.role == "unknown") {
                            //console.log("mapNode.role: " + geneInfo.role)
                            nodeColor = "#66d9ff";
                        }

                        if (geneInfo.operon == "operon") {
                            nodeColor = "#ffcc00";
                        }
                        //console.log("mapNode: " + mapNode);
                        createNodes = {id: mapNode, label: mapNode, genes: geneInfo.genes, color: nodeColor};
                        mapNodesToNodes.push(createNodes);
                        count++;
                    });
                    //console.log(mapNodesToNodes);
                    nodes = new vis.DataSet(mapNodesToNodes);
                    // create a network
                    container = document.getElementById('operons-combined');
                    var data = {
                        nodes: nodes,
                        edges: edges
                    };
                    // initialize your network!
                    network = new vis.Network(container, data, options);
                    var options = {
                        autoResize: true,
                        height: screenHeightString,
                        width: '100%',
                        nodes: {
                            shape: 'ellipse',
                            shapeProperties: {
                                interpolation: false    // 'true' for intensive zooming
                            }
                        },
                        edges: {
                            arrows: {
                                to: {enabled: true, scaleFactor: 1, type: 'arrow'}
                            },
                            smooth: {
                                enabled: false
                            }
                        },
                        physics: {
                            adaptiveTimestep: true,
                            solver: 'forceAtlas2Based',
                            forceAtlas2Based: {
                                gravitationalConstant: -50,
                                centralGravity: 0.01,
                                springConstant: 0.08,
                                springLength: 100,
                                damping: 0.4,
                                avoidOverlap: 0
                            }
                        },
                        layout: {improvedLayout: false}
                    }

                    network.setOptions(options);
                    network.on("stabilizationProgress", function (params) {
                        document.getElementById('loadingBar').style.opacity = 1;
                        var maxWidth = 496;
                        var minWidth = 20;
                        var widthFactor = params.iterations / params.total;
                        var width = Math.max(minWidth, maxWidth * widthFactor);
                        document.getElementById('bar').style.width = width + 'px';
                        document.getElementById('text').innerHTML = Math.round(widthFactor * 100) + '%';
                    });
                    network.on("stabilizationIterationsDone", function () {
                        document.getElementById('text').innerHTML = '100%';
                        document.getElementById('bar').style.width = '496px';
                        document.getElementById('loadingBar').style.opacity = 0;
                    });
                    //NetWork on Zoom
                    network.on("zoom", function () {
                        pos = [];
                        pos = network.getViewPosition();
                        if (network.getScale() >= 2.00) {

                            network.moveTo({
                                position: {x: pos.x, y: pos.y},
                                scale: 2.00,
                            });
                        }
                    });
                    network.on('click', function (properties) {
                        var ids = properties.nodes;
                        var clickedNodes = nodes.get(ids);
                        //console.log('clicked nodes:', clickedNodes);
                        if (clickedNodes.length == 1) {
                            //console.log("clickedNodes[0].label: ");
                            //console.log(clickedNodes);

                            var nodeAux = geneInfoMapCombined.get(clickedNodes[0].id);
                            var nodeInfo;
                            //console.log(nodeAux);
                            var genesToNewNetwork = [];
                            nodeInfo = "<b>Locus_Tag:</b> " + '<span><a href="geneInfo.htm?locusTag=' + nodeAux.locusTag + '&type=${type}" target="_blank">' + nodeAux.locusTag + '</a><span>'
                                    + "<br>" + "<b>Name:</b> " + nodeAux.name
                                    + "<br>" + "<b>Protein id:</b> " + '<span><a href="https://www.ncbi.nlm.nih.gov/protein/?term=' + nodeAux.proteinId + '" target="_blank">' + nodeAux.proteinId + '</a><span>';
                            if (nodeAux.role != "") {
                                //console.log("nodeAux.role.length: " + nodeAux.role.length);
                                if (nodeAux.role.length == 1) {
                                    if (nodeAux.role == "A") {
                                        nodeInfo += "<br>" + "<b>Role:</b> Activator";
                                    } else if (nodeAux.role == "R") {
                                        nodeInfo += "<br>" + "<b>Role:</b> Repressor";
                                    } else if (nodeAux.role == "D") {
                                        nodeInfo += "<br>" + "<b>Role:</b> Dual";
                                    }
                                } else {
                                    if (nodeAux.role == "unknown") {
                                        var isASigma = nodeAux.name.startsWith("sig");
                                        if (nodeAux.name.startsWith("sig")) {
                                            nodeInfo += "<br>" + "<b>Role:</b> Sigma";
                                        } else {
                                            nodeInfo += "<br>" + "<b>Role:</b>";
                                        }
                                    } else {
                                        nodeInfo += "<br>" + "<b>Role:</b> " + nodeAux.role;
                                    }
                                }
                            }

                            if (nodeAux.operon != "") {
                                nodeInfo = "<b>Operon:</b> " + '<span><a href="operonInfo.htm?name=' + nodeAux.locusTag + '&type=${type}" target="_blank">' + nodeAux.locusTag + '</a><span>'

                                var genesOperon = operons.get(nodeAux.locusTag);
                                //console.log("genesOperon: ");
                                //console.log(genesOperon);
                                for (var i = 0; i < genesOperon.locusTag.length; i++) {
                                    genesToNewNetwork.push(genesOperon.locusTag[i]);
                                    //console.log("genesOperon.locusTag[i]: " + genesOperon.locusTag[i]);
                                    //console.log("genesOperon.genes[i]: " + genesOperon.genes[i]);

                                    if (genesOperon.locusTag[i] != genesOperon.genes[i]) {
                                        nodeInfo += "<br>&nbsp;&nbsp;&nbsp;" + (i + 1) + ":</b> " + '<span><a href="geneInfo.htm?locusTag=' + genesOperon.locusTag[i] + '&type=${type}" target="_blank">' + genesOperon.locusTag[i] + '</a><span> (' + genesOperon.genes[i] + ')';
                                    } else {
                                        nodeInfo += "<br>&nbsp;&nbsp;&nbsp;" + (i + 1) + ":</b> " + '<span><a href="geneInfo.htm?locusTag=' + genesOperon.locusTag[i] + '&type=${type}" target="_blank">' + genesOperon.locusTag[i] + '</a><span>';
                                    }
                                }
                            } else {
                                genesToNewNetwork.push(nodeAux.locusTag);
                            }
                            //console.log("genesToNewNetwork:");
                            //console.log(genesToNewNetwork);
                            $('#gene-modal-content').html(nodeInfo);
                            $('#locus-for-network').val(genesToNewNetwork);
                            $('#geneModal').modal('show');
                        } else {
                            var edgeIds = properties.edges;
                            var clickedEdge = edges.get(edgeIds);
                            //console.log(clickedEdge);
                            if (clickedEdge.length == 1) {
                                //console.log("clickedNodes[0].label: " + clickedNodes[0].label);

                                var sourceNode = geneInfoMapCombined.get(clickedEdge[0].from);
                                var targetNode = geneInfoMapCombined.get(clickedEdge[0].to);
                                var edgeInfo;
                                if (sourceNode.locusTag != sourceNode.name) {
                                    edgeInfo = "<b>Source:</b> " + '<span><a href="geneInfo.htm?locusTag=' + sourceNode.locusTag + '&type=${type}" target="_blank">' + sourceNode.locusTag + '</a><span>' + '(' + sourceNode.name + ")";
                                } else {
                                    edgeInfo = "<b>Source:</b> " + '<span><a href="geneInfo.htm?locusTag=' + sourceNode.locusTag + '&type=${type}" target="_blank">' + sourceNode.locusTag + '</a><span>';
                                }

                                if (targetNode.locusTag != targetNode.name) {
                                    edgeInfo += "<br><b>Target:</b> " + '<span><a href="geneInfo.htm?locusTag=' + targetNode.locusTag + '&type=${type}" target="_blank">' + targetNode.locusTag + '</a><span>' + '(' + targetNode.name + ")";
                                } else {
                                    edgeInfo += "<br><b>Target:</b> " + '<span><a href="geneInfo.htm?locusTag=' + targetNode.locusTag + '&type=${type}" target="_blank">' + targetNode.locusTag + '</a><span>';
                                }

                                edgeInfo += "<br>" + "<b>Role:</b> " + clickedEdge[0].role;
                                if (clickedEdge[0].pValue != -1) {
                                    edgeInfo += "<br>" + "<b>P-value:</b> " + clickedEdge[0].pValue;
                                }

                                $('#ri-modal-content').html(edgeInfo);
                                $('#riModal').modal('show');
                            }
                        }
                        //var nodeInfo = "Name: " + d.data.name
                        //var nodeInfo = "Name: " + d.data.name
                        //      + "<br>" + "Colname: " + d.data.colname;
                    });
                    network.setOptions({
                        physics: {enabled: false},
                        edges: {
                            smooth: {
                                enabled: false, //setting to true enables curved lines
                                //type: "dynamic",
                                //roundness: 0.5
                            },
                        }
                    });
                    network.stabilize(1000); // 100 works for me, YMMV
                    console.log("End operonsCombined2");
                }


                function layoutStatus() {
                    //console.log(document.getElementById('stop-layout-button').innerHTML);
                    if (document.getElementById('stop-layout-button').innerHTML == "Stop Layouting") {
                        stopLayout();
                        document.getElementById('stop-layout-button').innerHTML = "Improve Layout";
                    } else {
                        startLayout();
                        document.getElementById('stop-layout-button').innerHTML = "Stop Layouting";
                    }
                }
                //document.getElementById('restart-layout-button').addEventListener("click", restartLayout);

                function stopLayout() {
                    network.setOptions({
                        physics: {enabled: false},
                        edges: {
                            smooth: {
                                enabled: false, //setting to true enables curved lines
                                //type: "dynamic",
                                //roundness: 0.5
                            },
                        }
                    });
                }

                function startLayout() {
                    network.setOptions({
                        physics: {enabled: true},
                        edges: {
                            smooth: {
                                enabled: true, //setting to true enables curved lines
                                //type: "dynamic",
                                //roundness: 0.5
                            },
                        }
                    });
                }
            </script>
        </div>

        <script>
            function noOperon() {
                noOperons();
                document.getElementById('no-operons').style.display = "block";
                document.getElementById('operons-combined').style.display = "none";
            }
            function operonsCombined() {
                document.getElementById('no-operons').style.display = "none";
                operonsCombined2();
                document.getElementById('operons-combined').style.display = "block";
            }
        </script>

    </body>
</html>
