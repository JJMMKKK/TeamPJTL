package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.memberService;

@WebServlet("/confirm_IDPW_login_Possible")
public class confirm_IDPW_login_Possible extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
        	String userId = request.getParameter("userId");
        	String userPw = request.getParameter("userPw");


            boolean canLogin = serv.loginPossible(userId, userPw);

            if (canLogin) {
                out.print("loginSuccess");
            } else {
                out.print("loginFail");
            }
        } catch (Exception e) {
            out.print("error");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}