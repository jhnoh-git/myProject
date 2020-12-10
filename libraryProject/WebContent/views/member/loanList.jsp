<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<c:set var="now" value="<%= new Date() %>" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="n_date" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.top{
		text-align:right;
	}
</style>
<title>도서 대출 내역</title>
</head>
<body>
	<div class="top">
		<jsp:include page="/views/main/main_top.jsp" />
	</div>
		<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 style="text-align:center;">도서 대출 내역</h2>
	<table style="width:80%; margin:auto; text-align:center; border-top:1px solid;">
		<tr>
			<td style="width:10%; border-bottom:1px solid; padding:8px; font-weight:bold;">책번호</td>
			<td style="width:35%; border-bottom:1px solid; padding:8px; font-weight:bold;">책이름</td>
			<td style="width:15%; border-bottom:1px solid; padding:8px; font-weight:bold;">대출일자</td>
			<td style="width:15%; border-bottom:1px solid; padding:8px; font-weight:bold;">반납예정일</td>
			<td style="width:15%; border-bottom:1px solid; padding:8px; font-weight:bold;">반납일자</td>
			<td style="width:10%; border-bottom:1px solid; padding:8px; font-weight:bold;">상태</td>
		</tr>
			
		<c:if test="${empty list}">
			<tr>
				<td colspan="6" style="text-align:center; border-bottom:1px solid; padding:8px;">
					대출 내역이 없습니다.
			</tr>
		</c:if>
		
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="list">
				<tr>
					<td style="border-bottom:1px dashed; padding:8px;">${list.b_num}</td>
					<td style="border-bottom:1px dashed; padding:8px;"><a href="${contextPath}/books/bookView.do?b_num=${list.b_num}">${list.b_title}</a></td>
					<td style="border-bottom:1px dashed; padding:8px;">${list.loanDate}</td>
					<td style="border-bottom:1px dashed; padding:8px;">${list.returnexpected}</td>	
					<td style="border-bottom:1px dashed; padding:8px;">${list.returnDate}</td>
					
					<c:if test="${(!empty list.returnDate)||(list.returnDate ne null)}">
						<td style="border-bottom:1px dashed; padding:8px;">반납 완료</td>	
					</c:if>
					<c:if test="${(empty list.returnDate)||(list.returnDate eq null)}">
						<c:if test="${(list.returnexpected) <= (n_date)}">
							<td style="border-bottom:1px dashed black; padding:8px; font-weight:bold; color:red;">대출중<br>반납요망</td>	
						</c:if>
						<c:if test="${(list.returnexpected) > (n_date)}">
							<td style="border-bottom:1px dashed; padding:8px;">대출중</td></c:if>	
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>