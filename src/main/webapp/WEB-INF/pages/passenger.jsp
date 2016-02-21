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
<title>Spectre - Pasajeros</title>
</head>
	<body>
		<div class="container">
			<jsp:include page="navigator.jsp"></jsp:include>
			<h2>Administrar Pasajeros</h2>
			<div class="row">
				<div class="col-lg-4">
					<div class="well">
						<form:form method="POST" modelAttribute="passenger"
							class="form-horizontal" id="newPassenger">
							<fieldset>
								<legend>Información del Pasajero</legend>
								<form:input type="hidden" path="idPassenger" id="idPassenger" />
								<div class="form-group">
									<label for="firstName" class="col-lg-3 control-label">Nombres</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control" path="firstName"
											id="firstName" />
									</div>
									<form:errors path="firstName" cssClass="error col-lg-12" />
								</div>
								<div class="form-group">
									<label for="lastName" class="col-lg-3 control-label">Apellidos</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control" path="lastName"
											id="lastName" />
									</div>
									<form:errors path="lastName" cssClass="error col-lg-12" />
								</div>
	
								<div class="form-group">
									<label for="email" class="col-lg-3 control-label">Email</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control" path="email"
											id="email" />
									</div>
									<form:errors path="email" cssClass="error col-lg-12" />
								</div>
	
								<div class="form-group">
									<label for="age" class="col-lg-3 control-label">Edad</label>
									<div class="col-lg-9">
										<form:select class="form-control" id="age" path="age">
											<c:forEach var="i" begin="15" end="120">
												<option ${i == passenger.age?'selected=selected':''}><c:out
														value="${i}" /></option>
											</c:forEach>
										</form:select>
									</div>
									<form:errors path="age" cssClass="error col-lg-12" />
								</div>
	
								<div class="form-group">
									<label for="identificationNumber" class="col-lg-3 control-label">#
										ID</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control"
											path="identificationNumber" id="identificationNumber"
											title="Número de identificación" />
									</div>
									<form:errors path="identificationNumber"
										cssClass="error col-lg-12" />
								</div>
								<div class="form-group">
									<div class="col-lg-8 col-lg-offset-2">
										<c:choose>
											<c:when test="${controlAction}">
												<button type="submit" class="btn btn-success">Actualizar</button>
												<a class="btn btn-warning" 
													href="<c:url value='/passenger/delete-${passenger.idPassenger}-passenger'/>">
													Eliminar
												</a>
											</c:when>
											<c:otherwise>
												<a href="<c:url value='/passenger/new' />" class="btn btn-default">Cancelar</a>
												<button type="submit" class="btn btn-primary">Crear</button>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
				<div id="passengersTable" class="col-lg-8">
					<h3>Listado de Pasajero</h3>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Número de identificación</th>
								<th>Edad</th>
								<th>Email</th>
								<th class="hide actions">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${passengers}" var="passenger">
								<tr>
									<td>${passenger.firstName} ${passenger.lastName}</td>
									<td>${passenger.identificationNumber}</td>
									<td>${passenger.age}</td>
									<td>${passenger.email}</td>
									<td class="hide actions">
										<a href="<c:url value='/passenger/edit-${passenger.idPassenger}-passenger'/>"> 
											<img src="${edit}" alt="Editar pasajero" height="16" width="16"
											class="center-img" />
										</a>
									</td>
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