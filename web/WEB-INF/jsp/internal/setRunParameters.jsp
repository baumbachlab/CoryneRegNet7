<%-- 
    Document   : setRunParameters
    Created on : Mar 27, 2019, 2:27:10 PM
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
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/runPipeline.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
    </head>

    <body style="background-image: url('images/background.png'); background-size: cover;" >


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
        <form action="setThresholds.htm" method="POST">
            <div class="row paddingBotton">
                <div class="col-sm-12 font">
                    <div class="title-size text-center centered-top">Run CoryneRegNet7 pipeline</div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12 shortPaddingBotton">
                     <span class="section-start ">WORKSPACE:</span><input type="text" class="form-control" id="workspaceField" name="workspace" value="${workspace}">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 shortPaddingBotton">
                    <span class="section-start ">TEMPLATE FOLDER:</span><input class="form-control" type="text" name="templateFolder" value="${templateFolder}">
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 paddingBotton">
                    <span class="section-start">TARGET FOLDER:</span><input class="form-control" type="text" name="targetfolder" value="${targetfolder}"> </br>
                </div>
            </div>

            <div class="row paddingBotton">
                <div class="col-sm-12 section-start ">
                    Step one:  INPUT DATA
                </div>
            </div>
            <div class="row paddingBotton">
                <div class="col-sm-5 ">
                    <a href="ncbiDataRecovery.htm?path=${targetfolder}" class="btn btn-info form-control" role="button">Download new target genomes from NCBI</a> 
                </div>
                <div class="col-sm-7" style="padding-top: 10px;">
                    ${downloadMessage}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 section-start paddingBotton">
                    Step two:  SET THRESHOLDS & CHOSE GENOMES
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="row paddingBotton">
                        <div class="col-sm-12" >
                            <div class="row paddingBotton section-organism">
                                Pipeline thresholds:
                            </div>
                            <div class="row" >
                                <div class="col-sm-6 form-group" style="padding-left: 0px;">
                                    <label for="blastCutoff"> BLAST:</label>
                                    <select class="form-control" style="width: 100%" id="blastCutoff" name="blastCutoff">
                                        <option value="1e-10">1e-10</option>
                                        <option value="1e-35" selected>1e-35</option>
                                        <option value="1e-48">1e-48</option>
                                    </select>
                                </div>
                                <div class="col-sm-6 form-group" style="padding-left: 0px;">
                                    <label for="hmmerCutoff"> HMMER:</label>
                                    <select class="form-control" style="width: 100%" id="hmmerCutoff" name="hmmerCutoff">
                                        <option value="" selected>default</option>
                                        <option value="1e-2">1e-2</option>
                                        <option value="1e-5">1e-5</option>

                                    </select>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 form-group">
                            <div class="row paddingBotton section-organism">
                                Template organisms:
                            </div>
                            <div class="row paddingBotton">
                                <label for="selectTemplate"> Select by:</label>
                                <select class="form-control" id="selectTemplate" name="selectTemplate" onchange="onChangeTemplate()">
                                    <option value="genera">Genera</option>
                                    <option value="species">Species</option>
                                    <option value="strain">Strain</option>
                                </select>
                            </div>
                            <div class="row paddingBotton section-organism">
                                Select template organisms:
                            </div>
                            <div class="row">
                                <div class="checkbox" id="templateOrganisms">
                                    <label><input type="checkbox" name="templateList" value="all" onclick="checkFormData()"  id="select_all_template"> All template organisms</label><br>
                                        <c:forEach items="${generaNamesTemplate}" var="generaName">
                                        <label class="italic"><input type="checkbox" name="templateList" value="${generaName}" onclick="checkFormData()" class="checkbox_template"> ${generaName}</label><br>
                                        </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="row paddingBotton section-organism">
                                Target organisms:
                            </div>
                            <div class="row paddingBotton">
                                <label for="selectTarget"> Select by:</label>
                                <select class="form-control" id="selectTarget" name="selectTarget" onchange="onChangeTarget()">
                                    <option value="genera">Genera</option>
                                    <option value="species">Species</option>
                                    <option value="strain">Strain</option>
                                </select>
                            </div>
                            <div class="row paddingBotton section-organism">
                                Select target organisms:
                            </div>
                            <div class="row">
                                <div class="checkbox" id="targetOrganisms">
                                    <label><input type="checkbox" name="targetList" value="all" onclick="checkFormData()"  id="select_all_target"> All target organisms</label><br>
                                        <c:forEach items="${generaNamesTarget}" var="generaName">
                                        <label class="italic"><input type="checkbox" name="targetList" value="${generaName}" onclick="checkFormData()" class="checkbox_target"> ${generaName}</label><br>
                                        </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12" id="submitdiv">
                    <input class="btn btn-primary btn-personalized form-control" type="submit" value="Save thresholds & genomes settings" id="submitButton" data-toggle="tooltip" title="Please select at least one model organism and one target organism" disabled>
                </div>
            </div>
        </form>
    </div>




    <div class="container-fluid badge badge-light" style="opacity: 0.99;">

        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">                    

            </div>
            <div class="col-sm-3 font" style="margin-top: 30px">

            </div>
        </div>
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
         function checkFormData() {
            var targetListChecked;
            if (!$('input[name=targetList]:checked').length > 0) {
                //   document.getElementById("errMessage").innerHTML = "Check Box 1 can not be null";
                targetListChecked = false;
            } else {
                targetListChecked = true;
            }

            var templateListChecked;
            if (!$('input[name=templateList]:checked').length > 0) {

                templateListChecked = false;

            } else {
                templateListChecked = true;
            }

            if (targetListChecked && templateListChecked) {
                document.getElementById("submitdiv").innerHTML = '<input class="btn btn-primary btn-personalized form-control" type="submit" value="Run CoryneRegNet7 pipeline" id="submitButton">';
            } else {
                document.getElementById("submitdiv").innerHTML = '<input class="btn btn-primary btn-personalized form-control" type="submit" value="Run CoryneRegNet7 pipeline" id="submitButton" data-toggle="tooltip" title="Please select at least one model organism and one target organism" disabled>';
            }
        }
        
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });

        $("#select_all_template").change(function () {  //"select all" change 
            var status = this.checked; // "select all" checked status
            $('.checkbox_template').each(function () { //iterate all listed checkbox items
                this.checked = status; //change ".checkbox" checked status
            });
            checkFormData();
        });
        
         $("#select_all_target").change(function () {  //"select all" change 
            var status = this.checked; // "select all" checked status
            $('.checkbox_target').each(function () { //iterate all listed checkbox items
                this.checked = status; //change ".checkbox" checked status
            });
            checkFormData();
        });

       

        function onChangeTemplate() {
            var x = document.getElementById("selectTemplate").value;
            if (x == 'genera') {
                var text = '<div class="checkbox" id="templateOrganisms">';
                text = text + '<label><input type="checkbox" name="templateList" value="all" onclick="checkFormData()"> All template organisms</label><br>';
                text = text + '<c:forEach items="${generaNamesTemplate}" var="generaName">';
                text = text + '<label class="italic"><input type="checkbox" name="templateList" value="${generaName}" onclick="checkFormData()"> ${generaName}</label><br>';
                text = text + '</c:forEach></div>';
                document.getElementById("templateOrganisms").innerHTML = text;
            }

            if (x == 'species') {
                var text = '<div class="checkbox" id="templateOrganisms">';
                text = text + '<label><input type="checkbox" name="templateList" value="all" onclick="checkFormData()"> All template organisms</label><br>';
                text = text + '<c:forEach items="${speciesNamesTemplate}" var="speciesName">';
                text = text + '<label class="italic"><input type="checkbox" name="templateList" value="${speciesName}" onclick="checkFormData()"> ${speciesName}</label><br>';
                text = text + '</c:forEach></div>';
                document.getElementById("templateOrganisms").innerHTML = text;
            }

            if (x == 'strain') {
                var text = '<div class="checkbox" id="templateOrganisms">';
                text = text + '<label><input type="checkbox" name="templateList" value="all" onclick="checkFormData()"> All template organisms</label><br>';
                text = text + '<c:forEach items="${strainNamesTemplate}" var="strainName">';
                text = text + '<label><input type="checkbox" name="templateList" value="${strainName[0]} ${strainName[1]}" onclick="checkFormData()"> <span class="italic">${strainName[0]} </span>${strainName[1]}</label><br>';
                text = text + '</c:forEach></div>';
                document.getElementById("templateOrganisms").innerHTML = text;
            }
        }

        function onChangeTarget() {
            var x = document.getElementById("selectTarget").value;
            if (x == 'genera') {
                var text = '<div class="checkbox" id="targetOrganisms">';
                text = text + '<label><input type="checkbox" name="targetList" value="all" onclick="checkFormData()"> All target organisms</label><br>';
                text = text + '<c:forEach items="${generaNamesTarget}" var="generaName">';
                text = text + '<label class="italic"><input type="checkbox" name="targetList" value="${generaName}" onclick="checkFormData()"> ${generaName}</label><br>';
                text = text + '</c:forEach></div>';
                document.getElementById("targetOrganisms").innerHTML = text;
            }

            if (x == 'species') {
                var text = '<div class="checkbox" id="targetOrganisms">';
                text = text + '<label><input type="checkbox" name="targetList" value="all" onclick="checkFormData()"> All target organisms</label><br>';
                text = text + '<c:forEach items="${speciesNamesTarget}" var="speciesName">';
                text = text + '<label class="italic"><input type="checkbox" name="targetList" value="${speciesName}" onclick="checkFormData()"> ${speciesName}</label><br>';
                text = text + '</c:forEach></div>';
                document.getElementById("targetOrganisms").innerHTML = text;
            }

            if (x == 'strain') {
                var text = '<div class="checkbox" id="targetOrganisms">';
                text = text + '<label><input type="checkbox" name="targetList" value="all" onclick="checkFormData()"> All target organisms</label><br>';
                text = text + '<c:forEach items="${strainNamesTarget}" var="strainName">';
                text = text + '<label><input type="checkbox" name="targetList" value="${strainName[0]} ${strainName[1]}" onclick="checkFormData()"> <span class="italic">${strainName[0]} </span>${strainName[1]}</label><br>';
                text = text + '</c:forEach></div>';
                document.getElementById("targetOrganisms").innerHTML = text;
            }
        }
    </script> 
</body>
</html>