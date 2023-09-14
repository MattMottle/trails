<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Trail</title>
</head>
<body>
	<div class="container">
	<h1>Edit the <c:out value="${originalTrailName}"/> trail!</h1>
		<div class="form">
				<form:form action="/trails/${trail.id}/edit/process" method="post" modelAttribute="trail">
				 <input type="hidden" name="_method" value="put">
					<div style="color: red;"><form:errors path="name"/></div>
					<div style="color: red;"><form:errors path="location"/></div>
					<div style="color: red;"><form:errors path="description"/></div>
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
				    <p>
				        <form:label path="description">Description:</form:label>
				        <form:textarea rows="4" path="description"/>     
				    </p>
				    <form:input type="hidden" path="trailCreator" value="${user.id}"/>
				    <form:input type="hidden" path="id" value="${trail.id}"/>    
				    <button class="button">Submit</button>
				</form:form>
			</div>
		<a href="http://localhost:8080/trails">Home</a>
		</div>
</body>
</html>