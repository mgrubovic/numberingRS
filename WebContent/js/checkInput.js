/**
 * 
 */

$(document).ready(function(){

	$("form").on("submit", function(){
		$("#errInput").html("");// to erase previous error messages if there are some of them
		$("#answerCheckRange").html(""); // also erase previous valid results

		var startRange = $("#startRange").val(); //$(":input[name=startRange]").val();
		var endRange = $("#endRange").val(); //$(":input[name=endRange]").val();

		var startResult = goodNumber(startRange, "start");
		var endResult = goodNumber(endRange, "end");
		
		if( startResult  && endResult ){
			if(compareStartEnd(startRange, endRange)){
				var formData = $(this).serialize();
				getAnswer(formData);
			}
		}
		return false;
		
/*		
		if( parseInt(startRange, 10)  > parseInt(endRange, 10) ){
			$("#errInput").append("<p>Error start range " + startRange + " must be less than end range "+ 
									endRange + " </p>" );
			return false;
		}
		if( startResult  && endResult ){
			var formData = $(this).serialize();
			getAnswer(formData)
			return false;
		}else{
			return false;
		}
*/		
	});//end of submit

	function getAnswer(formData ){
/*		var sendRange = {
				select:"range",
				net: 11
				};
		alert("sendRange" + sendRange.select );
*/
		$.post("/numberingRS/check", formData, processData);
		function processData(data) {
			var $newText = $(data).find(".main-right table");
			$("#answerCheckRange").append($newText);
		}
	}
	
	// if input number is good this function returns true,
	// otherwise it writes an error message and returns false
	function goodNumber(num, position){
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
		if(!result){
			$("#errInput").append("<p>Error in " + position + " range your input "+ num +" - " + errorString  + " </p>" );
		}
		return result;
	}// end goodNumber
	
	function compareStartEnd(startRange, endRange){
		var result=true;
		if( parseInt(startRange, 10)  > parseInt(endRange, 10) ){
			$("#errInput").append("<p>Error start range " + startRange + " must be less than end range "+ 
									endRange + " </p>" );
			result = false;
		}
		if(startRange.length != endRange.length){
			$("#errInput").append("<p>Error start range " + startRange + " and end range "+ 
					endRange + " must be the same length</p>" );
			result = false;
		}
		return result;
	}// end compareStartEnd
	
}); // end ready
