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
<title>도서정보_${vo.b_title}</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">도서 정보</h2>
	<h3 align="center">${vo.b_title}</h3>
	
	<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">책 번호</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:12px;">
				${vo.b_num}
			</td>
			<td rowspan="4" align="center">
				<img src="${contextPath}/download/b_image.do?b_imageName=${vo.b_imageName}" width="100" height="150">
			</td>
		</tr>
			
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">책 제목</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				${vo.b_title}
			</td>
		</tr>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">저자</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				${vo.b_name}
			</td>
		</tr>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">발행일</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				${vo.b_date}
			</td>
		</tr>
		
		<tr height="100">
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">줄거리</td>
			<td colspan="2" style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				${vo.b_content}
			</td>
		</tr>
		
		<tr>
			<td colspan="3" style="text-align:center; border:1px solid white; padding-top:10px;">
				<input type="button" value="뒤로가기" onclick="history.back()" >
				<c:choose>
					<c:when test="${vo.b_state==0}">
						<c:if test="${userInfo.user_id eq vo.id}">
							<input type="button" value="반납하기" onclick="location.href='${contextPath}/books/bookReturn.do?b_num=${vo.b_num}&id=${userInfo.user_id}&b_title=${vo.b_title}'">
						</c:if>
						
						<c:if test="${userInfo.user_id ne vo.id}">
							<input type="button" value="대출불가" disabled style="color:red;">
						</c:if>
					</c:when>
					
					<c:when test="${vo.b_state==1}">
						<input type="button" value="대출신청" onclick="location.href='${contextPath}/books/bookLoan.do?id=${id}&b_num=${vo.b_num}&b_title=${vo.b_title}&next_page=/books/bookView.do?b_num=${vo.b_num}'">
					</c:when>
				</c:choose>
				<c:if test="${userInfo.user_id eq 'admin'}">
					<input type="button" value="수정하기" onclick="location.href='${contextPath}/books/bookModifyForm.do?b_num=${vo.b_num}'">
				</c:if>
			</td>
		</tr>
	</table>
	
</body>
</html>