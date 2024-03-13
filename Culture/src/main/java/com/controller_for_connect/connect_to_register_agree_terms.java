package com.controller_for_connect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//회원가입 1단계 약관 동의 단계 연결
@WebServlet("/connect_to_register_agree_terms")
public class connect_to_register_agree_terms extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Register/register_agree_terms.jsp");
		
	}
}
