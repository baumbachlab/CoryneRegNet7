/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('[data-toggle="popover"]').popover();
});

function checkSelectSrnas() {
    var value = document.getElementById("srnaList-select").value;
    alert(value);
    //wildcards
    if (value == "wildcards") {
        //alert("wildcard selected!");
        document.getElementById("gene-search").style.display = "block";
        document.getElementById("dinamic-network-caller").disabled = true;

    } else if (value == "all") {
        document.getElementById("dinamic-network-caller").disabled = true;
    } else {
        document.getElementById("gene-search").style.display = "none";
    }
}


function checkSelectGenes() {
    var value = document.getElementById("geneList-select").value;
    alert(value);
    //wildcards
    if (value == "wildcards") {
        //alert("wildcard selected!");
        document.getElementById("gene-search").style.display = "block";
        document.getElementById("dinamic-network-caller").disabled = true;
    } else if (value == "all") {
        document.getElementById("dinamic-network-caller").disabled = true;
    } else {
        document.getElementById("gene-search").style.display = "none";
    }
}

function getGeneList(id) {
    $("#geneList-select").val("loading...");
    $.ajax({
        url: 'ajaxGeneList.htm?id=' + id,
        success: function (data) {
            document.getElementById("gene-search").style.display = "none";
            // $('#resultList').html(data);
            $('#srnaList').html("");

            var geneList = data.split(",");

            var geneListHtml = "";
            // $('#genesList').html(geneListHtml);
            var i;
            geneListHtml += '<select class="form-control space-after-input select2" id="geneList-select" name="geneListSelect" onchange="checkSelectGenes()">';
            geneListHtml += '<option value="all">All genes</option>';
            geneListHtml += '<option value="wildcards">Search with wildcards</option>';
            for (i = 0; i < geneList.length; i++) {
                geneListHtml += '<option value="' + geneList[i].replace("()", "") + '">' + geneList[i].replace("()", "") + '</option>';
            }
            geneListHtml += '</select>';
            //geneListHtml
            var value = document.getElementById("organism-search").value;

            $('#resultList').html(geneListHtml);
            $('.select2').select2();


            // alert(value);


        }
    });
}

function getRnaList(id) {
    $("#srnaList-select").val("loading...");
    $.ajax({
        url: 'ajaxRnaList.htm?id=' + id,
        success: function (data) {
            document.getElementById("gene-search").style.display = "none";
            $('#resultList').html("");
            // $('#resultList').html(data);
            //srnaList
            var geneList = data.split(",");

            var geneListHtml = "";
            // $('#genesList').html(geneListHtml);
            var i;
            geneListHtml += '<select class="form-control space-after-input select2" id="srnaList-select" name="geneListSelect" onchange="checkSelectSrnas()">';
            geneListHtml += '<option value="all">All sRNAS</option>';
            geneListHtml += '<option value="wildcards">Search with wildcards</option>';
            for (i = 0; i < geneList.length; i++) {
                geneListHtml += '<option value="' + geneList[i].replace("()", "") + '">' + geneList[i].replace("()", "") + '</option>';
            }
            geneListHtml += '</select>';
            //geneListHtml
            var value = document.getElementById("organism-search").value;

            $('#srnaList').html(geneListHtml);
            $('.select2').select2();
            // alert(value);


        }
    });
}

function setAllRnasSelect() {
    console.log("SET RNA SELECT")
    var geneListHtml = "";
    geneListHtml += '<select class="form-control space-after-input" id="srnaList-select" name="geneListSelect" onchange="checkSelectSrnas()">';
    geneListHtml += '<option value="all">All sRNAS</option>';
    geneListHtml += '<option value="wildcards">Search with wildcards</option>';
    geneListHtml += '</select>';
    $('#srnaList').html(geneListHtml);
    document.getElementById("srnaList-select").style.display = "block";
    document.getElementById("geneList-select").style.display = "none";

}


function setAllGenesSelect() {
    console.log("SET RNA SELECT")
    var geneListHtml = "";
    geneListHtml += '<select class="form-control space-after-input" id="geneList-select" name="geneListSelect" onchange="checkSelectGenes()">';
    geneListHtml += '<option value="all">All genes</option>';
    geneListHtml += '<option value="wildcards">Search with wildcards</option>';
    geneListHtml += '</select>';
    $('#resultList').html(geneListHtml);
    document.getElementById("srnaList-select").style.display = "none";
    document.getElementById("geneList-select").style.display = "block";

}

