/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function myFunction() {
    var x = document.getElementsByClassName("myDIV");
    var i;
    for (i = 0; i < x.length; i++) {
        if (x[i].style.display === "none") {
            x[i].style.display = "block";
        } else {
            x[i].style.display = "none";
        }
    }
}

$(document).ready(function splitRegulationInfo(info) {
    var x = document.getElementsByClassName("myDIV");
    var i;
    var locus_tag;
    for (i = 0; i < x.length; i++) {
        locus_tag = info.split(" ");
    }
});