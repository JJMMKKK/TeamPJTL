package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.memberService;

@WebServlet("/confirm_PhoneNum_Duplicate")
public class confirm_PhoneNum_Duplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//register_Member.jsp에서 사용
	//핸드폰 번호 전체가 동일한 유저 데이터가 있을 경우, 중복 처리를 하는 ajax를 위한 서블릿
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
        PrintWriter out = response.getWriter();

        try {
        	String userPhoneNum1 = request.getParameter("userPhoneNum1");
        	String userPhoneNum2 = request.getParameter("userPhoneNum2");
        	String userPhoneNum3 = request.getParameter("userPhoneNum3");

            boolean isDuplicate = serv.isUserPNDuplicate(userPhoneNum1, userPhoneNum2, userPhoneNum3);

            if (isDuplicate) {
                out.print("duplicate");
            } else {
                out.print("not duplicate");
            }
        } catch (Exception e) {
            out.print("error");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}