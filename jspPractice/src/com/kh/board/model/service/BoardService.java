package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.PageInfo;

public class BoardService {

	/**
	 * 1. 일반 게시판 리스트 총 개수 조회용 서비스
	 * @return	게시판 리스트 총 개수
	 */
	public int getListCount() {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().getListCount(conn);
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 1_2. 일반 게시판 리스트 조회용 서비스
	 * @param pi	currentPage, boardLimit 값이 담겨있는 pageInfo 객체
	 * @return		현재 페이지에 보여져야 할 게시판 리스트
	 */
	public ArrayList<Board> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
}
