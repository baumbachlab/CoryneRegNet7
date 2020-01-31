/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//TFs
$(document).ready(function () {
    var resolution = $(window).width();
    if (resolution < 1440) {
        document.getElementById('regulators-text').style.display = "none";
        document.getElementById('tfs-per-gene').style.display = "none";
        document.getElementById('co-regulating-text').style.display = "none";
        document.getElementById('profile-len-text').style.display = "none";
        document.getElementById('distances-text').style.display = "none";
    }
});