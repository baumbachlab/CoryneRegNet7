/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function noOperon(data) {

    var force = d3.layout.force()
            .nodes(d3.values(nodes))
            .links(links)
            .size([width, height])
            .gravity(0.2)
            .linkDistance(function (d) {
                //console.log(d.source);
                //console.log(d.target.name);
                //console.log(d);
                if (d.regulatorsDensity > 0) {
                    return 250;
                } else if (d.source.weight > 120) {
                    return 150;
                }
                return 75;
            })
            .charge(function (d) {
                if (d.regulatorsDensity > 0) {
                    return -300;
                }
                return -100;
            })
            .start();

    //###########################   BEHAVIORS #########################

    var drag = d3.behavior.drag()
            .origin(function (d) {
                return d;
            })
            .on("dragstart", dragstarted)
            .on("drag", dragged)
            .on("dragend", dragended);


    var svg = d3.select("#no-operons")
            .append("svg")
            .attr({
                "width": "100%",
                "height": "100%"
            })
            .attr("viewBox", "0 0 " + width + " " + height)
            .attr("preserveAspectRatio", "xMidYMid meet")
            .attr("pointer-events", "all")
            .append("g");

    var vis = svg
            .append('svg:g');

    function redraw() {
        vis.attr("transform",
                "translate(" + d3.event.translate + ")"
                + " scale(" + d3.event.scale + ")");
    }

// Per-type markers, as they don't inherit styles.
    svg.append("defs").selectAll("marker")
            .data(["small-sigma", "small-repressor", "small-activator", "small-dual", "sigma", "repressor", "activator", "dual", "big-sigma", "big-repressor", "big-activator", "big-dual", "everest-sigma", "everest-repressor", "everest-activator", "everest-dual"])
            .enter().append("marker")
            .attr("id", function (d) {
                return d;
            })
            .attr("viewBox", "0 -5 10 10")
            .attr("refX", function (d) {
                if (d == "small-activator") {
                    return 15;
                } else if (d == "small-repressor") {
                    return 15;
                } else if (d == "small-dual") {
                    return 15;
                } else if (d == "small-sigma") {
                    return 15;
                } else if (d == "activator") {
                    return 21;
                } else if (d == "repressor") {
                    return 21;
                } else if (d == "dual") {
                    return 21;
                } else if (d == "sigma") {
                    return 21;
                } else if (d == "big-activator") {
                    return 37;
                } else if (d == "big-repressor") {
                    return 37;
                } else if (d == "big-dual") {
                    return 37;
                } else if (d == "big-sigma") {
                    return 37;
                } else if (d == "everest-activator") {
                    return 67;
                } else if (d == "everest-repressor") {
                    return 67;
                } else if (d == "everest-dual") {
                    return 67;
                } else if (d == "everest-sigma") {
                    return 67;
                }
            })
            .attr("refY", -1.5)
            .attr("markerWidth", 10)
            .attr("markerHeight", 10)
            .attr("orient", "auto")
            .append("path")
            .attr("d", "M0,-5L10,0L0,5");
    
    var path = svg.append("g").selectAll("path")
            .data(force.links())
            .enter().append("path")
            .attr("class", function (d) {
                //console.log(d.type);
                return "link " + d.type;
            })
            .attr("marker-end", function (d) {
                //console.log(d.target.weight);
                if (d.target.weight <= 20) {
                    return "url(#" + "small-" + d.type + ")";
                } else if (d.target.weight <= 50) {
                    return "url(#" + d.type + ")";
                } else if ((d.target.weight <= 125)) {
                    return "url(#" + "big-" + d.type + ")";
                }
                return "url(#" + "everest-" + d.type + ")";
            })
            .on('mouseover.tooltip', function (d) {
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
                //console.log(d);
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
                var showGene = geneInfoMap.get(d.name);
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
                //console.log("==========================Node");
                //              for (i = i; i < d3.values(nodes).length; i++) {
                //console.log("role: " + d3.values(nodes)[i].role);
                //console.log("locusTag: " + d3.values(nodes)[i].locusTag);
                //console.log("name: " + d3.values(nodes)[i].name);
                //console.log("name: " + d3.values(nodes)[i].name);
                //console.log("role: " + d3.values(nodes)[i].role);
                if (d3.values(nodes)[i].role == "Activator") {
                    i++;
                    //console.log("green");
                    return "#349834";
                } else if (d3.values(nodes)[i].role == "Repressor") {
                    i++;
                    //console.log("red");
                    return "#ff6666";
                } else if (d3.values(nodes)[i].role == "Dual") {
                    i++;
                    //console.log("blue");
                    return "#4d94ff";
                } else if (d3.values(nodes)[i].role == "Sigma") {
                    i++;
                    return "#00b3b3";
                }
                i++;
                return 'gray';
                // }
            })
            .style("stroke", function (d) {
                //console.log("==========================Stroke");
                //for (k = k; k < d3.values(nodes).length; k++) {
                //console.log("role: " + d3.values(nodes)[k].role);
                if (d3.values(nodes)[k].role == "Activator") {
                    k++;
                    //console.log("green");
                    return "#193300";
                } else if (d3.values(nodes)[k].role == "Repressor") {
                    k++;
                    //console.log("red");
                    return "#b30000";
                } else if (d3.values(nodes)[k].role == "Dual") {
                    k++;
                    //console.log("blue");
                    return "blue";
                } else if (d3.values(nodes)[k].role == "Sigma") {
                    k++;
                    return "#008080";
                }
                k++;
//                    console.log("gray");
                return 'gray';
                //}
            })
            .attr("r", function (d) {
                //console.log("d.name: " + d.name);
                //console.log("d.weight: " + d.weight);
                if (d.weight > 125) {
                    return 70;
                }
                if (d.weight > 50) {
                    return 35;
                } else if (d.weight > 20) {
                    return 15;
                }
                return 8
            })
            .call(drag);

    var text = svg.append("g").selectAll("text")
            .data(force.nodes())
            .enter().append("text")
            .attr("x", 8)
            .attr("y", ".31em")
            .text(function (d) {
                return d.name;
            });

    var container = svg.append("g");

    container.append("g")
            .attr("class", "x axis")
            .selectAll("line")
            .data(d3.range(0, width, 10))
            .enter().append("line")
            .attr("x1", function (d) {
                return d;
            })
            .attr("y1", 0)
            .attr("x2", function (d) {
                return d;
            })
            .attr("y2", height);



    force.on("tick", tick);

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
        for (var i = 0; i < regInteractions.length; i++) {
            if ((regInteractions[i].tfName == a.name && regInteractions[i].tgName == b.name) || (regInteractions[i].tgName == a.name && regInteractions[i].tfName == b.name)) {
                //console.log("Achei, TF: " + regInteractions[i].tfName)
                //console.log("Achei, TG: " + regInteractions[i].tgName);
                return 1
            } else {
                //console.log("Não foi dessa vez");
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

    //####################################### EVENT HANDLERS  ########################

    function dragstarted(d) {
        force.stop()
    }

    function dragged(d) {
        d.px += d3.event.dx;
        d.py += d3.event.dy;
        d.x += d3.event.dx;
        d.y += d3.event.dy;
        tick(); // this is the key to make it work together with updating both px,py,x,y on d !
    }

    function dragended(d) {
        d.fixed = true; // of course set the node to fixed so the force doesn't include the node in its auto positioning stuff
        tick();
        force.resume();
    }
}