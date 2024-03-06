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

import com.dto.DTO;
import com.service.memberService;

@WebServlet("/FindPWController")
public class FindPWController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		int ssn1 = Integer.parseInt(request.getParameter("ssn1"));
		int ssn2 = Integer.parseInt(request.getParameter("ssn2"));
		

		memberService serv = new memberService();
		List<DTO> foundUserPW = serv.findUserPW(userId, userName, ssn1, ssn2);
		HttpSession session = request.getSession();
		    if (foundUserPW != null && !foundUserPW.isEmpty()) {
		    	session.setAttribute("foundUserPW", foundUserPW);
		    	RequestDispatcher dis = request.getRequestDispatcher("found_PW.jsp");
		    	dis.forward(request, response);
		    } else {
		        response.sendRedirect("cantFindUser.jsp");
		    }
		}
}
