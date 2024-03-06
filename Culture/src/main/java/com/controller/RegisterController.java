package com.controller;

import java.awt.Window.Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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

import com.dto.DTO;
import com.service.memberService;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		memberService serv = new memberService();
		
		HttpSession session = request.getSession();
		
		//회원가입 전 입력한 정보 최종 확인 및 insert
		
		String userId = request.getParameter("userId");
		boolean isDuplicate = serv.isUserIdDuplicate(userId);
				if(userId.length() < 4) {
					System.out.println("아이디 길이 오류 "+userId+" "+userId.length());
				} else if (isDuplicate) {
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
		List<DTO> foundUser = serv.findUserId(userName, ssn1, ssn2);	
		int ssn2FirstNum = Integer.parseInt(String.valueOf(ssn2).substring(0, 1));
				if (ssn2FirstNum < 1 || ssn2FirstNum > 4) {
					System.out.println("SSN2 숫자 오류");
				} else if (foundUser != null && !foundUser.isEmpty()) {
						System.out.println("이름, SSN 기존 회원 정보 있음");
					} else {
						System.out.println("이름, SSN 확인");
					}
		
		String nickname = request.getParameter("nickname");
		boolean isDuplicate2 = serv.isUserNicknameDuplicate(nickname);
				if(nickname.length() < 2) {
						System.out.println("닉네임 길이 오류 "+nickname+" "+nickname.length());
				} else if (isDuplicate2) {
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
		
		String userPhoneNumStr1 = request.getParameter("userPhoneNum1");
		String userPhoneNumStr2 = request.getParameter("userPhoneNum2");
		String userPhoneNumStr3 = request.getParameter("userPhoneNum3");
		int userPhoneNum1 = Integer.parseInt(userPhoneNumStr1);
		int userPhoneNum2 = Integer.parseInt(userPhoneNumStr2);
		int userPhoneNum3 = Integer.parseInt(userPhoneNumStr3);
				
				//핸드폰 번호 3개 동시에 검사 + 중복확인하는 함수
				//**********************************
				//**********************************
				//**********************************
				//**********************************
				
				if (false) {
		            System.out.println("핸드폰 번호 중복");
		        } else if(	
		        	userPhoneNumStr1.length() != 3 || 
		        	userPhoneNumStr2.length() != 4 ||
		        	userPhoneNumStr3.length() != 4 ){
					System.out.println("핸드폰 번호 오류 "+userPhoneNumStr1+" "+userPhoneNumStr2+" "+userPhoneNumStr3);
				} else {
					System.out.println("핸드폰 번호 확인");
				}
		
		String userEmailId = request.getParameter("userEmailId");
		String userEmailDomain = request.getParameter("userEmailDomain");
				
				//이메일 아이디 + 도메인 동시에 검사 + 중복확인하는 함수
				//**********************************
				//**********************************
				//**********************************
				//**********************************
				
				if (false) {
		            System.out.println("이메일 오류 "+userEmailId+" @ "+userEmailDomain);
		        } else {
		        	System.out.println("이메일 확인");
		        }
		
		Calendar calendar = Calendar.getInstance();
				
		Timestamp userSignDate = new Timestamp(new Date().getTime());
			System.out.println("가입일 확인");
			System.out.println(userName+"의 가입일은 "+userSignDate);
		

	//	DTO dto = new DTO(userId, userPw, userName, ssn1, ssn2, userGender, userPhoneNum1, userPhoneNum2, userPhoneNum3, userEmailId, userEmailDomain, userSignDate, nickname);
		DTO dto = new DTO();
			dto.setUserId(userId);
			dto.setUserPw(userPwConfirm);
			dto.setUserName(userName);
			dto.setNickname(nickname);
			dto.setUserSSN1(ssn1);
			dto.setUserSSN2(ssn2);
			dto.setUserGender(userGender);
			dto.setUserPhoneNum1(userPhoneNum1);
			dto.setUserPhoneNum2(userPhoneNum2);
			dto.setUserPhoneNum3(userPhoneNum3);
			dto.setUserEmailId(userEmailId);
			dto.setUserEmailDomain(userEmailDomain);
			dto.setUserSignDate(userSignDate);
			
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
