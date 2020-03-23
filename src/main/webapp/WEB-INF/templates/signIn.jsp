<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Log in page</title>
</head>
<body>
    <p>Log in <p>
    <form action="${pageContext.request.contextPath}/login/process" method="post">
        Name  <input name="name" type="text" required/>
        Password <input name="password" type="password" required />
        <input type="submit" value="authorization" />
    </form>
    <c:if test="${error == true}">
        <p>Invalid username or password<p>
        <p>Log in again or go to the registration page</p>
        <form>
            <input type="button" value="Sign up" onClick='location.href="${contextPath}/registration"'>
        </form>
    </c:if>
</body>
</html>