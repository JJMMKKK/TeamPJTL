package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.memberDTO;
import com.service.memberService;

@WebServlet("/confirm_Email_Duplicate")
public class confirm_Email_Duplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//register_Member.jsp에서 사용
	// 이메일 중복 여부를 확인하고 출력하는 ajax를 위한 서블릿
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
        PrintWriter out = response.getWriter();

        try {
        	String userEmailId = request.getParameter("userEmailId");
        	String userEmailDomain = request.getParameter("userEmailDomain");
        	
            boolean isDuplicate = serv.isUserEmailDuplicate(userEmailId, userEmailDomain);

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