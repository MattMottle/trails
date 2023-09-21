<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Review</title>
</head>
<body>
	<h1>Hello, <c:out value="${loggedUser.userName}"/>!</h1>
	<p><a href="/trails">Home</a> <a href="/logout">Log out</a></p>
	<h3>Edit review of the <c:out value="${thisReview.reviewedTrail.name}"/>:</h3>
	<form:form action="/reviews/${thisReview.id }/editReview" method="POST" modelAttribute="thisReview">
		<input type="hidden" name="_method" value="PUT"/>
		<p>
			<form:label path="rating">Rating:</form:label>
			<form:errors path="rating"/>
			<form:input type="number" path="rating"/>
		</p>
		<p>
			<form:label path="reviewText">Review Text:</form:label>
			<form:errors path="reviewText"/>
			<form:textarea rows="4" path="reviewText"/>
		</p>
		<form:input type="hidden" path="reviewer" value="${loggedUser.id}"/>
		<form:input type="hidden" path="reviewedTrail" value="${thisReview.reviewedTrail.id}"/>
		<input type="submit" value="Add review"/>
	</form:form>
</body>
</html>