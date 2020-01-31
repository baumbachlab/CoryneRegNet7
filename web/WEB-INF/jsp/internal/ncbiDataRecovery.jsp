<%-- 
    Document   : ncbiDataRecovery
    Created on : Jan 24, 2019, 3:25:57 PM
    Author     : doglas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link type="text/css" rel="stylesheet" href="css/ncbiDataRecovery.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <script type="text/javascript">
            var i=0;
            function ajax() {
                i++;
                console.log(document.readyState);
            }
        </script>
        <script type="text/javascript">
            var intervalIdOpStatus = 0;
            intervalIdOpStatus = setInterval(ajax, 5);
        </script>
    </head>

    <body style="background-image: url('images/background.png'); background-size: cover;">

    <nav class="navbar navbar-expand-md navbar-inverse bg-dark fixed-top navbar-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.htm"><span class="color logo-size">Coryne</span><span class="color-reg logo-size">Reg</span><span class="color logo-size">Net</span><span class="color-reg logo-size"> 7</span></a>
            </div>
            <div class="w-100 text-right">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>  
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row search-database">
            <div class="col-sm-12 font">
                <div class="title-size text-center centered-top">Download new target genomes from NCBI</div>
            </div>
        </div>
    </div>

    <div class="container-fluid badge badge-light" style="opacity: 0.99;">
        <form action="organismsToDownload.htm" method="POST">
            <hr>
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">                    
                    <div class="row">
                        <input type="hidden" value="${speciesNames}" name="speciesNames">
                        <input type="hidden" value="${path}" name="path">
                        <div class="col-sm-3 check-options">
                            <input type="radio" name="group" id="select-all" onclick="allOrganisms()" value="all" checked>All<br>
                            <input type="radio" name="group" id="genus" onclick="chooseGenus()"  value="genus">Genus<br>
                            <input type="radio" name="group" id="species" onclick="chooseSpecies()"  value="species">Species
                        </div>
                        <div class="col-sm-9">
                            <div class="row check-options">
                                <div id="all-genomes" class="col-sm-12 no-padding">
                                    <p>This will download all complete genomes of NCBI</p>
                                </div>
                                <%--GENERA NAMES--%>
                                <div id="choose-genus" class="col-sm-12 no-padding" style="display: none;">
                                    <c:forEach items="${generaNames}" var="generaName">
                                        <input type="checkbox" name="generaList" value="${generaName}">${generaName}<br>
                                    </c:forEach>
                                </div>
                                <%--SPECIES NAMES--%>
                                <div id="choose-species" class="col-sm-12 no-padding" style="display: none;">
                                    <c:forEach items="${speciesNames}" var="speciesName">
                                        <input type="checkbox" name="speciesList" value="${speciesName}">${speciesName}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3 font" style="margin-top: 30px">
                    <%-- <input formaction="organismsToDownload.htm?group=${group}&generaList=${generaList}&speciesList=${speciesList}" type="submit" class="btn btn-primary btn-personalized btn-block" value="Download"> --%>
                    <input class="btn btn-primary btn-personalized" type="submit" value="Download">
                </div>
            </div>
        </form>
        <hr>
    </div>

    <div style="margin-bottom: 150px"></div>

    <div class="container-fluid font">
        <div class="footer">
            <div class="row">
                <div class="col-sm-12">
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <span>Developed by: Mariana Parise, Doglas Parise, Vasco Azevedo and Jan Baumbach (2018) - CoryneRegNet 7.0</span>
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
        function allOrganisms() {
            document.getElementById('all-genomes').style.display = "block";
            document.getElementById('choose-genus').style.display = "none";
            document.getElementById('choose-species').style.display = "none";
        }
        function chooseGenus() {
            document.getElementById('all-genomes').style.display = "none";
            document.getElementById('choose-genus').style.display = "block";
            document.getElementById('choose-species').style.display = "none";
        }
        function chooseSpecies() {
            document.getElementById('all-genomes').style.display = "none";
            document.getElementById('choose-genus').style.display = "none";
            document.getElementById('choose-species').style.display = "block";
        }

    </script>
</body>
</html>