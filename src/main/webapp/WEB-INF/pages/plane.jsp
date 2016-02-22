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
<title>Spectre - Aeronaves</title>
</head>
<body>
	<div class="container">
		<jsp:include page="navigator.jsp"></jsp:include>
		<h2>Administrar Aeronaves</h2>
		<div class="row">
			<div class="col-lg-4">
				<div class="well">
					<form:form method="POST" modelAttribute="plane"
						class="form-horizontal" id="newPlane">
						<fieldset>
							<legend>Información de la ruta</legend>
							<form:input type="hidden" path="idPlane" id="idPlane" />
							<div class="form-group">
								<label for="manufacturer" class="col-lg-3 control-label">Fabricante</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" id="manufacturer" path="manufacturer"></form:input>
								</div>
								<form:errors path="manufacturer" cssClass="error col-lg-12" />
							</div>
							<div class="form-group">
								<label for="model" class="col-lg-3 control-label">Modelo</label>
								<div class="col-lg-9">
									<form:input type="number" min="1500" max="5000" 
										class="form-control" id="model" path="model"></form:input>
								</div>
								<form:errors path="model" cssClass="error col-lg-12" />
							</div>
							<div class="form-group">
								<label for="capacity" class="col-lg-3 control-label">Capacidad</label>
								<div class="col-lg-9">
									<form:input type="number" min="1" max="1000" 
										class="form-control" id="capacity" path="capacity"></form:input>
								</div>
								<form:errors path="capacity" cssClass="error col-lg-12" />
							</div>
							<div class="form-group">
								<label for="enrollment" class="col-lg-3 control-label">Matrícula</label>
								<div class="col-lg-9">
									<form:input type="text" class="form-control" id="enrollment" path="enrollment"></form:input>
								</div>
								<form:errors path="enrollment" cssClass="error col-lg-12" />
							</div>
							<div class="form-group">
								<div class="col-lg-8 col-lg-offset-2">
									<c:choose>
										<c:when test="${controlAction}">
											<a class="btn btn-default btn-form"
												href="<c:url value='/plane/delete-${plane.idPlane}-plane'/>">
												Eliminar </a>
											<button type="submit" class="btn btn-success btn-form">Actualizar</button>
										</c:when>
										<c:otherwise>
											<a href="<c:url value='/plane/new' />"
												class="btn btn-default btn-form">Cancelar</a>
											<button type="submit" class="btn btn-primary btn-form">Crear</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
			<div id="planesTable" class="col-lg-8">
				<h3>Listado de Aeronaves</h3>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Modelo</th>
							<th>Matrícula</th>
							<th>Capacidad</th>
							<th>Fabricante</th>
							<th class="hide actions">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${planes}" var="plane">
							<tr>
								<td>${plane.model}</td>
								<td>${plane.enrollment}</td>
								<td>${plane.capacity}</td>
								<td>${plane.manufacturer}</td>
								<td class="hide actions"><a
									href="<c:url value='/plane/edit-${plane.idPlane}-plane'/>">
										<img src="${edit}" alt="Editar aeronave" height="16" width="16"
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