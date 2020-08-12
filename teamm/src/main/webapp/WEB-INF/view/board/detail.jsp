<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 60%;
  margin: auto;
  
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
<style type="text/css">
	.leftcol {text-align: left; vertical-align: top;}
	.lefttoptable{
		height: 250px;	border-width: 0px;
		text-align: left;	vertical-align: top;	padding: 0px;
	}
	input[type=text]:focus {
  border: 3px solid #555;
}
</style>
</head>
<body>
<br><br><br><br><br>
<table id="customers"><tr><td colspan="2" align="center">QnA 게시판</td></tr>
	<tr><td width="15%" align="center">글쓴이</td>
		<td width="85%" class="leftcol">${board.name}</td></tr>
	<tr><td align="center">제목</td><td class="leftcol">${board.title}</td></tr>
	<tr><td align="center">내용</td><td class="leftcol">
		<table class="lefttoptable">
		<tr><td class="leftcol lefttoptable">${board.content}</td></tr>
		</table></td></tr>

	<tr><td colspan="2">
		
		<a href="update.shop?num=${board.no}">[수정]</a>
		<a href="delete.dev?no=${board.no}&&bno=${board.bno}">[삭제]</a>
		<a href="list.dev?no=${no }">[게시물목록]</a>
	</td></tr>
</table>
<br><br>
<form action="reply.dev">
<table id ="customers">

<tr><td colspan="3" align="center"> 댓글란</td></tr>
<tr><td width="8%">글쓴이</td><td colspan="2">내용</td></tr>
<tr><td width="8%"><input type="text" name="name" id="name"></td><td>내용</td><td width="10%"><input type="submit" value="등록"></td></tr>
</table>
</form>
</body>
</html>