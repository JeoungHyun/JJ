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
<title>게시글 작성</title>
</head>
<body>
<br><br><br><br><br><br><br><br>
<form:form modelAttribute="board" action="write.dev"
	enctype="multipart/form-data" name="f">

	<table id="customers"><tr><td align="center">글쓴이</td><td><form:input path="name" size="100%"/>
	</td></tr>

	<tr><td align="center">제목</td><td><form:input path="title" size="100%;" />
	</td></tr>
	<tr><td align="center">내용</td><td>
	<form:textarea path="content" rows="15" cols="80"/>
	<script>CKEDITOR.replace("content",{
		filebrowserImageUploadUrl: "imgupload.dev"
	});
	</script>
	<font color="red"><form:errors path="content"/></font></td></tr>
	
	<tr><td colspan="2">
		<a href="javascript:document.f.submit()">[게시글등록]</a>
		<a href="list.dev?no=${no }">[게시글목록]</a>
	</td></tr>
	</table>
	<input type="hidden" name="no" value="${no }">
</form:form>
</body>
</html>