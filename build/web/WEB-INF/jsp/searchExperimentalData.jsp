<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CoryneRegNet 7.0</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/search.css">
        <script type="text/javascript" src="js/mainjs.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>


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

            /* Styles go here */

            .tooltip {
                position: static;
                border-bottom: 1px dotted black;
                opacity: 1;
                font-family: inherit;
            }

            .tooltip .tooltiptext {
                visibility: hidden;
                width: 500px;
                background-color: #555;
                color: #fff;
                text-align: center;
                border-radius: 6px;
                padding: 5px 0;
                position: absolute;
                z-index: 1;
                bottom: 125%;
                left: 50%;
                margin-left: -60px;
                opacity: 0;
                transition: opacity 0.3s;
                font-family: sans-serif;
            }

            .tooltip .tooltiptext::after {
                content: "";
                position: absolute;
                top: 100%;
                left: 50%;
                margin-left: -5px;
                border-width: 5px;
                border-style: solid;
            }

            .tooltip:hover .tooltiptext {
                visibility: visible;
                opacity: 1;
            }

        </style>





        <script type="text/javascript">
            var intervalId = 0;
            //intervalId = setInterval(operonStatus, 3000);
        </script>


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
                        <a data-trigger="focus" class="nav-link menu-item link-color" href="requirements.htm">Requirements</a>
                    </li>
                </ul>
            </div>  
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row search-database">
            <div class="col-sm-12 bottom-btn font" style="padding-left: 0px;">
                <a href="index.htm"><button type="button" class="btn btn-primary btn-normal">Go Back</button></a>
                <div class="title-size text-center">Search experimental database</div>
            </div>
        </div>
    </div>
    <div class="container-fluid badge badge-light shadow space-to-footer">
        <hr style="color: #eee; margin-bottom: 30px">
        <form method="POST">
            <div class="row">
                <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <div class="row" style="color: red;">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-11">${message}</div>
                    </div>
                    <div class="row">
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label for="organism-search">Search by organism&nbsp;</label>
                            <a tabindex="0" role="button" data-toggle="popover" data-trigger="focus" title="How to search?" data-content="For network visualization it is necessary to select an organism. For search button it is possible to search the database content of all organisms, but a gene is required." style="text-rendering: optimizeLegibility;">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>
                            <div id="organism-select-gene">
                                <select class="form-control space-after-input" id="organism-search" name="organism" onchange="enableNetworkButton()">
                                    <option value="0">All</option>
                                    <c:forEach items="${items}" var="organismItem">

                                        <option value="${organismItem.id}">${organismItem.name}</option>
                                    </c:forEach>                              
                                </select>
                            </div>
                            <div id="organism-select-rna" style="display: none;">
                                <select class="form-control space-after-input" id="organism-search-rna" name="organism-rna" onchange="enableNetworkButton()">
                                    <option value="0">All genome rna</option>
                                    <c:forEach items="${itemsRna}" var="organismItemRna">

                                        <option value="${organismItemRna.id}">${organismItemRna.name}</option>
                                    </c:forEach>                              
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-1"></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-1">
                        </div>
                        <div class="col-sm-10 form-small-screen">
                            <label for="gene-search" id="gene-rna-label">Gene&nbsp;</label>
                            <a tabindex="1" role="button" data-toggle="popover" data-trigger="focus" title="How gene search works?" data-content="It is possible to search by gene id(locus_tag) and gene name. To search in all organisms a gene is mandatory." style="text-rendering: optimizeLegibility;">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>
                            <div id="resultList">
                                <select class="form-control space-after-input" id="geneList-select" name="geneListSelect" onchange="checkSelectGenes()">
                                    <option value="all">All genes</option>
                                    <option value="wildcards">Search with wildcards</option>
                                </select>
                            </div>
                            <div id="srnaList" >
                                <select class="form-control space-after-input" id="srnaList-select" name="srnaListSelect" onchange="checkSelectSrnas()"  style="display: none;">
                                    <option value="all">All sRNAs</option>
                                    <option value="wildcards">Search with wildcards</option>
                                </select>
                            </div>
                            <input type="text" class="form-control" id="gene-search" name="gene"  style="display: none;">

                            <label for="checkbox-div" style="margin-top: 15px;" >Search for:&nbsp;</label>
                            <%-- <a tabindex="1" role="button" data-toggle="popover" data-trigger="focus" title="How gene search works?" data-content="It is possible to search regulatory interactions regulated by Transcripction Factors (TFs), non-coding RNAs (ncRNAs) or both." style="text-rendering: optimizeLegibility;">
                                <i class="fa fa-question-circle" style='color:black;'></i>
                            </a>--%><br>
                            <span class="align-text-bottom" id="checkbox-div">
                                <input type="radio" id="gene" name="geneRna" value="gene" checked="checked" onclick="checkGeneRna(this);">
                                <label style="margin-right: 30px" for="tf">Genes</label>
                                <input type="radio" id="rna" name="geneRna" value="rna" onclick="checkGeneRna(this);">
                                <label for="rna">Small RNAs</label><br>
                            </span>
                            <span class="align-text-bottom"> 
                                <a href="#" onclick="document.getElementById('gene-search').value = 'cg0012'; document.getElementById('organism-search').value = 1239; document.getElementById('dinamic-network-caller').disabled = false;" style="color: #000000; font-size: small;">Example search</a>
                            </span><br>

                            <span class="align-text-bottom"> 
                                <a href="#" onclick="document.getElementById('gene-search').value = 'dna%'; document.getElementById('organism-search').value = 0; document.getElementById('dinamic-network-caller').disabled = true;" style="color: #000000; font-size: small;">Example search with wildcards</a>
                            </span>
                        </div>
                        <div class="col-sm-1"></div>
                    </div>

                </div>
                <div class="col-sm-3"></div>
            </div>

            <div id="search-loader" style="display:none; margin-top: 50px;">
                <center><span ><div class="loader" ></div> Loading data</span></center>
                <br><br>
            </div>
            <div id="network-loader" style="display:none; margin-top: 50px;">
                <center><span ><div class="loader" ></div> Loading Network Visualization</span></center>
                <br><br>
            </div>
            <div id="statistics-loader" style="display:none; margin-top: 50px;">
                <center><span ><div class="loader" ></div> Loading statistics</span></center>
                <br><br>
            </div>
            <div id="searches">        
                <div id="search-button" class="row">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10 font">
                                <center>
                                    <input type="hidden" name="searchType" value="experimental">
                                    <div class="tooltip">
                                        <input formaction="dataSearch.htm" type="submit" class="btn btn-primary btn-block btn-normal" value="Search" onclick="showLoaderSearch()">
                                        <span class="tooltiptext">Searches the database content and presents it in a table based style</span>
                                    </div>
                                </center>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                    <div class="col-sm-3"></div>
                </div>



                <div class="row" style="padding-bottom: 20px">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <center>
                                    <input type="hidden" name="goBackTo" value="experimental">
                                    <div class="tooltip">
                                        <input id="dinamic-network-caller" formaction="whichNetwork.htm?layoutType=fast" type="submit" class="btn btn-primary btn-block btn-normal font" value="Gene Regulatory Network" onclick="showLoader()" disabled>
                                        <span class="tooltiptext">Searches the database content and presents a dynamic network visualization of the TRN of selected organism</span>
                                    </div>
                                </center>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                    </div>
                    <div class="col-sm-3"></div>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-sm-12 text-center tables-top">
                <label class="font" for="statistics">Statistics Overview</label>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-4">
                <center>
                    <table id="statistics" class="table table-striped text-center">
                        <tbody>
                            <tr>
                                <td>Genes</td>
                                <td>${numberOfGenes}</td>
                            </tr>
                            <tr>
                                <td>Proteins</td>
                                <td>${numberOfProteins}</td>
                            </tr>
                            <tr>
                                <td>Small RNAs</td>
                                <td>${numberOfSmallRnas}</td>
                            </tr>
                            <tr>
                                <td>Regulatory RNAs</td>
                                <td>${numberOfRegulatoryRnas}</td>
                            </tr>

                            <tr>
                                <td>Transcription factors</td>
                                <td>${totalNumberOfRegulators}</td>
                            </tr>
                            <tr>
                                <td>Genomes</td>
                                <td>${numberOfGenomes}</td>
                            </tr>
                        </tbody>
                    </table>
                </center>
            </div>
            <div class="col-sm-4">
                <center>
                    <table id="statistics" class="table table-striped text-center">
                        <tbody>
                            <tr>
                                <td>Regulations (TFs)</td>
                                <td>${totalNumberOfRegulatoryInteractions}</td>
                            </tr>
                            <tr>
                                <td>Regulations (sRNAs)</td>
                                <td>${numberOfRegulationsSrna}</td>
                            </tr>
                            <tr>
                                <td>Genes regulated by TFs</td>
                                <td>${totalNumberOfRegulatedGenes}</td>
                            </tr>
                            <tr>
                                <td>Genes regulated by RNAs</td>
                                <td>${numberOfGenesByRna}</td>
                            </tr>
                            <tr>
                                <td>Binding motifs</td>
                                <td>${numberOfBindingSites}</td>
                            </tr>
                            <tr>
                                <td>HMM profiles</td>
                                <td>${numberOfHmmProfiles}</td>
                            </tr>

                        </tbody>
                    </table>
                </center>
            </div>
            <div class="col-sm-2"></div>
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
    $('.select2').select2();
</script>
<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
        $(function () {
            $('select').selectpicker();
        })
    });

    window.addEventListener('beforeunload', (event) => {
        showButtonsSearch();
    });

</script>

</html>
