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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		memberService serv = new memberService();

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