function enableNetworkButton(type) {
    //organism-search-rna
    if (document.getElementById('gene').checked) {
        var value = document.getElementById("organism-search").value;
    } else {
        var value = document.getElementById("organism-search-rna").value;
    }
    if (value == 0) {
        document.getElementById("dinamic-network-caller").disabled = true;
        if (document.getElementById('gene').checked) {
            setAllGenesSelect();
        } else {
            setAllRnasSelect();
        }


    } else if (document.getElementById('organism-search-rna').value == "1239" && type == "experimental") {
        document.getElementById("dinamic-network-caller").disabled = true;
        document.getElementById("tooltip-text").innerHTML = "There is no experimental sRNA network for this organism.";

    } else {
        document.getElementById("dinamic-network-caller").disabled = false;
        document.getElementById("tooltip-text").innerHTML = "Searches the database content and presents it in a table based style.";
        if (document.getElementById('gene').checked) {
            getGeneList(value);
        } else {

            getRnaList(value);
        }
    }


}

function checkGeneRna(element) {
    var value = element.value;
    var organismValue = "";
    if (value == 'gene') {
        document.getElementById('organism-search').value = "0";
//        organismValue = document.getElementById("organism-search").value;
        document.getElementById("gene-rna-label").innerHTML = "Gene&nbsp;";
        if (document.getElementById("resultList").innerHTML != "") {
            document.getElementById("geneList-select").style.display = "block";
            document.getElementById('geneList-select').value = "all";
        }
//        if (document.getElementById("srnaList").innerHTML != "") {
//            document.getElementById("srnaList-select").style.display = "none";
//        }else{
        document.getElementById("srnaList").innerHTML = "";
//        }
        document.getElementById("gene-search").style.display = "none";
        document.getElementById("gene-search").value = "";
        document.getElementById("dinamic-network-caller").value = "Gene Regulatory Network";


        document.getElementById("organism-select-rna").style.display = "none";
        document.getElementById("organism-select-gene").style.display = "block";
        setAllGenesSelect();
//        if (organismValue != 0) {
//            getGeneList(organismValue);
//        }else{
//            
//        }


        //srnaList-select
    } else {
        document.getElementById('organism-search-rna').value = "0";

//        organismValue = document.getElementById("organism-search-rna").value;
//        console.log("rna2");
        document.getElementById("gene-rna-label").innerHTML = "Small RNA&nbsp;";
//        console.log("rna3");
//        //aqui tá vindo NULL:
//        if (document.getElementById("srnaList").innerHTML != "") {
//            document.getElementById("srnaList-select").style.display = "block";
//            console.log("rna4");
//            document.getElementById('srnaList-select').value = "all";
//        }
//        
//        if (document.getElementById("resultList").innerHTML != "") {
//            
//            document.getElementById("geneList-select").style.display = "none";
//        }else{
        document.getElementById("resultList").innerHTML = "";
//        }
//        console.log("rna6");
//        document.getElementById("gene-search").style.display = "none";
//        console.log("rna7");
//        document.getElementById("gene-search").value = "";
//        console.log("rna8");
//        //dinamic-network-caller
        document.getElementById("gene-search").style.display = "none";
        document.getElementById("gene-search").value = "";
        document.getElementById("dinamic-network-caller").value = "sRNA Regulatory Network";
//
//
//        //organism-select-rna
        document.getElementById("organism-select-rna").style.display = "block";
        document.getElementById("organism-select-gene").style.display = "none";
        setAllRnasSelect();

//
//
//        if (organismValue != 0) {
//            getRnaList(organismValue);
//        }


        //srnaList-select
    }
}

function goBack() {
    window.history.back();

}

function showLoader() {
    $("#searches").hide();
    $("#stats").hide();
    $("#stats-title").hide();
    $("#network-loader").show();
}

function showLoaderWichNetwork() {
    $("#fast-layout").hide();
    $("#improved-layout").hide();
    $("#network-loader").show();
}

function showLoaderSearch() {
    $("#searches").hide();
    $("#stats").hide();
    $("#stats-title").hide();
    $("#search-loader").show();
}

function showButtonsSearch() {
    $("#search-loader").hide();
}

function showLoaderStatistics() {
    $("#searches").hide();
    $("#stats").hide();
    $("#stats-title").hide();
    $("#statistics-loader").show();
}

function showLoaderBSs() {
    $("#bs-searcher").hide();
    $("#binding-sites-loader").show();
}

function hideHmmLogo() {
    // this will run while the image is not ready
    $("#hmm-logo-loader").show();
    $("#hmm-logo").hide();
    $("#hmm-logo").load(" #hmm-logo > *");
}

function hmmLogo() {
    // this will run when the image is ready
    $("#hmm-logo-loader").hide();
    $("#hmm-logo").show();
}

function hmmLogoError() {
    $("#hmm-logo-loader").hide();
    $("#hmm-logo-error").show();
}