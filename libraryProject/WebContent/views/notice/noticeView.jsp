<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>공지사항</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">공지사항</h2>
	<h3 align="center">글 보기</h3>
	
	<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">작성자</td>
			<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">${noticeVO.n_name}</td>
		</tr>

		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">제목</td>
			<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">${noticeVO.n_title}</td>
		</tr>
		
		<tr height="100">
			<td style="width:20%; border:1px solid; text-align:right; vertical-align:top; padding-right:10px; padding-top:10px; font-weight:bold;">내용</td>
			<td style="border:1px solid; text-align:left; padding-left:10px; vertical-align:top; padding-top:10px;">
				${noticeVO.n_content}
			</td>
		</tr>
		<%-- 첨부파일 유무에 따라서 펼치기 숨기기 기능. --%>
		<c:if test="${(not empty noticeVO.imageFileName)||(noticeVO.imageFileName ne null)}">
			<tr>
				<td style="text-align:right; font-weight:bold; padding-right:10px;">첨부파일 :</td>
					<td style="border:1px solid;">				
						<img id="preview" src="${contextPath}/download/n_image.do?n_num=${noticeVO.n_num}&imageFileName=${noticeVO.imageFileName}" width="100" height="100" />
					</td>
				</tr>	
		</c:if>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">날짜</td>
			<td style="width:80%; border:1px solid; text-align:left; padding-left:10px">${noticeVO.n_date}</td>		
		</tr>
			
		<tr>
			<td colspan="3" style="text-align:center; border:1px solid white; padding-top:10px;">
				<input type="button" value="목록으로" onclick="history.back()">
				<c:if test="${(not empty msg)&&(msg==true)}">
				<input type="button" value="수정하기" onclick="location.href='${contextPath}/notice/noticeModifyForm.do?n_num=${noticeVO.n_num}'">
				<input type="button" value="삭제하기" onclick="location.href='${contextPath}/notice/noticeDelete.do?n_num=${noticeVO.n_num}'">
				</c:if>
			</td>
		</tr>
	</table>
	
</body>
</html>