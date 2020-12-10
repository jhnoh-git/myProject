<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${(userInfo!=null) && (userInfo.user_id != null)}">
			<h5 style="color:black; display:inline;">${userInfo.user_id}님 환영합니다.</h5>
			<%--<a href="${contextPath}/member/myPage.do"><img src="${contextPath}/icon/user.png" id="myPage" style="width:2%; height:5%;"></a>
			 --%><a href="${contextPath}/member/logout.do"><img src="${contextPath}/icon/logout.png" id="logout" style="width:2%; height:5%;"></a>
		</c:when>
		<c:otherwise>
			<a href="${contextPath}/member/loginForm.do"><img src="${contextPath}/icon/login.png" id="login" style="float:right; width:2%; height:5%;"></a>
		</c:otherwise>
	</c:choose>
</body>
</html>