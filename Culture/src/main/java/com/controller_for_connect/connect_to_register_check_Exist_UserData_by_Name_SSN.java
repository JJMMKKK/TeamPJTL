package com.controller_for_connect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/connect_to_register_check_Exist_UserData_by_Name_SSN")
public class connect_to_register_check_Exist_UserData_by_Name_SSN extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//약관 동의 여부를 DB에 저장할 지 여부는 현재 미정	
		//DB에 저장할 것을 대비해서 
		
		String checked_Agreement = request.getParameter("checked_Agreement");
		String checked_Info = request.getParameter("checked_Info");
		String checked_Withdraw = request.getParameter("checked_Withdraw");

		System.out.println(
					"checked_Agreement : " + checked_Agreement+"\n"+ 
					"checked_Info : " + checked_Info+"\n"+
					"checked_Withdraw : " + checked_Withdraw);
		
		
		HttpSession session = request.getSession();
		//session.setAttribute("checked_terms", checked_terms);
		
		response.sendRedirect("Register/register_check_Exist_UserData_by_Name_SSN.jsp");
		
	}

}
