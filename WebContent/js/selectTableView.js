$(document).ready(function(){
	
	//var tableView = $("#rangeTableView");
	var operator_Unique = [];
	
	var pageTableData ={"tableRanges" : getTableData()};
	selectUniqueOperator();
			
	$("form[name=tableForm]").change(pageTableData, changeTable);
	$("#selectStartRange").on('input', pageTableData, changeTable);	

	//reading data form full table view and putting data into tableData array
	function getTableData(){
		var pageTableData=[];
		$("#rangeTableView").find(".tableRow").each(function(){
	        var currentRow=$(this);

	        var col1_value=currentRow.find("td:eq(0)").text();//index
	        var col2_value=currentRow.find("td:eq(1)").text();//area code
	        var col3_value=currentRow.find("td:eq(2)").text();//start range
	        var col4_value=currentRow.find("td:eq(3)").text();//end range
	        var col5_value=currentRow.find("td:eq(4)").text();//operator

	  	  	if(operator_Unique.indexOf(col5_value) == -1){
	  	  		operator_Unique.push(col5_value);
	  	  	}
	        var oneRow={};
	        oneRow.col1=col1_value;
	        oneRow.col2=col2_value;
	        oneRow.col3=col3_value;
	        oneRow.col4=col4_value;
	        oneRow.col5=col5_value;

	        pageTableData.push(oneRow);
			
		});// end find(".tableRow").each(function()

		return pageTableData;
	}
	
	function selectUniqueOperator(){
		var selectOperator = $(":input[name=operatorTable]");
		operator_Unique.sort();
		for(var i=0; i<operator_Unique.length; i++){
			var optionOperator = "<option class='oTable' value='ts'>" + operator_Unique[i] + "</option>";
			selectOperator.append(optionOperator);
		}
	}
	
	// changes table view based on user input			
	function changeTable(evt){
		var tableData = evt.data.tableRanges;
		var startRangeTable= "";

		var selectAreaTable = $(":input[name=selectAreaTable]").val();
		var operatorTable = $(":input[name=operatorTable] option:selected").text();
		startRangeTable = $("#selectStartRange").val()
	
        var selectedRows = "";
		for(var i=0; i<tableData.length; i++){
			var col1_value = tableData[i].col1;
			var col2_value = tableData[i].col2;
			var col3_value = tableData[i].col3;
	        var col4_value = tableData[i].col4;
	        var col5_value = tableData[i].col5;
	        
			if( (selectAreaTable == "ALL" || selectAreaTable == col2_value) &&
					col3_value.startsWith(startRangeTable) &&
					(operatorTable == "ALL" || operatorTable == col5_value)){
				 	selectedRows = selectedRows +
				 	"<tr class='tableRow'>" +
					"<td>"+ col1_value + "</td>"+
					"<td>"+ col2_value + "</td>"+
					"<td>"+ col3_value + "</td>"+
					"<td>"+ col4_value + "</td>"+
					"<td>"+ col5_value + "</td>"+
					"</tr>";
			}
		}
				
		$(".tableRow").each(function(){
        	var currentRow=$(this);
			currentRow.remove();
		});
		$("#rangeTableView").append(selectedRows);
		
	}// end changeTable()

});	// end ready()