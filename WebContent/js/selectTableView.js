	$(document).ready(function(){
		
		var dataTable = $("#rangeTableView");
		var arrData=[];
		var operator_Unique = [];

		
		dataTable.find(".tableRow").each(function(){
			//$(".tableRow").each(function(){
			        var currentRow=$(this);

			        var col1_value=currentRow.find("td:eq(0)").text();//index
			        var col2_value=currentRow.find("td:eq(1)").text();//area code
			        var col3_value=currentRow.find("td:eq(2)").text();//start range
			        var col4_value=currentRow.find("td:eq(3)").text();//end range
			        var col5_value=currentRow.find("td:eq(4)").text();//operator

			        removeDuplicates(col5_value);
			        var obj={};
			        obj.col1=col1_value;
			        obj.col2=col2_value;
			        obj.col3=col3_value;
			        obj.col4=col4_value;
			        obj.col5=col5_value;

			        arrData.push(obj);

		});
		selectUniqueOperator();
		
		function removeDuplicates(arr){
		  if(operator_Unique.indexOf(arr) == -1){
			  operator_Unique.push(arr);
			  //$("#errInput").append("adding " + arr);
		   }
		};
		
		function selectUniqueOperator(){
			var selectOperator = $(":input[name=operatorTable]");
			for(var i=0; i<operator_Unique.length; i++)
			{
				//$("#errInput").append("+for-" + operator_Unique[i] + "for+");
				var optionOperator = "<option class='oTable' value='ts'>" + operator_Unique[i] + "</option>";
				selectOperator.append(optionOperator);
			}
		};
			
		$("form[name=tableForm]").change(changeTable);
		
		var startRangeTable ="";
/*		$("form[name=tableForm] :input[name=selectStartRange]").keypress(function(event){
			var lastChar="";
			var key = event.which;
			if(!(key ==8 || key ==0)){
				lastChar=String.fromCharCode(key);
			}
			
			startRangeTable = $(":input[name=selectStartRange]").val() + lastChar;
			
			changeTable();
		});// end of $("form[name=tableForm] :input[name=selectStartRange]").keypress

*/		
		$("form[name=tableForm] :input[name=selectStartRange]").on('input',function () { 
			changeTable();
        });	
		
//		$("form[name=tableForm]").change( function(){
		function changeTable(){
			var selectAreaTable = $(":input[name=selectAreaTable]").val();
			var operatorTable = $(":input[name=operatorTable] option:selected").text();
			startRangeTable = $(":input[name=selectStartRange]").val()

			//$( "#myselect option:selected" ).text();
			
	        var selectedRows = "";
			//$("#errInput").append("- " + selectAreaTable+ " " + operatorTable +" "+ startRangeTable +"--");

			//alert('change');
			//$("#errInput").append("dataTable-" + firstDataTable);
			for(var i=0; i<arrData.length; i++){
				var col1_value = arrData[i].col1;
				var col2_value = arrData[i].col2;
				var col3_value = arrData[i].col3;
		        var col4_value = arrData[i].col4;
		        var col5_value = arrData[i].col5;
		        
				if( (selectAreaTable == "ALL" || selectAreaTable == col2_value) &&
						col3_value.startsWith(startRangeTable) &&
						(operatorTable == "ALL" || operatorTable == col5_value)){
					 selectedRows = selectedRows + "<tr class='tableRow'>" +
						"<td>"+ col1_value + "</td>"+
						"<td>"+ col2_value + "</td>"+
						"<td>"+ col3_value + "</td>"+
						"<td>"+ col4_value + "</td>"+
						"<td>"+ col5_value + "</td>"+
						"</tr>";
					//$("#errInput").append("- " + col2_value + " " + col3_value + " " + col4_value + "--");
			
				}
			}
			$(".tableRow").each(function(){
	        	var currentRow=$(this);
				currentRow.remove();
			});
			dataTable.append(selectedRows);
			//$("#errInput").append(selectedRows);
		}
//		});//end of	$("form[name=tableForm]").change( function(){
		
	});		
		

		
