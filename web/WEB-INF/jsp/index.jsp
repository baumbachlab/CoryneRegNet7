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
                <a class="navbar-brand" href="#"><span class="color logo-size">Coryne</span><span class="color-reg logo-size">Reg</span><span class="color logo-size">Net</span><span class="color-reg logo-size"> 7</span></a>
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
    <div id="browser-messager" class="container-fluid" style="display: none;">
        <div class="alert alert-warning alert-dismissible centered-top" style="margin-bottom: -150px;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            CoryneRegNet is better visualized in Google Chrome, Mozilla Firefox, Opera and Internet Explorer 10+.
        </div>
    </div>
    <div class="container"> 
        <div class="row font">
            <div class="col-sm-12 font">
                <div class="title-size text-center centered-top">
                    Welcome to the reference database and analysis platform for <span class="color">coryne</span>bacterium transcription 
                    factors and gene <span class="color">reg</span>ulatory <span class="color">net</span>works
                </div>
            </div>
        </div>
    </div>
    <div class="container index-space">
        <div class="row">
            <div class="col-sm-12 font">
                <div  class="text-center " style="margin-top: 30px; font-size: 30px;">Search regulatory interactions by evidence level:</div>
            </div>
        </div>
    </div>

    <div class="container-fluid" style="margin-top: 30px">
        <div class="row font">
            <div class="col-sm-3"></div>
            <div class="col-sm-3">
                <a href="searchExperimentalData.htm"><button type="button" class="btn btn-primary btn-lg">Experimental</button></a>
            </div>
            <div class="col-sm-3">
                <a href="searchPredictedData.htm"><button type="button" class="btn btn-primary btn-lg btn-small-screen">Predicted <div class="predicted-text"><b>Experimental data plus automatically predicted data</b></div></button></a>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
    <div class="container note-pos">
        <div class="row">
            <div class="col-sm-12 font">
                <div  class="text-center " style="margin-top: 20px; font-size: large;">
                    Note: 'CoryneRegNet experimental' database contains regulatory data regarding the model organisms <i>Corynebacterium glutamicum</i>
                    ATCC 13032, <i>Bacilus subtilis</i> 168, <i>Escherichia coli</i> K-12 and <i>Mycobacterium tuberculosis</i> H37Rv. 
                    'CoryneRegNet predicted' also covers the full 'CoryneRegNet experimental' data and contains automatically predicted, 
                    not manually curated, gene regulatory interactions for the <i>Corynebacterium</i> genus.
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
</body>
<script>
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
    //if (chrome == false && opera == false && edge == false) {
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
</html>