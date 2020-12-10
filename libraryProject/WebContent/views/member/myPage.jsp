<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.top{
		text-align:right;
	}
</style>
<title>정보 수정하기</title>
</head>
<body>
	<div class="top">
	<jsp:include page="/views/main/main_top.jsp" />
	</div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 style="text-align:center;">정보 수정하기</h2>
	<form action="${contextPath}/member/memberInfoModify.do" method="post">
		<table style="width:100%; height:100%; text-align:center;">
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">아이디</td>
				<td style="text-align:left; width:55%;">${userInfo.user_id}
					<input type="hidden" name="user_id" value="${userInfo.user_id}">
				</td>
			</tr>
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">비밀번호</td>
				<td style="text-align:left; width:55%;"><input type="password" id="user_pw" name="user_pw"></td>
			</tr>
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">성명</td>
				<td style="text-align:left; width:55%;"><input type="text" id="user_name" name="user_name" value="${userInfo.user_name}" width="auto"></td>
			</tr>
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">생년월일</td>
				<td style="text-align:left; width:55%;">
					<input type="date" id="user_birth" name="user_birth" value="${userInfo.user_birth}">
				</td>
			</tr>
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">휴대전화</td>
				<td style="text-align:left; width:55%;"><input type="text" id="user_phone" name="user_phone" value="${userInfo.user_phone}" width="auto"></td>
			</tr>
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">e-mail</td>
				<td style="text-align:left; width:55%;">
					<input type="text" id="user_email" name="user_email" value="${fn:substringBefore(userInfo.user_email, '@')}" width="auto">
					<select name="email_address">
						<c:choose>
							<c:when test="${fn:substringAfter(userInfo.user_email, '@') eq 'daum.net'}">
								<option value="@daum.net" selected="selected">@daum.net</option>
								<option value="@naver.com">@naver.com</option>
								<option value="@google.com">@google.com</option>
								<option value="직접입력"></option>
							</c:when>
							<c:when test="${fn:substringAfter(userInfo.user_email, '@') eq 'naver.com'}">
								<option value="@daum.net">@daum.net</option>
								<option value="@naver.com"  selected="selected">@naver.com</option>
								<option value="@google.com">@google.com</option>
								<option value="직접입력"></option>
							</c:when>
							<c:when test="${fn:substringAfter(userInfo.user_email, '@') eq 'google.com'}">
								<option value="@daum.net">@daum.net</option>
								<option value="@naver.com">@naver.com</option>
								<option value="@google.com" selected="selected">@google.com</option>
								<option value="직접입력"></option>
							</c:when>
						</c:choose>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정하기">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>