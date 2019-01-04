	  
      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);
      
    
      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {
  		var arrData=[];
		var offset=1;
		
        // Create the data table.
        var data; //= new google.visualization.DataTable();
        var dataTable;
/*       
		var dataTable = $("#areaCodeDistribution");
        data.addColumn('string', 'Area code');
        data.addColumn('number', 'Amount of numbers');
*/
		if($("#areaCodeDistribution").length > 0){
			dataTable = $("#areaCodeDistribution");
			data = new google.visualization.DataTable();
	        data.addColumn('string', 'Area code');
	        data.addColumn('number', 'Amount of numbers');
	        offset=2;
		}else if ($("#operatorDistribution").length > 0){
			dataTable = $("#operatorDistribution");
			data = new google.visualization.DataTable();
	        data.addColumn('string', 'Operator');
	        data.addColumn('number', 'Amount of numbers');
	        offset=1;
		}
		
		dataTable.find(".tableRow").each(function(){
			
	        var currentRow=$(this);

	        var category=currentRow.find("td:eq(0)").text();
	        var amountOfNumbers=currentRow.find("td:eq(" + offset +")").text();

	        var obj=[category, parseInt(amountOfNumbers) ];
	        arrData.push(obj);

		});
        data.addRows(arrData);

        // Set chart options
        var options = {'title':'Number distribution',
                       'width':800,
                       'height':400};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
