<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<style><%@include file="/WEB-INF/views/css/style.css"%></style>
</head>
<body>
<a href="/messageboard/">Etusivulle</a><br><br>
<form:form method="POST" action="/messageboard/search/" modelAttribute="searchBar">
<form:checkbox path="username" /> Käyttäjänimi
<form:checkbox path="date" /> Päivämäärä
<form:checkbox path="message" /> Viestin sisältö
<br><br>
<form:input type="text" path="searchValue" /><br>
<input type="submit" value="Hae">
</form:form><br>

<c:out value="${searchPageTest}" />
<c:forEach items="${messagedataobjects}" var="messagedataobject">
<%@include file="cell.jspf" %><br><br>
</c:forEach>
</body>
</html>