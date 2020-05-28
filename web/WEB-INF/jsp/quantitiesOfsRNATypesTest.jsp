<%-- 
    Document   : quantitiesOfRegulatorAndRegulationTypes
    Created on : Jan 29, 2019, 3:25:18 PM
    Author     : doglas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link type="text/css" rel="stylesheet" href="css/main.css">
        <link type="text/css" rel="stylesheet" href="css/statistics.css">
        <link type="text/css" rel="stylesheet" href="css/qRRT.css">
        <script type="text/javascript" src="js/quantitiesOfsRNAAllTypes.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <script src="https://d3js.org/d3.v5.js"></script>
        <script src="js/d3-tip.js"></script>

        <style>
            /* Add shadow effect to chart. If you don't like it, get rid of it. */
            svg {
                -webkit-filter: drop-shadow( 0px 3px 3px rgba(0,0,0,.3) );
                filter: drop-shadow( 0px 3px 3px rgba(0,0,0,.25) );
            }

            /*Styling for the lines connecting the labels to the slices*/
            polyline{
                opacity: .3;
                stroke: black;
                stroke-width: 2px;
                fill: none;
            }

            /* Make the percentage on the text labels bold*/
            .labelName tspan {
                font-style: normal;
                font-weight: 700;
            }

            /* In biology we generally italicise species names. */
            .labelName {
                font-size: 0.9em;
                font-style: italic;
            }
        </style>
    </head>

    <body>
        <script>
            var count = -1;
            var regulatorsMap = new Map;
            var regulationsMap = new Map;
            var organisms = [];
        </script>

        <c:if test="${empty organismsRegulators}">
            <span>No entries found.</span>
        </c:if>

        <div class="container-fluid badge badge-light shadow space-to-footer">
            <hr style="color: #eee; margin-bottom: 30px">
            <div class="container-fluid" style="margin-bottom: 80px;">
                <div class="row font">
                    <div class="col-sm-12"><p class="title-size">Quantities of sRNA types (${type} database)</p></div>
                    <div class="col-sm-12">
                        <center>
                            <span class="title-size">Quantities of sRNA types for
                                <a href="organismInfo.htm?id=${organismsId.get(regulators.key)}&type=${type}">
                                    ${regulators.key}</a></span>
                        </center>
                    </div>
                </div>
                <div class="row" style="margin-top: 40px;">
                    <div class="col-sm-12">
                        <div id="sRNA-types0" align="center" class="pie-chart-pos"></div>
                        <b><center><p style="font-size: 22px; margin-top: 20px;">Quantities of sRNA types</p></center></b>
                    </div>
                </div>
            </div>

            <c:forEach items="${organismsRegulators}" var="regulators">
                <script>
                    var key = '${regulators.key}';
                    var value = '${regulators.value}';
                    console.log(value);
                    var regulatorsObject = {};
                    organisms.push(key);
                    //console.log(organisms);
                    //console.log("Regulators---------------------------------");
                    regulatorsObject = regulatorsObjectFunction(value);
                    //console.log(regulatorsObject);
                    regulatorsMap.set(key, regulatorsObject);
                    var operonRetriver = {};
                    operonRetriver = regulatorsMap.get(key);
                    //console.log(operonRetriver);
                    function regulatorsObjectFunction(value) {
                        var regulatorsAttributes = {};
                        var aux = [];
                        aux = value.split(", ");
                        var attributes = [];
                        var genesAux = [];
                        attributes = aux[0].split("[");
                        regulatorsAttributes.ncRnaExperimental = attributes[1];
                        regulatorsAttributes.ncRnaBsrd = aux[1];
                        regulatorsAttributes.ncRnaCmsearch = aux[2];
                        regulatorsAttributes.ncRnaGLASSgo = aux[3];
                        attributes = aux[4].split("]");
                        regulatorsAttributes.ncRNATypes = attributes[0];
                        return regulatorsAttributes;
                    }
                </script>
            </c:forEach>

            <script>

                //Pie Chart - sRNA types
                var count;
                for (var i = 0; i < regulatorsMap.size; i++) {
                    var ncRnaExperimental = parseInt(regulatorsMap.get(organisms[i]).ncRnaExperimental);
                    var ncRnaBsrd = parseInt(regulatorsMap.get(organisms[i]).ncRnaBsrd);
                    var ncRnaCmsearch = parseInt(regulatorsMap.get(organisms[i]).ncRnaCmsearch);
                    var ncRnaGLASSgo = parseInt(regulatorsMap.get(organisms[i]).ncRnaGLASSgo);
                    var ncRNATypes = parseInt(regulatorsMap.get(organisms[i]).ncRNATypes);
                    count = i;
                    if (i == 0) {
                        quantitiesOfsRNAAllTypes(ncRnaExperimental, ncRnaBsrd, ncRnaCmsearch,
                                ncRnaGLASSgo, ncRNATypes, count);
                    }
                }
            </script>
        </div>
    </body>
</html>
