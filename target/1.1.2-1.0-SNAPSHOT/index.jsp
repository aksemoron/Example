<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Users</h1>
<table>
    <c:forEach var="users" items="${users}">
        <tr>
            <td>${users.id}</td>
            <td>${users.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/delete?idToDelete=${users.id}">Delete</a>
                <a href="${pageContext.request.contextPath}/update?idToUpdate=${users.id}">Update</a>
            </td>
        </tr>

    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/index" method="POST">
    <legend>Add user</legend>
    <label>Имя:<br><input type="text" name="name"></label>
    <input type="submit">
</form>
</body>
</html>
