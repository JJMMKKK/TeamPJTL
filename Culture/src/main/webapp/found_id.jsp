<%@page import="com.dto.DTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Found ID</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            height: 100vh;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            font-size: 16px;
            margin-bottom: 10px;
        }

        a {
            text-decoration: none;
            background-color: #007BFF;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            font-size: 16px;
            margin-top: 20px;
            display: inline-block;
        }

        a:hover {
            background-color: #0056b3;
        }
    </style>

</head>

<body>

	<%
	List<DTO> list = (List<DTO>) session.getAttribute("foundUser");
	%>

	<h2>찾은 아이디 정보</h2>
	<%
	for (DTO dto : list) {
	%>
	<p><%=dto.getUserName()%>님의 아이디는
		<%=dto.getUserId()%>입니다.
	</p>
	<%
	}
	%>
	<div>
		<a href="loginForm.html">로그인</a> | <a href="find_Password.jsp">비밀번호
			찾기</a>
	</div>
</body>

</html>
