<%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 24.01.2019
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<head>
    <title>Edit User</title>
</head>
<body>
<h2>Edit user</h2>
<form method="post">
    <input type="hidden" value="<%=request.getAttribute("id")%>" name="id" />
    <label>Name</label><br>
    <input name="name" value="<%=request.getAttribute("name")%>" /><br><br>
    <label>Login</label><br>
    <input name="login" value="<%=request.getAttribute("login")%>" /><br><br>
    <label>Password</label><br>
    <input name="password" value="<%=request.getAttribute("password")%>" /><br><br>
    <label>E-mail</label><br>
    <input name="email" value="<%=request.getAttribute("email")%>" /><br><br>
    <label>Create date</label><br>
    <input name="date" value="<%=request.getAttribute("date")%>" /><br><br>
    <input type="hidden" value="<%=request.getAttribute("role")%>" name="role">
     <br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>
