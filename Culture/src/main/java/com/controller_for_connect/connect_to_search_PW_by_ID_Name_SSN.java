package com.controller_for_connect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//비밀번호 찾기 기능 연결
@WebServlet("/connect_to_search_PW_by_ID_Name_SSN")
public class connect_to_search_PW_by_ID_Name_SSN extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Find_Info/search_PW_by_ID_Name_SSN.jsp");
		
	}
}
