<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Trails</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="banner">
			<div class="title">
				<h1>Welcome, <c:out value="${loggedUser.userName}"/>!</h1>
				<h4>Trails:</h4>
			</div>
			<div class="links">
				<a href="http://localhost:8080/logout">Logout</a>
				<a href="http://localhost:8080/trails/new">+ Add a Trail!</a>
			</div>
		</div>
		<div class="table">
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Trail</th>
			      <th scope="col">Location</th>
			      <th scope="col">Posted By</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach var="trail" items="${trails}">
				    <tr>
				      <td><c:out value="${trail.id}"/></td>
				      <td><a href="http://localhost:8080/trails/${trail.id}"><c:out value="${trail.name}"/></a></td>
				      <td><c:out value="${trail.location}"/></td>
				      <td><c:out value="${trail.trailCreator.userName}"/></td>
				    </tr>
				</c:forEach>
			  </tbody>
			</table>
		</div>
	</div>
</body>
</html>