<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	.top{ 
		text-align:right
	}
	.text_center{
		text-align:center;
		font-size:20px;
	}
</style>
<c:if test="${msg==true}">
	<script type="text/javascript">
		alert("대출이 완료 되었습니다.");
	</script>
</c:if>
<title>도서 검색</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp"/></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">도서목록</h2>
	
	<table style="width:80%; margin:auto; text-align:center; border-top:1px solid;">
		<tr>
			<td width="10%" style="border-bottom:1px solid; padding:8px;"><b>책 번호</b></td>
			<td colspan="2" width="40%" style="border-bottom:1px solid; padding:8px;"><b>제목</b></td>
			<td width="30%" style="border-bottom:1px solid; padding:8px;"><b>저자</b></td>
			<td width="20%" style="border-bottom:1px solid; padding:8px;"><b>대출 상태</b></td>
		</tr>
		
		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="5" style="border:1px solid; text-align:center;">
						등록된 도서가 없습니다.
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="list">
				<tr>
					<td style="border-bottom:1px dashed; padding:8px;">${list.b_num}</td>
						<td style="border-bottom:1px dashed; padding:8px;"><a style="text-decoration: none; text-align:center; color: #000000;" href="${contextPath}/books/bookView.do?b_num=${list.b_num}">${list.b_title}</a></td>
						<td style="border-bottom:1px dashed; padding:8px; text-align:right;">
							<img src="${contextPath}/download/b_image.do?b_imageName=${list.b_imageName}&b_num=${b_num}" id="preview" width="100px" height="100px" border="1">
						</td>
						<td style="border-bottom:1px dashed; padding:8px;">${list.b_name}</td>
						<c:if test="${list.b_state == 0}">
							<td style="border-bottom:1px dashed; padding:8px;">대출 불가능</td>
						</c:if>
						<c:if test="${list.b_state == 1}">
						<td style="border-bottom:1px dashed; padding:8px;">대출 가능</td>
						</c:if>
				</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${admin}">
			<tr>
				<td colspan="5" style="text-align:center; border:1px solid white; padding-top:10px; font-weight:bold;">
					<a href="${contextPath}/books/bookAddForm.do" style="text-decoration:none; color:#000000;">도서등록</a>
				</td>
			</tr>
		</c:if>
	</table>
	
	<div class="text_center">
		<jsp:include page="/views/common/paging.jsp" />
	</div>
	
	<%
		String b = (String)request.getAttribute("b_search");
		String qe = (String)request.getAttribute("q");		
	%>
	 
	 <c:if test="${b_search != null}">
		<script type="text/javascript">
			$(function search(){
				var b_search = "<%= b %>";
				var q = "<%= qe %>";
							
				$("#b_search").val(b_search).prop("selected", true);
				$("#b_search").removeAttr('all');
				$("#q").prop("value", q);
			});	
		</script>
	</c:if>
	
	<form action="${contextPath}/books/booksSearch.do" style="text-align:center; padding-top:10px;">
		검색 : <select name="b_search" id="b_search">
				<option value="b_title||b_name">전체</option>
				<option value="b_title">책 제목</option>
			 	<option value="b_name">저자</option>
			 </select>
			 <input type="text" name="q" id="q">
			 <input type="submit" value="검색">
	</form>
</body>
</html>