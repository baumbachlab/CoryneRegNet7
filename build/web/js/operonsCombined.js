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

function operonsCombinedDraw(container, nodes, edges) {

    // provide the data in the vis format
    var data = {
        nodes: nodes,
        edges: edges
    };


    // initialize your network!
    var network = new vis.Network(container, data, options);
    // these are all options in full.
    // these are all options in full.
    var options = {
        autoResize: true,
        height: "1500px",
        width: '100%',
        nodes: {
            shape: 'ellipse'
        },
        edges: {
            arrows: {
                to: {enabled: true, scaleFactor: 1, type: 'arrow'}
            }
        },
        physics: {
            enabled: true,
            solver: 'forceAtlas2Based',
            forceAtlas2Based: {
                gravitationalConstant: -50,
                centralGravity: 0.01,
                springConstant: 0.08,
                springLength: 100,
                damping: 0.4,
                avoidOverlap: 0
            },
        }
    }

    network.setOptions(options);
    network.on('click', function (properties) {
        var ids = properties.nodes;
        var clickedNodes = nodes.get(ids);
        //console.log('clicked nodes:', clickedNodes);
        if (clickedNodes.length == 1) {
            console.log("clickedNodes[0].label: ");
            console.log(clickedNodes);

            var nodeAux = geneInfoMapCombined.get(clickedNodes[0].id);

            var nodeInfo;
            //console.log(nodeAux);
            nodeInfo = "<b>Locus_Tag:</b> " + '<span><a href="geneInfo.htm?locusTag=' + nodeAux.locusTag + '&type=${type}" target="_blank">' + nodeAux.locusTag + '</a><span>'
                    + "<br>" + "<b>Name:</b> " + nodeAux.name
                    + "<br>" + "<b>Protein id:</b> " + '<span><a href="https://www.ncbi.nlm.nih.gov/protein/?term=' + nodeAux.proteinId + '" target="_blank">' + nodeAux.proteinId + '</a><span>';
            if (nodeAux.role != "") {
                nodeInfo += "<br>" + "<b>Role:</b> " + nodeAux.role;
            }

            if (nodeAux.operon != "") {
                nodeInfo = "<b>Operon:</b> " + '<span><a href="operonInfo.htm?name=' + nodeAux.locusTag + '&type=${type}" target="_blank">' + nodeAux.locusTag + '</a><span>'

                console.log(nodeAux);
                var geneOperon;
                for (var i = 0; i < nodeAux.genes.length; i++) {
                    var geneOperon = geneInfoMap.get(nodeAux.genes[i]);
                    console.log("gene operon");
                    console.log(geneOperon);

                    if (geneOperon == undefined) {
                        nodeInfo += "<br>&nbsp;&nbsp;&nbsp;" + (i + 1) + ":</b> " + nodeAux.genes[i];
                    } else {
                        if (geneOperon.locusTag != geneOperon.name) {
                            nodeInfo += "<br>&nbsp;&nbsp;&nbsp;" + (i + 1) + ":</b> " + '<span><a href="geneInfo.htm?locusTag=' + geneOperon.locusTag[i] + '&type=${type}" target="_blank">' + geneOperon.locusTag + '</a><span> (' + geneOperon.name + ')';
                        } else {
                            nodeInfo += "<br>&nbsp;&nbsp;&nbsp;" + (i + 1) + ":</b> " + '<span><a href="geneInfo.htm?locusTag=' + geneOperon.locusTag[i] + '&type=${type}" target="_blank">' + geneOperon.locusTag + '</a><span>';
                        }
                    }
                }
            }
            $('#gene-modal-content').html(nodeInfo);
            $('#locus-for-network').val(nodeAux.locusTag);
            $('#geneModal').modal('show');
        } else {
            var edgeIds = properties.edges;
            var clickedEdge = edges.get(edgeIds);
            console.log(clickedEdge);
            if (clickedEdge.length == 1) {
                //console.log("clickedNodes[0].label: " + clickedNodes[0].label);

                var sourceNode = geneInfoMapCombined.get(clickedEdge[0].from);
                var targetNode = geneInfoMapCombined.get(clickedEdge[0].to);

                var edgeInfo;
                if (sourceNode.locusTag != sourceNode.name) {
                    edgeInfo = "<b>Source:</b> " + '<span><a href="geneInfo.htm?locusTag=' + sourceNode.locusTag + '&type=${type}" target="_blank">' + sourceNode.locusTag + '</a><span>' + '(' + sourceNode.name + ")";

                } else {
                    edgeInfo = "<b>Source:</b> " + '<span><a href="geneInfo.htm?locusTag=' + sourceNode.locusTag + '&type=${type}" target="_blank">' + sourceNode.locusTag + '</a><span>';
                }

                if (targetNode.locusTag != targetNode.name) {
                    edgeInfo += "<br><b>Target:</b> " + '<span><a href="geneInfo.htm?locusTag=' + targetNode.locusTag + '&type=${type}" target="_blank">' + targetNode.locusTag + '</a><span>' + '(' + targetNode.name + ")";

                } else {
                    edgeInfo += "<br><b>Target:</b> " + '<span><a href="geneInfo.htm?locusTag=' + targetNode.locusTag + '&type=${type}" target="_blank">' + targetNode.locusTag + '</a><span>';
                }

                edgeInfo += "<br>" + "<b>Role:</b> " + clickedEdge[0].role;
                if (clickedEdge[0].pValue != -1) {
                    edgeInfo += "<br>" + "<b>P-value:</b> " + clickedEdge[0].pValue;
                }

                $('#ri-modal-content').html(edgeInfo);
                $('#riModal').modal('show');
            }
        }
        //var nodeInfo = "Name: " + d.data.name
        //var nodeInfo = "Name: " + d.data.name
        //      + "<br>" + "Colname: " + d.data.colname;
    });
}
