<%@page import="com.dto.memberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 20px;
        text-align: center;
    }

    h1 {
        color: #333;
    }

    p {
        color: #555;
    }

    div {
        margin-top: 20px;
    }

    a {
        text-decoration: none;
        color: #007bff;
        font-weight: bold;
    }
</style>
</head>
<body>

<h1>모든 비밀번호 출력</h1>

<%
    List<memberDTO> list = (List<memberDTO>) session.getAttribute("foundUserInfo");
%>

<%
    for (memberDTO dto : list) {
%>
    <p><%=dto.getUserName()%>님의 비밀번호는 <%=dto.getUserPw()%>입니다.</p>
<%
    }
%>

<div>
    <a href="<%=request.getContextPath()%>/LoginForm_Active">로그인</a> 
</div>

</body>
</html>
