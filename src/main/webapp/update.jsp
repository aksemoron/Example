<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aks
  Date: 07.05.2020
  Time: 1:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>
    <b>Редактируем пользователя: </b>
        "${user}"

    <form action="${pageContext.request.contextPath}/update" method="POST">
        <legend>Введите новые данные:</legend>
        <label>Имя:<br><input type="text" name="name"></label>
        <input type="submit">
    </form>
<<<<<<< HEAD

=======
>>>>>>> ddd
</head>
<body>

</body>
</html>
