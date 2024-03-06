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

@WebServlet("/FindIdController")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		int ssn1 = Integer.parseInt(request.getParameter("ssn1"));
		int ssn2 = Integer.parseInt(request.getParameter("ssn2"));

		memberService serv = new memberService();
		List<DTO> foundUser = serv.findUserId(userName, ssn1, ssn2);
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
