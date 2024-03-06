package com.controller;

import java.awt.Window.Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.memberDTO;
import com.service.memberService;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//register_Member.jsp에서 사용
	//입력한 유저 데이터(아이디 / 비밀번호 / 이름 / SSN / 닉네임 / 성별 / 핸드폰 번호 / 이메일) 파싱
	//각각 맞는 형태인지 재검사 - 문제 없으면 System.out.println으로 OOO확인이라고 출력
	//println 이후 insert 기능 수행
	//정상적으로 insert가 될 경우, registerSuccess.jsp로 이동
	//제대로 insert되지 않을 경우, registerFailure.jsp로 이동
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		memberService serv = new memberService();
		
		HttpSession session = request.getSession();
		
		//회원가입 전 입력한 정보 최종 확인 및 insert
		
		String userId = request.getParameter("userId");
		boolean isDuplicateID = serv.isUserIdDuplicate(userId);
				if(userId.length() < 4) {
					System.out.println("아이디 길이 오류 "+userId+" "+userId.length());
				} else if (isDuplicateID) {
				            System.out.println("아이디 중복");
				        } else {
				        	System.out.println("아이디 확인");
				        }
				
		String userPw = request.getParameter("userPw");
		String userPwConfirm = request.getParameter("userPwConfirm");
				if(!(userPw.equals(userPwConfirm))) {
				    System.out.println("비밀번호 일치 오류 "+userPw+" "+userPwConfirm);
		        } else if(userPw.length() < 6) {
				    	System.out.println("비밀번호 길이 오류 "+userPw+" "+userPw.length());
			        } else {
			        	System.out.println("비밀번호 확인");
			        }
				
		String userName = request.getParameter("userName");
		int  ssn1 = (Integer) session.getAttribute("ssn1");
		int  ssn2 = (Integer) session.getAttribute("ssn2");
		List<memberDTO> foundUser = serv.findUserId(userName, ssn1, ssn2);	
		int ssn2FirstNum = Integer.parseInt(String.valueOf(ssn2).substring(0, 1));
				if (ssn2FirstNum < 1 || ssn2FirstNum > 4) {
					System.out.println("SSN2 숫자 오류");
				} else if (foundUser != null && !foundUser.isEmpty()) {
						System.out.println("이름, SSN 기존 회원 정보 있음");
					} else {
						System.out.println("이름, SSN 확인");
					}
		
		String nickname = request.getParameter("nickname");
		boolean isDuplicateNickname = serv.isUserNicknameDuplicate(nickname);
				if(nickname.length() < 2) {
						System.out.println("닉네임 길이 오류 "+nickname+" "+nickname.length());
				} else if (isDuplicateNickname) {
		            	System.out.println("닉네임 중복");
			        } else {
			        	System.out.println("닉네임 확인");
			        }
				
		String userGender = request.getParameter("userGender");
				if(!(userGender.equals("male") || userGender.equals("female"))) {
					System.out.println("성별 오류 "+userGender);
				} else {
					System.out.println("성별 확인");
				}
		
		String userPhoneNum1 = request.getParameter("userPhoneNum1");
		String userPhoneNum2 = request.getParameter("userPhoneNum2");
		String userPhoneNum3 = request.getParameter("userPhoneNum3");
		boolean isDuplicatePN = serv.isUserPNDuplicate(userPhoneNum1, userPhoneNum2, userPhoneNum3);
				if (isDuplicatePN) {
			        System.out.println("핸드폰 번호 중복 " + userPhoneNum1 +" - "+ userPhoneNum2 +" - "+ userPhoneNum3);
			    } else if(	
		    		userPhoneNum1.length() != 3 || 
    				userPhoneNum2.length() != 4 ||
					userPhoneNum3.length() != 4 ){
					System.out.println("핸드폰 번호 오류 "+userPhoneNum1+" - "+userPhoneNum2+" - "+userPhoneNum3);
				} else {
					System.out.println("핸드폰 번호 확인");
				}
		
		String userEmailId = request.getParameter("userEmailId");
		String userEmailDomain = request.getParameter("userEmailDomain");
		boolean isDuplicateEM = serv.isUserEmailDuplicate(userEmailId, userEmailDomain);
				if (isDuplicateEM) {
			        System.out.println("이메일 중복 " + userEmailId+" @ "+userEmailDomain);
			        System.out.println("회원 가입 실패");
			        session.setAttribute("mesg", "이미 가입된 이메일입니다. 확인해주세요");
					RequestDispatcher dis = request.getRequestDispatcher("registerFailure.jsp");
					dis.forward(request, response);
				} else {
		        	System.out.println("이메일 확인");
		        }
		
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String userSignDate = dateFormat.format(currentDate);

		memberDTO dto = new memberDTO(userId, userPw, userName, ssn1, ssn2, nickname, userGender, userPhoneNum1, userPhoneNum2, userPhoneNum3, userEmailId, userEmailDomain, userSignDate);
		int num = serv.insertNewMember(dto);
		
		if(num == 1) {
			System.out.println("회원 가입 성공");
			session.setAttribute("newMemberDTO", dto);
			RequestDispatcher dis = request.getRequestDispatcher("registerSuccess.jsp");
			dis.forward(request, response);
			
		} else {
			System.out.println("회원 가입 실패");
			RequestDispatcher dis = request.getRequestDispatcher("registerFailure.jsp");
			dis.forward(request, response);
			
		
		}
		
	}

}
