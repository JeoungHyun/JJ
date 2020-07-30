<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
<h2>환영합니다. ${sessionScope.loginUser.name }님</h2><hr>
<a href="myfeed.shop?name=${loginUser.name }">My Feed</a><br>


<a href="myfeed.shop?name=duck_bae"><h4>김덕배님 피드 보기</h4></a>
<a href="myfeed.shop?name=soon_ja"><h4>김순자님 피드 보기</h4></a>
</body>
</html>