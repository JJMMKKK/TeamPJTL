package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.memberService;

@WebServlet("/confirm_Id_Duplicate")
public class confirm_Id_Duplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//childWindowConfirmID.html에서 사용
	// 아이디 중복 여부를 확인하고 출력하는 ajax를 위한 서블릿
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
        PrintWriter out = response.getWriter();

        try {
            String userId = request.getParameter("userId");

            boolean isDuplicate = serv.isUserIdDuplicate(userId);

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