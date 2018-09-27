/**
 */ 
function checkMakeTable(){
		var form1 = document.forms["formGroup"];
		
		var vButton = document.form1.button1;
		var vSubmit = form1.send;
		
		var numClicked = 0;

		var vSubscriber = form1.newSubscriber;
		var vNet = form1.net;
		var vHidden = form1.numbersToSend;
		
		var elemTable = document.getElementById("expand-Table")
		var vHiddenValue = "";
		
		var snd1 = document.getElementById("snd1");
		var p1 = snd1.getElementsByTagName("p")[1];

		
		var pError = document.getElementById("errInput").getElementsByTagName("p")[0];
		
		var previousSubscriberValue=0;
		var previousNetValue= 0;
		
		var numberSet = new Set();
		
		
		function makeTable(event)
		{
			var goodInput=true;
			newNetValue= vNet.value;
			newSubscriberValue = vSubscriber.value;
			var txtNum = document.createTextNode(newSubscriberValue);
			
			if(newSubscriberValue.length == 0 || isNaN(newSubscriberValue)){

				pError.innerHTML =  "Please enter the number";
				goodInput=false;
				
				
			}else if(newSubscriberValue.length > 7){
				pError.innerHTML =  "The number " + newSubscriberValue + " is to long it should be more than 4 and less then 8 digits long ";
				goodInput=false;

			}else if(newSubscriberValue.length < 5){
				pError.innerHTML =  "The number " + newSubscriberValue + " is to short it should have more than 4 digits but not more than 7";
				goodInput=false;

			}else if (uniqueNumber(newNetValue, newSubscriberValue)){ // if everything is good append row in table and number in request hidden attribute
				pError.innerHTML =  "";
			
				
				var txtNet = document.createTextNode(newNetValue);
				
				var elemRow = document.createElement("tr");
				var elemCell1 = document.createElement("td");
				var elemCell2 = document.createElement("td");
				var elemCell3 = document.createElement("td");	
	
				elemCell1.appendChild(document.createTextNode(numClicked));
				elemRow.appendChild(elemCell1);
				
				elemCell2.appendChild(txtNet);
				elemRow.appendChild(elemCell2);
				
				elemCell3.appendChild(txtNum);
				elemRow.appendChild(elemCell3);
				
				elemTable.appendChild(elemRow);
			
				numClicked++;	
				
				vHiddenValue = vHiddenValue + newNetValue + ";" + newSubscriberValue + "!!!";
				vHidden.value = vHiddenValue;
				
				//Hidden 15;631000!!!15;2390000!!!15;463000!!!17;463000!!!18;463000!!!
				toSend(vHidden.value);	// it is a control function to show string that is going to send to server				
			}// end of if else block
			
			if(!goodInput && numClicked == 0){
			       if(event.preventDefault){ event.preventDefault();} 
			       if(window.event){window.event.returnValue = false;}
				
			}
			
		}
		
		function uniqueNumber(netNumber, subscriberNumber){
			number = netNumber*10000000 + subscriberNumber;
			if(!numberSet.has(number)){
				
				numberSet.add(number);
				return true;
			}else{
				return false;
			}
				
		}
		
		function toSend(vHiddenValue){
			
			p1.innerHTML =  vHiddenValue;
			snd1.appendChild(p1);
		}
		
		if(vButton.addEventListener){
			vButton.addEventListener("click", makeTable);
			document.form1.addEventListener("submit", makeTable);
		}else{
		    //IE doesn't have addEventListner
		    vButton.attachEvent("onclick", makeTable);
		    document.form1.attachEvent("onsubmit", makeTable);
		}
}

