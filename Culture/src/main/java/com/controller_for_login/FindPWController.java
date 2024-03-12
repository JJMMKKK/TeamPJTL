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

@WebServlet("/FindPWController")
public class FindPWController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//found_PW.jsp에서 사용
	//입력한 아이디 / 이름 / SSN이 일치하는지 확인
	//일치하면 found_PW.jsp로 이동
	//불일치하면 cantFindUser.jsp로 이동
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		int ssn1 = Integer.parseInt(request.getParameter("ssn1"));
		int ssn2 = Integer.parseInt(request.getParameter("ssn2"));
		

		memberService serv = new memberService();
		List<memberDTO> foundUserPW = serv.findUserPW(userId, userName, ssn1, ssn2);
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
