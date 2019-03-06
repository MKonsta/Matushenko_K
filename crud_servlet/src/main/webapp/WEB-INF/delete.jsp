<%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 09.01.2019
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<h1 align="center">Delete user with id <%=request.getAttribute("id")%>?</h1>
<table align="center">
    <tr>
        <td>
            <form action="<%=request.getContextPath()%>/deletejsp" method="post">
                <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
                <button type="submit">Yes</button>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/usersjsp">
                <button type="submit">No</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
