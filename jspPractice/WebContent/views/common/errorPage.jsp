<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)request.getAttribute("msg");	// getAttribute는 무조건 object로 리턴해서 강제형변환
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@ include file="menubar.jsp" %>
	
	<h1 align="center" style="color:red;"><%= message %></h1>
	
</body>
</html>