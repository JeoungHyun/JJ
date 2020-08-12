<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 등록</title>
</head><body>
<h2>사용자 등록</h2>
<form:form modelAttribute="user" method="post" action="join.sns">
	<%-- globalErrors: errors.reject("error.input.user") 에러 --%>
	<spring:hasBindErrors name="user"> 
		<font color="red">
			<c:forEach items="${errors.globalErrors}" var="error">
				<spring:message code="${error.code}"/>
			</c:forEach>
		</font> 
	</spring:hasBindErrors>
	<table border="1" style="border-collapse: collapse;">
		<tr height="40px"><td>닉네임</td><td><form:input path="name"/>
		<font color="red"><form:errors path="name"/></font></td></tr>
		<tr height="40px"><td>아이디</td><td><form:input path="id"/>
		<font color="red"><form:errors path="id"/></font></td></tr>
		<tr height="40px"><td>비밀번호</td><td><form:password path="password"/>
		<font color="red"><form:errors path="password"/></font></td></tr>
		<tr height="40px"><td>이메일</td><td><form:input path="email"/>
		<font color="red"><form:errors path="email"/></font></td></tr>
		<tr height="40px">
		<td colspan="2" align="center">
			<input type="submit" value="등록">
			<input type="reset" value="초기화">
		</td></tr>
	</table>
</form:form>
</body>
</html>