package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.DTO;

public class memberDAO {

	public List<DTO> selectAll(SqlSession session) {
		List<DTO> list = session.selectList("selectAll");
		return list;
	}

	public List<DTO> findUserId(SqlSession session, Map<String, Object> dateForFindUserId) {
		List<DTO> list = session.selectList("findUserId", dateForFindUserId);
		return list;
	}

	public List<DTO> findUserPW(SqlSession session, Map<String, Object> dataForFindUserPW) {
		List<DTO> list = session.selectList("findUserPW", dataForFindUserPW);
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

	public int insertNewMember(SqlSession session, DTO dto) {
		System.out.println(dto);
		int num = session.insert("insertNewMember", dto);
		return num;
	}

	public static boolean isUserPNDuplicate(SqlSession session, Map<String, Integer> dataForFindExistPN) {
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

	public List<DTO> findMemberInfo(SqlSession session, Map<String, String> idPW) {
		List<DTO> list = session.selectList("findMemberInfo", idPW);
		return list;
	}
	
	
	
	
	
}