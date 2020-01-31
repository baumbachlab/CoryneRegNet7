<%-- 
    Document   : organismInfo
    Created on : Nov 8, 2018, 3:18:30 PM
    Author     : doglas
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link type="text/css" rel="stylesheet" href="css/organismInfo.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">

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

            /* Styles go here */

            .tooltip {
                position: static;
                border-bottom: 1px dotted black;
                opacity: 1;
                font-family: inherit;
            }

            .tooltip .tooltiptext {
                visibility: hidden;
                width: 500px;
                background-color: #555;
                color: #fff;
                text-align: center;
                border-radius: 6px;
                padding: 5px 0;
                position: absolute;
                z-index: 1;
                bottom: 125%;
                left: 50%;
                margin-left: -60px;
                opacity: 0;
                transition: opacity 0.3s;
                font-family: sans-serif;
            }

            .tooltip .tooltiptext::after {
                content: "";
                position: absolute;
                top: 100%;
                left: 50%;
                margin-left: -5px;
                border-width: 5px;
                border-style: solid;
            }

            .tooltip:hover .tooltiptext {
                visibility: visible;
                opacity: 1;
            }

        </style>
    </head>

    <body style="background-color: #fcfcfc; background-size: cover;">

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

    <div class="container-fluid font" style="background-image: url('images/background.png'); background-size: cover;">
        <div id="top-button"></div>
        <div class="row bottom-btn">
            <div class="col-sm-4 col-4" style="padding-left: 0px;">
                <c:choose>
                    <c:when test="${type eq 'experimental'}">
                        <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                    </c:when>
                    <c:otherwise>
                        <a href="searchPredictedData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-sm-4 col-8">
                <center>
                    <a href="#bottom-button"><button type="button" class="btn btn-primary btn-normal">Bottom</button></a>
                </center>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="title-size text-center">${organism.genera} ${organism.species} ${organism.strain} (${type} database)</div>
            </div>
        </div>
    </div>

    <div class="container-fluid badge badge-light shadow" style="opacity: 0.9;">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="row organism-info-space">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <center>
                    <table id="gene-number" class="table table-striped">
                        <thead>
                            <tr>
                                <td>Organism ID:</td>
                                <td>${genome.ncbiAccessionNumber}</td>
                            </tr>
                            <tr>
                                <td>Organism name:</td>
                                <td>${organism.genera} ${organism.species} ${organism.strain}</td>
                            </tr>
                            <tr><td></td><td></td></tr>
                        </thead>
                    </table>
                </center>
            </div>
            <div class="col-sm-2"></div>
        </div>

        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <center>
                    <table id="statistics" class="table table-striped text-center">
                        <thead>
                            <tr>
                                <th class="tables-info-screens">Organism statistics</th>
                                <th></th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Genes</td>
                                <td>${numberOfGenes}</td>
                            </tr>
                            <tr>
                                <td>Proteins</td>
                                <td>${numberOfProteins}</td>
                            </tr>
                            <tr>
                                <td>Regulations</td>
                                <td>${numberOfRegulatoryInteractions}</td>
                            </tr>
                            <tr>
                                <td>Regulators</td>
                                <td>${numberOfRegulators}</td>
                            </tr>
                            <tr>
                                <td>Regulated genes</td>
                                <td>${numberOfRegulatedGenes}</td>
                            </tr>
                            <tr>
                                <td>Binding motifs</td>
                                <td>${numberOfBindingSites}</td>
                            </tr>
                            <tr>
                                <td>HMM profiles</td>
                                <td>${numberOfHmmProfiles}</td>
                            </tr>
                        </tbody>
                    </table>
                </center>
            </div>
            <div class="col-sm-4"></div>
        </div>


        <div class="row gene-number">
            <div class="col-sm-12">
                <table id="gene-number" class="table table-striped">
                    <thead>
                    <th>Organism has ${numberOfGenes} genes</th>
                    </thead>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <center>
                    <table id="organism-info" class="table table-striped text-center">
                        <thead>
                            <tr>
                                <th class="tables-info-screens">Gene ID</th>
                                <th class="tables-info-screens">Alt. Gene ID</th>
                                <th class="tables-info-screens">Gene name</th>
                                <th class="tables-info-screens">Protein id</th>
                                <th class="tables-info-screens">Protein name</th>
                                <th class="tables-info-screens">Predicted operon</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${genes}" var="gene">
                                <tr>
                                    <td><span><a href="geneInfo.htm?locusTag=${gene.locusTag}&type=${type}">${gene.locusTag}</a></span></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty gene.alternativeLocusTag}">
                                                <span><a href="geneInfo.htm?locusTag=${gene.locusTag}&type=${type}">${gene.alternativeLocusTag}</a></span>
                                                </c:when>
                                                <c:otherwise>
                                                <span>-</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty gene.name}">
                                                <span>${gene.name}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span>-</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty gene.proteinId}">
                                                <span><a href="geneInfo.htm?locusTag=${gene.locusTag}&type=${type}">${gene.proteinId}</a></span>
                                                </c:when>
                                                <c:otherwise>
                                                <span>-</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty gene.product}">
                                                <span>${gene.product}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span>-</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty operons.get(gene.id)}">
                                                <span><a href="operonInfo.htm?name=${operons.get(gene.id)}&type=${type}">${operons.get(gene.id)}</a></span>
                                                </c:when>
                                                <c:otherwise>
                                                <span>-</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </center>
            </div>
        </div>
        <div id="network-loader" style="display:none; margin-top: 20px;">
            <center><span ><div class="loader" ></div> Loading Network Visualization</span></center>
            <br><br>
        </div>
        <div id="searches" class="row font">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <div class="row">
                    <div class="col-sm-1"></div>
                    <div class="col-sm-10">
                        <center>
                            <div class="tooltip">
                                <a href="whichNetwork.htm?layoutType=fast&organism=${organism.id}&searchType=${type}&goBackTo=organism"><button type="button" class="btn btn-primary btn-block btn-normal font" onclick="showLoader()">Network Visualization</button></a>
                                <span class="tooltiptext">Searches the database content and presents a dynamic network visualization of the TRN of selected organism</span>
                            </div>
                        </center>
                    </div>
                    <div class="col-sm-1"></div>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
    <div class="container-fluid font" style="background-image: url('images/background.png'); background-size: cover;">
        <div class="row go-top-row">
            <div class="col-sm-12">
                <center>
                    <a href="#top-button"><button id="bottom-button" type="button" class="btn btn-primary btn-normal">Top</button></a>
                </center>
            </div>
        </div>
    </div>


    <div class="container-fluid font">
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
