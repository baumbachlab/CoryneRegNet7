<%-- 
    Document   : rnaInfo
    Created on : May 1, 2020, 10:02:26 AM
    Author     : mariana
--%>

<%@page import="com.sun.xml.wss.impl.misc.Base64"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
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
        <link type="text/css" rel="stylesheet" href="css/geneInfo.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">

        <style>
            .text-header{
                text-decoration: underline;
            }
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
    </head>

    <body style="background-image: url('images/background.png'); background-size: cover;">

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
                <div class="title-size text-center">Information recovered from ${type} database</div>
            </div>
        </div>
    </div>

    <div class="container-fluid badge badge-light go-top-row" style="opacity: 0.9; margin-top: 25px;">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li class="nav-item">
                <a class="nav-link tab-color active" data-toggle="tab" href="#gene">RNA:  ${srna.locusTag}

                </a>
            </li>
            <%--<c:if test="${not empty regulates}">--%>
            <li class="nav-item">
                <a class="nav-link tab-color" data-toggle="tab" href="#regulates">Regulates:</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content container-fluid">
            <div id="gene" class="tab-pane active gene-info"><br>
                <span>Locus tag: ${srna.locusTag}</span><br>  
                <span>Evidence: ${srna.evidence}</span><br>  
                <c:if test="${type eq 'predicted'}">
                    <span>Source RNA: 
                        <a style="color: black; " href="rnaInfo.htm?locusTag=${srna.sourceRna.locusTag}&type=${type}"><c:out value="${srna.sourceRna.locusTag}"/></a>&nbsp;

                    </span><br>
                </c:if>
                <c:if test="${type eq 'experimental'}">
                    <span>sRNA class: ${srna.srnaClass}</span><br>  
                </c:if>
                <span>Position: ${srna.startPosition} - ${srna.endPosition}</span><br>  
                <span>Orientation: ${srna.orientation}</span><br>
                <c:if test="${type eq 'predicted'}">
                    <span>Functional? ${srna.functionalRna}</span><br>
                    <span>Functional evidence: ${srna.evidenceFunctional}</span><br> 
                </c:if>
                <span>Nucleotide sequence:</span><br> ${srna.sequence}


            </div>
            <%--regulates: (The gene is a TF)--%>
            <div id="regulates" class="tab-pane fade tables-top">
                <div class="row" style="padding-bottom: 15px;">
                    <div class="col-sm-12">
                        <span class="gene-info">sRNA ${srna.locusTag} regulates:</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table id="regulates-table" class="table table-striped text-center" style="width:100%">
                            <thead>
                                <tr>
                                    <th>mRNA</th>
                                    <th>P-value</th>
                                    <th>Position mRNA</th>
                                    <th>Position sRNA</th>
                                    <th>Minimum energy</th>
                                    <th>Hybridization energy</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${rnaInteractions}" var="ri">
                                    <tr>
                                        <td><span><a href="geneInfo.htm?locusTag=${ri.mrna.locusTag}&type=${type}">${ri.mrna.locusTag}</a></span></td>
                                        <td>
                                            <span>${ri.copraPvalue}</span>
                                        </td>
                                        <td>
                                            <span>${ri.positionMrna}</span>
                                        </td>
                                        <td>
                                            <span>${ri.positionNcrna}</span>
                                        </td>
                                        <td>
                                            <span>${ri.energy}</span>
                                        </td>
                                        <td>
                                            <span>${ri.hybridizationEnergy}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>




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
            $('#homologous-table').DataTable({
                "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                "order": [[3, "asc"]]
            });
        if (${type eq 'experimental'}) {
                $('#regulatedby-table').DataTable({
                    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                    "order": [[0, "asc"]]
                });
            } else {
                $('#regulatedby-table').DataTable({
                    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                    "order": [[7, "asc"]]
                });
            }
            $('#regulates-table').DataTable({
                "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                "order": [[5, "desc"]]
            });
            $('[data-toggle="popover"]').popover();
        });
    </script>
</body>
</html>