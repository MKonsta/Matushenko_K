<%@ page import="ru.job4j.servlets.webarchitecturejsp.model.User" %>
<%@ page import="ru.job4j.servlets.webarchitecturejsp.logic.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 06.01.2019
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>

        var country;
        $(document).ready(function () {
            showCountrues();
            $('#country_id').change(function () {
                country = $('#country_id').val();
                selectCities();
            });
        });

        function showCountrues() {
            $.ajax({
                method: 'GET',
                url: 'countryservlet',
                contenttype: "application/json",
                success: function (data) {
                    var option = '';
                    for (var i = 0; i < data.length; i++) {
                        option += '<option value="' + data[i] + '">' + data[i] + '</option>';
                    }
                    $('#country_id').html('<option value = "<%=request.getAttribute("country")%>">-Выберите страну-</option>' + option);
                }
            });
        }

        function selectCities() {
            $.ajax({
                type: 'POST',
                url: 'citiesservlet',
                data: JSON.stringify(country),
                datatype: 'json',
                success: function (data) {
                    var option = '';
                    for (var i = 0; i < data.length; i++) {
                        option += '<option value="' + data[i] + '">' + data[i] + '</option>';
                    }
                    $('#city_id').html('<option value = "<%=request.getAttribute("city")%>">-Выберите город-</option>' + option);
                }
            });
        }

    </script>
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
    <label>Role</label><br>
    <select name="role">
        <option value="admin">admin</option>
        <option value="user">user</option>
    </select><br><br>

    <label>Country</label><br>
    <select name="country" id="country_id">
        <option value=""></option>
    </select> current: "<%=request.getAttribute("country")%>"<br><br>

    <label>City</label><br>
    <select name="city" id="city_id">
        <option value=""></option>
    </select> current: "<%=request.getAttribute("city")%>"<br><br>

    <input type="submit" value="Send" />
</form>
</body>
</html>
