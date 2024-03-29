package com.controller.member.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

//로그인 메인에서 사용하는 로그인 기능(임시 코드)
@WebServlet("/Mypage")
public class LoginToMypageServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");

        MemberService serv = new MemberService();
		MemberDTO dto = serv.findMemberInfo(userId, userPw);

		HttpSession session = request.getSession();

		//아이디와 비밀번호가 DB에 일치하는 유저가 있을 경우, 유저 전체 정보 출력
		if (dto != null) {
			session.setAttribute("loginUser", dto);
			response.sendRedirect("main");
		//아이디와 비밀번호가 DB에 일치하는 유저가 없을 경우, 유저 확인 불가로 연결
		} else {
			RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/member/Find_Info/cantFindUserdata.jsp");
			dis.forward(request, response);
		}
	}
}
