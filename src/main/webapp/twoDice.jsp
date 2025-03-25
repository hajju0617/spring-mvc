<%--
  Created by IntelliJ IDEA.
  User: hajju
  Date: 2025-02-19
  Time: 오후 7:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Random" %>
<%--  <%! 클래스 영역 %>  --%>
<%!
    int getRandomInt(int range){
        return new Random().nextInt(range) + 1;
    }
%>
<%--  <%  메서드 영역 - service()의 내부 %>  --%>
<%
    int idx1 = getRandomInt(6);
    int idx2 = getRandomInt(6);
%>
<html>
<head>
    <title>twoDice.jsp</title>
</head>
<body>
<img src='resources/img/dice<%=idx1%>.jpg'>
<img src='resources/img/dice<%=idx2%>.jpg'>
</body>
</html>
