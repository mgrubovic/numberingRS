/**
 * 
 */
		$(document).ready(function(){		
			alert("Hello");

			$("form1").on("submit", function(){
				alert("Hello form1");

				var num = $(":input[name=newSubscriber]").val();
				
				var errorString="Start range is good";
				alert(errorString + num);

				var result = true;
				if( num.length == 0){
					errorString = "Please enter a number";
					result = false;
				}else if( isNaN(num)){
					errorString = "You can enter only digits";
					result = false;
				}else if( num.length < 5 || num.length >7){
					errorString = "Start range length is invalid, it should be between 5 and 7 digits";
					result = false;
				}
		
				
				if(result){
					$("#errInput").html("");
					return true;
				}else{
					alert(errorString);
					$("#errInput").html("Error input: " + num +" " + errorString  );
					return false;
				}
			});
			
		});
