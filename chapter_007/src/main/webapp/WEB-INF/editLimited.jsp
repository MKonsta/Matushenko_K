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
    <input type="hidden" name="date" value="<%=request.getAttribute("date")%>" /><br><br>
    <input type="hidden" value="<%=request.getAttribute("role")%>" name="role"><br><br>
    <label>Country</label><br>
    <select name="country">
        <option selected="selected" value="<%=request.getAttribute("country")%>">No change</option>
        <option value="France">France</option>
        <option value="Russia">Russia</option>
        <option value="China">China</option>
        <option value="India">India</option>
    </select> current: "<%=request.getAttribute("country")%>"<br><br>
    <label>City</label><br>
    <input name="city" value="<%=request.getAttribute("city")%>" /><br><br>

    <input type="submit" value="Send" />
</form>
</body>
</html>
