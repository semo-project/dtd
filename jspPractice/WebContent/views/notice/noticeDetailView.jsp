<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer {
		width:800px;
		height:500px;
		background:black;
		color:white;
		margin:auto;
		margin-top:50px;
	}
	
	#detailArea>table{
		border:1px solid white;
	}
	
	#detailArea>table input {
		width:100%;
		box-sizing:border-box;
	}
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항 상세보기</h2>
		
		<table align="center" id="detailArea">
			<tr>
				<td>제목</td>
				<td colspan="3"><input type="text" name="title" value="<%=n.getNoticeTitle() %>" readonly></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" value="<%= n.getNoticeWriter() %>" name="writer" readonly></td>
				<td>작성일</td>
				<td><input type="date" name="date" value="<%= n.getNoticeDate() %>" readonly></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea name="content" cols="60" rows="15" style="resize:none" readonly><%= n.getNoticeContent() %></textarea>
				</td>
			</tr>
		</table>
			
		<br>
		<div class="btns" align="center">
			<% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
			<button type="button" onclick="updateForm();">수정하기</button>
			<!-- a 태그일 때 -->
			<!-- <a href=""<%=contextPath%>/updateForm.no?nno=<%= n.getNoticeNo() %>">수정하기</a> -->
			
			<button type="button" onclick="deleteNotice();">삭제하기</button>
			<% } %>
		</div>
		
	</div>
	
	<script>
		function updateForm(){
			location.href = "<%=contextPath%>/updateForm.no?nno=<%= n.getNoticeNo() %>";
		}
		
		function deleteNotice(){
			if(confirm("정말로 삭제하시겠습니까?")){
				location.href ="<%=contextPath%>/delete.no?nno=<%=n.getNoticeNo()%>";	
			}
		}
	</script>
</body>
</html>