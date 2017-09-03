	
	function updateDataforOptingeeks(jsonData){

		var spotPrice;
		
		if(jsonData.uSDINRFuture!=null){
			spotPrice= jsonData.uSDINRFuture.lastPrice;
		}else{
			spotPrice=jsonData.spotPrice;
		}		
	
		var dataset = jsonData.dataset
	
		for (var i = 0; i < dataset.length; i++) {
			blOptionGeeks(dataset[i], spotPrice, jsonData.expiry, jsonData.interestRate, 0 );
		 }
		console.log("data updating");
	}
	
function test(CE_deta){

		 
return 	optionPrice;	 
	}
	
	function blOptionGeeks(dataset, spot, expiry, int_rate, div_yld){
		
		
    	//console.log("jsonData " +JSON.stringify(dataset));
		var spot = parseFloat(spot);
		var strike = parseFloat(dataset.strike_Price);
		var expiry =expiry;
		var volt;
		
		if(dataset.ce_IV == "-"){
			volt = parseFloat(0);
		}else{
			volt = parseFloat(dataset.ce_IV);
		}
			
		var	int_rate = parseFloat(int_rate);
		var	div_yld = parseFloat(div_yld);

		//console.log("spot" +spot);
		//console.log("strike" +strike);
		//console.log("volt" +volt);
			//console.log("spot" +spot);
		//console.log("int_rate" +int_rate);
			//console.log("div_yld" +div_yld);
			
		//Validation
		var error=null;

		if(isNaN(spot) || isNaN(strike) || isNaN(volt) || isNaN(int_rate)) {
			console.log("spot" +spot);
			console.log("strike" +strike);
			console.log("volt" +volt);
			int_rate = parseFloat(int_rate);
			error = "Invalid Values";	
			console.error(error);
		}
		else if(spot < 0 || strike < 0) {
			error = "Spot and Strike should be positive values";			
			console.error(error);
		}
		else if(volt <0 || volt >100) {
			error = "Voltality should be between 0 - 100";			
			console.error(error);
		}
		else if(int_rate <0 || int_rate >100) {
			error = "Interest rate should be between 0 - 100";			
			console.error(error);
		}
		else {			
			expiry = expiry.replace(" ", "T");

			var date_expiry = new Date(expiry),
				date_now = new Date();
				
			var seconds = Math.floor((date_expiry - (date_now))/1000),
				minutes = Math.floor(seconds/60),
				hours = Math.floor(minutes/60),
				delta_t = (Math.floor(hours/24))/365.0;
				
				
				

			var volt = volt/100,
				int_rate = int_rate/100;

			if(hours < 24) {
				error = "Please select a later date and time <br> Expiry should be minimum 24 hours from now";
				console.log(error);
			}
			else {

				var d1 = (Math.log(spot/strike) + (int_rate + Math.pow(volt,2)/2) * delta_t) / (volt*Math.sqrt(delta_t)),
					d2 = (Math.log(spot/strike) + (int_rate - Math.pow(volt,2)/2) * delta_t) / (volt*Math.sqrt(delta_t));

				var fv_strike = (strike)*Math.exp(-1*int_rate*delta_t);

				//For calculating CDF and PDF using gaussian library
				var distribution = gaussian(0, 1);


				//Premium Price
				var call_premium = spot * distribution.cdf(d1) - fv_strike * distribution.cdf(d2),
					put_premium = fv_strike * distribution.cdf(-1*d2) - spot * distribution.cdf(-1*d1);

				//Option greeks
				var call_delta = distribution.cdf(d1),
					put_delta = call_delta-1;

				var call_gamma = distribution.pdf(d1)/(spot*volt*Math.sqrt(delta_t)),
					put_gamma = call_gamma; 

				var call_vega = spot*distribution.pdf(d1)*Math.sqrt(delta_t)/100,
					put_vega = call_vega;

				var call_theta = (-1*spot*distribution.pdf(d1)*volt/(2*Math.sqrt(delta_t)) - int_rate*fv_strike*distribution.cdf(d2))/365,
					put_theta = (-1*spot*distribution.pdf(d1)*volt/(2*Math.sqrt(delta_t)) + int_rate*fv_strike*distribution.cdf(-1*d2))/365;

				var call_rho = fv_strike*delta_t*distribution.cdf(d2)/100,
					put_rho = -1*fv_strike*delta_t*distribution.cdf(-1*d2)/100;
				
				dataset.ce_deta=call_delta.toFixed(3);
				dataset.ce_theta=call_theta.toFixed(3);
				dataset.ce_rho=call_rho.toFixed(3);
				
				dataset.pe_deta=put_delta.toFixed(3);
				dataset.pe_theta=put_theta.toFixed(3);
				dataset.pe_rho=put_rho.toFixed(3);
				
				
				//console.log(call_premium.toFixed(2));
				//console.log(put_premium.toFixed(2));
				//console.log(call_delta.toFixed(3));
				//console.log(put_delta.toFixed(3));
				//console.log(call_gamma.toFixed(4));
				//console.log(call_theta.toFixed(3));
				//console.log(put_theta.toFixed(3));
				//console.log(call_rho.toFixed(3));
				//console.log(put_rho.toFixed(3));
				//console.log(call_vega.toFixed(3));
				
				
			}
		}

		return false;
				
	}