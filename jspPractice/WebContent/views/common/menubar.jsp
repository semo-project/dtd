<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");

	String msg = (String)session.getAttribute("msg");
	
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body{
		background:url('<%= contextPath %>/resources/images/eight_col_PNG2.png') no-repeat;
		background-size:cover;
	}
	
	/* 로그인 폼 관련 스타일 */
	#loginForm, #userInfo{float:right}
	.btns>button{border-radius:5px;}
	#enrollBtn, #mypageBtn{background-color:yellowgreen;}
	#loginBtn, #logoutBtn{background-color:orangered;}
	
	/* 메뉴바 영역 관련 스타일 */
	.navWrap{
		background-color:black;
		width:100%;
		height:50px;
	}
	
	.nav{
		width:600px;
		margin-right:auto;
		margin-left:auto;
		align:center;
	}
	
	.menu{
		color:white;
		font-size:20px;
		font-weight:bold;
		display:table-cell;
		width:150px;
		text-align:center;
		height:50px;
		vertical-align:middle;
	}
	
	.menu:hover{
		cursor:pointer;
		background-color:darkgray;
		color:orangered;
	}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	
	// var msg = "회원가입 성공";
	var msg = "<%= msg %>";
	
	$(function(){
		
		if(msg != "null"){
			alert(msg);
			// 세션에 담겨있는 메시지 한번만 출력하고 삭제하기
			<% session.removeAttribute("msg"); %>
		}
	});
	
	function loginValidate(){
		
		if($("#userId").val().trim().length == 0){
			alert("아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		}
		
		if($("#userPwd").val().trim().length == 0){
			alert("비밀번호를 입력해주세요");
			$("#userPwd").focus();
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>

	<h1 align="center">Hello!</h1>

	<!-- 로그인 폼 영역 -->
	<div class="loginArea">

		<% if (loginUser == null) { %>

		<form id="loginForm" action="<%=contextPath%>/login.me" method="post" onsubmit="return loginValidate();">

			<table>
				<tr>
					<th><label for="userId">아이디 :</label></th>
					<td><input type="text" name="userId" id="userId"></td>
				</tr>
				<tr>
					<th><label for="userPwd">비밀번호 :</label></th>
					<td><input type="password" name="userPwd" id="userPwd"></td>
				</tr>
			</table>

			<div class="btns" align="center">

				<button type="button" id="enrollBtn" onclick="enrollPage();">회원가입</button>
				<button type="submit" id="loginBtn">로그인</button>
			</div>

		</form>

		<% } else { %>

		<div id="userInfo">

			<label><%=loginUser.getUserName()%>님의 방문을 환영합니다.</label>

			<div class="btns" align="center">

				<button id="mypageBtn" onclick="myPage();">마이페이지</button>
				<button id="logoutBtn" onclick="logout();">로그아웃</button>

			</div>

		</div>

		<% } %>

	</div>
	
	<script>
		function logout(){
			location.href="<%= request.getContextPath()%>/logout.me";
		}
		
		function enrollPage(){
			location.href="<%= request.getContextPath()%>/memberEnrollForm.me";
		}
		
		function myPage(){
			location.href="<%= request.getContextPath()%>/myPage.me";
		}
		
		function goMain(){
			location.href="<%= request.getContextPath()%>";
		}
		
		function goNotice(){
			location.href="<%= request.getContextPath()%>/list.no";
		}
		
		function goBoard(){
			location.href="<%= contextPath %>/list.bo";
		}
	
	</script>

	<br clear="both">	<!-- 위에 띄어진 폼 태그 뒤에 하겠음..? -->
	
	<!-- 메뉴바 영역 -->
	<div class="navWrap">	<!-- 메뉴바 전체 감싸는 영역 -->
		
		<div class="nav">	<!-- 메뉴  감싸는 영역 -->
		
			<div class="menu" onclick="goMain();">HOME</div>
			<div class="menu" onclick="goNotice();">공지사항</div>
			<div class="menu" onclick="goBoard();">일반게시판</div>
			<div class="menu" onclick="">사진게시판</div>
			
		</div>
	
	</div>
	
</body>
</html>