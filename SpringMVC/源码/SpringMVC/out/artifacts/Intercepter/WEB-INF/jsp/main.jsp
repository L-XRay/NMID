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
<h1>首页</h1>
<span>
    <p>用户:${username}已登录</p>
    <p>
        <a href="<c:url value="/user/GOOut"/>">注销</a>
    </p>
</span>
</body>
</html>
