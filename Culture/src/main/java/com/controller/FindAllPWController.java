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

@WebServlet("/FindAllPWController")
public class FindAllPWController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//*************미구현 상태****************
	//found_PW.jsp에서 사용
	//전체 비밀번호 찾기를 클릭하면, 전체 비밀번호를 찾을 수 있는 find_AllPassword.jsp로 연결하는 서블릿
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		//**************************************************************
		List<memberDTO> list = (List<memberDTO>) session.getAttribute("foundUserPW");
		session.setAttribute("foundUserPW", list);
		//**************************************************************
		
		//**************************************************************
		RequestDispatcher dis = request.getRequestDispatcher("find_AllPassword.jsp");
    	dis.forward(request, response);
    	//**************************************************************
	}
}
