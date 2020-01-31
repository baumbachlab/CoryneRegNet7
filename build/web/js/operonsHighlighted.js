/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function operonsHighlighted(data) {
    var force = d3.layout.force()
            .nodes(d3.values(nodes))
            .links(links)
            .size([width, height])
            .linkDistance(75)
            .charge(-250)
            .on("tick", tick)
            .start();
    var s = d3.select("#operons-highlighted")
            .append("svg")
            .attr({
                "width": "100%",
                "height": "100%"
            })
            .attr("viewBox", "0 0 " + width + " " + height)
            .attr("preserveAspectRatio", "xMidYMid meet")
            .attr("pointer-events", "all");

    var vis = s
            .append('svg:g');
    function redraw() {
        vis.attr("transform",
                "translate(" + d3.event.translate + ")"
                + " scale(" + d3.event.scale + ")");
    }

    // Per-type markers, as they don't inherit styles.
    s.append("defs").selectAll("marker")
            .data(["repressorA", "activatorA", "dualA", "sigmaA"])
            .enter().append("marker")
            .attr("id", function (d) {
                return d;
            })
            .attr("viewBox", "0 -5 10 10")
            .attr("refX", 18)
            .attr("refY", -1.5)
            .attr("markerWidth", 8)
            .attr("markerHeight", 8)
            .attr("orient", "auto")
            .append("path")
            .attr("d", "M0,-5L10,0L0,5");
    var path = s.append("g").selectAll("path")
            .data(force.links())
            .enter().append("path")
            .attr("class", function (d) {
                return "link " + d.type;
            })
            .attr("marker-end", function (d) {
                return "url(#" + d.type + "A" + ")";
            })
            .on('mouseover.tooltip', function (d) {
                //console.log(d);
                tooltip.transition()
                        .duration(300)
                        .style("opacity", .8);
                if (d.pValue == -1) {
                    tooltip.html("Source: " + d.source.name +
                            "<br>Target: " + d.target.name +
                            "<br>Role: " + d.type)
                            .style("left", (d3.event.pageX) + "px")
                            .style("top", (d3.event.pageY + 10) + "px");
                } else {
                    tooltip.html("Source: " + d.source.name +
                            "<br>Target: " + d.target.name +
                            "<br>Role: " + d.type +
                            "<br>P-value: " + d.pValue)
                            .style("left", (d3.event.pageX) + "px")
                            .style("top", (d3.event.pageY + 10) + "px");
                }
            })
            .on("mouseout.tooltip", function () {
                tooltip.transition()
                        .duration(100)
                        .style("opacity", 0);
            })
            .on("mousemove", function () {
                tooltip.style("left", (d3.event.pageX) + "px")
                        .style("top", (d3.event.pageY + 10) + "px");
            });

    var i = 0;
    var k = 0;
    var j = 0;
    var circle = s.append("g").selectAll("circle")
            .data(force.nodes())
            .enter().append("circle")
            .on("click", function (d) {
                //  var gene = d3.values(nodes).indexOf(this);
                tooltip.transition()
                        .duration(300)
                        .style("opacity", .8);
                var showGene = geneInfoMap.get(d.name);
                if (showGene.role != "") {
                    if (showGene.operon != "") {
                        tooltip.html("Locus Tag: " + showGene.locusTag + "<br>"
                                + "Gene name: " + showGene.name + "<br>"
                                + "Protein Id: " + showGene.proteinId + "<br>"
                                + "Role: " + showGene.role + "<br>"
                                + "Operon: " + showGene.operon)
                                .style("left", (d3.event.pageX) + "px")
                                .style("top", (d3.event.pageY + 10) + "px");
                    } else {
                        tooltip.html("Locus Tag: " + showGene.locusTag + "<br>"
                                + "Gene name: " + showGene.name + "<br>"
                                + "Protein Id: " + showGene.proteinId + "<br>"
                                + "Role: " + showGene.role + "<br>")
                                .style("left", (d3.event.pageX) + "px")
                                .style("top", (d3.event.pageY + 10) + "px");
                    }
                } else {
                    if (showGene.operon != "") {
                        tooltip.html("Locus Tag: " + showGene.locusTag + "<br>"
                                + "Gene name: " + showGene.name + "<br>"
                                + "Protein Id: " + showGene.proteinId + "<br>"
                                + "Operon: " + showGene.operon)
                                .style("left", (d3.event.pageX) + "px")
                                .style("top", (d3.event.pageY + 10) + "px");
                    } else {
                        tooltip.html("Locus Tag: " + showGene.locusTag + "<br>"
                                + "Gene name: " + showGene.name + "<br>"
                                + "Protein Id: " + showGene.proteinId)
                                .style("left", (d3.event.pageX) + "px")
                                .style("top", (d3.event.pageY + 10) + "px");
                    }
                }
            })
            .on('mouseover.fade', fade(0.1))
            .on('mouseover.operon', function (d) {
                var selectedGene = geneInfoMap.get(d.name);
                var geneOperonName = selectedGene.operon;
                //console.log("geneOperonName: " + geneOperonName);
                //console.log("selectedGene: " + selectedGene.name);
                circle.style("fill", function (d) {
                    var showGene = geneInfoMap.get(d.name);
                    //console.log("geneOperonName: " + geneOperonName);
                    //console.log("selectedGene: " + selectedGene.name + " == showGene.name: " + showGene.name);
                    if (geneOperonName != "" && showGene.operon == geneOperonName) {
                        return "#ffcc00";
                    } else if (showGene.role == "Activator") {
                        return 'green';
                    } else if (showGene.role == "Repressor") {
                        return "red";
                    } else if (showGene.role == "Dual") {
                        return "#4d94ff";
                    } else if (showGene.role == "Sigma") {
                        return "#00cc99";
                    }
                    return 'gray';
                })
            })
            .on("mouseout.tooltip", function () {
                tooltip.transition()
                        .duration(100)
                        .style("opacity", 0);
            })
            .on('mouseout.fade', fade(1))
            .on('mouseout.operon', function (d) {
                circle.style("fill", function (d) {
                    var showGene = geneInfoMap.get(d.name);
                    // console.log(showGene);
                    //console.log("role: " + showGene.role);
                    if (showGene.role == "Activator") {
                        return 'green';
                    } else if (showGene.role == "Repressor") {
                        return "red";
                    } else if (showGene.role == "Dual") {
                        return "#4d94ff";
                    } else if (showGene.role == "Sigma") {
                        return "#00cc99";
                    }
                    return 'gray';
                })
            })
            .on("mousemove", function () {
                tooltip.style("left", (d3.event.pageX) + "px")
                        .style("top", (d3.event.pageY + 10) + "px");
            })
            .on('dblclick', releasenode)
            .style("fill", function (d) {
                for (i = i; i < d3.values(nodes).length; i++) {

                    if (d3.values(nodes)[i].role == "Activator") {
                        i++;
                        return 'green';
                    } else if (d3.values(nodes)[i].role == "Repressor") {
                        i++;
                        return "red";
                    } else if (d3.values(nodes)[i].role == "Dual") {
                        i++;
                        return "#4d94ff";
                    } else if (d3.values(nodes)[i].role == "Sigma") {
                        i++;
                        return "#00cc99";
                    }
                    i++;
                    return 'gray';
                }
            })
            .style("stroke", function (d) {
                for (k = k; k < d3.values(nodes).length; k++) {
                    if (d3.values(nodes)[k].role == "Activator") {
                        k++;
                        return '#193300';
                    } else if (d3.values(nodes)[k].role == "Repressor") {
                        k++;
                        return "#b30000";
                    } else if (d3.values(nodes)[k].role == "Dual") {
                        k++;
                        return "blue";
                    } else if (d3.values(nodes)[k].role == "Sigma") {
                        k++;
                        return "#00cc99";
                    }
                    k++;
                    return 'gray';
                }
            })
            .attr("r", 8)
            .call(force.drag);
    var text = s.append("g").selectAll("text")
            .data(force.nodes())
            .enter().append("text")
            .attr("x", 8)
            .attr("y", ".31em")
            .text(function (d) {
                return d.name;
            });
    // Use elliptical arc path segments to doubly-encode directionality.
    function tick() {
        path.attr("d", linkArc);
        circle.attr("transform", transform);
        text.attr("transform", transform);
    }
    function linkArc(d) {
        var dx = d.target.x - d.source.x,
                dy = d.target.y - d.source.y,
                dr = Math.sqrt(dx * dx + dy * dy);
        return "M" + d.source.x + "," + d.source.y + "A" + dr + "," + dr + " 0 0,1 " + d.target.x + "," + d.target.y;
    }
    function transform(d) {
        return "translate(" + d.x + "," + d.y + ")";
    }
    function isConnected(a, b) {
        //console.log("isConnected start");
        //console.log("a.name: " + a + " b.name: " + b.name);
        if (a.name == b.name) {
            return 1;
        }
        for (var i = 0; i < regInteractions.length; i++) {
            if ((regInteractions[i].tfName == a.name && regInteractions[i].tgName == b.name) || (regInteractions[i].tgName == a.name && regInteractions[i].tfName == b.name)) {
                //console.log("Achei, TF: " + regInteractions[i].tfName)
                //console.log("Achei, TG: " + regInteractions[i].tgName);
                return 1
            } else {
                //console.log("Não foi dessa vez");
            }
        }
        //console.log("isConnected end");
        return 0;
    }

    function isOperonRegulates(a, b) {
        //console.log("isConnected start");
        //console.log("a.name: " + a + " b.name: " + b.name);
        for (var i = 0; i < regInteractions.length; i++) {
            if ((regInteractions[i].tfName == a && regInteractions[i].tgName == b.name) || (regInteractions[i].tgName == a && regInteractions[i].tfName == b.name)) {
                //console.log("Achei, TF: " + regInteractions[i].tfName)
                //console.log("Achei, TG: " + regInteractions[i].tgName);
                return 1
            } else {
                //console.log("Não foi dessa vez");
            }
        }
        //console.log("isConnected end");
        return 0;
    }

    function fade(opacity) {
        return d => {
            circle.style('stroke-opacity', function (o) {
                //console.log(d.name);
                //console.log(o.name);
                var selectedGene = d;
                var isPartOfOperon = o;
                var thisOpacity = isConnected(d, o) ? 1 : opacity;
                //console.log(thisOpacity);
                //console.log("selectedGene.operon: " + selectedGene.operon + " isPartOfOperon.operon: " + isPartOfOperon.operon);
                if (thisOpacity != 1 && selectedGene.operon != "" && selectedGene.operon == isPartOfOperon.operon) {
                    //console.log("Is equal");
                    thisOpacity = 1;
                } else if (thisOpacity != 1 && selectedGene.operon != "") {
                    var belongToOperon = [];
                    belongToOperon = operons.get(selectedGene.operon);
                    console.log("AQUI!");
                    console.log(belongToOperon.genes.length);
                    for (var i = 0; i < belongToOperon.genes.length; i++) {
                        if (selectedGene.name != belongToOperon.genes[i]) {
                            //console.log("belongToOperon.genes[" + i + "]: " + belongToOperon.genes[i] + " o: " + o.name);
                            thisOpacity = isOperonRegulates(belongToOperon.genes[i], o) ? 1 : opacity;
                            //console.log(thisOpacity);
                        }
                    }
                }
                //console.log("--------------------------------------------------");
                this.setAttribute('fill-opacity', thisOpacity);
                return thisOpacity;
            });
            path.style('opacity', function (o) {
                //console.log("Paths start--------------------------");
                var selectedGene = d;
                var isPartOfOperon = o;
                console.log("d: ");
                console.log(d);
                console.log("selectedGene: ");
                console.log(selectedGene);
                //console.log("isPartOfOperon: ")
                //console.log(o.target);
                //console.log(o.source);
                if (o.source === d || o.target === d) {
                    //console.log(1);
                    return 1;
                } else if (selectedGene.operon != "") {
                    //console.log("selectedGene.operon: " + selectedGene.operon + " == " + "isPartOfOperon.source.operon: " + isPartOfOperon.source.operon);
                    if (selectedGene.operon == isPartOfOperon.target.operon) {
                        //console.log(1);
                        return 1;
                    } else {
                        var belongToOperon = [];
                        belongToOperon = operons.get(selectedGene.operon);
                        console.log("AQUI!");
                        console.log(selectedGene.operon);
                        console.log(belongToOperon.genes.length);
                        for (var i = 0; i < belongToOperon.genes.length; i++) {
                            if (selectedGene.name != belongToOperon.genes[i]) {
                                //console.log("belongToOperon.genes[" + i + "]: " + belongToOperon.genes[i] + " o.target: " + o.target.name);
                                if (o.source.name === belongToOperon.genes[i] || o.target.name === belongToOperon.genes[i]) {
                                    //console.log(1);
                                    return 1;
                                }
                                //console.log(thisOpacity);
                            }
                        }
                    }
                }
                //console.log(0);
                //console.log("------------------------Paths end")
                return opacity;
            });
        };
    }
    function releasenode(d) {
        d.fx = null;
        d.fy = null;
    }
}