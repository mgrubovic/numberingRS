/**
 * 
 */

		$(document).ready(function(){

			$("form").on("submit", function(){
				$("#errInput").html("");

				var startRange = $(":input[name=startRange]").val();
				var endRange = $(":input[name=endRange]").val();

				var startResult = isNumber(startRange, "start");
				var endResult = isNumber(endRange, "end");
				
				if( startResult  && endResult ){
					return true;
				}else{
					return false;
				}

			});

			function isNumber(num, position){
				var errorString="Range is good";
				var result = true;
				if( num.length == 0){
					errorString = "Please enter a number";
					result = false;
				}else if( isNaN(num)){
					errorString = "You can enter only digits";
					result = false;
				}else if( num.length < 5 || num.length >7){
					errorString = "Length is invalid, it should be 5, 6 or 7 digits";
					result = false;
				}
		

				if(result){
					$("#errInput").append("");
					return true;
				}else{
					$("#errInput").append("<p>Error input in " + position + " range :"+ num +" - " + errorString  + " </p>" );
					return false;
				}

				
			}
			

		});
		
/*				
		var errorString="End range is good";
		var result = true;
		if( num.length == 0){
			errorString = "Please enter a number in the end range field";
			result = false;
		}else if( isNaN(num)){
			errorString = "You can enter only digits";
			result = false;
		}else if( num.length < 5 || num.length >7){
			errorString = "End range length is invalid, it should be between 5 and 7 digits";
			result = false;
		}

		
		if(result){
			$("#errInput").append("");
			return true;
		}else{
			alert(errorString);
			$("#errInput").append("<p>Error input: " + num +" " + errorString  + " </p>" );
			return false;
		}
*/
		
		/*			
		$("form").on("submit", function(){
			var num = $(":input[name=startRange]").val();
			
			var errorString="Start range is good";
			var result = true;
			if( num.length == 0){
				errorString = "Please enter a number in the start field";
				result = false;
			}else if( isNaN(num)){
				errorString = "You can enter only digits";
				result = false;
			}else if( num.length < 5 || num.length >7){
				errorString = "Start range length is invalid, it should be between 5 and 7 digits";
				result = false;
			}
	
			
			if(result){
				$("#errInput").append("");
				return true;
			}else{
				alert(errorString);
				$("#errInput").append("<p>Error input: " + num +" " + errorString  + " </p>" );
				return false;
			}

		});
*/					
