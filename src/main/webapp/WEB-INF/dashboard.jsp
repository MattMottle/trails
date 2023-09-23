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
			      <th scope="col">Trail</th>
			      <th scope="col">Location</th>
			      <th scope="col">Posted By</th>
			      <th scope="col">Review Options</th>
			      <th scope="col">Actions</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach var="trail" items="${trails}">
				    <tr>
				      <td><c:out value="${trail.name}"/></td>
				      <td><c:out value="${trail.location}"/></td>
				      <td><c:out value="${trail.trailCreator.userName}"/></td>
				      <td>
				      	<c:set var="reviewFound" value="${false}"/>
				      	<c:set var="reviewID" value="${null}"/>
				      	<c:forEach var="thisReview" items="${loggedUser.reviews}">
				      		<c:if test="${thisReview.reviewedTrail.id.equals(trail.id)}">
				      			<c:set var="reviewID" value="${thisReview.id}"/>
				      			<c:set var="reviewFound" value="${true}"/>
				      		</c:if>
				      	</c:forEach>
				      	<c:choose>
					      	<c:when test="${reviewFound}">
					      		<a href="/reviews/${reviewID}/edit_review">Edit review</a>
					      		<form action="/reviews/${reviewID}/deleteReview" method="post">
				      				<input type="hidden" name="_method" value="delete" />
				      				<button class="delete">Delete</button>
				    			</form>
				    		</c:when>
				    		<c:otherwise>
				    			<a href="/trails/${trail.id}/new_review">Add Review</a>
				    		</c:otherwise>
				      </c:choose>
				      </td>
				      <td>
				      	<a href="http://localhost:8080/trails/${trail.id}">View</a>
				      	<c:if test="${loggedUser.id == trail.trailCreator.id }">
				      		<a href="/trails/${trail.id}/edit">Edit</a>
				      		<form action="/${trail.id}/delete" method="POST">
				      			<input type="hidden" name="_method" value="Delete"/>
				      			<input type="submit" value="Delete"/>
				      		</form>
				      	</c:if>
				    </tr>
				</c:forEach>
			  </tbody>
			</table>
		</div>
	</div>
</body>
</html>