<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.example.web.MainServlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>City database test</title>
<link rel="stylesheet"
	  href="${pageContext.request.contextPath}/Style.css?v=1.1" type="text/css">
</head>
<body>
<div class="background"></div>
		<nav class="navbar">
			<div class="title">
				<a class="navbar-brand">City database test</a>
			</div>

			<div class="navbar-link">
				<ul class="nav-ul">
					<li><a href="<%=request.getContextPath()%>/list"
						   class="nav-link"><p class="link-text">List of cities</p></a></li>
				</ul>
			</div>
		</nav>
	<br>
<div class="button-row">
	<a href="<%=request.getContextPath()%>/new" class="btn" id="add">Add
		a new city</a>
</div>
	<div class="row">
		<div class="container">
			<div class="card">
			<h3 class="text-center">List of Cities</h3>
			<hr>
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Population</th>
						<th>Country</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->

					<c:forEach var="city" items="${listCity}">

						<tr>
							<td><c:out value="${city.id}" /></td>
							<td><c:out value="${city.cityName}" /></td>
							<td><c:out value="${city.population}" /></td>
							<td><c:out value="${city.country}" /></td>

							<td>
								<a href="edit?id=<c:out value='${city.id}' />"><p class="link-text">Edit</p></a>
								 <a href="delete?id=<c:out value='${city.id}' />"><p class="link-text" id="delete">Delete</p></a>
							</td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>

		</div>

		</div>

	</div>

</body>
</html>
