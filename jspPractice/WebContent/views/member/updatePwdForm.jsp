<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	//null / 성공적으로 비밀번호를 변경하였습니다. / 비밀번호 변경에 실패하였습니다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	var msg = "<%= msg %>";
	
	$(function(){
		if(msg != "null"){
			alert(msg);
		} 
		
		if(msg == "변경 성공"){
			window.close();
		}
	});
</script>
</head>
<body>
	
	<b>비밀번호 변경</b>
	<br>
	
	<form id="updatePwdForm" action="<%= request.getContextPath() %>/updatePwd.me" method="post">
	
		<table>
			<tr>
				<td>현재 비밀번호</td>
				<td><input type="password" name="userPwd"></td>
			</tr>
			<tr>
				<td>변경할 비밀번호</td>
				<td><input type="password" name="newPwd"></td>
			</tr>
			<tr>
				<td>변경할 비밀번호</td>
				<td><input type="password" name="checkPwd"></td>
			</tr>
		
		</table>
		
		<br><br>
		
		<div class="btns" align="center">
			<button type="submit" onclick="return pwdValidate();">변경하기</button>
		</div>
	
	</form>
	
	<script>
		function pwdValidate(){
			
			// 각 input요소 가져오기
			var userPwd = $("input[name='userPwd']");
			var newPwd = $("input[name='newPwd']");
			var checkPwd = $("input[name='checkPwd']");
			
			// 세 input 요소 중 하나라도 누락되지 않았는지 검사
			if(userPwd.val().trim() == "" || newPwd.val().trim() == "" || checkPwd.val().trim() == ""){
				alert("모든 비밀번호를 입력해주세요");
				return false;
			}
			
			if(newPwd.val() != checkPwd.val()){
				alert("비밀번호가 다릅니다. 다시 입력해주세요");
				checkPwd.focus();
				return false;
			}
			
			return true;
		}
	
	</script>
</body>
</html>