<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 124
  Date: 2021/8/14
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录页</h1>

<form action="<c:url value="/user/login"/>" method="post">
    用户名:<input type="text" name="username">
    密码:<input type="text" name="password">
    <input type="submit" value="登录">
</form>
</body>
</html>
