<%--
  Created by IntelliJ IDEA.
  User: Jonas Philbert
  Date: 30/08/2019
  Time: 09.43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <p>Please log in:</p>
    <p style="color: darkred">${error}</p>
    <form action="/login" method="post">
        <input type="text" placeholder="Username" name="username">
        <input type="password" placeholder="Password" name="password">
        <input type="submit">
    </form>
</body>
</html>
