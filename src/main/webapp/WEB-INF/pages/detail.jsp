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
<title>Spectre - Informe</title>
</head>
<body>
	<div class="container">
		<jsp:include page="navigator.jsp"></jsp:include>
		<div class="jumbotron">
			<div class="row">
				<div class="col-lg-12">
					<div class="well">
						<form:form method="POST" class="form-inline" action="/Spectre/detail/send" id="newReport" commandName="report">
							<fieldset>
								<legend>Información del reporte</legend>
								<div class="form-group">
									<label for="start" class="control-label">Fecha Inicio</label>
									<form:input type="text" pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01])" 
									class="form-control" id="start" path="start" title="Fecha ej: (2008/02/25)"></form:input>
								</div>
								<div class="form-group">
									<label for="end" class="control-label">Fecha Fin</label>
									<form:input type="text" pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01])" 
									class="form-control" id="end" path="end" title="Fecha ej: (2008/02/25)"></form:input>
								</div>
								<div class="form-group">
									<label for="plane" class="control-label">Aeronave</label>
									<form:select class="form-control" items="${planes}" id="plane" 
										itemValue="idPlane" path="plane" itemLabel="enrollment">
									</form:select>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-success">Generar</button>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div id="reportsTable" class="col-lg-12">
						<h3>Listado de resultados</h3>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Código Ruta</th>
									<th>Descripción Ruta</th>
									<th>Cantidad de vuelos</th>
									<th>Cantidad de pasajeros</th>
									<th class="hide actions">Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${reports}" var="report">
									<tr>
										<td>${report.route}</td>
										<td>${report.route.airportOrigin.name} - ${report.route.airportDestination.name}</td>
										<td>${report.flights}</td>
										<td>${report.passengers}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>