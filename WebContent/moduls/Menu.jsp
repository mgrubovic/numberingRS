<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src=<c:url value="/js/plainJs.js"/> ></script>

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
		<a href="#" class="dropdown">Summary
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
