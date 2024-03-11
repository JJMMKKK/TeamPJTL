package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.memberDTO;
import com.service.memberService;

@WebServlet("/confirm_Answer_For_All_Password")
public class confirm_Answer_For_All_Password extends HttpServlet {
       
	//childWindow_FindAllPassword.html에서 사용
	//전체 비밀번호 확인을 위한 질문과 대답을 받아와서, DB에 저장된 정보와 일치하면 전체 비밀번호 출력
	//일치할 경우, found_PW.jsp가 
	//불일치할 경우, found_PW.jsp에 ajax처리
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		memberService serv = new memberService();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
        PrintWriter out = response.getWriter();

        try {
        	String confirmUserInfo = request.getParameter("userInfo");
        	String userAnswer = request.getParameter("answer");
             String userId = request.getParameter("userId");
             boolean can_All_PW = false;
        	
             System.out.println(confirmUserInfo);
             System.out.println(userAnswer);
             System.out.println(userId);
             
                 // 선택된 질문에 따라 memberService로 전달
                 if (confirmUserInfo.equals("nickname")) {
                     can_All_PW = serv.findPWbyNickname(userAnswer, userId);
                 } else if (confirmUserInfo.equals("userPhoneNum")) {
                     can_All_PW = serv.findPWbyPhoneNum(userAnswer, userId);
                 } else if (confirmUserInfo.equals("userEmail")) {
                     can_All_PW = serv.findPWbyEmail(userAnswer, userId);
                 }
        	
                 System.out.println(can_All_PW);    
                 
            if (can_All_PW == true) {
                out.print("correct_Answer");
            } else if (can_All_PW == false){
                out.print("wrong_Answer");
            }
        } catch (Exception e) {
            out.print("error");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}