<%-- 
    Document   : rna.jsp
    Created on : Apr 23, 2020, 7:12:15 PM
    Author     : mariana
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
        <script type="text/javascript" src="js/mainjs.js"></script>
        <script type="text/javascript" src="js/teste.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.cssf">
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

    <div class="container-fluid font" style="background-image: url('images/background.png'); background-size: cover;">
        <div id="top-button"></div>
        <div class="row bottom-btn">
            <div class="col-sm-4 col-4" style="padding-left: 0px;">
                <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">Go Back</button></a>
            </div>
            <div class="col-sm-4 col-8">
                <center>
                    <a href="#bottom-button"><button type="button" class="btn btn-primary btn-normal">Bottom</button></a>
                    <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                </center>
            </div>
            <div class="col-sm-4 col-0"></div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="title-size text-center">Experimental data recovered from CoryneRegNet</div>
            </div>
        </div>
    </div>

    <div class="container-fluid badge badge-light space-to-footer">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="row">
            <div class="col-sm-12 text-center tables-top" style="font-size: 22px">
                <p class="font">Results (${entriesFound} found)</p>
            </div>
        </div>
        <div class="row">
            <%-- rnaTableView --%>
            <table id="experimental-data" class="table table-striped text-center" style="width:100%">
                <thead>
                    <tr>
                        <th>RNA ID</th>
                        <th>Evidence</th>
                            <c:choose>
                                <c:when test="${type eq 'predicted'}">
                                <th>Source RNA</th>
                                </c:when>
                            </c:choose>
                        <th>sRNA Class</th>
                        <th>Position</th>
                        <th>Orientation</th>
                            <%--<th>Sequence</th>
                            <th>Regulates</th>--%>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${rnaTableView}" var="list">
                        <tr>
                            <td>    
                                <a style="color: black; font-size: medium; font-weight: bold" href="rnaInfo.htm?locusTag=${list.locusTag}&type=experimental">${list.locusTag}</a>
                            </td>
                            <td>${list.evidence}</td>

                            <c:choose>
                                <c:when test="${type eq 'predicted'}">
                                    <td> ${list.sourceRna}
                                        <c:if test="${empty list.sourceRna}">-</c:if>
                                        </td>

                                </c:when>
                            </c:choose>

                            <td>${list.srnaClass}
                                <c:if test="${empty list.srnaClass}">-</c:if>
                                </td>
                                <td>${list.startPosition} - ${list.endPosition}</td>
                            <td>${list.orientation}</td>
                            <%--<c:choose>
                                <c:when test="${type eq 'predicted'}">
                                    <td style="word-wrap:break-word; max-width: 250px;">${list.sequence}</td>
                                </c:when>
                                <c:otherwise>
                                    <td style="word-wrap:break-word; max-width: 450px;">${list.sequence}</td>
                                </c:otherwise>    
                            </c:choose>
                            <td style="word-wrap:break-word; max-width: 500px;">      
                                <c:forTokens var="token" items="${list.mrnas} " delims=",">
                                    <c:set var="i" value="0"/>
                            <%--<a style="color: black" href="geneInfo.htm?locusTag=${token}&type=experimental"><c:out value="${token}"/></a>&nbsp;--%>
                            <%--        ${token}&nbsp;
                                </c:forTokens>--%>
                            </td> 
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container-fluid font">
    <div class="row go-top-row">
        <div class="col-sm-12">
            <center>
                <a href="#top-button"><button id="bottom-button" type="button" class="btn btn-primary btn-normal">Top</button></a>
                <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
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

<script>
    $(document).ready(function () {
        $('#experimental-data').DataTable({
            "lengthMenu": [[-1, 10, 25, 50], ["All", 10, 25, 50]],
            "ordering": false
        });
    });
</script>
</body>
</html>