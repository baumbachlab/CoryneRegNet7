function operonsCombined2(data) {
    //console.log("Combined Data");

    var links = [];
    var nodes = {};
    for (i = 0; i < regInteractionsCombined.length; i++) {
        if (regInteractionsCombined[i].riRole == "A") {
            links.push({source: regInteractionsCombined[i].tfName, target: regInteractionsCombined[i].tgName, type: "activator", tfRole: regInteractionsCombined[i].tfRole, pValue: regInteractions[i].pValue});
        } else if (regInteractionsCombined[i].riRole == "R") {
            links.push({source: regInteractionsCombined[i].tfName, target: regInteractionsCombined[i].tgName, type: "repressor", tfRole: regInteractionsCombined[i].tfRole, pValue: regInteractions[i].pValue});
        } else if (regInteractionsCombined[i].riRole == "D") {
            links.push({source: regInteractionsCombined[i].tfName, target: regInteractionsCombined[i].tgName, type: "dual", tfRole: regInteractionsCombined[i].tfRole, pValue: regInteractions[i].pValue});
        } else if (regInteractionsCombined[i].riRole == "S") {
            links.push({source: regInteractionsCombined[i].tfName, target: regInteractionsCombined[i].tgName, type: "sigma", tfRole: regInteractionsCombined[i].tfRole, pValue: regInteractions[i].pValue});
        }
    }
    // Compute the distinct nodes from the links.
    links.forEach(function (link) {
        //console.log("gene: " + link.target);
        if (link.tfRole == "Repressor") {
            //console.log("gene: " + link.source);
            link.source = nodes[link.source] || (nodes[link.source] = {name: link.source, role: link.tfRole, operon: geneInfoMapCombined.get(link.source).operon});
        } else if (link.tfRole == "Activator") {
            //console.log("gene: " + link.source);
            link.source = nodes[link.source] || (nodes[link.source] = {name: link.source, role: link.tfRole, operon: geneInfoMapCombined.get(link.source).operon});
        } else {
            //console.log("gene: " + link.source);
            link.source = nodes[link.source] || (nodes[link.source] = {name: link.source, role: link.tfRole, operon: geneInfoMapCombined.get(link.source).operon});
        }
    });

    links.forEach(function (link) {
        link.target = nodes[link.target] || (nodes[link.target] = {name: link.target, role: "", operon: geneInfoMapCombined.get(link.target).operon});
    });

    var force = d3.layout.force()
            .nodes(d3.values(nodes))
            .links(links)
            .size([width, height])
            .linkDistance(100)
            .charge(-150)
            .on("tick", tick)
            .start();
    var svg = d3.select("#operons-combined")
            .append("svg")
            .attr({
                "width": "100%",
                "height": "100%"
            })
            .attr("viewBox", "0 0 " + width + " " + height)
            .attr("preserveAspectRatio", "xMidYMid meet")
            .attr("pointer-events", "all")
            .call(d3.behavior.zoom().on("zoom", redraw));
    var vis = svg
            .append('svg:g');
    function redraw() {
        vis.attr("transform",
                "translate(" + d3.event.translate + ")"
                + " scale(" + d3.event.scale + ")");
    }

    // Per-type markers, as they don't inherit styles.
    svg.append("defs").selectAll("marker")
            .data(["sigma-small", "repressor-small", "activator-small", "dual-small", "sigma-normal", "repressor-normal", "activator-normal", "dual-normal", "sigma-big", "repressor-big", "activator-big", "dual-big"])
            .enter().append("marker")
            .attr("id", function (d) {
                return d;
            })
            .attr("viewBox", "0 -5 10 10")
            .attr("refX", function (d) {
                if (d == "activator-small") {
                    return 18;
                } else if (d == "repressor-small") {
                    return 18;
                } else if (d == "dual-small") {
                    return 18;
                } else if (d == "sigma-small") {
                    return 18;
                } else if (d == "activator-normal") {
                    return 20;
                } else if (d == "repressor-normal") {
                    return 20;
                } else if (d == "dual-normal") {
                    return 20;
                } else if (d == "sigma-normal") {
                    return 20;
                } else if (d == "activator-big") {
                    return 23;
                } else if (d == "repressor-big") {
                    return 23;
                } else if (d == "dual-big") {
                    return 23;
                } else if (d == "sigma-big") {
                    return 23;
                }
            })
            .attr("refY", -1.5)
            .attr("markerWidth", 8)
            .attr("markerHeight", 8)
            .attr("orient", "auto")
            .append("path")
            .attr("d", "M0,-5L10,0L0,5");
    var path = svg.append("g").selectAll("path")
            .data(force.links())
            .enter().append("path")
            .attr("class", function (d) {
                return "link " + d.type;
            })
            .attr('marker-end', function (d, i) {
                //console.log("call marker-end:");
                //console.log(d);
                //console.log(i);
                var genesOperon = operons.get(d.target.name);
                //console.log(genesOperon);
                if (genesOperon != null) {
                    if (genesOperon.genes.length == 2) {
                        return "url(#" + d.type + "-normal" + ")";
                    } else {
                        return "url(#" + d.type + "-big" + ")";
                    }
                } else {
                    return "url(#" + d.type + "-small" + ")";
                }

            })
            .on('mouseover.tooltip', function (d) {
                //console.log(d);
                tooltip.transition()
                        .duration(300)
                        .style("opacity", .8);
                if (d.pValue == -1) {
                    console.log("pvalue: " + d.pValue);
                    tooltip.html("Source: " + d.source.name +
                            "<br>Target: " + d.target.name +
                            "<br>Role: " + d.type)
                            .style("left", (d3.event.pageX) + "px")
                            .style("top", (d3.event.pageY + 10) + "px");
                } else {
                    console.log("pvalue: " + d.pValue);
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
            .on('mouseout.fade', fade(1))
            .on("mousemove", function () {
                tooltip.style("left", (d3.event.pageX) + "px")
                        .style("top", (d3.event.pageY + 10) + "px");
            });
    var i = 0;
    var k = 0;
    var j = 0;
    var circle = svg.append("g").selectAll("circle")
            .data(force.nodes())
            .enter().append("circle")
            .on("click", function (d) {
                //  var gene = d3.values(nodes).indexOf(this);
                tooltip.transition()
                        .duration(300)
                        .style("opacity", .8);
                var showGene = geneInfoMapCombined.get(d.name);
                //console.log(showGene);
                var operon = operons.get(showGene.name);
                if (operon != null) {
                    var belongToOperon = operons.get(showGene.name);
                    //console.log(belongToOperon.genes);
                    var aux;
                    for (var i = 0; i < belongToOperon.genes.length; i++) {
                        //console.log("name: " + belongToOperon.genes[i]);
                        //console.log("locusTag: " + belongToOperon.locusTag[i]);
                        if (i == 0) {
                            if (belongToOperon.genes[i] == belongToOperon.locusTag[i]) {
                                aux = belongToOperon.genes[i] + "," + "<br>";
                            } else {
                                aux = belongToOperon.locusTag[i] + " (" + belongToOperon.genes[i] + ")," + "<br>";
                            }

                        } else if (i == belongToOperon.genes.length - 1) {
                            if (belongToOperon.genes[i] == belongToOperon.locusTag[i]) {
                                aux += belongToOperon.genes[i];
                            } else {
                                aux += belongToOperon.locusTag[i] + " (" + belongToOperon.genes[i] + ")";
                            }

                        } else {
                            if (belongToOperon.genes[i] == belongToOperon.locusTag[i]) {
                                aux += belongToOperon.genes[i] + "," + "<br>";
                            } else {
                                aux += belongToOperon.locusTag[i] + " (" + belongToOperon.genes[i] + ")," + "<br>";
                            }
                        }
                        //console.log(belongToOperon.genes[i]);
                        //console.log("aux: " + aux);
                    }
                    //console.log(aux);
                    tooltip.html("Operon: " + showGene.locusTag + "<br>"
                            + "Genes: " + "<br>" + aux)
                            .style("left", (d3.event.pageX) + "px")
                            .style("top", (d3.event.pageY + 10) + "px");
                } else {
                    if (showGene.role != "") {
                        tooltip.html("Locus Tag: " + showGene.locusTag + "<br>"
                                + "Gene name: " + showGene.name + "<br>"
                                + "Protein Id: " + showGene.proteinId + "<br>"
                                + "Role: " + showGene.role)
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
            .on("mouseout.tooltip", function () {
                tooltip.transition()
                        .duration(100)
                        .style("opacity", 0);
            })
            .on('mouseout.fade', fade(1))
            .on("mousemove", function () {
                tooltip.style("left", (d3.event.pageX) + "px")
                        .style("top", (d3.event.pageY + 10) + "px");
            })
            .on('dblclick', releasenode)
            .style("fill", function (d) {
                for (i = i; i < d3.values(nodes).length; i++) {
                    var geneName = d3.values(nodes)[i].name;
                    //console.log(geneName);
                    var operon = operons.get(geneName);
                    if (operon != null) {
                        //console.log(d3.values(nodes)[i].name);
                        i++;
                        return "#ffcc00";
                    } else if (d3.values(nodes)[i].role == "Activator") {
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
                    var geneName = d3.values(nodes)[k].name;
                    //console.log(geneName);
                    var operon = operons.get(geneName);
                    if (operon != null) {
                        k++;
                        return "#ffcc00";
                    } else if (d3.values(nodes)[k].role == "Activator") {
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
            .attr("r", function (d) {
                //console.log(d.name);
                var genesOperon = operons.get(d.name);
                //console.log(genesOperon);
                if (genesOperon != null) {
                    if (genesOperon.genes.length >= 11) {
                        return 16;
                    } else if (genesOperon.genes.length >= 8) {
                        return 14;
                    } else if (genesOperon.genes.length >= 5) {
                        return 12;
                    } else {
                        //alert("This operon has more than 4 genes");
                        return 10;
                    }
                } else {
                    return 8;
                }
            })
            .call(force.drag);
    var text = svg.append("g").selectAll("text")
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
        circle.attr("cx", function (d) {
            return d.x = Math.max(8, Math.min(width - 8, d.x));
        })
                .attr("cy", function (d) {
                    return d.y = Math.max(8, Math.min(height - 8, d.y));
                });
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
        if (a.name == b.name) {
            return 1;
        }
        for (var i = 0; i < regInteractionsCombined.length; i++) {
            if ((regInteractionsCombined[i].tfName == a.name && regInteractionsCombined[i].tgName == b.name) || (regInteractionsCombined[i].tgName == a.name && regInteractionsCombined[i].tfName == b.name)) {
                return 1
            }
        }
        return 0;
    }

    function isOperonRegulates(a, b) {
        for (var i = 0; i < regInteractionsCombined.length; i++) {
            if ((regInteractionsCombined[i].tfName == a && regInteractionsCombined[i].tgName == b.name) || (regInteractionsCombined[i].tgName == a && regInteractionsCombined[i].tfName == b.name)) {
                return 1
            } else {
            }
        }
        return 0;
    }

    function fade(opacity) {
        return d => {
            circle.style('stroke-opacity', function (o) {
                //console.log(d.name);
                //console.log(o.name);
                const thisOpacity = isConnected(d, o) ? 1 : opacity;
                this.setAttribute('fill-opacity', thisOpacity);
                return thisOpacity;
            });
            path.style('opacity', function (o) {
                if (o.source === d || o.target === d) {
                    return 1;
                } else {
                    return opacity;
                }
            });
        };
    }
    function releasenode(d) {
        d.fx = null;
        d.fy = null;
    }
}