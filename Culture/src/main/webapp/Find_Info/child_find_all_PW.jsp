<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<!-- 전체 비밀번호를 확인하는 jsp -->

<head>
<meta charset="UTF-8">
<title>전체 비밀번호 확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>


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
    margin: 20px; 
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

<script type="text/javascript">

	//userId를 받아 처리하는 함수
	 var userId = opener.$("#userId").val();
	console.log(userId);
	
	function moveToParentPage() {
		window.opener.location.href = "<%=request.getContextPath()%>/Servlet_search_all_PW_by_answer?userId=" + userId;
        window.close(); 
    }
	
	function checkUserAnswer() {
		
		 var userInfo = $("#confirmUserInfo").val();
         var answer = $("#userAnswer").val();
         var errorSpan = $("#confirmAnswerError");
         
         if (userInfo === "userPhoneNum" && !/^\d{11}$/.test(answer)) {
             alert("핸드폰 번호는 숫자 11자리여야 합니다.");
             event.preventDefault();
             return false;
         } else if (userInfo === "userEmail" && !/@/.test(answer)) {
             alert("이메일 주소는 @를 포함해야 합니다.");
             event.preventDefault();
             return false;
         } else {
             
			//질문에 대한 답변 타당성 판정
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/Ajax_match_QnA_for_find_all_PW",
				data : {
					userInfo : userInfo,
					answer : answer,
					userId: userId
				},
				success : function(response) {
					if (response == "wrong_Answer") {
						errorSpan.text("질문에 대한 답변이 올바르지 않습니다.");
					} else if (response == "correct_Answer") {
						moveToParentPage();
					}
				},
				error : function(error) {
					console.error("비밀번호 출력 검사 에러:", error);
				}
			});
		}
	}
	
</script>

</head>

<body>

	<form id="confirmForm">
        <label for="confirmUserId">전체 비밀번호 확인을 위한 질문</label>
        <select id="confirmUserInfo" name="confirmUserInfo">
            <option value="nickname">본인의 닉네임은?</option>
            <option value="userPhoneNum">본인의 핸드폰 번호는?(숫자만 쓰시오)</option>
            <option value="userEmail">본인의 이메일 주소는?</option>
        </select>
        <input type="text" id="userAnswer" name="userAnswer">
        <button type="button" onclick="checkUserAnswer()">확인</button><br>
        <span id="confirmAnswerError"></span>
    </form>

</body>

</html>
