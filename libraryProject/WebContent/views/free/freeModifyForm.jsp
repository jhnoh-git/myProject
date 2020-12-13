<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Date" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%= new Date()%>" />
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
	<h3 align="center">글 수정</h3>
	
	<form action="${contextPath}/free/freeModify.do" method="post">
	<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">작성자</td>
			<td style="width:80%; border:1px solid black; text-align:left; padding-left:10px; color:gray;">
				${freeVO.f_name}
				<input type="hidden" name="f_name" value="${freeVO.f_name}">
				<input type="hidden" name="f_num" value="${freeVO.f_num}">
			</td>
		</tr>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">제목</td>
			<td style="width:80%; border:1px solid; text-align:left; padding-left:10px;">
				<input type="text" name="f_title" value="${freeVO.f_title}" style="border:none; font-size:15px;">
			</td>
		</tr>
		
		<tr height="100">
			<td style="width:20%; border:1px solid; text-align:right; vertical-align:top; padding-top:10px; padding-right:10px; font-weight:bold;">내용</td>
			<td style="text-align:left; padding-left:10px;">
				<textarea rows="8" name="f_content" style="text-align:left; font-size:15px; border:none; width:99%;">${freeVO.f_content}</textarea>
			</td>
		</tr>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">날짜</td>
			<td style="width:80%; border:1px solid black; text-align:left; padding-left:10px; color:gray;">
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="f_date" />
				<input type="text" name="f_date" value="${f_date}" disabled style="border:none;">
				<input type="hidden" name="f_date" value="${f_date}">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" style="padding-top:10px; border:1px solid white; text-align:center;">		
				<input type="button" value="뒤로가기" onClick="history.back()">
				<input type="submit" value="수정완료">
				<input type="button" value="삭제하기" onClick="location.href='${contextPath}/free/freeDelete.do?f_num=${freeVO.f_num}'">
			</td>		
		</tr>
	</table>
</form>

</body>
</html>