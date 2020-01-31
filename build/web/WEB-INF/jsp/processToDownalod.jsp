<%-- 
    Document   : download
    Created on : Mar 6, 2019, 10:20:34 AM
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
                        <a class="nav-link menu-item link-color flex-nowrap" href="#">Download</a>
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

    <div id="browser-messager" class="container-fluid" style="display: none;">
        <div class="alert alert-warning alert-dismissible centered-top" style="margin-bottom: -120px;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            CoryneRegNet is better visualized in Google Chrome, Mozilla Firefox, Opera and Internet Explorer 10+.
        </div>
    </div>

    <div class="container-fluid">
        <div class="row bottom-btn">
            <div class="col-sm-12 font" style="padding-left: 0px;">
                <a><button class="btn btn-primary btn-normal" onclick="goBack()">Go Back</button></a>
                <div class="title-size text-center">Download from CoryneRegNet database</div>
            </div>
        </div>
    </div>
    <div class="container-fluid badge badge-light space-to-footer">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="container centered-top centered-top-process-to-download" style="font-size: 20px;">
            <form method="POST">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-12">
                                <label for="exp">From:</label><br>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-5" style="padding-right: 0px; padding-left: 0px">
                                <label class="radio-inline"><input type="radio" name="from" id="exp" value="exp" onclick="exp1()" checked> Experimental</label>
                            </div>
                            <div class="col-sm-7" style="padding-right: 0px; padding-left: 0px">
                                <label class="radio-inline"><input type="radio" name="from"  value="pred" onclick="pred()"> Experimental + Predicted</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6" id="experimental">
                        <label for="organism">Organism:</label><br>
                        <select id="organism" name="organismExp" style="width: 100%">
                            <option value="0">All</option>
                            <c:forEach items="${modelOrganisms}" var="organism">
                                <option value="${organism.id}">${organism.genera} ${organism.species} ${organism.strain}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-6" id="predicted" style="display: none;">
                        <label for="organism">Organism:</label><br>
                        <select id="organism" name="organismPred" style="width: 100%">
                            <option value="0">All</option>
                            <c:forEach items="${organisms}" var="organism">
                                <option value="${organism.id}">${organism.genera} ${organism.species} ${organism.strain}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row font" style="margin: 20px 0 20px 0">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-4">
                        <input id="search-type" type="hidden" name="searchType" value="0">
                        <input formaction="download.htm" type="submit" class="btn btn-primary btn-block btn-normal" value="Download">
                    </div>
                    <div class="col-sm-4"></div>
                </div>
            </form>
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
        function exp1() {
            document.getElementById('experimental').style.display = "block";
            document.getElementById('predicted').style.display = "none";
            document.getElementById('search-type').value = "0";
        }
        function pred() {
            document.getElementById('experimental').style.display = "none";
            document.getElementById('predicted').style.display = "block";
            document.getElementById('search-type').value = "1";
        }

        var firefox = navigator.userAgent.indexOf('Firefox') >= 0;
        var chrome = navigator.userAgent.indexOf('Chrome') >= 0;
        var opera = navigator.userAgent.indexOf('Opera') >= 0;
        if (opera == false) {
            opera = navigator.userAgent.indexOf('OPR') >= 0;
        }
        var edge = window.navigator.userAgent.indexOf("Edge") >= 0;

        //console.log("firefox: " + firefox);
        //console.log("chrome: " + chrome);
        //console.log("opera: " + opera);
        //console.log("edge: " + edge);

        if (firefox == false && chrome == false && opera == false && edge == false) {
            var ie = Check_IE_Version();
            console.log("ie: " + ie);
            if (ie < 10) {
                //console.log("Showing message!");
                document.getElementById('browser-messager').style.display = "block";
            }
        }

        function Check_IE_Version() {
            var rv = -1; // Return value assumes failure.

            if (navigator.appName == 'Microsoft Internet Explorer') {

                var ua = navigator.userAgent,
                        re = new RegExp("MSIE ([0-9]{1,}[\\.0-9]{0,})");
                if (re.exec(ua) !== null) {
                    rv = parseFloat(RegExp.$1);
                }
            } else if (navigator.appName == "Netscape") {
                /// in IE 11 the navigator.appVersion says 'trident'
                /// in Edge the navigator.appVersion does not say trident
                if (navigator.appVersion.indexOf('Trident') === 1) {
                    rv = 11;
                }
            }
            return rv;
        }
    </script>
</body>
</html>
