<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:forEach var="codeItem" items="${applicationScope.areaCode.areaCodeJaxb}">
		<option class="ndc-geo" value="${codeItem.code}">${codeItem.code}</option>
	</c:forEach>
