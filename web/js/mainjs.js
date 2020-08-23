/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function callGeneExampleSearch(value) {
    //geneRna
    //var value = element.value;
    var geneRna = document.getElementById('gene').checked;
    alert(geneRna);
    
    if (geneRna) {
         console.log('is gene');
         document.getElementById('organism-search').value = 1239;
        var select = document.getElementById('geneList-select');
        select.value = 'wildcards';
    }else{
        console.log('is rna');
        document.getElementById('organism-search-rna').value = 1239;
        var select = document.getElementById('srnaList-select');
        select.value = 'wildcards';
        if(value.endsWith("%")){
            value = 'cgb_075%';
        }else{
            value = 'cgb_07555';
        }
        
    }
    
    document.getElementById('gene-search').style.display = 'block';
    document.getElementById('gene-search').value = value;
    document.getElementById('dinamic-network-caller').disabled = false;
}

function callRnaExampleSearch(value) {

    document.getElementById('organism-search').value = 1239;
    var select = document.getElementById('srnaList-select');
    select.value = 'wildcards';
    document.getElementById('gene-search').style.display = 'block';
    document.getElementById('gene-search').value = value;
    document.getElementById('dinamic-network-caller').disabled = false;
}



function chooseFilter() {
    var value = document.getElementById("filters").value;

    if (value == 'all') {
        showAllRows();
    }

    if (value == 'tfs') {
        showAllRows();
        hideNonTfs();
    }

    if (value == 'func') {
        showAllRows();
        hideNonFunc();
    }

    if (value == 'trn') {
        showAllRows();
        hideNonTrns();
    }

    getNumberOfRows();

}

function showAllRows() {
    $("[funcional]").show();

}

function hideNonFunc() {
    $('#experimental-data').dataTable({
        destroy: true,
        "lengthMenu": [[-1, 10, 25, 50], ["All", 10, 25, 50]],
        "order": [[0, "asc"]],

    });
    if ($("[funcional=false]").is(":visible")) {
        $("[funcional=false]").hide();
    }
}

function hideNonTfs() {
    $('#experimental-data').dataTable({
        destroy: true,
        "lengthMenu": [[-1, 10, 25, 50], ["All", 10, 25, 50]],
        "order": [[0, "asc"]],

    });
    if ($("[regulates-tf=0]").is(":visible")) {
        $("[regulates-tf=0]").hide();
    }
}

function hideNonTrns() {
    $('#experimental-data').dataTable({
        destroy: true,
        "lengthMenu": [[-1, 10, 25, 50], ["All", 10, 25, 50]],
        "order": [[0, "asc"]],

    });
    if ($("[regulates-trn=0]").is(":visible")) {
        $("[regulates-trn=0]").hide();
    }
}

function getNumberOfRows() {
    var length = $('tr:visible').length;
    document.getElementById("table-length").innerHTML = "Results (" + (parseInt(length) - 1) + " found)";
    //$("#table-length").load();
}

$(document).ready(function () {
    $('[data-toggle="popover"]').popover();
});

function checkSelectSrnas() {
    var value = document.getElementById("srnaList-select").value;
    //alert(value);
    //wildcards
    if (value == "wildcards") {
        //alert("wildcard selected!");
        document.getElementById("gene-search").style.display = "block";
        document.getElementById("dinamic-network-caller").disabled = true;
        document.getElementById("dinamic-network-caller-list").disabled = true;
    } else if (value == "all") {
        document.getElementById("dinamic-network-caller").disabled = true;
        document.getElementById("dinamic-network-caller-list").disabled = true;
    } else {
        document.getElementById("gene-search").style.display = "none";
    }
}


function checkSelectGenes() {
    var value = document.getElementById("geneList-select").value;
    //alert(value);
    //wildcards
    if (value == "wildcards") {
        //alert("wildcard selected!");
        document.getElementById("gene-search").style.display = "block";
        document.getElementById("dinamic-network-caller").disabled = true;
        document.getElementById("dinamic-network-caller-list").disabled = true;
    } else if (value == "all") {
        document.getElementById("dinamic-network-caller").disabled = true;
        document.getElementById("dinamic-network-caller-list").disabled = true;
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
            geneListHtml += '<select class="form-control space-after-input" id="geneList-select" name="geneListSelect" onchange="checkSelectGenes()">';
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
            geneListHtml += '<select class="form-control space-after-input select2" id="srnaList-select" name="srnaListSelect" onchange="checkSelectSrnas()">';
            geneListHtml += '<option value="all">All sRNAS</option>';
            geneListHtml += '<option value="wildcards">Search with wildcards</option>';
            for (i = 0; i < geneList.length; i++) {
                geneListHtml += '<option value="' + geneList[i].replace("()", "") + '">' + geneList[i].replace("()", "") + '</option>';
            }
            geneListHtml += '</select>';
            //geneListHtml
            var value = document.getElementById("organism-search").value;

            $('#srnaList').html(geneListHtml);

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
    console.log("SET GENES SELECT")
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
    //window.alert("organism rna"+document.getElementById('organism-search-rna').value);
    //window.alert("organism "+document.getElementById('organism-search').value);
    if (document.getElementById('gene').checked) {
        var value = document.getElementById("organism-search").value;
    } else {
        var value = document.getElementById("organism-search-rna").value;
    }
    if (value == 0) {
        //window.alert("value 0");
        document.getElementById("dinamic-network-caller").disabled = true;
        document.getElementById("dinamic-network-caller-list").disabled = true;
        if (document.getElementById('gene').checked) {
            setAllGenesSelect();
        } else {
            setAllRnasSelect();
        }


    } else {
        //window.alert("else :)");
        document.getElementById("dinamic-network-caller").disabled = false;
        document.getElementById("dinamic-network-caller-list").disabled = false;
        if (document.getElementById('gene').checked) {
            getGeneList(value);
        } else {
            getRnaList(value);
        }

    }

    if (document.getElementById('rna').checked &&
            document.getElementById('organism-search-rna').value == "1239" &&
            type == "experimental") {
        //window.alert("CG! :)");
        document.getElementById("dinamic-network-caller").disabled = true;
        document.getElementById("dinamic-network-caller-list").disabled = true;
        document.getElementById("tooltip-text").innerHTML = "There is no experimental sRNA network for this organism.";

    } else {
        document.getElementById("tooltip-text").innerHTML = "Searches the database content and presents it in a table based style.";
    }


}

function checkGeneRna(element) {
    document.getElementById("dinamic-network-caller").disabled = true;
    document.getElementById("dinamic-network-caller-list").disabled = true;
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
        document.getElementById("dinamic-network-caller-list").value = "Gene List";


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
        document.getElementById("dinamic-network-caller").value = "Gene Regulatory Network";
        document.getElementById("dinamic-network-caller-list").value = "sRNA Regulatory List";
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