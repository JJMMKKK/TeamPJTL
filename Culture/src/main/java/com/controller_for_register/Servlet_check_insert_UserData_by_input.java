package com.controller_for_register;

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

@WebServlet("/Servlet_check_insert_UserData_by_input")
public class Servlet_check_insert_UserData_by_input extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// register_Member.jsp에서 사용
	// 입력한 유저 데이터(아이디 / 비밀번호 / 이름 / SSN / 닉네임 / 성별 / 핸드폰 번호 / 이메일) 파싱
	// 각각 맞는 형태인지 재검사 - 문제 없으면 System.out.println으로 OOO확인이라고 출력
	// println 이후 insert 기능 수행
	// 정상적으로 insert가 될 경우, registerSuccess.jsp로 이동
	// 제대로 insert되지 않을 경우, Register/register_failure.jsp로 이동

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		memberService serv = new memberService();

		HttpSession session = request.getSession();

		// 입력한 정보가 타당하지 않으면 false로 전환
		boolean failMesg = true;

		// 아이디
		String userId = request.getParameter("userId");
		boolean isDuplicateID = serv.isUserIdDuplicate(userId);

		if (userId.length() < 4) { // 아이디 길이 규격확인
			System.out.println("아이디 길이 오류 " + userId + " " + userId.length());
			System.out.println("회원 가입 실패");
			session.setAttribute("mesg", "아이디 길이가 규정에 맞지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else if (isDuplicateID) { // 아이디 중복여부 재확인
			System.out.println("아이디 중복");
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "이미 가입된 아이디입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);
		} else { // 아이디 규격 통과
			System.out.println("아이디 확인");
		}

		// 비밀번호
		String userPw = request.getParameter("userPw");
		String userPwConfirm = request.getParameter("userPwConfirm");

		if (!(userPw.equals(userPwConfirm))) { // 비밀번호와 비밀번호 재확인 번호 일치 확인
			System.out.println("비밀번호 일치 오류 " + userPw + " " + userPwConfirm);
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "비밀번호가 일치하지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else if (userPw.length() < 6) { // 비밀번호 길이 규격확인
			System.out.println("비밀번호 길이 오류 " + userPw + " " + userPw.length());
			System.out.println("회원 가입 실패");
			session.setAttribute("mesg", "비밀번호 길이가 규정에 맞지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else { // 비밀번호 규격 통과
			System.out.println("비밀번호 확인");
		}

		// 이름 및 SSN
		String userName = request.getParameter("userName");
		int ssn1 = (Integer) session.getAttribute("ssn1");
		int ssn2 = (Integer) session.getAttribute("ssn2");
		List<memberDTO> foundUser = serv.findUserId(userName, ssn1, ssn2);
		int ssn2FirstNum = Integer.parseInt(String.valueOf(ssn2).substring(0, 1));

		if (ssn2FirstNum < 1 || ssn2FirstNum > 4) { // SSN2 첫 숫자(1~4) 확인
			System.out.println("SSN2 숫자 오류");
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "불가능한 주민등록번호입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else if (foundUser != null && !foundUser.isEmpty()) { // 이름 + SSN이 모두 일치하는 유저가 있는지 확인
			System.out.println("이름, SSN 기존 회원 정보 있음");
			System.out.println("회원 가입 실패");
			session.setAttribute("mesg", "이미 가입된 이름과 주민등록번호입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else { // 이름 + SSN 규격 통과
			System.out.println("이름, SSN 확인");
		}

		// 닉네임
		String nickname = request.getParameter("nickname");
		boolean isDuplicateNickname = serv.isUserNicknameDuplicate(nickname);

		if (nickname.length() < 2) { // 닉네임 길이 규격 확인
			System.out.println("닉네임 길이 오류 " + nickname + " " + nickname.length());
			System.out.println("회원 가입 실패");
			session.setAttribute("mesg", "닉네임 길이가 규정에 맞지 않습니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else if (isDuplicateNickname) { // 닉네임 중복 여부 확인
			System.out.println("닉네임 중복");
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "이미 가입된 닉네임입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else { // 닉네임 규격 통과
			System.out.println("닉네임 확인");
		}

		// 성별
		String userGender = request.getParameter("userGender");
		if (!(userGender.equals("male") || userGender.equals("female"))) { // 성별 확인
			System.out.println("성별 오류 " + userGender);
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "있을 수 없는 성별입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else { // 성별 규격 통과
			System.out.println("성별 확인");
		}

		// 핸드폰 번호
		String userPhoneNum1 = request.getParameter("userPhoneNum1");
		String userPhoneNum2 = request.getParameter("userPhoneNum2");
		String userPhoneNum3 = request.getParameter("userPhoneNum3");
		boolean isDuplicatePN = serv.isUserPNDuplicate(userPhoneNum1, userPhoneNum2, userPhoneNum3);

		if (isDuplicatePN) { // 핸드폰 번호 중복 확인(모든 번호 일치)
			System.out.println("핸드폰 번호 중복 " + userPhoneNum1 + " - " + userPhoneNum2 + " - " + userPhoneNum3);
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "이미 가입된 핸드폰 번호입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else if (userPhoneNum1.length() != 3 || userPhoneNum2.length() != 4 || userPhoneNum3.length() != 4) { // 핸드폰
																												// 번호 길이
																												// 확인
			System.out.println("핸드폰 번호 오류 " + userPhoneNum1 + " - " + userPhoneNum2 + " - " + userPhoneNum3);
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "있을 수 없는 핸드폰 번호입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else { // 핸드폰 번호 규격 통과
			System.out.println("핸드폰 번호 확인");
		}

		// 이메일
		String userEmailId = request.getParameter("userEmailId");
		String userEmailDomain = request.getParameter("userEmailDomain");
		boolean isDuplicateEM = serv.isUserEmailDuplicate(userEmailId, userEmailDomain);

		if (isDuplicateEM) { // 이메일 중복 확인(이메일 아이디 + 이메일 도메인이 모두 일치)
			System.out.println("이메일 중복 " + userEmailId + " @ " + userEmailDomain);
			System.out.println("회원 가입 실패");
			failMesg = false;
			session.setAttribute("mesg", "이미 가입된 이메일입니다. 확인해주세요");
			RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
			dis.forward(request, response);

		} else { // 이메일 규격 통과
			System.out.println("이메일 확인");
		}

		// 가입일 - 가입한 당일 날짜
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String userSignDate = dateFormat.format(currentDate);

		// 모든 규격을 통과한 경우, insert 진행
		// userType(회원 등급)은 1(일반 멤버)로 고정
		if (failMesg) {

			memberDTO dto = new memberDTO(userId, userPwConfirm, userName, ssn1, ssn2, nickname, userGender,
					userPhoneNum1, userPhoneNum2, userPhoneNum3, userEmailId, userEmailDomain, userSignDate, "1");
			int num = serv.insertNewMember(dto);

			// 성공적으로 insert된 경우, 회원가입 성공 페이지로 이동
			if (num == 1 && failMesg == true) {
				System.out.println("회원가입 성공");
				session.setAttribute("newMemberDTO", dto);
				RequestDispatcher dis = request.getRequestDispatcher("Register/register_success.jsp");
				dis.forward(request, response);

				// 모든 데이터가 규격을 통과했음에도 insert되지 않았을 경우, 회원가입 실패 페이지로 이동
			} else {
				System.out.println("회원가입 실패");
				session.setAttribute("mesg", "모종에 이유로 가입에 실패했습니다. 다시 한번 해주세요");
				RequestDispatcher dis = request.getRequestDispatcher("Register/register_failure.jsp");
				dis.forward(request, response);
			}
		}

	}

}
