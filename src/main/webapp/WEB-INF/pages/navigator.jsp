<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="masthead">
	<h1 class="text-muted">SPECTRE</h1>
	<nav class="navbar navbar-default">
		<ul class="nav navbar-nav">
			<li><a href="<c:url value='/' />">Inicio</a></li>
			<li><a href="<c:url value='/passenger/new' />">Pasajeros</a></li>
			<li><a href="<c:url value='/route/new/' />">Rutas</a></li>
			<li><a href="<c:url value='/plane/new/' />">Aviones</a></li>
			<li><a href="<c:url value='/flight/new/' />">Vuelos</a></li>
			<li><a href="<c:url value='/detail' />">Informe</a></li>
		</ul>
	</nav>
</div>