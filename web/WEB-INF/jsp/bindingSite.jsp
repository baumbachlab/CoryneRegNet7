<%-- 
    Document   : bSOtherUpstreamSeq
    Created on : Dec 12, 2018, 4:38:51 PM
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
        <link type="text/css" rel="stylesheet" href="css/bsSearch.css">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.cssf">
    </head>

    <script type="text/javascript">
        function ajax() {
            location.reload();
        }


        function runBs() {
            $.ajax({
                url: 'predictBindingSite.htm?jobNumber=${jobNumber}'
            });

            //window.alert("teste");
            $("#job-status").html("<div class='alert alert-primary w-100' role='alert'>" +
                    "<b>Your prediction is running!<br>  The page will automatically refresh every 30 seconds." +
                    "DO NOT refresh this page manually, instead use this link to access the results " +
                    "<a href='https://www.exbio.wzw.tum.de/coryneregnet/preBindingSite.htm?currentJob=${jobNumber}' class='alert-link' style='font-size: inherit;'>" +
                    "https://www.exbio.wzw.tum.de/coryneregnet/preBindingSite.htm?currentJob=${jobNumber}</a></b>" +
                    "</div>");

            console.log('1');

            setTimeout(function afterTenSeconds() {
                location.href = 'preBindingSite.htm?currentJob=${jobNumber}'
            }, 10000);

            console.log('3');
            //$("#prediction-button").html("HAHAHAHAHHAHAHAHA");
            //$("#prediction-button").removeClass('btn btn-primary btn-normal').addClass('btn btn-info btn-block');
            // wait(9000);
            //alert("Nine seconds");
            //location.href = 'preBindingSite.htm?currentJob=${jobNumber}';
            //setTimeout(location.href = 'preBindingSite.htm?currentJob=${jobNumber}', 20000);
        }

    </script>
    <script type="text/javascript">
        var intervalId = 0;
        var jobStatus = '${jobStatus}';

        if (jobStatus == "In progress") {
            intervalId = setInterval(ajax, 30000);
        }
    </script>
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
                <a href="geneInfo.htm?locusTag=${gene.locusTag}&type=${type}"><button type="button" class="btn btn-primary btn-normal">Go Back</button></a>
            </div>
            <div class="col-sm-4 col-8">
                <center>
                    <a href="#bottom-button"><button type="button" class="btn btn-primary btn-normal">Bottom</button></a>
                    <c:choose>
                        <c:when test="${type eq 'experimental'}">
                            <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                        </c:when>    
                        <c:otherwise>
                            <a href="searchPredictedData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                        </c:otherwise>
                    </c:choose>
                </center>
            </div>
        </div>
    </div>
    <div class="container-fluid badge badge-light space-to-footer">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="row">
            <div class="col-sm-12">
                <p class="font" style="font-size: 20px;">Used parameters:</p>
                <p>Job number: ${jobNumber}</p> 
                <p>Job status: ${jobStatus}</p> 
                <c:choose>
                    <c:when test="${bsSearchType eq 1}">
                        <p>Regulator: <a href="geneInfo.htm?locusTag=${gene.locusTag}&type=${type}">${gene.locusTag}</a> <c:if test="${not empty gene.name}">(${gene.name})</c:if></p>
                        <p>Used background model organism: <a href="organismInfo.htm?id=${gene.genome.organism.id}&type=${type}">${gene.genome.organism.genera} ${gene.genome.organism.species} ${gene.genome.organism.strain}</a></p>
                        <p>E-value cutOff: <c:choose><c:when test="${evalue eq 'default'}">Hmmer's default</c:when><c:otherwise>${evalue}</c:otherwise></c:choose></p>
                    </c:when>    
                    <c:otherwise>
                        <p>Upstream region of gene: <a href="geneInfo.htm?locusTag=${gene.locusTag}&type=${type}">${gene.locusTag}</a> <c:if test="${not empty gene.name}">(${gene.name})</c:if></p>
                        <p>E-value cutOff: <c:choose><c:when test="${evalue eq 'default'}">Hmmer's default</c:when><c:otherwise>${evalue}</c:otherwise></c:choose></p>
                        <p>Used HMM profiles: ${usedHMMs}</p>
                        <p>With minimal HMM profile quality: ${minNOfHMMs}</p>
                        <p>Upstream region relative to gene: ${upStreamRegion}</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <c:choose>

            <c:when test = "${jobStatus eq 'new'}">
                <%--button--%>
                <div class="row" id="job-status" style="margin-top: 20px">
                    <div class="col-sm-12" style="padding: 0px; margin-top: 15px;">
                        <button type="button" id="prediction-button" class="btn btn-primary btn-personalized form-control" onclick="runBs()">Confirm parameters & Run prediction</button>
                    </div>
                </div>
            </c:when>

            <c:when test = "${jobStatus eq 'In progress'}">
                <div class='alert alert-primary w-100' role='alert'>
                    <b>Your prediction is running! The page will automatically refresh every 30 seconds.<br>
                        DO NOT refresh this page manually, instead use this link to access the results 
                        <a href='https://www.exbio.wzw.tum.de/coryneregnet/preBindingSite.htm?currentJob=${jobNumber}' class='alert-link' style='font-size: inherit;'>
                            https://www.exbio.wzw.tum.de/coryneregnet/preBindingSite.htm?currentJob=${jobNumber}
                        </a></b>
                </div>
            </c:when>

            <c:otherwise>
                <%--table--%>
                <div class="row" style="margin-top: 20px">
                    <div class="col-sm-12">
                        <table id="predicted-bss" class="table table-striped text-center" style="width:100%">
                            <c:if test="${genesFound ne 0}">
                                <thead>
                                    <tr>
                                        <th>HMM from<br>(locus tag)</th>
                                        <th>HMM from<br>(gene name)</th>
                                        <th>Target gene<br>(locus tag)</th>
                                        <th>Target gene<br>(gene name)</th>
                                        <th>Predicted operon</th>
                                        <th>e-value</th>
                                        <th>Sequence</th>
                                        <th>Already known to be regulated<br> by source gene?</th>
                                            <c:choose>
                                                <c:when test="${bsSearchType eq 1}">
                                                <th>Homologous TF candidates<br>in the target organism</th>
                                                <th>Homologous TG candidates<br>in the template organism</th>
                                                </c:when>    
                                                <c:otherwise>
                                                <th>Homologous TF candidates<br>in the template organism</th>
                                                <th>Homologous TG candidates<br>in the target organism</th>
                                                </c:otherwise>
                                            </c:choose>
                                </thead>
                            </c:if>
                            <tbody> 
                                <c:forEach items="${bsRegPredictions}" var="bsPredicted">
                                    <tr>
                                        <th><span><a href="geneInfo.htm?locusTag=${bsPredicted.transcriptionFactor.locusTag}&type=${type}">${bsPredicted.transcriptionFactor.locusTag}</a></span></th>
                                                    <c:choose>
                                                        <c:when test="${bsPredicted.transcriptionFactor.name eq ''}">
                                                <th><span>-</span></th>
                                                </c:when>    
                                                <c:otherwise>
                                                <th><span>${bsPredicted.transcriptionFactor.name}</span></th>
                                                    </c:otherwise>
                                                </c:choose>
                                        <th><span><a href="geneInfo.htm?locusTag=${bsPredicted.targetGene.locusTag}&type=${type}">${bsPredicted.targetGene.locusTag}</a></span></th>
                                                    <c:choose>
                                                        <c:when test="${bsPredicted.targetGene.name eq ''}">
                                                <th><span>-</span></th>
                                                </c:when>    
                                                <c:otherwise>
                                                <th><span>${bsPredicted.targetGene.name}</span></th>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${bsPredicted.operon.name eq null}">
                                                <th><span>-</span></th>
                                                </c:when>    
                                                <c:otherwise>
                                                <th><span><a href="operonInfo.htm?name=${bsPredicted.operon.name}&type=${type}">${bsPredicted.operon.name}</a></span></th>
                                                        </c:otherwise>
                                                    </c:choose>
                                        <th><span>${bsPredicted.evalue}</span></th>
                                                <c:choose>
                                                    <c:when test="${bsPredicted.sequence eq null}">
                                                <th><span>-</span></th>
                                                </c:when>    
                                                <c:otherwise>
                                                <th><span>${bsPredicted.sequence}</span></th>
                                                    </c:otherwise>
                                                </c:choose>
                                        <th><span>${bsPredicted.knowedRegulation}</span></th>
                                        <th>
                                            <c:choose>
                                                <c:when test="${bsPredicted.homologousTFs eq '-'}">
                                                    <span>${bsPredicted.homologousTFs}</span>
                                                </c:when>    
                                                <c:otherwise>
                                                    <span><a href="geneInfo.htm?locusTag=${bsPredicted.homologousTFs}&type=${type}">${bsPredicted.homologousTFs}</a></span>
                                                    </c:otherwise>
                                                </c:choose>
                                        </th>
                                        <th>
                                            <c:choose>
                                                <c:when test="${bsPredicted.homologousTGs eq '-'}">
                                                    <span>${bsPredicted.homologousTGs}</span>
                                                </c:when>    
                                                <c:otherwise>
                                                    <span><a href="geneInfo.htm?locusTag=${bsPredicted.homologousTGs}&type=${type}">${bsPredicted.homologousTGs}</a></span>
                                                    </c:otherwise>
                                                </c:choose>
                                        </th>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>



    </div>
    <div class="container-fluid font">
        <div class="row go-top-row">
            <div class="col-sm-12">
                <center>
                    <a href="#top-button"><button id="bottom-button" type="button" class="btn btn-primary btn-normal">Top</button></a>
                    <c:choose>
                        <c:when test="${type eq 'experimental'}">
                            <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                        </c:when>
                        <c:otherwise>
                            <a href="searchPredictedData.htm"><button type="button" class="btn btn-primary btn-normal">New search</button></a>
                        </c:otherwise>
                    </c:choose>
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
            $('#predicted-bss').DataTable({
                "lengthMenu": [[-1, 10, 25, 50], ["All", 10, 25, 50]],
                "order": [[5, "asc"]]
            });
        });
    </script>
</body>
</html>
