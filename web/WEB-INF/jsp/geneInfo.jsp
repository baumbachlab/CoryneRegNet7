<%-- 
    Document   : geneInfo
    Created on : Nov 8, 2018, 10:07:53 AM
    Author     : doglas
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
                <a class="nav-link tab-color active" data-toggle="tab" href="#gene">Gene: ${gene.locusTag} 
                    <c:if test="${not empty gene.name}">
                        (${gene.name})
                    </c:if>
                </a>
            </li>
            <c:if test="${not empty gene.proteinId}">
                <li class="nav-item">
                    <a class="nav-link tab-color" data-toggle="tab" href="#protein">ProteinID: ${gene.proteinId}</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link tab-color" data-toggle="tab" href="#homologous">Homologous proteins</a>
            </li>
            <c:if test="${not empty regulatedBy || (not empty rnaRegViews && type eq 'predicted') }">
                <li class="nav-item">
                    <a class="nav-link tab-color" data-toggle="tab" href="#regulatedBy">Regulated by:</a>
                </li>
            </c:if>
            <c:if test="${not empty regulates}">
                <li class="nav-item">
                    <a class="nav-link tab-color" data-toggle="tab" href="#regulates">Regulates:</a>
                </li>
            </c:if>
            <li class="nav-item"><a class="nav-link tab-color" data-toggle="tab" href="#genePos">Gene position <c:if test="${not empty hmmLogoName}">and HMM logo</c:if></a></li>
                <li class="nav-item"><a class="nav-link tab-color" data-toggle="tab" href="#bsPrediction">Binding site prediction</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content container-fluid">
                <div id="gene" class="tab-pane active gene-info"><br>
                    <div class="row">
                        <div class="col-sm-8">
                            <span>Gene name: </span> 
                        <c:choose>
                            <c:when test="${not empty gene.name}">
                                <span>${gene.name}</span>
                            </c:when>    
                            <c:otherwise>
                                <span>-</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-4">

                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-8">
                        <span>Gene ID: ${gene.locusTag}</span>
                    </div>
                    <div class="col-sm-4"></div>
                </div>
                <div class="row">
                    <div class="col-sm-8">
                        <span>Alt. Gene ID: </span>
                        <c:choose>
                            <c:when test="${not empty gene.alternativeLocusTag}">
                                <span>${gene.alternativeLocusTag}</span>
                            </c:when>    
                            <c:otherwise>
                                <span>-</span>
                            </c:otherwise>
                        </c:choose>                        
                    </div>
                    <div class="col-sm-4"></div>
                </div>
                <div class="row">
                    <div class="col-sm-8">
                        <span>Operon: </span>
                        <c:choose>
                            <c:when test="${not empty operon}">
                                <span><a href="operonInfo.htm?name=${operon.name}&type=${type}">${operon.name}</a></span>
                                </c:when>    
                                <c:otherwise>
                                <span>-</span>
                            </c:otherwise>
                        </c:choose>                        
                    </div>
                    <div class="col-sm-4"></div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Organism: <a href="organismInfo.htm?id=${gene.genome.organism.id}&type=${type}">${gene.genome.organism.genera} ${gene.genome.organism.species} ${gene.genome.organism.strain}</a> - <a href="https://www.ncbi.nlm.nih.gov/genome/?term=${gene.genome.organism.genera} ${gene.genome.organism.species} ${gene.genome.organism.strain}" target="_blank">NCBI</a></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Nucleotide sequence:</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>${gene.nucleotideSequence}</span>
                    </div>
                </div>
            </div>
            <div id="protein" class="tab-pane fade gene-info"><br>
                <div class="row">
                    <div class="col-sm-12">
                        <span>ProteinID: ${gene.proteinId} - <a href="https://www.ncbi.nlm.nih.gov/protein/${gene.proteinId}" target="_blank">NCBI</a></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Product: </span>
                        <c:choose>
                            <c:when test="${not empty gene.product}">
                                <span>${gene.product}</span>
                            </c:when>    
                            <c:otherwise>
                                <span>-</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Organism: <a href="organismInfo.htm?id=${gene.genome.organism.id}&type=${type}">${gene.genome.organism.genera} ${gene.genome.organism.species} ${gene.genome.organism.strain}</a> - <a href="https://www.ncbi.nlm.nih.gov/genome/?term=${gene.genome.organism.genera} ${gene.genome.organism.species} ${gene.genome.organism.strain}" target="_blank">NCBI</a></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Protein sequence:</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>${gene.proteinSequence}</span>
                    </div>
                </div>
            </div>
            <div id="homologous" class="tab-pane fade tables-top">
                <div class="row" style="padding-bottom: 15px;">
                    <div class="col-sm-12">
                        <span class="gene-info">Candidate homologous of gene ${gene.locusTag} <c:if test="${not empty gene.name}">(${gene.name})</c:if>:</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12" style="padding-left: 0px">
                            <table id="homologous-table" class="table table-striped text-center" style="width:100%">
                                <thead>
                                    <tr>
                                        <th>Homologous gene ID</th>
                                        <th>Homologous gene name</th>
                                        <th>Homologous protein id</th>
                                        <th>Organism</th>
                                </thead>
                                <tbody> 
                                <c:forEach items="${orthologous}" var="ort">
                                    <tr>
                                        <td><span><a href="geneInfo.htm?locusTag=${ort.gene.locusTag}&type=${ort.dbType}">${ort.gene.locusTag}</a></span></td>
                                                <c:choose>
                                                    <c:when test="${ort.gene.name eq ''}">
                                                <td><span>-</span></td>
                                            </c:when>    
                                            <c:otherwise>
                                                <td><span>${ort.gene.name}</span></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${ort.gene.proteinId eq ''}">
                                                <td><span>-</span></td>
                                            </c:when>    
                                            <c:otherwise>
                                                <td><span>${ort.gene.proteinId}</span></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td><span><a href="organismInfo.htm?id=${ort.gene.genome.organism.id}&type=${dbType}">${ort.gene.genome.organism.genera} ${ort.gene.genome.organism.species} ${ort.gene.genome.organism.strain}</span></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%--regulatedby: (The gene is a TGs)--%>
            <div id="regulatedBy" class="tab-pane fade tables-top">
                <div class="row" style="padding-bottom: 15px;">
                    <div class="col-sm-12">
                        <span class="gene-info">Gene ${gene.locusTag} <c:if test="${not empty gene.name}">(${gene.name})</c:if> is regulated by:</span>
                        </div>
                    </div>
                    <div class="row">
                    <c:choose>
                        <c:when test="${not empty regulatedBy}">
                            <div class="col-sm-12">

                                <table id="regulatedby-table" class="table table-striped text-center" style="width:100%">
                                    <thead>
                                        <tr>
                                            <c:if test="${type eq 'predicted'}">
                                                <th>Type</th>
                                                </c:if>
                                            <th>Gene ID</th>
                                            <th>Gene name</th>
                                            <th>Protein id</th>
                                            <th>Product</th>
                                            <th>Role</th>
                                                <c:if test="${type eq 'predicted'}">
                                                <th>Model Organism</th>
                                                </c:if>
                                            <th>Evidence</th>
                                                <c:if test="${type eq 'predicted'}">
                                                <th>p-value</th>
                                                </c:if>
                                            <th>Binding motifs</th>
                                                <c:if test="${type eq 'experimental'}">
                                                <th>PubMed</th>
                                                </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${regulatedBy}" var="tf">
                                            <tr>
                                                <c:if test="${type eq 'predicted'}">
                                                    <td>TF</td>
                                                </c:if>
                                                <td><span><a href="geneInfo.htm?locusTag=${tf.transcriptionFactor.locusTag}&type=${type}">${tf.transcriptionFactor.locusTag}</a></span></td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${not empty tf.transcriptionFactor.name}">
                                                            <span>${tf.transcriptionFactor.name}</span>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <span>-</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td><span><a href="geneInfo.htm?locusTag=${tf.transcriptionFactor.locusTag}&type=${type}">${tf.transcriptionFactor.proteinId}</a></span></td>
                                                <td><span>${tf.transcriptionFactor.product}</span></td>
                                                <td><span>${tf.role}</span></td> 
                                                <c:if test="${type eq 'predicted'}">
                                                    <td>
                                                        <span>
                                                            <a href="organismInfo.htm?id=${tf.modelOrganism.id}&type=experimental" target="_blank">${tf.modelOrganism.genera} ${tf.modelOrganism.species} ${tf.modelOrganism.strain}</a>

                                                            <c:if test="${empty tf.modelOrganism.genera}">-</c:if>
                                                            </span>
                                                        </td>

                                                </c:if>

                                                <td><span>${tf.evidence}</span></td>
                                                <c:if test="${type eq 'predicted'}">
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${not empty tf.pValue}">
                                                                <span>${tf.pValue}</span>
                                                            </c:when>    
                                                            <c:otherwise>
                                                                <span>-</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </c:if>
                                                <td><span>${tf.bindingSite}</span></td>
                                                <c:if test="${type eq 'experimental'}">
                                                    <c:if test="${tf.pmid eq '-'}"><th><span>${tf.pmid}</span></th></c:if>
                                                    <c:if test="${tf.pmid ne '-'}"><td><span><a href="https://www.ncbi.nlm.nih.gov/pubmed/?term=${tf.pmid}" target="_blank">${tf.pmid}</a></span></td></c:if>
                                                        </c:if>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${type eq 'predicted'}">
                                            <c:forEach items="${rnaRegViews}" var="rna">
                                                <tr>
                                                    <td>sRNA</td>
                                                    <td><span><a href="rnaInfo.htm?locusTag=${rna.smallRna.locusTag}&type=${type}">${rna.smallRna.locusTag}</a></span></td>
                                                    <td>
                                                        <span>-</span>
                                                    </td>
                                                    <td><span>-</span></td>
                                                    <td><span>-</span></td>
                                                    <td><span>-</span></td> 
                                                    <c:if test="${type eq 'predicted'}"><td><span>-</span></td></c:if>
                                                    <td><span>${rna.evidence}</span></td>
                                                    <c:if test="${type eq 'predicted'}">
                                                        <td>
                                                            <span>${rna.copraPvalue}</span>
                                                        </td>
                                                    </c:if>
                                                    <td><span>-</span></td>
                                                    <c:if test="${type eq 'experimental'}">
                                                        <td><span>-</span></td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>    
                                        </c:if>
                                    </tbody>
                                </table>

                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-sm-12">

                                <table id="regulatedby-table" class="table table-striped text-center" style="width:100%">
                                    <thead>
                                        <tr>
                                            <c:if test="${type eq 'predicted'}">
                                                <th>Type</th>
                                                </c:if>

                                            <th>RNA id</th>
                                            <th>Evidence</th>
                                                <c:if test="${type eq 'predicted'}">
                                                <th>p-value</th>
                                                </c:if>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${type eq 'predicted'}">
                                            <c:forEach items="${rnaRegViews}" var="rna">
                                                <tr>
                                                    <td>sRNA</td>
                                                    <td><span><a href="rnaInfo.htm?locusTag=${rna.smallRna.locusTag}&type=${type}">${rna.smallRna.locusTag}</a></span></td>
                                                    <td><span>${rna.evidence}</span></td>
                                                    <c:if test="${type eq 'predicted'}">
                                                        <td>
                                                            <span>${rna.copraPvalue}</span>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>    
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <%--regulates: (The gene is a TF)--%>
            <div id="regulates" class="tab-pane fade tables-top">
                <div class="row" style="padding-bottom: 15px;">
                    <div class="col-sm-12">
                        <span class="gene-info">Gene ${gene.locusTag} <c:if test="${not empty gene.name}">(${gene.name})</c:if> regulates:</span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="regulates-table" class="table table-striped text-center" style="width:100%">
                                <thead>
                                    <tr>
                                        <th>Gene ID</th>
                                        <th>Gene name</th>
                                        <th>Protein id</th>
                                        <th>Product</th>
                                        <th>Role</th>
                                        <th>Operon</th>
                                        <c:if test="${type eq 'predicted'}">
                                        <th>Model Organism</th>
                                        </c:if>
                                    <th>Evidence</th>
                                        <c:if test="${type eq 'predicted'}">
                                        <th>p-value</th>
                                        </c:if>
                                    <th>Binding motifs</th>
                                    <th>Coregulators</th>
                                        <c:if test="${type eq 'experimental'}">
                                        <th>PubMed</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${regulates}" var="tg">
                                    <tr>
                                        <td><span><a href="geneInfo.htm?locusTag=${tg.targetGene.locusTag}&type=${type}">${tg.targetGene.locusTag}</a></span></td>
                                        <td><c:choose>
                                                <c:when test="${not empty tg.targetGene.name}">
                                                    <span>${tg.targetGene.name}</span>
                                                </c:when>    
                                                <c:otherwise>
                                                    <span>-</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><span><a href="geneInfo.htm?locusTag=${tg.targetGene.locusTag}&type=${type}">${tg.targetGene.proteinId}</a></span></td>
                                        <td><span>${tg.targetGene.product}</span></td>
                                        <td><span>${tg.role}</span></td> 
                                        <c:if test="${tg.operon eq '-'}"><td><span>${tg.operon}</span></td></c:if>
                                        <c:if test="${tg.operon ne '-'}"><td><span><a href="operonInfo.htm?name=${tg.operon}&type=${type}">${tg.operon}</a></span></td></c:if>
                                                <c:if test="${type eq 'predicted'}">
                                            <td>
                                                <span><a href="organismInfo.htm?id=${tg.modelOrganism.id}&type=experimental" target="_blank">${tg.modelOrganism.genera} ${tg.modelOrganism.species} ${tg.modelOrganism.strain}</a>
                                                </span>
                                                <c:if test="${empty tf.modelOrganism.genera}">-</c:if>
                                                </td>
                                        </c:if>
                                        <td><span>${tg.evidence}</span></td>
                                        <c:if test="${type eq 'predicted'}">
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty tg.pValue}">
                                                        <span>${tg.pValue}</span>
                                                    </c:when>    
                                                    <c:otherwise>
                                                        <span>-</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </c:if>
                                        <td><span>${tg.bindingSite}</span></td>
                                        <td><span>${tg.coregulators}</span></td>
                                        <c:if test="${type eq 'experimental'}">
                                            <c:if test="${tg.pmid eq '-'}"><td><span>${tg.pmid}</span></td></c:if>
                                            <c:if test="${tg.pmid ne '-'}"><td><span><a href="https://www.ncbi.nlm.nih.gov/pubmed/?term=${tg.pmid}" target="_blank">${tg.pmid}</a></span></td></c:if>
                                                </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%--genePos and Logo--%>
            <div id="genePos" class="tab-pane fade gene-info"><br>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Gene start position: ${gene.startPosition}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <span>Gene end position: ${gene.endPosition}</span>
                    </div>
                </div>
                <div style="padding-bottom: 20px">
                    <div class="col-sm-12">
                        <c:if test="${not empty regulates}">
                            <span>TF is an autoregulator? ${autoregulator}</span>
                        </c:if>
                    </div>
                </div> 
                <c:choose>
                    <c:when test="${fn:endsWith(hmmLogoName, '.png')}">
                        <div class="row" id="download-profile" style="padding-bottom: 20px">
                            <div class="col-sm-12">
                                <a href="UploadDownloadFileServlet?gene=${gene.locusTag}&local=${gene.hmmProfile}">
                                    <button class="btn btn-primary" >Download HMM profile</button>
                                </a>
                            </div>        
                        </div>
                    </c:when>
                </c:choose>

                <div id="hmm-logo-loader" class="row" style="display: none;">
                    <center><span ><div class="loader" ></div> Loading hmmm logo</span></center>
                    <br><br>
                </div>
                <div class="row">
                    <c:set var="logoName" value="${hmmLogoName}"/>

                    <div id="hmm-logo" class="col-sm-12">
                        <c:choose>
                            <c:when test="${fn:endsWith(hmmLogoName, '.png')}">
                                <img src="data:image/png;base64,
                                     <%= new String(Base64.encode(Files.readAllBytes(Paths.get("/home/ubuntu/database/CoryneRegNet7/web/images/" + pageContext.getAttribute("logoName")))))%>"/>
                            </c:when>
                            <c:otherwise>
                                ${hmmLogoName}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%--bsPrediction--%>
            <div id="bsPrediction" class="tab-pane fade gene-info"><br>
                <div class="row bs-title">
                    <div class="col-sm-12">
                        <center>
                            <span style="font-size: 20px;"><b>Binding site search options</b></span>    
                        </center>
                    </div>                          
                </div>
                <form action="#" method="POST">
                    <div class="row check-options">
                        <div class="col-sm-12">
                            <input type="radio" name="colors" id="ustg" onclick="thisGene()" checked>
                            In the upstream sequence of this gene&nbsp;
                            <a tabindex="0" role="button" class="fa fa-question-circle" data-trigger="focus" data-toggle="popover" title="How it works?" data-content="It uses HMMER to predict binding sites in the upstream sequence of this gene using the hmmer profiles of transcription factors of the selected organism(s)." style="color: black; text-rendering: optimizeLegibility;"></a><br>
                            <input type="radio" name="colors" id="rous" onclick="otherGenes()">
                            For this regulator in other upstream sequences&nbsp;<a tabindex="1" role="button" class="fa fa-question-circle" data-trigger="focus" data-toggle="popover" title="How it works?" data-content="It uses HMMER to predict binding sites in the upstream sequence of the genes of the selected organism using the hmmer profile of this gene (if this gene is a transcription factor)." style="color: black; text-rendering: optimizeLegibility;">
                            </a>
                        </div>
                    </div>
                    <div class="row check-options">
                        <div id="otherGenes" class="col-sm-4 bs-prediction" style="padding-right: 10px;">
                            <label for="organism-search">Organism&nbsp;</label><a tabindex="2" role="button" class="fa fa-question-circle" data-trigger="focus" data-toggle="popover" title="For the HMM profiles of..." style="color: black; text-rendering: optimizeLegibility;"></a><br/>
                            <select id="organism-search" class="form-control" name="selectedOrganism">
                                <c:forEach items="${organisms}" var="organism">
                                    <option value="${organism.id}">${organism.genera} ${organism.species} ${organism.strain}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="bsProfiles" class="col-sm-4 bs-prediction" style="padding-right: 10px;">
                            <label for="number-bs">Nr. of BSs&nbsp;</label><a tabindex="3" role="button" class="fa fa-question-circle" data-trigger="focus" data-toggle="popover" title="Minimal nr. of binding sites the HMM profile was created from" style="color: black; text-rendering: optimizeLegibility;"></a>
                            <select class="form-control" id="number-bs" name="minNOfHMMs">
                                <c:forEach begin="1" end="50" varStatus="loop">
                                    <option value="${loop.index}">${loop.index}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-4 bs-prediction">
                            <label for="e-value">e-value cutOff:</label>
                            <select class="form-control" id="e-value" name="evalue">
                                <option selected value="default">default</option>
                                <option value="1e-2">1e-2 (10^-2)</option>
                                <option value="1e-3">1e-3 (10^-3)</option>
                                <option value="1e-4">1e-4 (10^-4)</option>
                                <option value="1e-5">1e-5 (10^-5)</option>
                                <option value="1e-6">1e-6 (10^-6)</option>
                                <option value="1e-7">1e-7 (10^-7)</option>
                                <option value="1e-8">1e-8 (10^-8)</option>
                                <option value="1e-9">1e-9 (10^-9)</option>
                                <option value="1e-10">1e-10 (10^-10)</option>
                            </select>
                        </div>
                    </div>
                    <div class="row font">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-4">
                            <div id="binding-sites-loader" style="display:none; margin-top: 50px;">
                                <center><span ><div class="loader" ></div>Searching binding sites</span></center>
                                <br><br>
                            </div>
                            <input id="bs-search-type" type="hidden" name="bsSearchType" value="0">
                            <input id="bs-search-type" type="hidden" name="id" value="${gene.id}">
                            <input id="bs-search-type" type="hidden" name="type" value="${type}">
                            <input id="bs-searcher" type="submit" formaction="preBindingSite.htm" class="btn btn-primary btn-block" value="Save parameters" onclick="showLoaderBSs()">
                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                </form>
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
        function thisGene() {
            document.getElementById('bsProfiles').style.display = "block";
            document.getElementById('organism-search').name = "selectedOrganism";
            //var x = document.getElementById('organism-search').name;
            document.getElementById('bs-search-type').value = "0";
        }
        function otherGenes() {
            document.getElementById('bsProfiles').style.display = "none";
            document.getElementById('organism-search').name = "organismId";
            //var x = document.getElementById('organism-search').name;
            document.getElementById('bs-search-type').value = "1";
        }
    </script>

    <c:if test="${not empty hmmLogoName}">
        <script type="text/javascript">
            if ('${hmmLogoName}'.startsWith("Skyalign")) {
                document.getElementById("hmm-logo").innerHTML = '${hmmLogoName}';
            } else {
                var t = 0;
                var intervalIdOpStatus = 0;

                intervalIdOpStatus = setInterval(showHmmLogo, 1000, t);
                // document.getElementById("hmm-logo").innerHTML = "We are bringing your logo. Please, refresh the page to see the hmm logo.";
            }

            function showHmmLogo() {

                t++;
                var x = document.getElementById('logo');
                //console.log("===> " + x);
                //console.log("x.naturalWidth: " + x.naturalWidth);
                if (x.naturalWidth === 0) {
                    hideHmmLogo();
                } else {
                    hmmLogo();
                    clearInterval(intervalIdOpStatus);
                }
                //console.log("t: " + t);
                if (t == 40) {
                    //console.log("ifffff " + x);
                    clearInterval(intervalIdOpStatus);
                    hmmLogoError();
                    document.getElementById("hmm-logo-error").innerHTML = "The image is taking to long to load. Please try again in a few minutes!!!";
                }
            }
        </script>
    </c:if>

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
