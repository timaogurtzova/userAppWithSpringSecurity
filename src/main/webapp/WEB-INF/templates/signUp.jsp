<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registration Cat</title>
</head>
<body>
    <p> Do you want to register? Here goes! <p>
    <p> But only admin can register an admin. User-cats are registered here</p>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        Name <input name="name" type="text" required />
        Password <input name="password" type="password" required />
        Age: <input name="age" type="number" required/>
        City: <input name="city" type="text" required/>
        <input type="submit" value="register me" />
    </form>
</body>
</html>