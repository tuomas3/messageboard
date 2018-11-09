<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<style><%@include file="/WEB-INF/views/css/style.css"%></style>
</head>
<body>
<a href="/messageboard/">Etusivulle</a><br><br>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
Käyttäjätunnus ja salasana<br>
<input type="text" name="username" />
<input type="text" name="password" />
<input type="submit" value="kirjaudu" />
<c:if test="${param.error != null}">
    <p>Väärä käyttäjätunnus tai salasana</p>
</c:if>
</form:form>
</body>
</html>