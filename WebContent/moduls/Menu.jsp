<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<div class="menu">
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/index.jsp">About</a>
			</div>
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/numbers/CheckNumber.jsp">Find numbers</a>
				<!-- a href="/numberingRS/numbers/number">Find numbers</a -->
			</div>
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/ranges/CheckRange.jsp">Find ranges</a>
			</div>
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/compare/Compare.jsp">Compare</a>
			</div>
			<div class="menuitem">
				<a class="dropdown">Summary
					<i class="fa fa-caret-down"></i>
				</a>

					<div class="dropdown-container">
						<a href="${pageContext.request.contextPath}/summary/AreaCodeDistribution.jsp">by Area Code</a>
						<a href="${pageContext.request.contextPath}/summary/OperatorDistribution.jsp">by Operator</a>
					</div>
			</div>
			
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/configuration/mainConfig.jsp">Configuration</a>
			</div>
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