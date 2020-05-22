/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function tfsRegAGene(data, count, maxValue) {

    //console.log(data);
    //console.log(count);
    //console.log(maxValue);
    
    var margin = {left: 80, right: 4.17, top: 6.17, bottom: 60};
    var width = 500;
    var height = 300 - margin.top - margin.bottom;
    var g = d3.select('#sRNA' + count)
            .append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + ", "
                    + margin.top + ")");

    var tip = d3.tip().attr('class', 'd3-tip').direction('e').offset([0, 5])
            .html(function (d) {
                var genes = Number(d.genes);
                var content = "<span style='margin-left: 2.5px;'>" + "Number of sRNAs: " + d.tfs + "<br>" + "Number of genes: "
                        + genes + "</span><br>";
                return content;
            });
    g.call(tip);

    var x = d3.scaleBand()
            .domain(data.map(function (d) {
                return d.tfs;
            }))
            .range([0, width])
            .paddingInner(0.2)
            .paddingOuter(0.2);
    var y = d3.scaleLinear()
            .domain([0, d3.max(data, function (d) {
                    return d.genes;
                })])
            .range([height, 0]);
    var xAxisCall = d3.axisBottom(x);
    g.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0, " + height + ")")
            .call(xAxisCall)
            .selectAll("text");
    var yAxisCall = d3.axisLeft(y)
            .ticks(5)
            .tickFormat(function (d) {
                return d;
            });
    g.append("g")
            .attr("class", "y-axis")
            .call(yAxisCall);
    //x label
    g.append("text")
            .attr("class", "x axis-label")
            .attr("x", width / 2)
            .attr("y", height + 40)
            .attr("font-size", "20px")
            .attr("text-anchor", "middle")
            .text("Number of sRNAs regulating a gene");
    //y label
    g.append("text")
            .attr("class", "y axis-label")
            .attr("x", -(height / 2))
            .attr("y", -65)
            .attr("font-size", "20px")
            .attr("text-anchor", "middle")
            .attr("transform", "rotate(-90)")
            .text("Number of genes");
    var rectangle = g.selectAll("rect")
            .data(data);
    rectangle.enter()
            .append("rect")
            .attr("y", function (d) {
                return y(d.genes);
            })
            .attr("x", function (d) {
                return x(d.tfs);
            })
            .attr("width", x.bandwidth)
            .attr("height", function (d) {
                return height - y(d.genes);
            })
            .attr("fill", "grey")
            .on('mouseover', tip.show)
            .on('mouseout', tip.hide);

}


