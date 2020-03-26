<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Update</title>
</head>
<body>
<p>Hello, admin cat! You can update user <p>

<table border="5" cellspacing="0" cellpadding="20">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Age</td>
        <td>Password</td>
        <td>City</td>
        <td>Role</td>
    </tr>
        <form action="${contextPath}/admin/update" method="post">
            <td> <input type="text" name="id" value="${user.getId()}" hidden> ${user.getId()} </td>
            <td> <input type="text" name="name" value="${user.getName()}"></td>
            <td><input type="number" name="age" value="${user.getAge()}"></td>
            <td><input type="text" name="password" value="${user.getPassword()}"></td>
            <td><input type="text" name="city" value="${user.getCity()}"></td>
            <td>
                <c:forEach items="${roles}" var="role">
                    <input type="checkbox" name="roleArray" value="${role.getAuthority()}"> <label>"${role.getAuthority()}"</label>
                   <br>
                </c:forEach>
            </td>
            <td>
                <button type="submit">update</button>
            </td>
        </form>
    </tr>
</table>

</body>
</html>
