<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Trail</title>
</head>
<body>
	<div class="container">
		<h1>Add a Trail!</h1>
		<form:form action="/trails/new/process" method="post" modelAttribute="trail">
			<div style="color: red"><form:errors path="name"/></div>
			<div style="color: red"><form:errors path="location"/></div>
			<div style="color: red"><form:errors path="isHikedEntirely"/></div>
			<div style="color: red"><form:errors path="description"/></div>
			<p>
				<form:label path="name">Name:</form:label>
				<form:input path="name"/>
			</p>
			<p>
				<form:label path="location">Location:</form:label>
				<form:input path="location"/>
			</p>
			<p>
				<form:label path="isHikedEntirely">Has hiked?</form:label>
				<form:radiobutton path="isHikedEntirely" value="true" label="yes"/>
				<form:radiobutton path="isHikedEntirely" value="false" label="no"/>
			<p>
				<form:label path="description">Description:</form:label>
				<form:textarea rows="4" path="description"/>
			</p>
			<p>
				<form:input type="hidden" path="trailCreator" value="${user.id}"/>
			</p>
			<button class="button">Create</button>
		</form:form>
		<a href="http://localhost:8080/trails">Home</a>
	</div>
</body>
</html>