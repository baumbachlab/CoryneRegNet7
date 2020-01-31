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
                        <a data-trigger="focus" class="nav-link menu-item link-color" href="#">Requirements</a>
                    </li>
                </ul>
            </div>  
        </div>
    </nav>


    <div class="container-fluid">
        <div class="row font">
            <div class="col-sm-12 bottom-btn" style="padding-left: 0px;">
                <a><button class="btn btn-primary btn-normal" onclick="goBack()">Go Back</button></a>
                <div class="title-size text-center">Requirements</div>
            </div>
        </div>
    </div>
    <div class="container-fluid badge badge-light shadow space-to-footer">
        <hr style="color: #eee; margin-bottom: 30px">
        <div class="row requirements justify-content-center">
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
                <span>CoryneRegNet has been tested and works with the following browsers:</span>
            </div>
            <div class="col-sm-1"></div>
        </div>

        <div class="row requirements justify-content-center">
            <div class="col-sm-2"></div>
            <div class="col-sm-2" align="center">
                <span>
                    <img src="images/Chrome.png" alt="img">
                    <p style="padding-top: 10px;">Google Chrome</p>
                </span>
            </div>
            <div class="col-sm-2" align="center">
                <span>
                    <img src="images/firefox.png" alt="img">
                    <p style="padding-top: 10px;">Mozilla FireFox</p>
                </span>
            </div>
            <div class="col-sm-2" align="center">
                <span>
                    <img src="images/Opera.png" alt="img">
                    <p style="padding-top: 10px;">Opera</p>
                </span>
            </div>
            <div class="col-sm-2" align="center">
                <span>
                    <img src="images/ie.png" alt="img">
                    <p style="padding-top: 10px;">Internet Explorer 10+</p>
                </span>
            </div>
            <div class="col-sm-2" align="center"></div>
        </div>

        <div class="row requirements">
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
                <span class="badge badge-light">Screen resolutions that were tested and worked for the following devices and screen resolutions:</span>
            </div>
            <div class="col-sm-1"></div>
        </div>

        <div class="row requirements requirements-end">
            <div class="col-sm-12" align="center">
                <span>
                    <img class="img-fluid" src="images/tablet.png" alt="img">
                    <img class="img-fluid" src="images/laptop.png" alt="img">
                    <img class="img-fluid" src="images/monitor.png" alt="img">
                </span>
                <p style="margin-top: 20px;">Tablets, laptops and computers<br>
                    (1024x768, 1440x900, 1680x1050, 1920x1080 and 2560x1440)</p>
            </div>
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
                        <img src="images/tum-logo.svg" alt="img" class="tum-logo">
                    </a>
                    <a href="https://ufmg.br/" target="_blank" class="center-block">
                        <img src="images/logo_ufmg3.png" alt="img" class="ufmg-logo">
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
