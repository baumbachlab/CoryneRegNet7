/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('[data-toggle="popover"]').popover();
});

function getGeneList(id) {
    $.ajax({
        url: 'ajaxGeneList.htm?id='+id,
        success: function (data) {
            $('#resultList').html(data);
            var geneList = data.split(",");

            var geneListHtml = "";
            $('#genesList').html(geneListHtml);
            var i;
            geneListHtml += '<select class="form-control space-after-input" id="geneList-select" name="geneListSelect">';
            for (i = 0; i < geneList.length; i++) {
                geneListHtml += '<option value="' + geneList[i] + '">' + geneList[i] + '</option>';
            }
            geneListHtml += '</select>';
            //geneListHtml
            var value = document.getElementById("organism-search").value;

            $('#resultList').html(geneListHtml);
            alert(value);


        }
    });
}

function enableNetworkButton() {
    var value = document.getElementById("organism-search").value;
    if (value == 0) {
        document.getElementById("dinamic-network-caller").disabled = true;
    } else {
        document.getElementById("dinamic-network-caller").disabled = false;
        getGeneList(value);
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