<%--
  Created by IntelliJ IDEA.
  User: hajju
  Date: 2025-02-20
  Time: 오후 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.spring.ch2.*" %>
<%@ page import="com.spring.ch2.servletandjsp.Person" %>
    <%--  Person 객체 생성하고 Person 객체를 request 객체에 저장.  --%>
<%
    Person person = new Person();
    request.setAttribute("person", person);
    request.setAttribute("name", "남궁성");
    request.setAttribute("list", new java.util.ArrayList());
%>
<html>
<head>
    <title>EL</title>
</head>
<body>
person.getCar().getColor()=<%=person.getCar().getColor()%> <br>
person.getCar().getColor()=${person.getCar().getColor()} <br>
                            <%--  Person에 getter가 있어야 아래와 같이 쓸 수 있음.(car.color라고 적혀있지만 getCar().getColor()를 호출한 것.  --%>
person.getCar().getColor()=${person.car.color} <br>
name=<%=request.getAttribute("name")%> <br>
name=${requestScope.name} <br>
name=${name} <br>
id=<%=request.getParameter("id")%> <br>
id=${pageContext.request.getParameter("id")} <br>
id=${param.id} <br>
"1"+1 = ${"1"+1} <br>
"1"+="1" = ${"1"+="1"} <br>
"2">1 = ${"2">1} <br>
null = ${null}<br>
null+1 = ${null+1} <br>
null+null = ${null+null} <br>
"" + null = ${""+null} <br>
""-1 = ${""-1} <br>
empty null=${empty null} <br>
empty list=${empty list} <br>
null==0 = ${null==0} <br>
null eq 0 = ${null eq 0} <br>
name == "남궁성"=${name=="남궁성"} <br>
name != "남궁성"=${name!="남궁성"} <br>
name eq "남궁성"=${name eq "남궁성"} <br>
name ne "남궁성"=${name ne "남궁성"} <br>
name.equals("남궁성")=${name.equals("남궁성")} <br>
</body>
</html>