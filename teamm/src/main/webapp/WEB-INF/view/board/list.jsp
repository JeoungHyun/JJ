<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>게시판 목록</title>
<script type="text/javascript">
	function listpage(page){
		document.searchform.pageNum.value = page;
		document.searchform.submit();
	}
</script>
<style>
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 80%;
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

</head>
<body>
<br><br><br><br><br><br>
<table id="customers" >
	<tr><td colspan="6" align="center" height="60px">
		<div style="display:inline;">
		<form action="list.dev?no=${no }" method="post" name="searchform" >
			<input type="hidden" name="pageNum" value="1">
			<select name="searchtype" style="width:100px;">
				<option value="">선택하세요</option>
				<option value="subject">제목</option>
				<option value="name">작성자</option>
				<option value="content">내용</option>
			</select>
			<script type="text/javascript">
				searchform.searchtype.value="${param.searchtype}";
			</script>
			<input type="text" name="searchcontent"
				value="${param.searchcontent}" style="width:250px;">
			<input type="submit" value="검색">
			<input type="button" value="전체게시물보기"
				onclick="location.href='list.shop'">
			</form></div></td></tr>			
	<c:if test="${listcount>0}">	<%-- 등록된 게시물이 있음 --%>
		<c:if test="${no } ==4">
		<tr><td colspan="6">QnA 게시판</td>
		</c:if>
		<tr><th width="5%">번호</th><th width="50%;">제목</th><th width="10%">글쓴이</th>
		<th width="5%">추천</th><th width="12%">기술</th><th width="8%">날짜</th></tr>
		<c:forEach var="board" items="${boardlist}">
			<tr><td>${boardno}</td>
				<c:set var="boardno"  value="${boardno-1}"/>
			<td style="text-align: left;">


			
			<a href="detail.dev?bno=${board.bno}&&no=${board.no}">${board.title}</a></td>
			<td>${board.name}</td>
			<fmt:formatDate var="regdate" value="${board.regdate}" pattern="yyyyMMdd"/>

			<td>추천수</td>
			
			<td>궁금한 기술</td>
			
			<td><c:if test="${today == regdate}">
			<fmt:formatDate value="${board.regdate}" pattern="HH:mm:ss"/>
			</c:if>
			<c:if test="${today != regdate}">
				<fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm"/>
			</c:if></td>
						
			</tr>
			</c:forEach>
			<%-- 페이지 출력 부분 --%>
			<tr><td colspan="6">
				<c:if test="${pageNum > 1}">
				<a href="javascript:listpage('${pageNum - 1}')">[이전]</a></c:if>
				<c:if test="${pageNum <= 1}">[이전]</c:if>
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a==pageNum}">[${a}]</c:if>
				<c:if test="${a!=pageNum}">
				<a href="javascript:listpage('${a}')">[${a}]</a></c:if>
			</c:forEach>
			<c:if test="${pageNum < maxpage}">
				<a href="javascript:listpage('${pageNum + 1}')">[다음]</a></c:if>
			<c:if test="${pageNum >= maxpage}">[다음]</c:if>
			</td></tr></c:if>
		<c:if test="${listcount == 0}">
			<tr><td colspan="6">등록된 게시물이 없습니다.</td></tr></c:if>
		<tr><td colspan="6" align="right">
		<a href="write.dev?no=${no }">[글쓰기]</a></td></tr>
</table>
</body>
</html>