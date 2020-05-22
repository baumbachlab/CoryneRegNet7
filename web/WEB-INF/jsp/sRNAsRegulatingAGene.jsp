<%-- 
    Document   : tFsRegulatingAGene
    Created on : Feb 17, 2019, 1:17:54 PM
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
        <script type="text/javascript" src="js/sRNARegAGene.js"></script>
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
            var sRNAsRegAGeneShow = new Map;
            var organisms = [];
        </script>

        <div class="container-fluid badge badge-light shadow space-to-footer">
            <hr style="color: #eee; margin-bottom: 30px">
            <c:set var="count" value="-1" scope="page"/>
            <c:forEach items="${sRNAsRegAGene}" var="sRNAsRegGene">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:set var="divName" value="sRNA${count}" scope="page"/>
                <div class="container-fluid" style="margin-bottom: 80px;">
                    <div class="row font">
                        <c:choose>
                            <c:when test="${count==0}">
                                <div class="col-sm-12"><p class="dist-info">Distribution of the number of sRNAs regulating a gene (${type} database)</p></div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-sm-12"><center><span class="dist-info">Distribution of the number of sRNAs regulating a gene for <a href="organismInfo.htm?id=${organismsId.get(sRNAsRegGene.key)}&type=${type}">${sRNAsRegGene.key}</a></span></center></div>
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
                    var key = '${sRNAsRegGene.key}';
                    var value = '${sRNAsRegGene.value}';
                    var sRNAsRegAGeneObject = {};
                    organisms.push(key);
                    sRNAsRegAGeneObject = sRNAsRegAGeneObjectFunction(value);
                    //console.log(tfsRegAGeneObject);
                    sRNAsRegAGeneShow.set(key, sRNAsRegAGeneObject);
                    var auxRetriver = {};
                    auxRetriver = sRNAsRegAGeneShow.get(key);
                    //console.log(auxRetriver);
                    function sRNAsRegAGeneObjectFunction(value) {
                        var sRNAsRegAGeneAttributes = {};
                        var aux = [];
                        var tfs = [];
                        var genes = [];
                        aux = value.split(", ");
                        for (var i = 0; i < aux.length; i++) {
                            var attributes = aux[i].split("=");
                            //console.log(attributes[0]);
                            var numberOfsRNAs = [];
                            numberOfsRNAs[0] = 0;
                            if (attributes[0].includes("{")) {
                                numberOfsRNAs = attributes[0].split("{");
                                //tfs.push(attributesAux[1]);
                            } else {
                                //tfs.push(attributes[0]);
                                numberOfsRNAs[1] = attributes[0];
                            }

                            //console.log("numberOfsRNAs[1]: " + numberOfsRNAs[1]);
                            //console.log(attributes[1]);

                            var numberOfGenes = [];
                            if (attributes[1].includes("}")) {
                                numberOfGenes = attributes[1].split("}");
                            } else {
                                numberOfGenes[0] = attributes[1];
                            }

                            if (numberOfGenes[0].length == 1) {
                                genes.push("0000" + numberOfGenes[0]);
                            } else if (numberOfGenes[0].length == 2) {
                                genes.push("000" + numberOfGenes[0]);
                            } else if (numberOfGenes[0].length == 3) {
                                genes.push("00" + numberOfGenes[0]);
                            } else if (numberOfGenes[0].length == 4) {
                                genes.push("0" + numberOfGenes[0]);
                            } else {
                                genes.push(numberOfGenes[0]);
                            }

                            tfs.push(numberOfsRNAs[1]);
                        }
                        sRNAsRegAGeneAttributes.tfs = tfs;
                        sRNAsRegAGeneAttributes.genes = genes;
                        return sRNAsRegAGeneAttributes;
                    }
                </script>
            </c:forEach>

            <script>
                for (var i = 0; i < sRNAsRegAGeneShow.size; i++) {
                    count++;
                    //console.log("count: " + count);
                    // console.log("Bar chart start!!!!!!");
                    // console.log(tfsRegAGeneShow.get(organisms[i]));

                    var maxValue;
                    var tfsRegAGene2 = [];
                    for (var k = 0; k < sRNAsRegAGeneShow.get(organisms[i]).genes.length; k++) {
                        // console.log("tfsRegAGeneShow.get(organisms[i]).genes[k]: " + tfsRegAGeneShow.get(organisms[i]).genes[k]);
                        // console.log("tfsRegAGeneShow.get(organisms[i]).tfs[k]: " + tfsRegAGeneShow.get(organisms[i]).tfs[k]);
                        var tfsRegAGeneAux = [];

                        if (k == 0) {
                            maxValue = sRNAsRegAGeneShow.get(organisms[i]).genes[k];
                        } else {
                            var aux = sRNAsRegAGeneShow.get(organisms[i]).genes[k];
                            // console.log("maxValue < aux? " + maxValue + " < " + aux);
                            maxValue = Math.max(maxValue, aux);
                            //  console.log("new maxValue: " + maxValue);
                        }
                        tfsRegAGeneAux.genes = sRNAsRegAGeneShow.get(organisms[i]).genes[k];
                        tfsRegAGeneAux.tfs = sRNAsRegAGeneShow.get(organisms[i]).tfs[k];
                        // console.log(tfsRegAGeneAux);
                        tfsRegAGene2.push(tfsRegAGeneAux);
                        //console.log(tfsRegAGene2);
                    }

                    // console.log("maxValue: " + maxValue);

                    //    for (var k = 0; k < tfsRegAGeneShow.get(organisms[i]).length; k++) {
                    //      var tfsReg = [];
                    //    console.log(tfsRegAGeneShow.get(organisms[i]).genes[k]);
                    //  tfsReg.genes = tfsRegAGeneShow.get(organisms[i]).genes[k];
                    //tfsReg.tfs = tfsRegAGeneShow.get(organisms[i]).tfs[k];
                    //tfsRegAGeneAux.push(tfsReg);
                    // }

                    var data = tfsRegAGene2;
                    //console.log(data);
                    tfsRegAGene(data, count, maxValue);
                    // Create Tooltips

                }
            </script>

            <c:if test="${empty sRNAsRegAGene}">
                <span>No entries found.</span>
            </c:if>
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