
function netPayOffShort(premiumRecived, int_Value) {
	return premiumRecived - int_Value;
}

//netPayOffLong
function netPayOffLong(premiumRecived, int_Value) {
	return int_Value - premiumRecived;
}


//#####################################common functions for Call and put###################

// intrinsic value value will always same for call and put
function callInternsicValue(spotOnExpiry, strickPrice) {
	var result = Math.max((spotOnExpiry - strickPrice), 0);
	return result;
}

//intrinsic value value will always same for call and put
function putInternsicValue(spotOnExpiry, strickPrice) {
	var result = Math.max((strickPrice - spotOnExpiry), 0);
	return result;
}

function calStrickOnExpiry(strickPrice) {
	var insSelected = document.getElementById('instrumentSelect').value;
	//console.log("******** " + insSelected);
	var strickPriceArray = new Array();
	if(insSelected == "nifty"){
		
		var distance = 50;
		var b = distance * 10;
		var start = strickPrice - b;
		
		var temp = start;
		for (var i = 0; i < 20; i++) {
			temp = temp + distance;
			strickPriceArray.push(temp);
		}
		
	}else if(insSelected == "bankNifty"){
		var distance = 50;
		var b = distance * 15;
		var start = strickPrice - b;
		
		var temp = start;
		for (var i = 0; i < 30; i++) {
			temp = temp + distance;
			strickPriceArray.push(temp);
		}
		
	} else if(insSelected == "usdinr"){
		
		var distance = 0.25;
		var b = distance * 15;
		var start = strickPrice - b;
		
		var temp = start;
		for (var i = 0; i < 30; i++) {
			temp = temp + distance;
			strickPriceArray.push(temp.toFixed(2));
		}
		
	}else if(insSelected == "other"){
		
	}
	
	
	
	return strickPriceArray;
}

function drawOIChart(title, netPayOff, strickPrice, breakEvenLinep) {
	
	//console.log("netPayOff " +netPayOff);
	//console.log("strickPrice " +strickPrice);
	var breakEventIndex = strickPrice.indexOf(breakEvenLinep);
	//console.log(breakEventIndex);
	
	Highcharts.chart(container0, {
		chart : {
			type : 'line'
		},
		title : {
			text : title
		},

		xAxis : {
			categories : strickPrice,
			title : {
				text : 'Price of Underlying'
			},
			plotLines: [{
				label: "breaEventPoint",
				color: 'yellow', // Red
				width: 1,
				value: breakEventIndex // Position, you'll have to translate this to the values on your x axis
			}] 
		},
		yAxis : {
			title : {
				text : 'Profit Or Loss'
			}
		},
		plotOptions : {
			line : {
				dataLabels : {
					enabled : true
				},
				enableMouseTracking : true
			},

		},
		series : [ {
			showInLegend : false,
			threshold : 0,
			negativeColor : '#ffcccc',
			color : '#b3ffb3',
			type : 'area',
			data : netPayOff
		} ]
	});
}

function addDataToTable(expiryPrice, netPayOff) {
	var insSelected = document.getElementById('instrumentSelect').value;
	var tr;
	for (var i = 0; i < netPayOff.length; i++) {
		tr = $('<tr/>');
		tr.append("<td >" + expiryPrice[i] + "</td>");
		if(insSelected =="usdinr"){
			tr.append("<td >" + netPayOff[i].toFixed(2) + "</td>");	
		}else{
			tr.append("<td >" + netPayOff[i] + "</td>");
		}
		
		$('#payofftable').append(tr);
	}
}

function createPayOffTable() {
	var x = document.createElement("TABLE");
	x.setAttribute("id", "payofftable");
	x.setAttribute("class", "opttbldata");
	// x.setAttribute("border", "0");
	x.setAttribute("align", "center");
	x.setAttribute("cellpadding", "4");
	x.setAttribute("cellspacing", "1");
	// x.setAttribute("class", "table");
	x.setAttribute("width", "100%");

	$("#payOffTbaleDiv").append(x);

	// table header
	$("#payofftable")
			.append(
					'<thead>'
							+ '<tr class="optionHead"><th colspan="2" >Pay Off Table</th>'
							+

							'<tr class="optionHead">'
							+ '<th title="Delta">Spot On Expiry</th> <th title="Theta">Total Profit and Loss</th>'
							+ '</tr>' + '</thead>');

	// table Fotter
	$("#optionchain")
			.append(
					'<tfoot><tr class="optionHead"> '
							+ '<td><b></b></td>  <td ><b></b></td> <td ><b></b></td> <td id="ce_oi_total" ><b></b></td> <td colspan="9"></td>'
							+ '<td id="pe_oi_total" ><b></b></td> <td><b></b></td>  <td ><b></b></td> <td ><b></b></td>'
							+ '</tr></tfoot>');
}



function setCookie(cname,cvalue, path) {
	var exdays=90;
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/"+path;
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
