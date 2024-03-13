<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!--로그인 메인 페이지의 html-->

<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
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

        #for_Login {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
        }

        table tr {
            margin-bottom: 10px;
        }

        table td {
            padding: 10px;
        }

        #showPasswd {
            background-color: #007BFF;
            color: white;
            padding: 8px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        #showPasswd:hover {
            background-color: #0056b3;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 15px 20px; /* 크기 조절 */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;

        }

        input[type="submit"]:hover {
			width: 100%;
            background-color: #218838;
        }

        #sitesShortCut {
            margin-top: 10px;
            text-align: center;
        }

        #sitesShortCut a {
            text-decoration: none;
            background-color: #007BFF;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
            font-weight: bold;
            margin: 0 5px;
        }

        #sitesShortCut a:hover {
            background-color: #0056b3;
        }

        #debugLink {
            display: block;
            margin-top: 10px;
            text-align: center;
        }

        #debugLink a {
            text-decoration: none;
            color: #6c757d;
            font-size: 14px;
        }

        #debugLink a:hover {
            color: #495057;
        }
    </style>
    <script type="text/javascript">
        $(function () {
			
			$("#loginForm").on("submit", function(event) {
				event.preventDefault(); // 폼이 서버로 전송되지 않도록 기본 동작을 막음

			    var userId = $("#userId").val();
			    var userPw = $("#userPw").val();
			    var errorSpan = $("#confirmUserIdPwError");
			
			    if (userId && userPw) {
			        $.ajax({
			            type: "POST",
			            url: "<%=request.getContextPath()%>/Ajax_check_IDPW_for_login", 
			            data: {
			            	userId: userId,
			            	userPw: userPw,
			            },
			            beforeSend: function() {
                            // AJAX 요청 전에 수행할 작업 (로딩 표시 등)
                            $("#loginButton").prop("disabled", true); // 버튼 비활성화
                        },
			            success: function(response) {
			                if (response === "loginFail") {
			                	errorSpan.text("아이디나 비밀번호를 확인해주세요.");
			                } else {
			                	errorSpan.text("");
			                	$("#loginForm")[0].submit();
			                }
			            },
			            error: function(error) {
			                console.error("아이디, 비밀번호 검사 에러:", error);
			            },
			            complete: function() {
                            // AJAX 요청 완료 후 수행할 작업 (로딩 표시 해제 등)
                            $("#loginButton").prop("disabled", false); // 버튼 활성화
                        }
			        });
			    } else {
			    	errorSpan.text("");
				}
			});
			
			$("#loginForm").submit(function(event) {
				return validateForm(event);
			});
			
            function validateForm(event) {
                var userId = $("#userId").val();
                var userPw = $("#userPw").val();

                // ID 공백 여부
                if (userId.trim() === "") {
                    alert("아이디를 입력하세요");
                    event.preventDefault();
                    return false;
                }

                // PW 공백 여부
                if (userPw.trim() === "") {
                    alert("비밀번호를 입력하세요");
                    event.preventDefault();
                    return false;
                }
                
                //아이디 및 비밀번호 존재 여부
				if($("#confirmUserIdPwError").text() != ""){
					event.preventDefault();
					$("#userId").focus();
					return false;
				}
					
				return true;
                
            };

            // PW visible
            $("#showPasswd").click(function () {
                var showPW = $("#userPw");
                if (showPW.attr("type") === "password") {
                    showPW.attr("type", "text");
                } else {
                    showPW.attr("type", "password");
                }
            });
        });
    </script>
</head>

<body>
    <div id="for_Login">
        <form id="loginForm" action="<%=request.getContextPath()%>/Servlet_login_by_ID_PW" method="post">
            <table>
                <tr>
                    <td>아이디:</td>
                    <td><input type="text" id="userId" name="userId" class="loginSet"></td>
                </tr>
                <tr>
                    <td>비밀번호:</td>
                    <td><input type="password" id="userPw" name="userPw" class="loginSet"><br>
                    <span id="confirmUserIdPwError" style="color: red;"></span>
                        <button type="button" id="showPasswd">비밀번호 보이기</button></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input id="loginButton" type="submit" value="로그인">
                    </td>
                </tr>
            </table>
        </form>
        <div id="sitesShortCut">
            <a href="<%=request.getContextPath()%>/connect_to_search_ID_by_Name_SSN">아이디 찾기</a> |
            <a href="<%=request.getContextPath()%>/connect_to_search_PW_by_ID_Name_SSN">비밀번호 찾기</a> |
            <a href="<%=request.getContextPath()%>/connect_to_register_agree_terms">회원가입</a>
        </div>
        <div id="debugLink">
            <a href="<%=request.getContextPath()%>/memberListServlet">디버그용 - 회원리스트</a>
        </div>
    </div>
</body>

</html>