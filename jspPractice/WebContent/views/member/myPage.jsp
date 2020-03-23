<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	Member mem = (Member)request.getAttribute("mem");

	String userId = mem.getUserId();
	String userPwd = mem.getUserPwd();
	String userName = mem.getUserName();
	String phone = (mem.getPhone() != null) ? mem.getPhone() : "";
	String email = (mem.getEmail() != null) ? mem.getEmail() : "";
	String address = (mem.getAddress() != null) ? mem.getAddress() : "";
	
	String[] checkedInterest = new String[6];	// 값이 있는 인덱스에 checked 문자열 넣을 예정
	
	if(mem.getInterest() != null){
		String[] interests = mem.getInterest().split(",");
		
		for(int i=0; i<interests.length; i++){
			switch(interests[i]){
			case "운동" : checkedInterest[0] = "checked"; break;
			case "등산" : checkedInterest[1] = "checked"; break;
			case "낚시" : checkedInterest[2] = "checked"; break;
			case "요리" : checkedInterest[3] = "checked"; break;
			case "게임" : checkedInterest[4] = "checked"; break;
			case "기타" : checkedInterest[5] = "checked"; break;
			}
		}
	}
%>
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
	
	#updateForm{
		width:100%;
		margin-left:auto;
		margin-right:auto;
	}
	
	#updateForm td:nth-child(1){text-align:right}
	#updateForm input{margin:3px}
	
	#updateBtn{background:yellowgreen;}
	#pwdUpdateBtn{background:orangered;}
	#deleteBtn{background:yellow;}

</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>
	
		<div class="outer">
	
		<br>
		<h2 align="center">마이페이지</h2>
		
		<form id="UpdateForm" method="post" action="<%= request.getContextPath() %>/update.me">
		
			<table>
				<tr>
					<td width="200px">* 아이디</td>
					<td><input type="text" name="userId" value="<%=userId %>" readonly></td>
					<td width="200px"></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" value="<%=userName %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>연락처 :</td>
					<td><input type="tel" name="phone" value="<%=phone %>" placeholder="(-없이)01012345678"></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일 :</td>
					<td><input type="email" name="email" value="<%=email%>"></td>
					<td></td>
				</tr>
				<tr>
					<td>주소 :</td>
					<td><input type="text" name="address" value="<%=address%>"></td>
					<td></td>
				</tr>
				<tr>
					<td>취미 :</td>
					<td>
						<input type="checkbox" name="interest" value="운동" id="sports" <%= checkedInterest[0] %>>
						<label for="sports">운동</label>
						
						<input type="checkbox" name="interest" value="등산" id="climbing" <%= checkedInterest[1] %>>
						<label for="climbing">등산</label>
						
						<input type="checkbox" name="interest" value="낚시" id="fishing" <%= checkedInterest[2] %>>
						<label for="fishing">낚시</label>
						
						<input type="checkbox" name="interest" value="요리" id="cooking" <%= checkedInterest[3] %>>
						<label for="cooking">요리</label>
						
						<input type="checkbox" name="interest" value="게임" id="game" <%= checkedInterest[4] %>>
						<label for="game">게임</label>
						
						<input type="checkbox" name="interest" value="기타" id="etc" <%= checkedInterest[5] %>>
						<label for="etc">기타</label>
					</td>
					<td></td>
				</tr>
			</table>
			<br>
			
			<div class="btns" align="center">
			
				<button type="submit" id="updateBtn">수정하기</button>
				<button type="button" id="pwdUpdateBtn" onclick="updatePwd();">비밀번호 변경</button>
				<button type="button" id="deleteBtn" onclick="deleteMember();">탈퇴하기</button>
				
			</div>
		
		</form>
	</div>
	
	<script>
		function updatePwd(){
			//window.open("<%=request.getContextPath()%>/views/member/updatePwdForm.jsp", "비밀번호 변경창", "width=500, height=300");
			window.open("<%= request.getContextPath()%>/updatePwdForm.me", "비밀번호 변경창", "width=500, height=300");
		}
		
		function deleteMember(){
			var pwd = prompt("현재 비밀번호를 입력하세요");
			
			if(pwd == "<%= userPwd %>"){
				var bool = confirm("탈퇴하시겠습니까?");
				
				if(bool){
					location.href = "<%= request.getContextPath() %>/delete.me";
				} else{
					alert("취소되었습니다.");
				}
			} else{
				alert("비밀번호를 잘못 입력하였습니다.");
			}
		}
	</script>
	
</body>
</html>