<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		background-color:black;
		width:600px;
		height:500px;
		color:white;
		margin-top:50px;
		margin-left:auto;
		margin-right:auto;
	}
	
	#enrollForm{
		width:100%;
		margin-left:auto;
		margin-right:auto;
	}
	
	#enrollForm td:nth-child(1){text-align:right}
	#enrollForm input{margin:3px}
	
	#joinBtn{background:yellowgreen;}

</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
		<br>
		<h2 align="center">회원 가입</h2>
		
		<form id="enrollForm" method="post" action="<%= request.getContextPath() %>/insert.me">
		
			<table>
				<tr>
					<td width="200px">* 아이디</td>
					<td><input type="text" name="userId" required></td>
					<td width="200px"><button type="button" id="idCheckBtn" onclick="">중복확인</button></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="userPwd" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td><input type="password" name="checkPwd" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" required></td>
					<td></td>
				</tr>
				<tr>
					<td>연락처 :</td>
					<td><input type="tel" name="phone" placeholder="(-없이)01012345678"></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일 :</td>
					<td><input type="email" name="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>주소 :</td>
					<td><input type="text" name="address"></td>
					<td></td>
				</tr>
				<tr>
					<td>취미 :</td>
					<td>
						<input type="checkbox" name="interest" value="운동" id="sports">
						<label for="sports">운동</label>
						
						<input type="checkbox" name="interest" value="등산" id="climbing">
						<label for="climbing">등산</label>
						
						<input type="checkbox" name="interest" value="낚시" id="fishing">
						<label for="fishing">낚시</label>
						
						<input type="checkbox" name="interest" value="요리" id="cooking">
						<label for="cooking">요리</label>
						
						<input type="checkbox" name="interest" value="게임" id="game">
						<label for="game">게임</label>
						
						<input type="checkbox" name="interest" value="기타" id="etc">
						<label for="etc">기타</label>
					</td>
					<td></td>
				</tr>
			</table>
			<br>
			
			<div class="btns" align="center">
			
				<button type="submit" id="joinBtn">가입하기</button>
				
			</div>
		
		</form>
	</div>
	
</body>
</html>