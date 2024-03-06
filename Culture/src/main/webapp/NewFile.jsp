$(".loginSet").on("input", function() {
			    var userId = $("#userId").val();
			    var userPw = $("#userPw").val();
			    var errorSpan = $("#confirmUserIdPwError");
			
			    if (userId && userPw) {
			        $.ajax({
			            type: "POST",
			            url: "confirm_IDPW_login_Possible", 
			            data: {
			            	userId: userId,
			            	userPw: userPw,
			            },
			            success: function(response) {
			                if (response === "loginFail") {
			                	errorSpan.text("아이디나 비밀번호를 확인해주세요.");
			                } else {
			                	errorSpan.text("");
			                }
			            },
			            error: function(error) {
			                console.error("아이디, 비밀번호 검사 에러:", error);
			            }
			        });
			    } else {
			    	errorSpan.text("");
				}
			});