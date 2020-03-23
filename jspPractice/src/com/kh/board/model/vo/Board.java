package com.kh.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardId;		// 게시글 고유 번호
	private int boardType;		// 게시글 타입(1. 일반게시판 , 2. 사진게시판)
	private String category;	// 게시글 분류(조회시 카테고리명, 추가시 카테고리 번호)
	private String boardTitle;	// 게시글 제목
	private String boardContent;// 게시글 내용
	private String boardWriter;	// 게시글 작성자(조회시 작성자 이름, 조회시 작성자 번호)
	private int boardCount;		// 게시글 조회수
	private Date createDate;	// 게시글 작성일
	private Date modifyDate;	// 게시글 수정일
	private String status;		// 게시글 상태값(Y,N)

	public Board() {
		
	}

	public Board(int boardId, int boardType, String category, String boardTitle, String boardContent,
			String boardWriter, int boardCount, Date createDate, Date modifyDate, String status) {
		super();
		this.boardId = boardId;
		this.boardType = boardType;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardCount = boardCount;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	
	
	// 게시글 리스트 조회용 매개변수 생성자
	public Board(int boardId, String category, String boardTitle, String boardWriter, int boardCount, Date createDate) {
		super();
		this.boardId = boardId;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardCount = boardCount;
		this.createDate = createDate;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", boardType=" + boardType + ", category=" + category + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", boardCount="
				+ boardCount + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}
	
	
}
