<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<style><%@include file="/WEB-INF/views/css/style.css"%></style>
</head>
<body>
<a href="/messageboard/">Etusivulle</a><a href="${pageContext.request.contextPath}/logout" style="float: right;">Kirjaudu ulos</a><br><br>
<form:form method="POST" action="/messageboard/admin/">
Poista viestejä id:n perusteella (välilyönneillä erotettu lista tai lukuväli luku1-luku2)<br>
<input type="text" name="deleteTheseIds"><br>
<input type="submit" value="Poista">
</form:form>
<c:out value="${adminPageTest}" />
<c:forEach items="${messagedataobjects}" var="messagedataobject">
<hr>
<b>id=</b><c:out value="${messagedataobject.id}" /><br>
<b>username=</b><c:out value="${messagedataobject.username}" /><br>
<b>date=</b><c:out value="${messagedataobject.date}" /><br>
<b>message=</b><c:out value="${messagedataobject.message}" /><br>
<form:form method="POST" action="/messageboard/admin/">
<input type="hidden" name="deleteClickedMessage" value=${messagedataobject.id}>
<input type="submit" value="Poista"></form:form>
</c:forEach>
<hr>
</body>
</html>