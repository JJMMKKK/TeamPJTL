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

@WebServlet("/Servlet_search_all_PW_by_answer")
public class Servlet_search_all_PW_by_answer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userId = request.getParameter("userId");

		memberService serv = new memberService();
		List<memberDTO> list = serv.selectMemberData(userId);
		System.out.println(list);
		
		HttpSession session = request.getSession();
		session.setAttribute("foundUserInfo", list);

		RequestDispatcher dis = request.getRequestDispatcher("Find_Info/view_all_PW.jsp");
		dis.forward(request, response);
		 		 	
		
	}
}
