<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<c:if test="${msg=='false'}"> 
		<script>
			window.onload = function()
			{
				result();
			}
			function result(){
				alert("아이디나 비밀번호가 틀립니다. 다시 로그인해주세요.");
			}
		</script>
	</c:if>
	<style>
		td{
			font-weight:bold; 	
		}
	</style>
<title>로그인</title>
</head>
<body>
	<jsp:include page="/views/main/main_top.jsp" />
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 style="text-align:center;">로그인</h2>
	<form action="${contextPath}/member/login_chk.do" method="post">
		<table style="width:100%; height:100%;">
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">아이디</td>
				<td style="text-align:left; width:55%;"><input type="text" name="user_id"></td>
				<td><input type="hidden" id="next_page" name="next_page" value="<%=request.getParameter("next_page") %>" />
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">비밀번호</td>
				<td style="text-align:left; width:55%;"><input type="password" name="user_pw"></td>
			</tr>
		
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" value="로그인">
					<input type="button" value="회원가입" onclick="location.href='${contextPath}/member/memberJoinForm.do'">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>