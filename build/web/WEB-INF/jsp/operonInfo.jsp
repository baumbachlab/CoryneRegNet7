<%-- 
    Document   : operonInfo
    Created on : Nov 8, 2018, 3:07:53 PM
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
        <link type="text/css" rel="stylesheet" href="css/operonInfo.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
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

    <div class="container-fluid font">
        <div id="top-button"></div>
        <div class="row bottom-btn">
            <div class="col-sm-12" style="padding-left: 0px;">
                <c:choose>
                    <c:when test="${type eq 'experimental'}">
                        <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                    </c:when>
                    <c:otherwise>
                        <a href="searchPredictedData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="title-size text-center">Operon information</div>
            </div>
        </div>
    </div>

    <div class="container-fluid badge badge-light" style="opacity: 0.9;">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <table id="table-operon-top">
                    <tr>
                        <td style="width: 212px;"><span>Predicted operon:</span></td>
                        <td><span>${operon.name}</span></td><br/>
                    </tr>
                    <tr>
                        <td style="width: 212px;"><span>First gene in operon: </span></td>
                        <td><span>${firstGene.locusTag}</span></td><br/>
                    </tr>
                    <tr>
                        <td style="width: 212px;"><span>Organism: </span></td>
                        <td><span><a href="organismInfo.htm?id=${firstGene.genome.organism.id}&type=${type}">${firstGene.genome.organism.genera} ${firstGene.genome.organism.species} ${firstGene.genome.organism.strain}</a></span></td>
                    </tr>
                </table>
            </div>
            <div class="col-sm-2"></div>
        </div>

        <div class="row operon-info-space">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <center>
                    <table id="operon-info" class="table table-striped text-center">
                        <thead>
                            <tr>
                                <th class="tables-info-screens">Position</th>
                                <th class="tables-info-screens">Locus tag</th>
                                <th class="tables-info-screens">Alt. locus tag</th>
                                <th class="tables-info-screens">Gene name</th>
                                <th class="tables-info-screens">Protein id</th>
                                <th class="tables-info-screens">Protein name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="-1" scope="page"/>
                            <c:forEach items="${genes}" var="gene">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <tr>
                                    <td><span>${genesOperon.get(count).pos}</span></td>
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
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </center>
            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
    <div class="container-fluid font footer-download">
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