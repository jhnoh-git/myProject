<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>자유게시판</title>
</head>
<style>
	.top{
		text-align:right;
	}
	.text_center{
		text-align:center;
		font-size:20px;
	}
</style>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<%-- 부제목 표시 --%>
	<h2 align="center">자유게시판</h2>
	<c:if test="${action eq 'freeList'}">
		<h3 align="center">글 목록</h3>
	</c:if>
	<c:if test="${action eq 'freeSearch'}">
		<h3 align="center">검색 결과</h3>
	</c:if>
	
	<%-- 게시판 목록 or 검색 결과 출력 테이블 --%>
	<table style="width:80%; margin:auto; text-align:center; border-top:1px solid;">
		<tr>
			<td width="10%" style="border-bottom:1px solid; padding:8px;"><b>순번</b></td>
			<td width="40%" style="border-bottom:1px solid; padding:8px;"><b>제목</b></td>
			<td width="30%" style="border-bottom:1px solid; padding:8px;"><b>작성자</b></td>
			<td width="20%" style="border-bottom:1px solid; padding:8px;"><b>날짜</b></td>
		</tr>

		<c:choose>
			<c:when test="${empty list}">
				<tr>
					<td colspan="4" style="text-align:center; border-bottom:1px solid; padding:8px;">
						등록된 내용이 없습니다.
					</td>
				</tr>
			</c:when>
			
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list">
					<tr>
						<td style="border-bottom:1px dashed; padding:8px;">${list.rownum}</td>
						<td style="border-bottom:1px dashed; padding:8px;"><a style="text-decoration: none; color: #000000;" href="${contextPath}/free/freeView.do?f_num=${list.f_num}">${list.f_title}</a></td>
						<td style="border-bottom:1px dashed; padding:8px;">${list.f_name}</td>
						<td style="border-bottom:1px dashed; padding:8px;">${list.f_date}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

	<%-- 페이징 인클루드 --%>
	<div class="text_center">
		<jsp:include page="/views/common/paging.jsp" />
	</div>

	<%-- f_search가 null이면 전체 범위로 지정 --%>
	<%
		String f = (String)request.getAttribute("f_search");
		String qe = (String)request.getAttribute("q");
		String action = (String)request.getAttribute("action");
		System.out.println("action : " + action);
	%>
	
	<%-- f_search가 null이 아니라면 해당 범위로 지정 --%>
	<c:if test="${f_search != null}">
		<script type="text/javascript">
			$(function search(){
				var f_search = "<%= f %>";
				var q = "<%= qe %>";
				$("#f_search").val(f_search).prop("selected", true);
				$("#f_search").removeAttr('all');
				$("#q").prop("value", q);
			});	
		</script>
	</c:if>
	
	<h4 align="center"><a style="display:inline; text-decoration:none; color:#000000;" href="${contextPath}/free/freeWriteForm.do">글 쓰기</a></h4>
	
	<form action="${contextPath}/free/freeSearch.do" style="text-align:center; padding-top:10px;" >
		검색 : <select id="f_search" name="f_search" >
				<option value="all" selected="selected">전체</option>
				<option value="f_name">작성자</option>
				<option value="f_title">제목</option>
				<option value="f_content">내용</option>
			</select>
			<input type="text" id="q" name="q">
			<input type="submit" value="검색">
	</form>
</body>
</html>