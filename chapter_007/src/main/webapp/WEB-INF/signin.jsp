<%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 23.01.2019
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div><br><br>
</c:if>

<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login: <input type="text" name="login"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="submit" value="sign in">
</form>
Login "admin"<br>
Password "1"
</body>
</html>
