package com.controller_for_login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.memberDTO;
import com.service.memberService;

//전체 비밀번호 찾기 자식창 --> 전체 비밀번호 출력창
@WebServlet("/Servlet_search_all_PW_by_answer")
public class Servlet_search_all_PW_by_answer extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");

		memberService serv = new memberService();
		memberDTO dto = serv.selectMemberData(userId);
		
		request.setAttribute("foundUserInfo", dto);
																								//디버그 코드************************************************************************
//																								RequestDispatcher dis = request.getRequestDispatcher("Find_Info/view_all_PW.jsp");
																								//디버그 코드************************************************************************
		RequestDispatcher dis = request.getRequestDispatcher("Send_EmailServlet");
		dis.forward(request, response);
	}
}
