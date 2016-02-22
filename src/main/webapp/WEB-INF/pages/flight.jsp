<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="head.jsp"></jsp:include>
<spring:url value="/resources/img/del.png" var="delete" />
<spring:url value="/resources/img/edi.png" var="edit" />
<title>Spectre - Vuelos</title>
</head>
<body>
	<div class="container">
		<jsp:include page="navigator.jsp"></jsp:include>
		<h2>Administrar Vuelos</h2>
		<div class="row">
			<div class="col-lg-6">
				<div class="well">
					<form:form method="POST" modelAttribute="flight"
						class="form-horizontal" id="newFlight">
						<fieldset>
							<legend>Información del vuelo</legend>
							<form:input type="hidden" path="idFlight" id="idFlight" />
							<div class="form-group">
								<label for="date" class="col-lg-3 control-label">Fecha</label>
								<div class="col-lg-9">
									<form:input type="text" pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01])" 
									class="form-control" id="date" path="date" title="Fecha ej: (2008/02/25)"></form:input>
								</div>
								<form:errors path="date" cssClass="error col-lg-9 col-lg-offset-3" />
							</div>
							<div class="form-group">
								<label for="plane" class="col-lg-3 control-label">Aeronave</label>
								<div class="col-lg-9">
									<form:select class="form-control" items="${planes}" id="plane" 
										itemValue="idPlane" path="plane" itemLabel="enrollment">
									</form:select>
								</div>
								<form:errors path="plane" cssClass="error col-lg-9 col-lg-offset-3" />
							</div>
							<div class="form-group">
								<label for="route" class="col-lg-3 control-label">Ruta</label>
								<div class="col-lg-9">
									<form:select class="form-control" id="route" 
										itemValue="idRoute" var="route" items="${routes}" path="route">
									</form:select>
								</div>
								<form:errors path="route" cssClass="error col-lg-9 col-lg-offset-3" />
							</div>
							<div class="form-group">
								<label for="timeInit" class="col-lg-3 control-label">Hora despegue</label>
								<div class="col-lg-9">
									<form:input type="text" pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01]) (0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2} (AM|PM)"
										class="form-control" id="timeInit" path="timeInit" title="Hora en formato 24H, ej:(2008/02/25 01:30:25 PM)"></form:input>
								</div>
								<form:errors path="timeInit" cssClass="error col-lg-9 col-lg-offset-3" />
							</div>
							<div class="form-group">
								<div class="col-lg-8 col-lg-offset-2">
									<a href="<c:url value='/flight/new' />"
												class="btn btn-default btn-form">Cancelar</a>
									<c:choose>
										<c:when test="${controlAction}">
											<button type="submit" class="btn btn-success btn-form">Actualizar</button>
										</c:when>
										<c:otherwise>
											<button type="submit" class="btn btn-primary btn-form">Crear</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="well">
					<form:form method="POST" modelAttribute="purchase" action="/Spectre/flight/new/purchase"
						class="form-horizontal" id="newPurchase">
						<fieldset>
							<legend>Reserva de vuelo</legend>
							<form:input type="hidden" path="idPurchase" id="idPurchase" />
							<div class="form-group">
								<label for="passenger" class="col-lg-3 control-label">Pasajero</label>
								<div class="col-lg-9">
									<form:select class="form-control" items="${passengers}" id="passenger" 
										itemValue="idPassenger" path="passenger" itemLabel="fullName">
									</form:select>
								</div>
								<form:errors path="passenger" cssClass="error col-lg-9 col-lg-offset-3" />
							</div>
							<div class="form-group">
								<label for="flight" class="col-lg-3 control-label">Vuelo</label>
								<div class="col-lg-9">
									<form:select class="form-control" items="${flightsAvailable}" id="flight" 
										itemValue="idFlight" path="flight" itemLabel="flightCode">
									</form:select>
								</div>
								<form:errors path="flight" cssClass="error col-lg-9 col-lg-offset-3" />
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-4">
									<button type="submit" class="btn btn-success btn-form">Reservar</button>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
		<div class="row">
			<div id="flightTable" class="col-lg-12">
				<h3>Listado de Vuelos</h3>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Código</th>
							<th>Aeronave</th>
							<th>Ruta</th>
							<th>Fecha Despegue</th>
							<th>Fecha Aterrizaje</th>
							<th class="hide actions">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${flights}" var="flight">
							<tr>
								<td>${flight.flightCode}</td>
								<td>${flight.plane.enrollment}</td>
								<td>${flight.route.airportOrigin.name} - ${flight.route.airportDestination.name} </td>
								<td>${flight.timeInitVisual}</td>
								<td>${flight.timeEndVisual}</td>
								<td class="hide actions"><a
									href="<c:url value='/flight/edit-${flight.idFlight}-flight'/>">
										<img src="${edit}" alt="Editar ruta" height="16" width="16"
										class="center-img" />
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="source-button" class="btn btn-primary btn-xs">&lt;
					&gt;</div>
			</div>
		</div>
	</div>
</body>
</html>