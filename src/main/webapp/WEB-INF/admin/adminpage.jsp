<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Admin Cat</title>
</head>

<body>
    <p>Hello, admin cat! <p>

    <form>
        <input type="button" value="UserProfile" onClick='location.href="${contextPath}/user"'>
    </form>
    <c:if test="${users != null}">
        <table border="5" cellspacing="0" cellpadding="20">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Age</td>
                <td>Password</td>
                <td>City</td>
                <td>Role</td>
            </tr>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td>${user.getId()}</td>
                    <td>${user.getName()}</td>
                    <td>${user.getAge()}</td>
                    <td>${user.getPassword()}</td>
                    <td>${user.getCity()}</td>
                    <td>${user.getRole()}</td>
                    <td> <input type="submit" value="update"  onClick='location.href="${contextPath}/admin/update/"+${user.getId()}' ></td>
                    <td> <input type="submit" value="delete"  onClick='location.href="${contextPath}/admin/delete/"+${user.getId()}' ></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</p>
<form action="${contextPath}/admin/add" method="post">
    Name: <input name="name" type="text" minlength="1" />
    Age: <input name="age" type="number" min=1 />
    Password: <input name="password" type="password" min=1 />
    City: <input name="city" type="text" />
    Role: <input name="role" type="radio" value="user" />user
    <input name="role" type="radio" value="admin" />admin<br>
    <p>
        <input type="submit" value="addDB" />
</form>
    </p>
<br><br>
</body>
</html>
