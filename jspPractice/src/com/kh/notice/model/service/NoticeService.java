package com.kh.notice.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	/**
	 * 1. 공지사항 리스트 조회용 서비스
	 * @return 조회된 공지사항 리스트가 담겨 있는 ArrayList 객체
	 */
	public ArrayList<Notice> selectList(){
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(conn);
		
		// 단순히 select(조회)이기 때문에 commit, rollback 할 것 없다.
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 2. 공지사항 작성용 서비스
	 * @param n		글 제목, 작성자 아이디, 글 내용이 담겨있는 Notice 객체
	 * @return		처리된 행의 갯수
	 */
	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 3. 공지사항 상세보기용 서비스
	 * @param nno	선택된 공지사항 글 번호
	 * @return		해당 번호의 조회된 공지사항 Notice 객체
	 */
	public Notice selectNotice(int nno) {
		
		Connection conn = getConnection();
		
		// 1. 해당 공지사항 조회수 증가시키는 dao
		int result = new NoticeDao().increaseCount(conn, nno);
		
		Notice n = null;
		
		if(result > 0) {
			commit(conn);
			
			// 2. 해당 공지사항 조회하는 dao
			n = new NoticeDao().selectNotice(conn, nno);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return n;
		
	}
	
	/**
	 * 4. 수정용 공지사항 조회 서비스
	 * @param nno	수정하고자 하는 공지사항 글 번호
	 * @return		조회된 공지사항 객체
	 */
	public Notice selectUpdateNotice(int nno) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, nno);
		
		close(conn);
		
		return n;
	}
	
	/**
	 * 5. 공지사항 수정용 서비스
	 * @param n		수정할 제목, 수정할 내용, 수정하고자 하는 공지사항 글 번호 담겨있는 객체
	 * @return		처리된 행의 개수
	 */
	public int updateNotice(Notice n) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 6. 공지사항 삭제용 서비스
	 * @param nno	삭제하고자 하는 공지사항 글 번호
	 * @return		처리된 행의 개수
	 */
	public int deleteNotice(int nno) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, nno);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
