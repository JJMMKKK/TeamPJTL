package com.controller;

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

@WebServlet("/confirmIdController")
public class confirmIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//confirm_Id.jsp에서 사용
	//이름 / SSN을 받아서 동일한 유저 정보가 있는지 확인하는 서블릿
	//일치하면 findUser.jsp로 이동
	//불일치하면 register_Member.jsp로 이동
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userName = request.getParameter("userName");
		int ssn1 = Integer.parseInt(request.getParameter("ssn1"));
		int ssn2 = Integer.parseInt(request.getParameter("ssn2"));

		memberService serv = new memberService();
		List<memberDTO> foundUser = serv.findUserId(userName, ssn1, ssn2);
		HttpSession session = request.getSession();
		if (foundUser != null && !foundUser.isEmpty()) {
			session.setAttribute("foundUser", foundUser);
			RequestDispatcher dis = request.getRequestDispatcher("findUser.jsp");
			dis.forward(request, response);
		} else {
			session.setAttribute("userName", userName);
			session.setAttribute("ssn1", ssn1);
			session.setAttribute("ssn2", ssn2);
			RequestDispatcher dis = request.getRequestDispatcher("register_Member.jsp");
			dis.forward(request, response);
		}
	}
}
