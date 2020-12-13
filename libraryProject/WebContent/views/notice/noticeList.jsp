<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="msg" value="${msg}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<style>
	.top{
		text-align:right;
	}
	.text_center{
		text-align:center;
		font-size:20px;
	}
</style>
<title>공지사항</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">공지사항</h2>
	<c:if test="${action eq 'noticeList'}">
		<h3 align="center">글 목록</h3>
	</c:if>
	<c:if test="${action eq 'noticeSearch'}">
		<h3 align="center">검색 결과</h3>
	</c:if>
	
	<table style="width:80%; margin:auto; text-align:center; border-top:1px solid;">
		<tr>
			<td style="width:10%; border-bottom:1px solid; padding:8px; font-weight:bold;">순번</td>
			<td style="width:40%; border-bottom:1px solid; padding:8px; font-weight:bold;">제목</td>
			<td style="width:30%; border-bottom:1px solid; padding:8px; font-weight:bold;">작성자</td>
			<td style="width:20%; border-bottom:1px solid; padding:8px; font-weight:bold;">날짜</td>
		</tr>
		
		<c:choose>
		<c:when test="${not empty list}">
			<c:forEach items="${list}" var="list">
				<tr>
					<td style="border-bottom:1px dashed; padding:8px;">${list.rownum}</td>
					<td style="border-bottom:1px dashed; padding:8px;"><a style="text-decoration: none; color: #000000;" href="${contextPath}/notice/noticeView.do?n_num=${list.n_num}">${list.n_title}</a></td>
					<td style="border-bottom:1px dashed; padding:8px;">${list.n_name}</td>
					<td style="border-bottom:1px dashed; padding:8px;">${list.n_date}</td>
				</tr>	
			</c:forEach>
		</c:when>
		<c:when test="${empty list}">
			<tr>
				<td colspan="4" style="text-align:center; border-bottom:1px solid; padding:8px;">
						등록된 내용이 없습니다.
				</td>
			</tr>
		</c:when>
		</c:choose>
	</table>
	<c:if test="${(not empty msg)&&(msg==true)}">
		<h4 align="center"><a style="text-decoration:none; color:#000000;" href="${contextPath}/notice/noticeWriteForm.do">글 쓰기</a></h4>
	</c:if>
	
	
	<div class="text_center">
	<jsp:include page="/views/common/paging.jsp" />
	</div>

	<%
		String n = (String)request.getAttribute("n_search");
		String qe = (String)request.getAttribute("q");		
	%>
	<%-- f_search가 null이 아니라면 해당 범위로 지정 --%>
	<c:if test="${n_search != null}">
		<script type="text/javascript">
			$(function search(){
				var n_search = "<%= n %>";
				var q = "<%= qe %>";
				$("#n_search").val(n_search).prop("selected", true);
				$("#n_search").removeAttr('all');
				$("#q").prop("value", q);
			});	
		</script>
	</c:if>
	
	<form action="${contextPath}/notice/noticeSearch.do" style="text-align:center; padding-top:10px;">
		검색 : <select id="n_search" name="n_search">
				<option value="all" selected="selected">전체</option>
				<option value="n_title">제목</option>
				<option value="n_content">내용</option>
			</select>
			<input type="text" id="q" name="q">
			<input type="submit" value="검색">
	</form>
</body>
</html>