<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <title>Welcome cat page</title>
</head>
<body>
    <p>Hello, meow! <p>
</body>
<form>
    <input type="button" value="Sign in" onClick='location.href="${contextPath}/login"'>
</form>
<form>
    <input type="button" value="Sign up" onClick='location.href="${contextPath}/registration"'>
</form>
</html>
