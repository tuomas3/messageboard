<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<style><%@include file="/WEB-INF/views/css/style.css"%></style>
</head>
<body>
<a href="/messageboard/search">Haku</a><a href="/messageboard/admin" style="float: right;">Ylläpito</a><br><br>
<form:form method="POST" action="/messageboard/">
Viestin sisältö (enintään 500 merkkiä):<br>
<textarea rows="10" cols="50" name="textareacontent"></textarea><br><br>
Nimimerkki (ei pakollinen, enintään 50 merkkiä):<br>
<input type="text" name="username"><br>
<input type="submit" value="Lähetä">
</form:form><br>
<c:out value="${alert}" />
<c:forEach items="${messagedataobjects}" var="messagedataobject">
<%@include file="cell.jspf" %><br><br>
</c:forEach>
</body>
</html>