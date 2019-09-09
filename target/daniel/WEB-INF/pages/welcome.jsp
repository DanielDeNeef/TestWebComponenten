<%--
  Created by IntelliJ IDEA.
  User: danie
  Date: 09-09-19
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome</title>
</head>
<body>

<h1>Welcome ${username}</h1>

<form action="logout" method="post">
    <input type="submit" name="logout" value="Log out">
</form>

</body>
</html>
