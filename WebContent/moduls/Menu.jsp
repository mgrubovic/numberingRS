<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<div class="menu">
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/index.html">About</a>
			</div>
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/numbers/CheckNumber.jsp">Find numbers</a>
				<!-- a href="/numberingRS/numbers/number">Find numbers</a -->
			</div>
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/ranges/CheckRange.jsp">Find ranges</a>
			</div>
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/compare.html">Compare</a>
			</div>
			<div class="menuitem">
				<!-- a href="${pageContext.request.contextPath}/summary/SummaryHib.jsp">Summary</a -->
				<a href="${pageContext.request.contextPath}/summary/AreaCodeDistribution.jsp">Summary</a>
				
			</div>
			<div class="menuitem">
				<a href="${pageContext.request.contextPath}/configuration/mainConfig.jsp">Configuration</a>
			</div>
			<!-- 			${initParam["file-geo"]}  -->
			<%-- = application.getInitParameter("file-geo-short") --%>
			
		</div>