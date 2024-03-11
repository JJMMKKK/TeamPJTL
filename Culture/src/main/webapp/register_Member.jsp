<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입을 위한 유저 정보를 입력하는 페이지의 jsp -->

<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 20px;
	text-align: center;
}

.container {
	max-width: 400px;
	margin: 0 auto;
}

form {
	display: flex;
	flex-direction: column;
	align-items: center;
}

label {
	margin: 10px 0;
}

input, select {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	box-sizing: border-box;
}

#userSSN1, #userSSN2 {
	width: 45%;
}

#userId, #userName, #userSSN1, #userSSN2 {
	background-color: #f0f0f0;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
}
</style>
</head>

<body>

	<%
	String userName = (String) session.getAttribute("userName");
	int ssn1 = (int) session.getAttribute("ssn1");
	int ssn2 = (int) session.getAttribute("ssn2");
	%>

	<div class="container">
		<h1>회원가입</h1>
		<form id="registerForm" action="RegisterController"
			method="post">
			<label for="userId">아이디 (영어 + 숫자, 최소 4글자)</label> 
				<input type="text" id="userId" name="userId" pattern="[a-zA-Z0-9]{4,}" required readonly>
				<button id = "userIdButton" type="button" onclick="openIdWindow()">아이디 확인</button>

			<label for="userPw">비밀번호 (최소 6글자)</label> 
				<input type="password" id="userPw" name="userPw" minlength="6" required> 
			
			<label for="userPwConfirm">비밀번호 재입력</label> 
				<input type="password" id="userPwConfirm" name="userPwConfirm" minlength="6" required>
				<span id="pwMismatch" style="color: red;"></span> 
			
			<label for="userName">유저 이름</label> 
				<input type="text" id="userName" name="userName" value="<%=userName%>" readonly> 
				
			<label for="nickname">유저 닉네임 (최소 2글자)</label> 
				<input type="text" id="nickname" name="nickname" minlength="2" required> 
				<span id="confirmNicknameError" style="color: red;"></span>
				
			<label for="userSSN1">주민등록번호</label>
				<div style="display: flex; gap: 5px;">
					<input type="text" id="userSSN1" name="userSSN1" value="<%=ssn1%>" readonly> 
						&nbsp;&nbsp;-&nbsp;&nbsp; 
					<input type="password" id="userSSN2" name="userSSN2" value="<%=ssn2%>" readonly>
				</div>

			<label for="userGender">성별</label> 
				<select id="userGender" name="userGender" required>
					<option value="male" selected>남성</option>
					<option value="female">여성</option>
				</select> 
				
			<label for="userPhoneNum1">핸드폰 번호</label>
				<div style="display: flex; gap: 5px;">
					<select id="userPhoneNum1" name="userPhoneNum1" class="phoneNum" required>
				        <option value="010">010</option>
				        <option value="011">011</option>
   					 </select> 
					<input type="text" id="userPhoneNum2" name="userPhoneNum2" class="phoneNum" required maxlength="4"> 
					<input type="text" id="userPhoneNum3" name="userPhoneNum3" class="phoneNum" required maxlength="4">
				</div>
				<div id="phoneNumError" style="color: red;"></div>
				<span id="confirmPhoneNumError" style="color: red;"></span>

			<label for="userEmailDomain">이메일</label>
				<div style="display: flex; gap: 5px;">
					<input type="text" id="userEmailId" name="userEmailId" class ="userEmail" required>
						@ 
					<input type="text" id="userEmailDomain" name="userEmailDomain" class ="userEmail" required> 
					<select id="domainSelect" name="domainSelect" onchange="domainSelectMethod(this.value)" required>
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="" selected>직접 입력</option>
					</select>
				</div>
				<span id="confirmUserEmailError" style="color: red;"></span>
				

			<button type="submit">가입</button>
		</form>
	</div>


	<script type="text/javascript">
		//ID 새창 열기
		function openIdWindow() {
			var popup = window.open("childWindowConfirmID.html", "아이디 확인",
					"width=400,height=200");
			popup.onbeforeunload = function() {
				var confirmedUserId = popup.$("#confirmUserId").val();
				$("#userId").val(confirmedUserId);
			};
		}

		//닉네임 중복 확인
		$("#nickname").on("input", function() {
			var nickname = $("#nickname").val();
		    var errorSpan = $("#confirmNicknameError");
			
			$.ajax({
                type: "POST",
                url: "confirm_Nickname_Duplicate", 
                data: { nickname: nickname },
                success: function (response) {
                    if (response === "duplicate") {
                        errorSpan.text("이미 사용 중인 닉네임입니다.");
                    } else {
                        errorSpan.text("");
                    } 
                },
                error: function (error) {
                    console.error("닉네임 중복 검사 에러:", error);
                }
            });
		});
		
		//폰번 숫자로 제한
		function isNumeric(value) {
			return /^\d+$/.test(value);
		}
		
		//폰번 focus
		$("#userPhoneNum2").on('input', function() {
			var maxLength = 4;
			if ($(this).val().length >= maxLength) {
				$(this).val($(this).val().slice(0, maxLength));
				$("#userPhoneNum3").focus();
			}
		});
		
		//핸드폰 번호 중복 확인
		$(".phoneNum").on("input", function() {
		    var userPhoneNum1 = $("#userPhoneNum1").val();
		    var userPhoneNum2 = $("#userPhoneNum2").val();
		    var userPhoneNum3 = $("#userPhoneNum3").val();
		    var errorSpan = $("#confirmPhoneNumError");
		
		    if (userPhoneNum1 && userPhoneNum2 && userPhoneNum3) {
		        $.ajax({
		            type: "POST",
		            url: "confirm_PhoneNum_Duplicate", 
		            data: {
		            	userPhoneNum1: userPhoneNum1,
		            	userPhoneNum2: userPhoneNum2,
		            	userPhoneNum3: userPhoneNum3
		            },
		            success: function(response) {
		                if (response === "duplicate") {
		                	errorSpan.text("이미 사용 중인 핸드폰 번호입니다.");
		                } else {
		                	errorSpan.text("");
		                }
		            },
		            error: function(error) {
		                console.error("핸드폰 번호 중복 검사 에러:", error);
		            }
		        });
		    } else {
		    	errorSpan.text("");
			}
		});
		
		//도메인 readonly
		function domainSelectMethod(tv) {
			var userEmailDomain = $("#userEmailDomain");
			if (tv == "") {
				userEmailDomain.val("").prop("readonly", false);
			} else {
				userEmailDomain.val(tv).prop("readonly", true);
			}
		}
		
		//이메일 중복 확인
		$(".userEmail").on("input", function() {
		    var userEmailId = $("#userEmailId").val();
		    var userEmailDomain = $("#userEmailDomain").val();
		    var errorSpan = $("#confirmUserEmailError");
		
		    if (userEmailId && userEmailDomain) {
		        $.ajax({
		            type: "POST",
		            url: "confirm_Email_Duplicate", 
		            data: {
		            	userEmailId: userEmailId,
		            	userEmailDomain: userEmailDomain,
		            },
		            success: function(response) {
		                if (response === "duplicate") {
		                	errorSpan.text("이미 사용 중인 이메일입니다.");
		                } else {
		                	errorSpan.text("");
		                }
		            },
		            error: function(error) {
		                console.error("이메일 중복 검사 에러:", error);
		            }
		        });
		    } else {
		    	errorSpan.text("");
			}
		});

		//submit 제한규칙
		$("#registerForm").submit(function(event) {
			return validateForm(event);
		});

		function validateForm(event) {
			
			//아이디 유무
			if($("#userId").val() == ""){
				alert("아이디를 입력해주세요");
				event.preventDefault();
				$("#userIdButton").focus();
				return false;
			}
			
			//비번 일치 여부
			var password = $("#userPw").val();
			var confirmPassword = $("#userPwConfirm").val();

			if (password !== confirmPassword) {
				$("#pwMismatch").text("입력한 비밀번호가 일치하지 않습니다.");
				alert("비밀번호 일치 여부를 확인해주세요");
				event.preventDefault();
				$("#userPw").focus();
				return false;
			} else {
				$("#pwMismatch").text("");
			}

			//닉네임 중복 여부
			if($("#confirmNicknameError").text() != ""){
				alert("닉네임 중복 여부를 확인해주세요");
				event.preventDefault();
				$("#nickname").focus();
				return false;
			}
			
			//폰번 숫자 제한
			var phoneNum2 = $("#userPhoneNum2").val();
			var phoneNum3 = $("#userPhoneNum3").val();

			if (!isNumeric(phoneNum2) || !isNumeric(phoneNum3)) {
				$("#phoneNumError").text("핸드폰 번호에는 숫자만 입력해주세요.");
				alert("핸드폰 번호를 확인해주세요");
				event.preventDefault();
				$("#userPhoneNum2").focus();
				return false;
			} else {
				$("#phoneNumError").text("");
			}

			//핸드폰 번호 중복 여부
			if($("#confirmPhoneNumError").text() != ""){
				alert("핸드폰 번호 중복 여부를 확인해주세요");
				event.preventDefault();
				$("#userPhoneNum2").focus();
				return false;
			}
			
			//이메일 중복 여부
			if($("#confirmUserEmailError").text() != ""){
				alert("이메일 중복 여부를 확인해주세요");
				event.preventDefault();
				$("#userEmailId").focus();
				return false;
			}
				
			return true;
		}
	</script>



</body>

</html>
