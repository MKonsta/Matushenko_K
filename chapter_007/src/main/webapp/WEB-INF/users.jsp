<%@ page import="ru.job4j.servlets.webarchitecturejsp.logic.ValidateService" %>
<%@ page import="ru.job4j.servlets.webarchitecturejsp.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 06.01.2019
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Users</title>
</head>
<body>
<p><h2>Users List</h2></p>
<p><a href="<%=request.getContextPath()%>/addjsp">new User</a></p>

<table style="border: 1px solid black;" cellpadding="1"; border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Login</th>
        <th>E-mail</th>
        <th>Create date</th>
        <th>Edit user</th>
        <th>Delete user</th>
    </tr>
    <%for (User user : ValidateService.getValidateService().findAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td><a href="<%=request.getContextPath()%>/editjsp?id=<%=user.getId()%>">edit</a></td>
        <td>
        <form method="get" action="<%=request.getContextPath()%>/deletejsp" style="display: inline"/>
        <input type="hidden" name="id" value="<%=user.getId()%>">
        <input type="submit" value="Delete">
        </form>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
