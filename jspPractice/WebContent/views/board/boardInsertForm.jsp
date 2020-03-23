<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer {
		width:900px;
		height:500px;
		background:black;
		color:white;
		margin:auto;
		margin-top:50px;
	}
	
	#insertForm>table{
		border:1px solid white;
	}
	
	#insertForm>table input, #insertForm>table textarea{
		width:100%;
		box-sizing:border-box;
	}
</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">게시판 작성하기</h2>
		<br>
		
		<form id="insertForm" action>
			
			<table align="center">
				<tr>
					<th width="50">분야</th>
					<td width="500">
						<select name="category">
							<option value="">공통</option>
							<option value="">운동</option>
							<option value="">등산</option>
							<option value="">게임</option>
							<option value="">낚시</option>
							<option value="">요리</option>
							<option value="">기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="15" name="content" style="resize:none;"></textarea>
				</tr>
				<input type="hidden" name="writer" value="<%= loginUser.getUserNo() %>">
			</table>
			<br>
			
			<div class="btns" align="center">
				<button type="reset">취소하기</button>
				<button type="submit">작성하기</button>
			</div>
		</form>
	</div>
</body>
</html>