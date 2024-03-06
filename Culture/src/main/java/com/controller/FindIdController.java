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

@WebServlet("/FindIdController")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//find_Id.jsp에서 사용
	//입력한 이름과 주민번호가 일치하면 찾은 아이디 정보(found_id.jsp)로 이동
	//입력한 이름과 주민번호가 불일치하면 찾은 아이디 정보(cantFindUser.jsp)로 이동
	
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
		    	RequestDispatcher dis = request.getRequestDispatcher("found_id.jsp");
		    	dis.forward(request, response);
		    } else {
		        response.sendRedirect("cantFindUser.jsp");
		    }
		}
}
