<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<div class="menuitem">
		<a href=<c:url value="/index.jsp"/> >About</a>
	</div>
	<div class="menuitem">
	<a href=<c:url value="/numbers/CheckNumber.jsp"/> >Find numbers</a>
		<!-- a href="${pageContext.request.contextPath}/numbers/CheckNumber.jsp">Find numbers</a -->
		<!-- a href="/numberingRS/numbers/number">Find numbers</a -->
	</div>
	<div class="menuitem">
		<a href=<c:url value="/ranges/CheckRange.jsp"/> >Find ranges</a>
	</div>
	<div class="menuitem">
		<a href=<c:url value="/compare/Compare.jsp"/> >Compare</a>
	</div>
	<div class="menuitem">
		<a class="dropdown">Summary
			<i class="fa fa-caret-down"></i>
		</a>
		<div class="dropdown-container">
			<a href=<c:url value="/summary/AreaCodeDistribution.jsp"/> >by Area Code</a>
			<a href=<c:url value="/summary/OperatorDistribution.jsp"/> >by Operator</a>
		</div>
	</div>
	
	<div class="menuitem">
		<a href=<c:url value="/configuration/mainConfig.jsp"/> >Configuration</a>
	</div>
	
	
<script>
	/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
	var dropdown = document.getElementsByClassName("dropdown");
	var i;
	
	for (i = 0; i < dropdown.length; i++) {
	  dropdown[i].addEventListener("click", function() {
		  this.classList.toggle("active");
		  var dropdownContent = this.nextElementSibling;
		  if (dropdownContent.style.display === "block") {
		  	dropdownContent.style.display = "none";
		  } else {
		  	dropdownContent.style.display = "block";
		  }
	  });
	}
</script>