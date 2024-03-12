package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.memberDTO;

public class memberDAO {

	public List<memberDTO> selectAll(SqlSession session) {
		List<memberDTO> list = session.selectList("selectAll");
		System.out.println("dao "+ list);
		return list;
	}
	
	public List<memberDTO> findUserId(SqlSession session, Map<String, Object> dateForFindUserId) {
		List<memberDTO> list = session.selectList("findUserId", dateForFindUserId);
		return list;
	}

	public List<memberDTO> findUserPW(SqlSession session, Map<String, Object> dataForFindUserPW) {
		List<memberDTO> list = session.selectList("findUserPW", dataForFindUserPW);
		return list;
	}

	public static boolean isUserIdDuplicate(SqlSession session, String userId) {
		try {
			int num = session.selectOne("isUserIdDuplicate", userId);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isUserNicknameDuplicate(SqlSession session, String nickname) {
		try {
			int num = session.selectOne("isUserNicknameDuplicate", nickname);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int insertNewMember(SqlSession session, memberDTO dto) {
		int num = session.insert("insertNewMember", dto);
		return num;
	}

	public static boolean isUserPNDuplicate(SqlSession session, Map<String, String> dataForFindExistPN) {
		try {
			int num = session.selectOne("isUserPNDuplicate", dataForFindExistPN);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isUserEmailDuplicate(SqlSession session, Map<String, String> dataForFindExistEmail) {
		try {
			int num = session.selectOne("isUserEmailDuplicate", dataForFindExistEmail);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean loginPossible(SqlSession session, Map<String, String> dataForLogin) {
		try {
			int num = session.selectOne("loginPossible", dataForLogin);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<memberDTO> findMemberInfo(SqlSession session, Map<String, String> idPW) {
		List<memberDTO> list = session.selectList("findMemberInfo", idPW);
		return list;
	}

	public static boolean findPWbyNickname(SqlSession session, HashMap<String, String> nicknameMap) {
		try {
			int num = session.selectOne("findPWbyNickname", nicknameMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean findPWbyPhoneNum(SqlSession session, Map<String, String> phoneNumMap) {
		try {
			int num = session.selectOne("findPWbyPhoneNum", phoneNumMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean findPWbyEmail(SqlSession session, HashMap<String, String> emailMap) {
		try {
			int num = session.selectOne("findPWbyEmail", emailMap);
			return num > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<memberDTO> selectMemberData(SqlSession session, String userId) {
		List<memberDTO> list = session.selectList("selectMemberData", userId);
		return list;
	}

	public memberDTO selectOne(SqlSession session, String userId) {
		memberDTO dto = session.selectOne("selectOne", userId);
		return dto;
	}
	
	
	
	
	
}