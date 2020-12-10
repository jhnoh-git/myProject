<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.top{
		text-align:right;
	}
</style>
<title>자유게시판</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">자유게시판</h2>
	<h3 align="center">글 보기</h3>
	
	<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
		<%-- <tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">순번</td>
			<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">${freeVO.f_num}</td>
		</tr>--%>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">작성자</td>
			<td style="width:80%; border:1px solid; text-align:left; padding-left:10px">${freeVO.f_name}</td>
		</tr>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">제목</td>
			<td style="width:80%; border:1px solid; text-align:left; padding-left:10px">${freeVO.f_title}</td>
		</tr>
		
		<tr height="100">
			<td style="width:20%; border:1px solid; text-align:right; vertical-align:top; padding-right:10px; padding-top:10px; font-weight:bold;">내용</td>
			<td style="border:1px solid; text-align:left; padding-left:10px; vertical-align:top; padding-top:10px;">
				${freeVO.f_content}
			</td>
		</tr>
		
		<tr>
			<td style="border:1px solid; text-align:right; padding-right:10px; font-weight:bold;">날짜</td>
			<td style="border:1px solid; text-align:left; padding-left:10px;">${freeVO.f_date}</td>
		</tr>
		
		<tr>
			<td colspan="2" style="text-align:center; border:1px solid white; padding-top:10px">
				<input type="button" value="목록으로" onClick="history.back()">
				
					<c:if test="${(not empty login)&&(login==true)}">
						<input type="button" onclick="location.href='${contextPath}/free/freeModifyForm.do?f_num=${freeVO.f_num}'" value="수정하기">
						<input type="button" onclick="location.href='${contextPath}/free/freeDelete.do?f_num=${freeVO.f_num}'" value="삭제하기">
					</c:if>
			</td>
		</tr>
	</table>
	
</body>
</html>