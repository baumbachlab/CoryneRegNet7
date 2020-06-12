/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function quantitiesOfsRNATypes(ncRnaExperimental, ncRnaBsrd, ncRnaCmsearch,
        ncRnaGLASSgo, ncRNATypes, count) {

    var ncRNAType = new Map;
    ncRNAType.set(ncRnaExperimental, "Experimental");
    ncRNAType.set(ncRnaBsrd, "BSRD");
    ncRNAType.set(ncRnaCmsearch, "CMsearch");
    ncRNAType.set(ncRnaGLASSgo, "GLASSgo");
    var data = [ncRnaExperimental, ncRnaBsrd, ncRnaGLASSgo, ncRnaCmsearch];
    var chart_width = 450;
    var chart_height = 300;
    var color = d3.scaleOrdinal(['red', 'blue', 'grey', 'green']);
    //Pie layout
    var pie = d3.pie();
    //Arc
    var outer_radius = (chart_width - 150) / 2;
    var inner_radius = 0;
    var arc = d3.arc()
            .innerRadius(inner_radius)
            .outerRadius(outer_radius);
    //Create SVG element
    var svg = d3.select("#sRNA-types" + count)
            .append("svg")
            .attr("width", chart_width)
            .attr("height", chart_height);
   
   
    // Create Tooltips
    var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
            .html(function (d) {
                //console.log(d);
                var content = "<span style='margin-left: 2.5px;'> " + ncRNAType.get(d.value) + ": " + d.value + "</span><br>";
                return content;
            });
    svg.call(tip);
    //Groups
    var arcs = svg.selectAll('g.arc')
            .data(pie(data))
            .enter()
            .append('g')
            .attr('class', 'arc')
            .attr('transform',
                    "translate(" + outer_radius + "," + chart_height / 2 + ")"
                    );
    //Arcs
    arcs.append('path')
            .attr('fill', function (d, i) {
                return color(i);
            })
            .attr('d', arc)
            .on('mouseover', tip.show)
            .on('mouseout', tip.hide);
    //Labels
    arcs.append('text')
            .attr('transform', function (d, i) {
                return "translate(" + arc.centroid(d) + ")";
            })
            .attr('text-anchor', 'text-middle')
            .text(function (d) {
                var total = ncRNATypes;
                if (d.value == 0) {
                    return "";
                }
                return (d.value * 100 / total).toPrecision(3) + "%";
            });
    // define legend
    var regulatorTypes = ["Experimental", "BSRD", "CMsearch", "GLASSgo"];
    var legend = svg.append("g")
            .attr("transform", "translate(" + (chart_width - 145) +
                    "," + (chart_height - 250) + ")");
    regulatorTypes.forEach(function (regulatorType, i) {

        var legendRow = legend.append("g")
                .attr("transform", "translate(0, " + (i * 30) + ")");
        legendRow.append("rect")
                .attr("x", 130)
                .attr("y", 0)
                .attr("width", 15)
                .attr("height", 15)
                .attr("fill", function () {
                    //console.log(regulatorType);
                    if (regulatorType == "Experimental") {
                        return "red";
                    } else if (regulatorType == "BSRD") {
                        return "blue";
                    } else if (regulatorType == "CMsearch") {
                        return "green";
                    }
                    return "grey";
                });
        legendRow.append("text")
                .attr("x", 120)
                .attr("y", 15)
                .attr("text-anchor", "end")
                .text(regulatorType)
                .attr("fill", function () {
                    if (regulatorType == "Experimental") {
                        return "red";
                    } else if (regulatorType == "BSRD") {
                        return "blue";
                    } else if (regulatorType == "CMsearch") {
                        return "green";
                    }
                    return "grey";
                })
                .style("font-size", "20px");
    });
}