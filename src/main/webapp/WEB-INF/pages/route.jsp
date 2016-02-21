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
<title>Spectre - Rutas</title>
</head>
<body>
	<div class="container">
		<jsp:include page="navigator.jsp"></jsp:include>
		<h2>Administrar Rutas</h2>
		<div class="row">
			<div class="col-lg-4">
				<div class="well">
					<form:form method="POST" modelAttribute="route"
						class="form-horizontal" id="newRoute">
						<fieldset>
							<legend>Información de la ruta</legend>
							<form:input type="hidden" path="idRoute" id="idRoute" />
							<div class="form-group">
								<label for="airportOrigin" class="col-lg-3 control-label">Origen</label>
								<div class="col-lg-9">
									<form:select class="form-control" items="${airports}" id="airportOrigin" 
										itemValue="idAirport" path="airportOrigin" itemLabel="name">
									</form:select>
								</div>
								<form:errors path="airportOrigin" cssClass="error col-lg-12" />
							</div>
							<div class="form-group">
								<label for="airportDestination" class="col-lg-3 control-label">Destino</label>
								<div class="col-lg-9">
									<form:select class="form-control" id="airportDestination" itemValue="idAirport" 
										itemLabel="name" items="${airports}" path="airportDestination">
									</form:select>
								</div>
								<form:errors path="airportDestination" cssClass="error col-lg-12" />
							</div>
							<div class="form-group">
								<label for="time" class="col-lg-3 control-label">Duración
									(SEG)</label>
								<div class="col-lg-9">
									<form:input type="number" min="10" max="86400"
										class="form-control" id="time" path="time"></form:input>
								</div>
								<form:errors path="time" cssClass="error col-lg-12" />
							</div>
							<div class="form-group">
								<div class="col-lg-8 col-lg-offset-2">
									<c:choose>
										<c:when test="${controlAction}">
											<button type="submit" class="btn btn-success">Actualizar</button>
											<a class="btn btn-warning"
												href="<c:url value='/route/delete-${route.idRoute}-route'/>">
												Eliminar </a>
										</c:when>
										<c:otherwise>
											<a href="<c:url value='/route/new' />"
												class="btn btn-default">Cancelar</a>
											<button type="submit" class="btn btn-primary">Crear</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
			<div id="routesTable" class="col-lg-8">
				<h3>Listado de Rutas</h3>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Aeropuerto de origen</th>
							<th>Aeropuerto de destino</th>
							<th>Duración</th>
							<th class="hide actions">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${routes}" var="route">
							<tr>
								<td>${route.airportOrigin.name}</td>
								<td>${route.airportDestination.name}</td>
								<td>${route.timeToVisual}</td>
								<td class="hide actions"><a
									href="<c:url value='/route/edit-${route.idRoute}-route'/>">
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