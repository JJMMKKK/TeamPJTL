package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.memberDAO;
import com.dto.memberDTO;

public class memberService {

	memberDAO dao = new memberDAO();

	public List<memberDTO> selectAll() {

		List<memberDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			list = dao.selectAll(session);
		} finally {
			session.close();
		}
		return list;
	}

	public List<memberDTO> findUserId(String userName, int ssn1, int ssn2) {
		Map<String, Object> dataForFindUserId = new HashMap<>();
		dataForFindUserId.put("userName", userName);
		dataForFindUserId.put("ssn1", ssn1);
		dataForFindUserId.put("ssn2", ssn2);

		List<memberDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			list = dao.findUserId(session, dataForFindUserId);
		} finally {
			session.close();
		}
		return list;
	}

	public List<memberDTO> findUserPW(String userId, String userName, int ssn1, int ssn2) {
		Map<String, Object> dataForFindUserPW = new HashMap<>();
			dataForFindUserPW.put("userId", userId);
			dataForFindUserPW.put("userName", userName);
			dataForFindUserPW.put("ssn1", ssn1);
			dataForFindUserPW.put("ssn2", ssn2);
		List<memberDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			list = dao.findUserPW(session, dataForFindUserPW);
		} finally {
			session.close();
		}
		return list;
	}

	public static boolean isUserIdDuplicate(String userId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		return memberDAO.isUserIdDuplicate(session, userId);
	}

	public static boolean isUserNicknameDuplicate(String nickname) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		return memberDAO.isUserNicknameDuplicate(session, nickname);
	}

	public int insertNewMember(memberDTO dto) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int num = 0;
		try {
			num = dao.insertNewMember(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return num;
	}

	public boolean isUserPNDuplicate(String userPhoneNum1, String userPhoneNum2, String userPhoneNum3) {
		Map<String, String> dataForFindExistPN = new HashMap<>();
			dataForFindExistPN.put("userPhoneNum1", userPhoneNum1);
			dataForFindExistPN.put("userPhoneNum2", userPhoneNum2);
			dataForFindExistPN.put("userPhoneNum3", userPhoneNum3);
		SqlSession session = MySqlSessionFactory.getSqlSession();
            return memberDAO.isUserPNDuplicate(session, dataForFindExistPN);
    }

	public boolean isUserEmailDuplicate(String userEmailId, String userEmailDomain) {
		Map<String, String> dataForFindExistEmail = new HashMap<>();
			dataForFindExistEmail.put("userEmailId", userEmailId);
			dataForFindExistEmail.put("userEmailDomain", userEmailDomain);
		SqlSession session = MySqlSessionFactory.getSqlSession();
        return memberDAO.isUserEmailDuplicate(session, dataForFindExistEmail);
	}

	public boolean loginPossible(String userId, String userPw) {
		Map<String, String> dataForLogin = new HashMap<>();
			dataForLogin.put("userId", userId);
			dataForLogin.put("userPw", userPw);
		SqlSession session = MySqlSessionFactory.getSqlSession();
	    return memberDAO.loginPossible(session, dataForLogin);
	}

	public List<memberDTO> findMemberInfo(String userId, String userPw) {
		Map<String, String> idPW = new HashMap<>();
			idPW.put("userId", userId);
			idPW.put("userPw", userPw);
		List<memberDTO> list = null;
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			list = dao.findMemberInfo(session, idPW);
		} finally {
			session.close();
		}
		return list;
	}
}