package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {

	/**
	 * 1. 로그인용 서비스
	 * @param userId	사용자가 입력한 아이디
	 * @param userPwd	사용자가 입력한 비밀번호
	 * @return	아이디와 비밀번호가 일치해 조회된 회원 객체
	 */
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = getConnection();
		
		Member loginUser = new MemberDao().loginMember(conn, userId, userPwd);

		close(conn);
		
		return loginUser;
	}
	
	/**
	 * 2. 회원가입용 서비스
	 * @param mem	회원가입 페이지에서 입력받은 정보들이 담겨있는 Member 객체
	 * @return	insert실행 후 처리된 행의 개수
	 */
	public int insertMember(Member mem) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(conn, mem);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 3. 회원번호를 통한 회원 정보 조회용 서비스
	 * @param userNo	조회하고자하는 로그인한 회원 번호
	 * @return	해당 회원번호와 일치하는 회원 객체
	 */
	public Member selectMember(String userId) {
		
		Connection conn = getConnection();
		
		Member mem = new MemberDao().selectMember(conn, userId);
		
		close(conn);
		
		return mem;
	}
	

	/**
	 * 4. 회원 정보 수정 후 수정된 회원 정보 재조회용 서비스
	 * @param m 	수정 내용들, 수정하고자 하는 회원 아이디가 담겨있는 객체
	 * @return		수정 완료 후 다시 조회된 회원 객체
	 */
	public Member updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) {
			commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
		
	}
	
	/**
	 * 5. 회원 비밀번호 변경 후 변경된 회원 재조회용 서비스
	 * @param userId	수정하고자 하는 회원 아이디
	 * @param userPwd	비밀번호 변경창에서 회원이 입력한 현재 비밀번호
	 * @param newPwd	비밀번호 변경창에서 회원이 입력한 변경할 비밀번호
	 * @return			변경완료된 회원 객체
	 */
	public Member updatePwdMember(String userId, String userPwd, String newPwd) {
		Connection conn = getConnection();
		
		Member updateMem = null;
		
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, newPwd);
		
		if(result > 0) {
			commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, userId);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
	}
	
	/**
	 * 6. 회원 탈퇴용 서비스
	 * @param userId	탈퇴하고자 하는 회원아이디
	 * @return			탈퇴 후 처리된 행의 갯수
	 */
	public int deleteMember(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
}
