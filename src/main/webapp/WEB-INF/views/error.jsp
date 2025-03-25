<%--
  Created by IntelliJ IDEA.
  User: hajju
  Date: 2025-03-08
  Time: 오후 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>error.jsp</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
<ol>
    <c:forEach items="${pageContext.exception.stackTrace}" var="i">
        <li>${i.toString()}</li>
    </c:forEach>
</ol>
</body>
</html>
