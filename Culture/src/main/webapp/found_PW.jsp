<%@page import="com.dto.memberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 비밀번호 찾기에서 비밀번호를 찾을 경우, 나오는 페이지의 jsp -->

<head>
    <meta charset="UTF-8">
    <title>Found PW</title>
    
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
    
    <script type="text/javascript">
        function maskPassword(password) {
            var visible = Math.ceil(password.length / 2);				// 마스킹할 길이 (비밀번호 길이의 절반)
            var masked = "*".repeat(password.length - visible);
            return password.slice(0, visible) + masked;
        }
    </script>
</head>

<body>
    <%
            List<memberDTO> list = (List<memberDTO>) session.getAttribute("foundUserPW");
    %>

    <h2>찾은 비밀번호 정보</h2>
    
    <%
		    //JavaScript 함수는 클라이언트 측에서 실행되므로, Java 코드에서 직접 호출할 수 없기 때문에 scrip 형식
            for (memberDTO dto : list) {
                String maskedPassword = "<script>document.write(maskPassword('" + dto.getUserPw() + "'));</script>";
    %>
    
    <p><%=dto.getUserName()%>님의 비밀번호는 <%=maskedPassword%>입니다.</p>
    
    <%
        }
    %>
    <div>
        <a href="loginForm.html">로그인</a> | <a href="FindAllPWController">전체 비밀번호 찾기</a>
    </div>
</body>

</html>
