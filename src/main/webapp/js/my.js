function findLargest3(ar) {
	var num = new Array();
	// sort descending
	ar.sort(function(a, b) {
		if (a < b) {
			return 1;
		} else if (a == b) {
			return 0;
		} else {
			return -1;
		}
	});
	num.push(ar[0]);
	num.push(ar[1]);
	num.push(ar[2]);

	return num;
}


function  genNiftyStrick(){
	
	var strickArray = new Array();
	var startStrick = 8500;
	var endStrick = 10000;
	var temp=startStrick;
	for (var i = 0; i < 50; i++) {
		temp = temp+50;
		strickArray.push(temp);	
		 if(temp == endStrick){
			break;
		}
	}
	return strickArray;
}

function  genBankNiftyStrick(){
	
	var strickArray = new Array();
	var startStrick = 19800;
	var endStrick = 24500;
	var temp=startStrick;
	for (var i = 0; i < 50; i++) {
		temp = temp+100;
		strickArray.push(temp);	
		 if(temp == endStrick){
			break;
		}
	}
	
	return strickArray;
}

function  genUSDINDStrick(){
	
	var strickArray = new Array();
	var startStrick = 61.00;
	var endStrick = 69;
	var temp=startStrick;
	for (var i = 0; i < 50; i++) {
		temp = temp+0.25;
		strickArray.push(temp.toFixed(2));	
		 if(temp == endStrick){
			break;
		}
	}
	
	return strickArray;
}


function createStrickPriceSelectByIDForPayOff(divID,selectPriceID, insturement) {
	
	var id =selectPriceID;
    $('#'+selectPriceID).remove();
	var myDiv = document.getElementById(divID);
	var strickArray= new Array();
	if(insturement == "nifty"){	
		var a=genNiftyStrick();
		strickArray=a;
	} else if(insturement == "bankNifty"){
		var a =genBankNiftyStrick();
		strickArray=a;
		//strickArray=bankNiftyStrickes;
	} else if(insturement == "usdinr"){
		strickArray=genUSDINDStrick();
	} else if(insturement == "other"){
		//strickArray=niftyStrickes;
		// $('#strickPriceSelect').remove();
		var myDiv = document.getElementById(divID);
		var selectList = document.createElement("input");
		//strickPriceSelect
		selectList.setAttribute("id", selectPriceID);
		selectList.setAttribute("class", "form-control");
		myDiv.appendChild(selectList);
		return;
	}
	 
	//Create and append select list
	var selectList = document.createElement("select");
	selectList.setAttribute("id", selectPriceID);
	selectList.setAttribute("class", "form-control");
	selectList.setAttribute("onchange", "calPayOff()");
	myDiv.appendChild(selectList);

	//Create and append the options
	for (var i = 0; i < strickArray.length; i++) {
		var option = document.createElement("option");
		option.setAttribute("value", strickArray[i]);
		option.text = strickArray[i];
		selectList.appendChild(option);
	}
}

function createNiftyStrickPriceSelectByIDandProvideSelectID(divID, selectPriceID) {
	
	var array = [ 8500, 8550, 8600, 8650, 8700, 8750, 8800, 8850, 8900, 8950,
			9000, 9050, 9100, 9150, 9200, 9250, 9300, 9350, 9400, 9450, 9500,
			9550, 9600, 9650, 9700, 9750, 9800, 9850, 9900, 9950, 10000 ];
	
	var myDiv = document.getElementById(divID);
	//Create and append select list
	var selectList = document.createElement("select");
	selectList.setAttribute("id", selectPriceID);
	selectList.setAttribute("class", "form-control");
	selectList.setAttribute("onchange", "calPayOff()");
	myDiv.appendChild(selectList);

	//Create and append the options
	for (var i = 0; i < array.length; i++) {
		var option = document.createElement("option");
		option.setAttribute("value", array[i]);
		option.text = array[i];
		selectList.appendChild(option);
	}
}

function createNiftyStrickPriceSelectByID(divID) {
	var array = [ 8500, 8550, 8600, 8650, 8700, 8750, 8800, 8850, 8900, 8950,
			9000, 9050, 9100, 9150, 9200, 9250, 9300, 9350, 9400, 9450, 9500,
			9550, 9600, 9650, 9700, 9750, 9800, 9850, 9900, 9950, 10000 ];
	var myDiv = document.getElementById(divID);
	//Create and append select list
	var selectList = document.createElement("select");
	selectList.setAttribute("id", "strickPriceSelect");
	selectList.setAttribute("class", "form-control");
	selectList.setAttribute("onchange", "calPayOff()");
	myDiv.appendChild(selectList);

	//Create and append the options
	for (var i = 0; i < array.length; i++) {
		var option = document.createElement("option");
		option.setAttribute("value", array[i]);
		option.text = array[i];
		selectList.appendChild(option);
	}
}

function createStrickPriceSelect(array) {
	var myDiv = document.getElementById("strickPriceDiv");
	//Create and append select list
	var selectList = document.createElement("select");
	selectList.setAttribute("id", "strickPriceSelect");
	selectList.setAttribute("class", "form-control");
	selectList.setAttribute("onchange", "loadOIChart()");
	myDiv.appendChild(selectList);

	//Create and append the options
	for (var i = 0; i < array.length; i++) {
		var option = document.createElement("option");
		option.setAttribute("value", array[i]);
		option.text = array[i];
		selectList.appendChild(option);
	}
}

