<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>회원 가입 이력 없음</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
            text-align: center;
        }

        .notFound {
            max-width: 600px;
            margin: 0 auto;
        }

        .mesg {
            font-size: 18px;
            margin-bottom: 20px;
            color: red;
        }

        #linkSet {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .links {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
        }
    </style>
</head>

<body>
    <div class="notFound">
        <h1>회원 가입 이력 없음</h1>
        <p class="mesg">회원 정보가 없습니다. 입력하신 정보를 다시 한번 확인해주세요.</p>
        <div id="linkSet">
            <a href="loginForm.html" class="links">로그인</a>
            <a href="find_Id.jsp" class="links">아이디 찾기</a>
            <a href="find_Password.jsp" class="links">비밀번호 찾기</a>
            <a href="register_Term.jsp" class="links">회원가입</a>
        </div>
    </div>
</body>

</html>
