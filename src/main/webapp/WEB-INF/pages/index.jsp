<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="head.jsp"></jsp:include>
	<spring:url value="/resources/img/logo.png" var="logo" />
	<title>Spectre</title>
</head>
<body>
	<div class="container">
		<jsp:include page="navigator.jsp"></jsp:include>
		<div class="jumbotron">
			<div class="row">
				<div class="col-sm-6">
					<h1 class="title-spectre">SPECTRE</h1>
					<h5 class="title-spectre">Sistema de control de información para aerolineas</h5>
				</div>
				<div class="col-sm-6">
					<img src="${logo}" alt="Spectre" height="200" width="200" class="center-img"/>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-8">
					<p class="lead" style="text-align:center">
						Este sistema se encarga de la administración de
						información de una aerolinea, de tal forma que administra algunas
						de las muchas caracteristicas necesarias para el funcionamiento de
						una empresa en este tipo de negocio.
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>