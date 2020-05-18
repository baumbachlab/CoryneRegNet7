<%-- 
    Document   : databaseStatistics
    Created on : Jun 21, 2019, 9:02:42 AM
    Author     : doglas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CoryneRegNet 7.0</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                            <a class="nav-link menu-item link-color flex-nowrap" href="#">Statistics</a>
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
            <div id="first-div">
                <div class="row">
                    <div class="col-sm-12 bottom-btn font" style="padding-left: 0px;">
                        <a><button class="btn btn-primary btn-normal" onclick="goBack()">Go Back</button></a>
                        <div class="text-center title-size" >Visualize <span class="color">Coryne</span>Reg<span class="color">Net</span> statistics by evidence level:</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container index-space"></div>

        <div id="statistics-loader" style="display:none; margin-top: 50px;">
            <center><span ><div class="loader" ></div> Loading statistics</span></center>
            <br><br>
        </div>

        <div id="searches" class="container-fluid" style="margin-top: 50px">
            <div class="row font">
                <div class="col-sm-3"></div>
                <div class="col-sm-3">
                    <a href="statistics.htm?type=experimental"><button type="button" class="btn btn-primary btn-lg">
                            <i class="fa fa-line-chart" aria-hidden="true"></i>
                            Experimental</button></a>
                </div>
                <div class="col-sm-3">
                    <a href="whichStatistics.htm?type=predicted"><button type="button" class="btn btn-primary btn-lg btn-small-screen">
                            <i class="fa fa-line-chart" aria-hidden="true"></i>

                            Predicted <div class="predicted-text"><b>Experimental data plus automatically predicted data</b></div></button></a>
                </div>
                <div class="col-sm-3"></div>
            </div>
        </div>

        <div class="container note-pos">
            <div class="row">
                <div class="col-sm-12 font">
                    <%--<div  class="text-center " style="margin-top: 20px; font-size: large;">Note: 'CoryneRegNet predicted' also covers the full 'CoryneRegNet experimental' data but also automatically predicted, not manually curated gene regulatory interactions.
                    </div>--%>
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
</html>
