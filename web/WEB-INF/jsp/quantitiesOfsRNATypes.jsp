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
        <script type="text/javascript" src="js/quantitiesOfsRNATypes.js"></script>
        <script type="text/javascript" src="js/quantitiesOfsRNAAllTypes.js"></script>
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

        <c:if test="${empty organismsRegulators}">
            <span>No entries found.</span>
        </c:if>

        <div class="container-fluid badge badge-light shadow space-to-footer">
            <hr style="color: #eee; margin-bottom: 30px">
            <c:set var="count" value="-1" scope="page"/>
            <c:forEach items="${organismsRegulators}" var="regulators">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:set var="divName" value="sRNA-types${count}" scope="page"/>
                <div class="container-fluid" style="margin-bottom: 80px;">
                    <div class="row font">
                        <c:choose>
                            <c:when test="${count==0}">
                                <div class="col-sm-12"><p class="title-size">Quantities of sRNA types (${type} database)</p></div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-sm-12">
                                    <center>
                                        <span class="title-size">Quantities of sRNA types for
                                            <a href="organismInfo.htm?id=${organismsId.get(regulators.key)}&type=${type}">
                                                ${regulators.key}</a></span>
                                    </center>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="row" style="margin-top: 40px;">
                        <div class="col-sm-12">
                            <div id="${divName}" align="center" class="pie-chart-pos"></div>
                            <b><center><p style="font-size: 22px; margin-top: 20px;">Quantities of sRNA types</p></center></b>
                        </div>
                    </div>
                </div>

                <script>
                    var key = '${regulators.key}';
                    var value = '${regulators.value}';
                    //console.log(value);
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
                        regulatorsAttributes.ncRnaExperimental = attributes[1];
                        regulatorsAttributes.ncRnaBsrd = aux[1];
                        regulatorsAttributes.ncRnaCmsearch = aux[2];
                        regulatorsAttributes.ncRnaGLASSgo = aux[3];
                        attributes = aux[4].split("]");
                        regulatorsAttributes.ncRNATypes = attributes[0];
                        return regulatorsAttributes;
                    }
                </script>
            </c:forEach>

            <script>

                //Pie Chart - sRNA types
                var count;
                for (var i = 0; i < regulatorsMap.size; i++) {
                    var ncRnaExperimental = parseInt(regulatorsMap.get(organisms[i]).ncRnaExperimental);
                    var ncRnaBsrd = parseInt(regulatorsMap.get(organisms[i]).ncRnaBsrd);
                    var ncRnaCmsearch = parseInt(regulatorsMap.get(organisms[i]).ncRnaCmsearch);
                    var ncRnaGLASSgo = parseInt(regulatorsMap.get(organisms[i]).ncRnaGLASSgo);
                    var ncRNATypes = parseInt(regulatorsMap.get(organisms[i]).ncRNATypes);
                    count = i;
                    if (i == 0) {
                        quantitiesOfsRNAAllTypes(ncRnaExperimental, ncRnaBsrd, ncRnaCmsearch,
                                ncRnaGLASSgo, ncRNATypes, count);
                    } else {
                        quantitiesOfsRNATypes(ncRnaExperimental, ncRnaBsrd, ncRnaCmsearch,
                                ncRnaGLASSgo, ncRNATypes, count);
                    }
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