function createSelect(array) {
	var myDiv = document.getElementById("expiryDiv");
	//Create and append select list
	var selectList = document.createElement("select");
	selectList.setAttribute("id", "mySelect");
	selectList.setAttribute("class", "form-control");
	selectList.setAttribute("onchange", "loadDoc()");
	myDiv.appendChild(selectList);

	//Create and append the options
	for (var i = 0; i < array.length; i++) {
		var option = document.createElement("option");
		option.setAttribute("value", array[i]);
		option.text = array[i];
		selectList.appendChild(option);
	}
}


function createOptionTable() {
	var x = document.createElement("TABLE");
	x.setAttribute("id", "optionchain");
	x.setAttribute("class", "opttbldata");
	//x.setAttribute("border", "0");
	x.setAttribute("align", "center");
	x.setAttribute("cellpadding", "4");
	x.setAttribute("cellspacing", "1");
	//x.setAttribute("class", "table");
	x.setAttribute("width", "100%");

	$("#mydiv").append(x);

	// table header
	$("#optionchain")
			.append(
					'<thead>'
							+ '<tr class="optionHead"><th colspan="8" >CALLS</th><th>&nbsp;</th><th colspan="8" >PUTS</th></tr>'
							+

							'<tr class="optionHead">'
							+ '<th title="Delta">Delta</th> <th title="Theta">Theta</th>  <th title="Rho">Rho</th>'
							+ '<th title="Open Interest">OI</th><th title="Change OI"> Change in OI</th>'
							+ '<th title="No. of Contracts traded">Volume</th><th title="Implied Volatility">IV</th>'
							+ '<th title="Last Traded Price">LTP</th><th title="Strike Price">Strike Price</th>'
							+ '<th title="Last Traded Price">LTP</th><th title="Implied Volatility">IV</th><th title="No. of Contracts traded">Volume</th>'
							+ '<th title="Change OI"> Change in OI</th><th title="Open Interest">OI</th>'
							+ '<th title="Rho">Rho</th>  <th title="Theta">Theta</th> <th title="Delta">Delta</th> '
							+ '</tr>' + '</thead>');

	// table Fotter 
	$("#optionchain")
			.append(
					'<tfoot><tr class="optionHead"> '
							+ '<td><b></b></td>  <td ><b></b></td> <td ><b></b></td> <td id="ce_oi_total" ><b></b></td> <td colspan="9"></td>'
							+ '<td id="pe_oi_total" ><b></b></td> <td><b></b></td>  <td ><b></b></td> <td ><b></b></td>'
							+ '</tr></tfoot>');
}

function drawCallsPuts(calls, puts) {
	console.log(calls);
	console.log(puts);
	var largest = 0;
	if(calls>=puts){
		largest=calls;
	}else{
		largest=puts;
	}
	
	
	
	console.log(largest);
	Highcharts.chart('totalCallsPutsDiv', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: ''
	    },
	    subtitle: {
	        text: ''
	    },
	    xAxis: {
	        type: 'category',
	        labels: {
	            rotation: -0,
	            style: {
	                fontSize: '13px',
	               // fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    },
	    yAxis: {
	    	labels: {
                enabled: false
            },
           
	        title: {
	            text: ''
	        },
	        visible: false
	    },
	    legend: {
	        enabled: false
	    },
	    
	    exporting: { enabled: false },
	    tooltip: {
	        pointFormat: '<b>{point.y:.1f} </b>'
	    },
        plotOptions: {
        	column: {
            	zones: [{
                	value: largest, // Values up to 10 (not including) ...
                    color: 'red' // ... have the color blue.
                },{
                	color: 'green' // Values from 10 (including) and up have the color red
                		
                }]
            }
        },
	    series: [{
	        name: 'Population',
	        data: [
	            ['Calls', calls, ],
	            ['Puts', puts]
	            
	        ],
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y:.1f}', // one decimal
	            y: 10, // 10 pixels down from the top
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    }]
	});
	
}

function totalOIgraph(callOI, putOI) {

	historicalBarChart = [ {
		key : "Cumulative Return",
		values : [ {
			"label" : "Calls",
			"color" : "red",
			"value" : callOI
		},

		{
			"label" : "Puts",
			"color" : "green",
			"value" : putOI
		} ]
	} ];

	nv.addGraph(function() {
		var chart = nv.models.discreteBarChart().x(function(d) {
			return d.label;
		}).y(function(d) {
			return d.value;
		}).staggerLabels(true).showValues(true).showYAxis(false).duration(250);

		d3.select('#totalOI svg').datum(historicalBarChart).call(chart);

		nv.utils.windowResize(chart.update);
		return chart;
	});
}

function oiBarchart(jsonResponse) {
	var d = jsonResponse;
	var data = d.dataset;
	var callArray = [];
	var putArray = [];

	for (var i = 0; i < data.length; i++) {
		var chartCallData;
		if (data[i].ce_OI == "-") {
			chartCallData = {
				"x" : data[i].strike_Price.trim(),
				"y" : 0
			};
		} else {
			chartCallData = {
				"x" : data[i].strike_Price.trim(),
				"y" : parseInt(data[i].ce_OI.replace(/,/, "").trim())
			};
		}
		var chartPutData;
		if (data[i].pe_OI == "-") {
			chartPutData = {
				"x" : data[i].strike_Price.trim(),
				"y" : 0
			};
		} else {
			chartPutData = {
				"x" : data[i].strike_Price.trim(),
				"y" : parseInt(data[i].pe_OI.replace(/,/, "").trim())
			};
		}

		callArray.push(chartCallData);
		putArray.push(chartPutData);
	}

	var chart = nv.models.multiBarChart().stacked(false).showControls(false);
	;
	d3.select('#oiBarchart svg').datum([ {
		key : "Call",
		color : "#51A351",
		values : callArray
	}, {
		key : "Put",
		color : "#BD362F",
		values : putArray

	} ]).transition().duration(500).call(chart);
}

