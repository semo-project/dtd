package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// -------------------- 페이징 처리 -------------------------
		int listCount;			// 총 게시글 개수
		int currentPage;		// 현재 요청한 페이지 번호
		int startPage;			// currentPage의 시작 페이지 번호
		int endPage;			// currentPage의 마지막 페이지 번호
		int maxPage;			// 전체 페이지의 마지막 페이지 번호
		
		int pageLimit;			// 한 페이지 하단에 보여질 페이지 최대 개수
		int boardLimit;			// 한 페이지내에 보여질 게시글 최대 개수
		
		// * listCount : 총 개시글 개수
		listCount = new BoardService().getListCount();
		
		// * currentPage : 현재 요청한 페이지 번호
		currentPage = 1;
		
		// 페이지 전환시 전달받은 페이지가 있을 경우 전달받은 페이지를 currentPage로!
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// * pageLimit : 한 페이지 하단에 보여질 페이지 최대 개수
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글 최대 개수
		boardLimit = 10;
		
		// * maxPage : 총 페이지 수
		/*
		 * ex) boardLimit : 10
		 * 100.0 / 10 = 10.0		==> 10 페이지
		 * 101.0 / 10 = 10.1		==> 11 페이지
		 * 105.0 / 10 = 10.5		==> 11 페이지
		 * 109.0 / 10 = 10.9		==> 11 페이지
		 * 
		 * 총 개시글 개수(listCount)(실수) / boardLimit 올림한 값 = maxPage
		 * 
		 */
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		// * startPage : 현재 페이지에 보여지는 페이징바의 시작 번호
		/*
		 * ex) pageLimit : 10
		 * startPage : 1, 11, 21, 31, ...	--> n * 10 + 1
		 * 
		 * currentPage = 1					--> 0 * 10 + 1
		 * currentPage = 5					--> 0 * 10 + 1
		 * currentPage = 10					--> 0 * 10 + 1
		 * 
		 * currentPage = 11					--> 1 * 10 + 1
		 * currentPage = 15					--> 1 * 10 + 1
		 * currentPage = 20					--> 1 * 10 + 1
		 * 
		 * currentPage = 1 ~ 10				--> n = 0
		 * currentPage = 11 ~ 20			--> n = 1
		 * 
		 *												0~9 10~19		  0 또는 1 
		 * 										n = (currentPage - 1) / pageLimit
		 * 
		 * startPage = (currentPage - 1) / pageLimit + 1
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		
		// * endPage : 현재 페이지에 보여지는 페이징바의 마지막 번호
		/*
		 * ex) pageLimit : 10
		 * startPage : 1		--> endPage : 10
		 * startPage : 11		--> endPage : 20
		 */
		endPage = startPage + pageLimit - 1;
		
		// 단, maxPage가 고작 13 이라면 endPage를 maxPage로 바꿔주어야 한다.
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		
		// 구해진 페이지 정보들 PageInfo 객체에 담기
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
		
		
		// 현재 페이지에 보여질 게시글 리스트 조회하기
		/*
		 SELECT *
		 FROM(SELECT ROWNUM RNUM, A.*
		    	FROM(SELECT BID, CNAME, BTITLE, USER_NAME, BCOUNT, CREATE_DATE
		            	FROM BOARD
		            	JOIN CATEGORY USING(CID)
		            	JOIN MEMBER ON(BWRITER=USER_NO)
		            	WHERE BTYPE=1 AND B.STATUS='Y'
		            	ORDER BY BID DESC) A)
		WHERE ROWNUM BETWEEN ? AND ?;
		*/
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
