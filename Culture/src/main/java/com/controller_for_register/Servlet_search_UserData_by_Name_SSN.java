package com.controller_for_register;

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

//회원가입 2단계 기존 유저인지 확인하는 서블릿
@WebServlet("/Servlet_search_UserData_by_Name_SSN")
public class Servlet_search_UserData_by_Name_SSN extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		int ssn1 = Integer.parseInt(request.getParameter("ssn1"));
		int ssn2 = Integer.parseInt(request.getParameter("ssn2"));

		memberService serv = new memberService();
		memberDTO dto = serv.findUserId(userName, ssn1, ssn2);

		//이름과 SSN이 모두 일치하는 DB정보가 있을 경우, 기존 유저 있음 jsp로 이동
		if (dto != null) {
			request.setAttribute("foundUser", dto);
			RequestDispatcher dis = request.getRequestDispatcher("Register/view_exist_UserData.jsp");
			dis.forward(request, response);
			
		//이름과 SSN이 모두 일치하는 DB정보가 없을 경우, 회원가입 3단계로 이동
		} else {
			request.setAttribute("userName", userName);
			request.setAttribute("ssn1", ssn1);
			request.setAttribute("ssn2", ssn2);
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_member.jsp");
			dis.forward(request, response);
		}
	}
}
