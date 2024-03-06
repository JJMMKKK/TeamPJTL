<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 회원가입을 위한 약관에 동의하는 페이지.jsp -->

<head>
<meta charset="UTF-8">
<title>약관 동의 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 20px;
}

.container {
	max-width: 600px;
	margin: 0 auto;
}

textarea {
	width: 100%;
	margin-bottom: 10px;
}

.checkboxes {
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 5px;
}

button {
	padding: 10px;
	background-color: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
}
</style>
</head>
<body>

	<div class="container">
		<h1>약관 동의 페이지</h1>

		<div>
			<textarea readonly="readonly">
            이용 약관 내용
        </textarea>
			<label><input type="checkbox" class="checkboxes"> 이용
				약관에 동의합니다.</label>
		</div>

		<div>
			<textarea readonly="readonly">
            개인정보 처리방침 내용
        </textarea>
			<label><input type="checkbox" class="checkboxes">
				개인정보 처리방침에 동의합니다.</label>

		</div>

		<div>
			<textarea readonly="readonly">
            회원 탈퇴 및 서비스 이용 중지 규정 내용
        </textarea>
			<label><input type="checkbox" class="checkboxes"> 회원
				탈퇴 및 서비스 이용 중지에 동의합니다.</label>
		</div>
		<br>

		<div>
			<label><input type="checkbox" id="allCheckbox"
				onclick="clickAllChk(this.checked)"> 모두 동의합니다.</label>
		</div>
		<button onclick="chkAgreement()">다음 페이지로 이동</button>
	</div>

	<script type="text/javascript">
		$(function() {
			$("#allCheckbox").click(function() {
				clickAllChk(this.checked);
			});

			$(".checkboxes").click(function() {
				$("#allCheckbox").prop("checked",$(".checkboxes:checked").length === $(".checkboxes").length);
			});
		});

		function clickAllChk(tc) {
			$(".checkboxes").prop("checked", tc);
		}

		function chkAgreement() {

			if ($(".checkboxes:not(:checked)").length === 0) {
				alert("약관에 모두 동의하셨습니다. 다음 페이지로 이동합니다.");
				location.href = "confirm_Id.jsp";
			} else {
				alert("모든 약관에 동의해야 다음 페이지로 이동할 수 있습니다.");
			}
		}
	</script>

</body>
</html>
