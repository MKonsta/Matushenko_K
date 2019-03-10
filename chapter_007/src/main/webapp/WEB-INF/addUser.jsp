<%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 06.01.2019
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Add new user</title>
    <script>
        $(document).ready(function () {
            getcou();
        });

        function validate() {
            if (document.getElementById("name").value == '') {
                alert("enter name");
            } if (document.getElementById("login").value == '') {
                alert("enter login");
            } if (document.getElementById("password").value == '') {
                alert("enter password");
            } if (document.getElementById("email").value == '') {
                alert("enter E-mail");
            }  if (document.getElementById("city").value == '') {
                alert("enter City");
            }
            return false;
        }

        function getcou() {
            $.ajax({
                method: 'GET',
                url: 'countryservlet',
                contenttype: "application/json",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        $('#country option:last').after('<option value="' + data[i] + '">' + data[i] + '</option>');
                    }
                }
            });
        }


    </script>
</head>
<body>
<h1>Add new User</h1>
<form method="post">
    <label for="name">Name</label><br>
    <input type="text" name="name" id="name" /><br><br>

    <label for="login">Login</label><br>
    <input name="login" id="login" /><br><br>

    <label for="password">Password</label><br>
    <input name="password" id="password"><br><br>

    <label for="email">E-mail</label><br>
    <input name="email" id="email"/><br><br>

    <label for="role">Role</label><br>
    <select name="role" id="role">
        <option value="admin">admin</option>
        <option value="user">user</option>
    </select> <br><br>

    <label for="country">Country</label><br>
    <select name="country" id="country">
        <option value=""></option>
    </select><br><br>

    <label for="city">City</label><br>
    <input name="city" id="city"/><br><br>
    <input type="submit" onclick="validate()" value="add">
</form>
</body>
</html>
