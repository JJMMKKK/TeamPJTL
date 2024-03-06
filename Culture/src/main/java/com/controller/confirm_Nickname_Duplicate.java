package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.memberService;

@WebServlet("/confirm_Nickname_Duplicate")
public class confirm_Nickname_Duplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//**************가끔 작동하지 않는 버그가 발생하기 때문에 수정 필요************
	//register_Member.jsp에서 사용
	//닉네임 중복을 확인하는 ajax를 위한 서블릿
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		memberService serv = new memberService();

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();

		try {
			String nickname = request.getParameter("nickname");

			boolean isDuplicate = serv.isUserNicknameDuplicate(nickname);

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