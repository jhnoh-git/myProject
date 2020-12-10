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
	<%--td{
		font-weight:bold;
	}--%>
</style>
<title>도서 관리 프로그램</title>
</head>
<body>
	<div class="top">
	<jsp:include page="main_top.jsp" />
	</div>
	<%--<br> --%>
	<jsp:include page="main_menu.jsp" />
	
	<table style="width:100%; height:100%;">
		<%-- 공지사항 메뉴 가져오기 (최근 5개) --%>

		<tr>
			<td>
				<table style="border-collapse:collapse; width:80%; margin-top:20px; margin-left:auto; margin-right:auto;">
					<tr>
						<td colspan="2" style="border-bottom:1px solid black; font-weight:bold; font-size:20px; margin-left:3px;">
							공지사항
						</td>
						<td style="border-bottom:1px solid black; text-align:right; padding-right:10px;">
							<a style="font-size:5px; color:gray;" href="${contextPath}/notice/noticeList.do">more</a>
						</td>
					</tr>
					<tr height="30">
						<td style="border-bottom:1px solid black; width:40%; text-align:center; font-weight:bold;">
							제목
						</td>
						<td style="border-bottom:1px solid black; width:20%; text-align:center; font-weight:bold;">
							작성자
						</td>
						<td style="border-bottom:1px solid black; width:40%; text-align:center; font-weight:bold;">
							날짜
						</td>
					</tr>
					<c:if test="${n_list != null}">
					<c:forEach items="${n_list}" var="n_list" begin="0" end="5">
						<tr height="30">
							<td style="border-bottom:1px dashed; text-align:center;">
								<a style="text-decoration:none; color:black;" href="${contextPath}/notice/noticeView.do?n_num=${n_list.n_num}">${n_list.n_title}</a>
							</td>
							<td style="border-bottom:1px dashed; text-align:center;">${n_list.n_name}</td>
							<td style="border-bottom:1px dashed; text-align:center;">${n_list.n_date}</td>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${n_list == null}">
						<tr>
							<td>공지사항 내역이 없습니다.</td>
						</tr>
					</c:if>
				</table>
			</td>
		</tr>
	
		<%-- 이달의 책 가져오기 (10개) --%>
		<tr>
			<td align="center">
				<table style="border-collapse:collapse; width:80%; margin-top:40px; margin-left:auto; margin-right:auto;">
					<tr>
						<td colspan="4" style="border-bottom:1px solid black; font-weight:bold; font-size:20px; margin-left:3px;">
							이달의 책
						</td>
					</tr>
					
					<c:forEach items="${b_list}" var="b_list" begin="0" end="9">
						<table style="text-align:center; display:inline;">
							<tr>
								<td>
									<img src="${contextPath}/download/b_image.do?b_imageName=${b_list.b_imageName}&b_num=${b_list.b_num}" id="preview" width="100px" height="100px" border="1">
								</td>
							</tr>
							<tr>
								<td>${b_list.b_title}</td>
							</tr>
							<tr>
								<c:if test="${b_list.b_state == 1}">
									<td>대출 가능</td>
								</c:if>
								<c:if test="${b_list.b_state == 0}">
									<td color="red">대출 불가능</td>
								</c:if>
							</tr>
						</table>
							
					</c:forEach>
					
					</tr>
					<%-- 
					<c:forEach items="${b_list}" var="b_list" begin="6" end="10">
						<tr>
							<td>${b_list}</td>
						</tr>
					</c:forEach>--%>
				</table>
			
	</table>
</body>
</html>