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

        var coutry;
        $(document).ready(function () {
            showcountries();
            $('#country_id').change(function () {
                coutry = $('#country_id').val();
                selectCities();
            });
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

        function showcountries() {
            $.ajax({
                method: 'GET',
                url: 'countryservlet',
                contenttype: "application/json",
                success: function (data) {
                    var option = '';
                    for (var i = 0; i < data.length; i++) {
                        option += '<option value="' + data[i] + '">' + data[i] + '</option>';
                    }
                    $('#country_id').html('<option value = "0">-Выберите страну-</option>' + option);
                }
            });
        }

        function selectCities() {
            $.ajax({
                type: 'POST',
                url: 'citiesservlet',
                data: JSON.stringify(coutry),
                datatype: 'json',
                success: function (data) {
                    var option = '';
                    for (var i = 0; i < data.length; i++) {
                        option += '<option value="' + data[i] + '">' + data[i] + '</option>';
                    }
                    $('#city_id').html('<option value = "0">-Выберите город-</option>' + option);
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

    <label for="country_id">Country</label><br>
    <select name="country" id="country_id">
        <option value=""></option>
    </select><br><br>

    <label for="city_id">City</label><br>
    <select name="city" id="city_id">
        <option value=""></option>
    </select><br><br>
    <input type="submit" onclick="validate()" value="add">
</form>
</body>
</html>
