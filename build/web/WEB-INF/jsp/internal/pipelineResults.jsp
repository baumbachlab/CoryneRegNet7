<%-- 
    Document   : pipelineResults
    Created on : Mar 7, 2019, 11:11:12 AM
    Author     : mariana
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
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/pipelineResults.css">
        <link type="text/css" rel="stylesheet" href="css/runPipeline.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <script type="text/javascript">

            function ajax() {
                $.ajax({
                    url: 'testajax.htm?id=${run.id}&transferFolder=${transferFolder}&targetfolder=${targetfolder}&templateFolder=${templateFolder}'
                });

                window.alert("teste");
                document.getElementById("show-results").style = "opacity: 1;padding-top: 30px;";

            }

            function changeIcon(elementId) {
                var x = document.getElementById(elementId);
                var icon = x.innerHTML;
                if (icon == '<span class="fa fa-chevron-up"></span>') {
                    x.innerHTML = '<span class="fa fa-chevron-down"></span>';
                } else {
                    x.innerHTML = '<span class="fa fa-chevron-up"></span>';
                }
                //window.alert(icon);
            }

            function operonStatus() {
                $.ajax({
                    url: 'getOperonStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#operon-status').html(data);
                    }
                });
            }

            function operonColor() {
                var operons = document.getElementById("operon-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner.includes("Not iniciated")) {
                    // window.alert("achei " + operonsInner + " :)");
                    document.getElementById("operon-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("operon-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("operon-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }

//hmm-template-status

            function hmmTemplateStatus() {
                $.ajax({
                    url: 'getHmmTemplateStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#hmm-template-status').html(data);
                    }
                });
            }

            function hmmTemplateColor() {
                var operons = document.getElementById("hmm-template-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("hmm-template-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("hmm-template-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("hmm-template-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }

            //promoter-template-status
            function promoterTemplateStatus() {
                $.ajax({
                    url: 'getPromoterTemplateStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#promoter-template-status').html(data);
                    }
                });
            }

            function promoterTemplateColor() {
                var operons = document.getElementById("promoter-template-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("promoter-template-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("promoter-template-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("promoter-template-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }

            // promoter-target-status
            function promoterTargetStatus() {
                $.ajax({
                    url: 'getPromoterTargetStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#promoter-target-status').html(data);
                    }
                });
            }

            function promoterTargetColor() {
                var operons = document.getElementById("promoter-target-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("promoter-target-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("promoter-target-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("promoter-target-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }

            //blast-status
            function blastStatus() {
                $.ajax({
                    url: 'getBlastStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#blast-status').html(data);
                    }
                });
            }

            function blastColor() {
                var operons = document.getElementById("blast-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("blast-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("blast-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("blast-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }

            //binding-sites-status
            function bindingSiteStatus() {
                $.ajax({
                    url: 'getBindingSiteStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#binding-sites-status').html(data);
                    }
                });
            }

            function bindingSiteColor() {
                var operons = document.getElementById("binding-sites-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("binding-sites-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("binding-sites-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("binding-sites-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }

            //hmm-target-status
            function hmmTargetStatus() {
                $.ajax({
                    url: 'getHmmTargetStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#hmm-target-status').html(data);
                    }
                });
            }

            function hmmTargetColor() {
                var operons = document.getElementById("hmm-target-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("hmm-target-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("hmm-target-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("hmm-target-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }

            //blast-template-status
            function blastTemplateStatus() {
                $.ajax({
                    url: 'getBlastTemplateStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#blast-template-status').html(data);
                    }
                });
            }

            function blastTemplateColor() {
                var operons = document.getElementById("blast-template-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("blast-template-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("blast-template-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("blast-template-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }


            //blast-template-status
            function blastTargetStatus() {
                $.ajax({
                    url: 'getBlastTargetStatus.htm?id=${run.id}',
                    success: function (data) {
                        $('#blast-target-status').html(data);
                    }
                });
            }

            function blastTargetColor() {
                var operons = document.getElementById("blast-target-status");
                var operonsInner = operons.innerHTML;
                if (operonsInner == 'Not iniciated') {

                    document.getElementById("blast-target-status").className = "progress-bar bg-danger";

                } else if (operonsInner.includes("Done")) {

                    document.getElementById("blast-target-status").className = "progress-bar bg-success";

                } else {
                    document.getElementById("blast-target-status").className = "progress-bar bg-warning progress-bar-striped progress-bar-animated";
                }

            }


        </script>

        <script type="text/javascript">
            var intervalIdOpStatus = 0;
            intervalIdOpStatus = setInterval(operonStatus, 3000);

            var intervalIdOpColor = 0;
            intervalIdOpColor = setInterval(operonColor, 500);

            var intervalIdHmmStatus = 0;
            intervalIdHmmStatus = setInterval(hmmTemplateStatus, 3000);

            var intervalIdHmmColor = 0;
            intervalIdHmmColor = setInterval(hmmTemplateColor, 500);

            var intervalIdPromStatus = 0;
            intervalIdPromStatus = setInterval(promoterTemplateStatus, 3000);

            var intervalIdPromColor = 0;
            intervalIdPromColor = setInterval(promoterTemplateColor, 500);

            var intervalIdPromTargStatus = 0;
            intervalIdPromTargStatus = setInterval(promoterTargetStatus, 3000);

            var intervalIdPromTargColor = 0;
            intervalIdPromTargColor = setInterval(promoterTargetColor, 500);

            var intervalIdBlastStatus = 0;
            intervalIdBlastStatus = setInterval(blastStatus, 3000);

            var intervalIdBlastColor = 0;
            intervalIdBlastColor = setInterval(blastColor, 500);

            var intervalIdBindingSiteStatus = 0;
            intervalIdBindingSiteStatus = setInterval(bindingSiteStatus, 3000);

            var intervalIdBindingSiteColor = 0;
            intervalIdBindingSiteColor = setInterval(bindingSiteColor, 500);

            var intervalIdHmmTargetStatus = 0;
            intervalIdHmmTargetStatus = setInterval(hmmTargetStatus, 3000);

            var intervalIdHmmTargetColor = 0;
            intervalIdHmmTargetColor = setInterval(hmmTargetColor, 500);

            var intervalIdBlastTemplateStatus = 0;
            intervalIdBlastTemplateStatus = setInterval(blastTemplateStatus, 3000);

            var intervalIdBlastTargetColor = 0;
            intervalIdBlastTargetColor = setInterval(blastTemplateColor, 500);

            var intervalIdBlastTemplateStatus = 0;
            intervalIdBlastTemplateStatus = setInterval(blastTargetStatus, 3000);

            var intervalIdBlastTargetColor = 0;
            intervalIdBlastTargetColor = setInterval(blastTargetColor, 500);
        </script>
    </head>

    <body style="background-image: url('images/background.png'); background-size: cover;" >

    <nav class="navbar navbar-expand-md navbar-inverse bg-dark fixed-top navbar-dark paddingBotton">
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

    <div class="container-fluid ">

        <div class="row paddingBotton">
            <div class="col-sm-12 font">
                <div class="title-size text-center centered-top">Run CoryneRegNet7 pipeline</div>
            </div>
        </div>
    </div>

    <div class="container paddingBotton">
        <div class="row paddingBotton">
            <div class="col-sm-12 ">
                <div class="row ">
                    <div class="col-sm-12 section-start paddingBotton" style="font-size: large;">
                        You have successfully chosen your parameters and genomes.
                    </div>
                </div>

                <div class="panel-group" id="accordion">
                    <div class="panel panel-default paddingBotton" >
                        <div class="panel-heading" >
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" onclick="changeIcon('iconCollapse1')">
                                <h5 class="panel-title">
                                    <span>Cutoffs</span>
                                    <span class="float-right" id="iconCollapse1"><span class="fa fa-chevron-down"></span></span>
                                </h5>

                            </a>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse in paddingCollapse">
                            <div class="panel-body" style="padding-left: 10px;">
                                <span><b>BLAST cutoff:</b> ${run.blastCutoff}</span><br>
                                <span><b>HMMER cutoff:</b> ${hmmerCutoff}</span>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default paddingBotton">
                        <div class="panel-heading">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" onclick="changeIcon('iconCollapse2')">
                                <h5 class="panel-title">
                                    Template & target genomes
                                    <span class="float-right" id="iconCollapse2"><span class="fa fa-chevron-down"></span></span>
                                </h5>
                            </a>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse paddingCollapse">
                            <div class="panel-body">
                                <div class="row" style="font-size: medium;">
                                    <div class="col-sm-6 form-group">
                                        <div class="row paddingBotton section-organism">
                                            Template organisms:
                                        </div>

                                        <c:forEach items="${templateOrganismsList}" var="templateOrganisms">
                                            <div class="row">
                                                <i>${templateOrganisms.genera} ${templateOrganisms.species}&nbsp;</i> ${templateOrganisms.strain}<br/>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="row paddingBotton section-organism">
                                            Target organisms:
                                        </div>
                                        <c:forEach items="${targetOrganismsList}" var="targetOrganisms">
                                            <div class="row">
                                                <i>${targetOrganisms.genera} ${targetOrganisms.species}&nbsp;</i> ${targetOrganisms.strain}
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>

        </div>
        <div class="row">
            <div class="col-sm-12" style="padding: 0px; margin-top: 15px;">
                <button type="button"  class="btn btn-primary btn-personalized form-control" onclick="ajax()">Run Pipeline</button>
            </div>
        </div>
    </div>

    <div class="container" style="padding-top: 30px;"  id="show-results">
        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                PREDICT OPERONS <span style="font-size: 90%">(TARGET GENOMES)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="operon-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                HMMER PROFILES <span style="font-size: 90%">(TEMPLATE GENOMES)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="hmm-template-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4" >
                PROMOTER REGIONS <span style="font-size: 90%">(TEMPLATE GENOMES)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="promoter-template-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                PROMOTER REGIONS <span style="font-size: 90%">(TARGET GENOMES)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="promoter-target-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>

        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                BLAST <span style="font-size: 90%">(TEMPLATE x TEMPLATE)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="blast-template-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>

        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                BLAST <span style="font-size: 90%">(TARGET x TARGET)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="blast-target-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>


        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                BLAST <span style="font-size: 90%">(TARGET x TEMPLATE)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="blast-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                BINDING SITES
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="binding-sites-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
        <div class="row paddingBotton">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-4">
                HMMER PROFILES <span style="font-size: 90%">(TARGET GENOMES)</span>
            </div>
            <div class="col-sm-4">
                <div class="progress" style="height:100%">
                    <div class="progress-bar bg-danger" style="width:100%" id="hmm-target-status">Not iniciated</div>
                </div>
            </div>
            <div class="col-sm-2">
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
    <script type="text/javascript">
        $(document).ready(function () {
            $("#show-results").fadeTo(0, 0.4);
        });

        if (document.readyState === 'loading') {
            console.log('LOADING........')
        }
    </script>
</body>
</html>
