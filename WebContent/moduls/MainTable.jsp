<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="../js/selectTableView.js"></script>

<form action="" name="tableForm">
	<div id="divTable">
		<table border="1px" bgcolor="#c5c5c5" id="rangeTableView">
			<tr>
					<td>Index</td>
					<td>Area code</td>
					<td>Start of range</td>
					<td>End of range</td>
					<td>Operator</td>
			</tr>
			<tr>
					<td />
					<td><select name="selectAreaTable">
							<option class='ndc-geo' value='ALL'>ALL</option>
								<c:forEach var="codeItem" items="${applicationScope.areaCode.areaCodeJaxb}">
									<option class='ndc-geo' value="${codeItem.code}">${codeItem.code}</option>
								</c:forEach>


					</select></td>
					<td><input type="text" name="selectStartRange" maxLength="3"size="3" />
					</td>
					<td></td>
					<td>
						<select name="operatorTable">
						<option class='oTable' value='ts'>ALL</option>
							<!-- JavaScript adds operators					 -->
						</select>
					</td>
				</tr>

				 
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
