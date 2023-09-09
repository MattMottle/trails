<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trail</title>
</head>
<body>
	<div class="container">
		<div class="banner">
			<h1>${trail.name}</h1>
			<a href="http://localhost:8080/trails">Back to the trails</a>
		</div>
		<div class="details">
			<c:choose>
				<c:when test="${loggedUser.id == trail.trailCreator.id}">
					<h3>You hiked <span style="color: purple"> ${trail.name}</span> at <span style="color: green">${trail.location}.</span></h3>
					<h3>Here are your thoughts:</h3>
				</c:when>
				<c:otherwise>
					<h3><span style="color: red">${trail.trailCreator.userName}</span> hiked <span style="color: purple"> ${trail.name}</span> at <span style="color: green">${trail.location}.</span></h3>
					<h3>Here are ${trail.trailCreator.userName}'s thoughts:</h3>
				</c:otherwise>
			</c:choose>
			<p>${trail.description}</p>
		</div>
		<c:if test="${loggedUser.id == trail.trailCreator.id}">
			<div class="actions">
				<form action="/trails/${trail.id}/edit" method="get">
					<input type="hidden" value="Edit"/>
					<button class="edit">Edit</button>
				</form>
				<form action="/${trail.id}/delete" method="post">
			      	<input type="hidden" name="_method" value="delete" />
			      	<button class="delete">Delete</button>
			    </form>
			</div>
		</c:if>
	</div>
</body>
</html>