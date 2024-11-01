<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<a href = "/spring/user/register">회원가입</a>
<a href = "/spring/user/login">로그인</a>
<a href = "/spring/user/memberContent">회원페이지</a>
<a href = "/spring/user/logout">로그아웃</a>
<a href = "/spring/guest/w">날씨 확인하러 가기</a>
<a href = "/spring/test/b">인천버스정보</a>
<a href = "/spring/guest/getList">방명록 리스트</a>
<%
String se = (String) session.getAttribute("id");
		
%>
<%=se %>
</body>
</html>
