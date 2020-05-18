<%--
  Created by IntelliJ IDEA.
  User: aks
  Date: 17.05.2020
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <form action="${pageContext.request.contextPath}/login" method="POST">
        <legend>Авторизация:</legend>
        <label>Логин:<br><input type="text" name="name"></label>
        <label>Пароль:<br><input type="text" name="password"></label>
        <input type="submit">
    </form>
</head>
<body>

</body>
</html>
