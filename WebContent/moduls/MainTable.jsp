<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src=<c:url value="/js/selectTableView.js"/> /></script>

<form action="" name="tableForm">
<div id="divTable">
	<table border="1px" bgcolor="#c5c5c5" id="rangeTableView">
		<tr>
			<th>Index</th>
			<th>Area code</th>
			<th>Start of range</th>
			<th>End of range</th>
			<th>Operator</th>
		</tr>
		<tr><!-- second row in the tabel is for selecting view options, uses  selectTableView.js-->
			<td/>
			<td><select name="selectAreaTable">
					<option class='ndc-geo' value='ALL'>ALL</option>
					<jsp:include page="/moduls/OptionsAreaCode.jsp"></jsp:include>

			</select></td>
			<td><input type="text" name="selectStartRange" maxLength="3" size="3" />
			</td>
			<td></td>
			<td>
				<select name="operatorTable">
				<option class='oTable' value='ts'>ALL</option>
					<!-- JavaScript adds operators					 -->
				</select>
			</td>
		</tr>
		<!-- data rows -->
		<c:forEach var="firstRange" items="${applicationScope.geoRange}" varStatus="rangeCount">
		<tr class="tableRow">
				<td>${rangeCount.count}</td>
				<td>${firstRange.mg}</td>
				<td>${firstRange.startRange}</td>
				<td>${firstRange.endRange}</td>
				<td>${firstRange.operator}</td>
		</tr>
		</c:forEach>

	</table>
</div>
</form>
