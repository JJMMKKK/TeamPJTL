<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<!-- 회원가입에서 아이디 중복 확인하는 페이지의 html -->

<head>
    <meta charset="UTF-8">
    <title>아이디 확인</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        form {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        button {
            padding: 8px 16px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #0056b3;
        }

        #confirmIdError {
            color: red;
        }
    </style>
    
    
</head>

<body>
    <form id="confirmForm">
        <label for="confirmUserId">아이디 (영어 + 숫자, 최소 4글자):</label>
        <input type="text" id="confirmUserId" name="confirmUserId" pattern="[a-zA-Z0-9]{4,}" required>
        <button type="button" onclick="checkDuplicate()">확인</button><br>
        <span id="confirmIdError"></span>
    </form>

    <script type="text/javascript">
		
        function checkDuplicate() {
            var userId = $("#confirmUserId").val();
            var errorSpan = $("#confirmIdError");

			//아이디 생성 규칙
            var regex = /^[a-zA-Z0-9]{4,}$/;
            if (!regex.test(userId)) {
                errorSpan.text("아이디는 영어와 숫자로 4글자 이상이어야 합니다.");
                return;
            }
            errorSpan.text("");

			//아이디 중복 체크
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath()%>/Ajax_check_ID_duplicate_for_register", 
                data: { userId: userId },
                success: function (response) {
                    if (response === "duplicate") {
                        errorSpan.text("이미 사용 중인 아이디입니다.");
                    } else {
                        window.opener.$("#userId").val(userId);
                        window.close();
                    }
                },
                error: function (error) {
                    console.error("아이디 중복 검사 에러:", error);
                }
            });
        }
    </script>
</body>

</html>
