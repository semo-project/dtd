<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board, com.kh.board.model.vo.PageInfo" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

   .outer{
      width:1000px;
      height:500px;
      background:black;
      color:white;
      margin:auto;
      margin-top:50px;
   }
   .listArea{
      border:1px solid white;
      text-align:center;
   }
   .searchArea{
      margin-top:50px;
   }
   .listArea>tbody>tr:hover{
      cursor:pointer;
      background:darkgray;
   }

</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판</h2>
		<br>
		
		<% if(loginUser != null) { %>
		<button onclick="location.href='<%= contextPath %>/insertForm.bo';">작성하기</button>
		<% } %>
		
		<!-- 게시판 리스트 영역  -->
		<table class="listArea" align="center">
			<thead>
				<tr>
					<th width="100">글번호</th>
					<th width="100">카테고리</th>
					<th width="300">글제목</th>
					<th width="100">작성자</th>
					<th width="100">조회수</th>
					<th width="150">작성일</th>
				</tr>
			</thead>
			<tbody>
				
				<% if(list.isEmpty()) { %>
				<tr>
					<td colspan="6">조회된 리스트가 없습니다.</td>
				</tr>
				<% } else { %>
					<% for(Board b : list) { %>
					<tr>
						<td><%= b.getBoardId() %></td>
						<td><%= b.getCategory() %></td>
						<td><%= b.getBoardTitle() %></td>
						<td><%= b.getBoardWriter() %></td>
						<td><%= b.getBoardCount() %></td>
						<td><%= b.getCreateDate() %></td>
					</tr>
					<% } %>
				<% } %>
			</tbody>
		
		</table>
		<br><br>
		
		<!-- 페이징바 영역 -->
		<div class="pagingArea" align="center">
			<!-- 맨 처음으로(<<) -->
			<button onclick="location.href='<%= contextPath %>/list.bo';"> &lt;&lt; </button>
			
			<!-- 이전 페이지로(<) -->
			<% if(currentPage == 1) { %>
			<button disabled> &lt; </button>
			<% } else { %>
			<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= currentPage - 1 %>';"> &lt; </button>
			<% } %>
			
			
			<!-- 페이지 목록 -->
			
			<% for(int p=startPage; p<endPage; p++) { %>
				<% if(currentPage == p) { %>
				<button disabled><%= p %></button>
				<% } else { %>
				<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= p %>';"><%= p %></button>
				<% } %>
			<% } %>
			
			
			
			<!-- 다음 페이지로(>) -->
			<% if(currentPage == maxPage) { %>
			<button disabled> &gt; </button>
			<% } else { %>
			<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= currentPage + 1 %>';"> &gt; </button>
			<% } %>
			
			
			<!-- 맨 마지막으로(>>) -->
			<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= maxPage %>';"> &gt;&gt; </button>
		</div>
	</div>
</body>
</html>