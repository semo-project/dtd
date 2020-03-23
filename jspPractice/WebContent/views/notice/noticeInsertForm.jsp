<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);	//"2020-03-08"
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
	
	#insertForm>table{
		border:1px solid white;
	}
	
	#insertForm>table input {
		width:100%;
		box-sizing:border-box;
	}
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항 작성하기</h2>
		
		<form id="insertForm" action="<%= contextPath %>/insert.no" method="post">
			
			<table align="center">
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" name="title"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" value="<%= loginUser.getUserId() %>" name="writer" readonly></td>
					<td>작성일</td>
					<td><input type="date" name="date" value="<%= today %>" readonly></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea name="content" cols="60" rows="15" style="resize:none"></textarea>
					</td>
				</tr>
			</table>
			
			<br>
			<div class="btns" align="center">
				<button type="submit">등록하기</button>
			</div>
		
		</form>
	</div>
</body>
</html>