<%-- 
    Document   : experimentaldata
    Created on : Oct 28, 2018, 6:07:48 PM
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
        <script type="text/javascript" src="js/mainjs.js"></script>
        <script type="text/javascript" src="js/teste.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">

        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
    </head>
    <script>
        function getNumberOfRows() {
            var length = $('tr:visible').length;
            document.getElementById("table-length").innerHTML = "Results (" + (parseInt(length) - 1) + " found)";
            //$("#table-length").load();
        }

        function chooseFilter() {
            var value = document.getElementById("filters").value;

            if (value == 'all') {
                //spinner
                showAllRows();
            }

            if (value == 'tfs') {
                showAllRows();
                showRows('tf');//
            }

            if (value == 'tg') {
                showAllRows();
                showRows('regulated-by-both');
            }

            if (value == 'reg-tf') {
                showAllRows();
                showRows('regulated-by-tf');
            }

            if (value == 'reg-srna') {
                showAllRows();
                showRows('regulated-by-rna');
            }

            if (value == 'reg-tf-srna') {
                showAllRows();
                showRowsTags('regulated-by-rna', 'regulated-by-tf');
            }

            getNumberOfRows();
            myFunction();


            //alert('teste2');



        }

        function showAllRows() {
            $("[tf]").show();
        }

        //regulated-by-tf
        function showRows(tag) {
            if ($("[" + tag + "=0]").is(":visible")) {
                $("[" + tag + "=0]").hide();
            }
        }

        function showRowsTags(tag1, tag2) {
            if ($("[" + tag1 + "=0]").is(":visible") && $("[" + tag2 + "=0]").is(":visible")) {
                $("[" + tag1 + "=0]").hide();
                $("[" + tag2 + "=0]").hide();
            }
        }

        function myFunction() {
            var x = document.getElementById("myDIV");
            //var t = document.getElementById("teste");

            if (x.style.display === "none") {
                // t.innerHTML = "loading.....";
                x.style.display = "block";
            } else {
                //  t.innerHTML = "";
                x.style.display = "none";
            }

        }
    </script>
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
                <div class="title-size text-center">Experimental data</div>
            </div>
        </div>
    </div>

    <div class="container-fluid badge badge-light space-to-footer">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="row">
            <div class="col-sm-3">
                <label for="filters">Filter gene table: </label>
                <select id="filters" class="form-control" onchange="myFunction();chooseFilter()">
                    <option value="all">See all genes</option>
                    <option value="tfs">Genes enconding regulatory proteins</option>
                    <option value="reg-tf">Genes regulated by regulatory proteins</option>
                    <%--<option value="reg-srna">Genes regulated by sRNAs</option>
                    <option value="tg">Genes regulated by regulatory proteins or sRNAs</option>
                    <option value="reg-tf-srna">Genes regulated by regulatory proteins and sRNAs</option>--%>
                </select>
            </div>
             <div class="col-sm-2" id="myDIV" style="display: none;">
                <div class="loader"></div> We are loading your filters... 
            </div>
            <div class="col-sm-7" id="teste">
            </div>

        </div>
        <div class="row">
            <div class="col-sm-12 text-center tables-top" style="font-size: 22px">
                <p class="font" id="table-length">Results (${fn:length(tableViews)} found)</p>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <table id="experimental-data" class="table table-striped text-center" style="width:100%">
                    <c:if test="${genesFound ne 0}">
                        <thead>
                            <tr>
                                <th>Gene ID</th>
                                <th>Alt. Gene ID</th>
                                <th>Gene name</th>
                                <th>Protein id</th>
                                <th>Product</th>
                                <th>Predicted operon</th>
                                <th>Organism</th>
                                <th>Regulated by</th>
                                <th>Regulates</th>
                                <th>Regulation</th>
                            </tr>
                        </thead>
                    </c:if>
                    <tbody> 
                        <c:forEach items="${tableViews}" var="g">
                            <tr tf="${g.countRegulatedGenes}" tg="${g.countRegRna}" regulated-by-tf="${g.countRegulatedBy}" regulated-by-rna="0" regulated-by-both="0">
                                <th><span><a href="geneInfo.htm?locusTag=${g.locusTag}&type=experimental">${g.locusTag}</a></span></th>
                                            <c:choose>
                                                <c:when test="${g.alternativeLocusTag eq ''}">
                                        <th><span>-</span></th>
                                        </c:when>    
                                        <c:otherwise>
                                        <th><span>${g.alternativeLocusTag}</span></th>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${g.geneName eq ''}">
                                        <th><span>-</span></th>
                                        </c:when>    
                                        <c:otherwise>
                                        <th><span>${g.geneName}</span></th>
                                            </c:otherwise>
                                        </c:choose>

                                <c:choose>
                                    <c:when test="${g.proteinId eq ''}">
                                        <th><span>-</span></th>
                                        </c:when>    
                                        <c:otherwise>
                                        <th><span><a href="https://www.ncbi.nlm.nih.gov/protein/=${g.proteinId}" target="_blank">${g.proteinId}</a></span></th>
                                                </c:otherwise>
                                            </c:choose>

                                <th><span>${g.product}</span></th>
                                        <c:choose>
                                            <c:when test="${g.operonName eq null}">
                                        <th><span>-</span></th>
                                        </c:when>    
                                        <c:otherwise>
                                            <c:set var = "opName" value = "${fn:replace(g.operonName, '>', '')}" />
                                        <th><span><a href="operonInfo.htm?name=${opName}&type=experimental">${opName}</a></span></th>              
                                                </c:otherwise>
                                            </c:choose>
                                <th><span><a href="organismInfo.htm?id=${g.organismId}&type=experimental">${g.organismName}</a></span></th>
                                <th>${g.countRegulatedBy}</th>
                                <th>${g.countRegulatedGenes}</th>
                                <th>
                                    <c:set var="emptyReg" value="0"/>
                                    <c:choose>
                                        <c:when test="${g.regulatedBy eq null}">
                                            <span>-</span>
                                            <c:set var="emptyReg" value="1"/>
                                        </c:when>    
                                        <c:otherwise>
                                            <span>Regulated by: 
                                            </span><br>
                                            <c:forTokens var="token" items="${g.regulatedBy}"
                                                         delims=";">
                                                <c:set var="i" value="0"/>
                                                <c:forTokens var="tokenInside" items="${token}" delims=",">
                                                    <c:choose>
                                                        <c:when test="${i eq 0}">
                                                            <a href="rnaInfo.htm?locusTag=${tokenInside}&type=experimental"><c:out value="${tokenInside}"/> </a>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${fn:length(tokenInside) eq 1}">
                                                                    , <c:out value="${tokenInside}"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    , <c:out value="${fn:substring(tokenInside, 0, 1)}"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>

                                                    </c:choose>
                                                    <c:set var="i" value="1"/>
                                                </c:forTokens><br>
                                            </c:forTokens>
                                        </c:otherwise>
                                    </c:choose>

                                    <br>

                                    <c:choose>
                                        <c:when test="${g.regulatedGenes eq null}">
                                            <c:choose>
                                                <c:when test="${emptyReg eq 1}">
                                                </c:when>    
                                                <c:otherwise>
                                                    <span>-</span>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:when>    
                                        <c:otherwise>
                                            <span>Regulates:</span><br>
                                            <c:forTokens var="token" items="${g.regulatedGenes}" delims=";">
                                                <c:set var="i" value="0"/>
                                                <c:forTokens var="tokenInside" items="${token}" delims=",">
                                                    <c:choose>
                                                        <c:when test="${i eq 0}">
                                                            <a href="geneInfo.htm?locusTag=${tokenInside}&type=experimental"><c:out value="${tokenInside}"/> </a>
                                                        </c:when>    
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${fn:length(tokenInside) eq 1}">
                                                                    , <c:out value="${tokenInside}"/>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    , <c:out value="${fn:substring(tokenInside, 0, 1)}"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>

                                                    </c:choose>
                                                    <c:set var="i" value="1"/>
                                                </c:forTokens><br>
                                            </c:forTokens>
                                        </c:otherwise>
                                    </c:choose>
                                </th>
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
                "order": [[0, "asc"]]
            });

            $('[data-toggle="popover"]').popover();
        });
    </script>
</body>
</html>