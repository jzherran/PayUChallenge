<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/css/spectre.css" var="spectreCss" />
<spring:url value="/resources/js/jquery-1.12.0.min.js" var="jqueryJs" />
<spring:url value="/resources/js/spectre.js" var="spectreJs" />

<link href="${spectreCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>
<script src="${spectreJs}"></script>

<title>Spectre</title>
</head>
<body>
	<div>
		<h2>List of Passengers</h2>
		<table>
			<tr>
				<td>Name</td>
				<td>Identification</td>
				<td>Age</td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach items="${passengers}" var="passenger">
				<tr>
					<td>${passenger.firstName}${passenger.lastName}</td>
					<td>${passenger.identificationNumber}</td>
					<td>${passenger.age}</td>
					<td><a
						href="<c:url value='/edit-${passenger.idPassenger}-passenger' />">edit</a></td>
					<td><a
						href="<c:url value='/delete-${passenger.idPassenger}-passenger' />">delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<br /> <a href="<c:url value='/new' />">Add New Passenger</a>
	</div>
</body>
</html>