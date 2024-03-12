package com.controller_for_login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.memberDTO;
import com.service.memberService;

@WebServlet("/LoginFormController")
public class LoginFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//loginForm.html에서 사용
	//입력한 아이디와 비밀번호가 일치하는지 확인
	//일치하면 loginSuccess.jsp(임시)
	//불일치하면 cantFindUser.jsp로 이동
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        memberService serv = new memberService();
		List<memberDTO> foundUser = serv.findMemberInfo(userId, userPw);
		HttpSession session = request.getSession();
		if (foundUser != null && !foundUser.isEmpty()) {
			session.setAttribute("foundUser", foundUser);
			RequestDispatcher dis = request.getRequestDispatcher("loginSuccess.jsp");
			dis.forward(request, response);
		} else {
			RequestDispatcher dis = request.getRequestDispatcher("cantFindUser.jsp");
			dis.forward(request, response);
		}
	
	
	}

}
