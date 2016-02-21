<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
	<spring:url value="/resources/css/spectre.css" var="spectreCss" />
	<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
	
	<spring:url value="/resources/js/jquery-1.12.0.min.js" var="jqueryJs" />
	<spring:url value="/resources/js/spectre.js" var="spectreJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
	
	<link href="${spectreCss}" rel="stylesheet" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<script src="${jqueryJs}"></script>
	<script src="${spectreJs}"></script>
	<script src="${bootstrapJs}"></script>
</head>