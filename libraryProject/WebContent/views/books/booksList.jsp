<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--<% request.setCharacterEncoding("utf-8"); --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	
	<%--페이징--%>
	<div class="text_center">
	<c:set var="totalRow" value="${paging.totalRow}" />
	<c:set var="page_Row" value="${paging.page_Row}" />
	<c:set var="nowSection" value="${paging.nowSection}" />
	<c:set var="section_Page" value="${paging.section_Page}" />
	<c:set var="nowPage" value="${paging.nowPage}" />
	
	<c:if test="${(totalRow % page_Row) != 0}">
		<c:if test="${nowSection > 0 }">
			<a href="${contextPath}/books/bookList.do?nowSection=${nowSection-1}&nowPage=${((nowSection-1)*section_Page)+section_Page}">[이전]</a>
		</c:if>
		
		<c:forEach var="paging" begin="${(nowSection*section_Page)+1}" end="${((nowSection*section_Page))+section_Page}" step="1">
			<c:if test="${paging <= (totalRow/page_Row+1)}">
				<c:if test="${paging == nowPage}">
					<a style="font-weight:bold; color:blue;" href="${contextPath}/books/bookList.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
				</c:if>
				<c:if test="${paging != nowPage}">
					<a href="${contextPath}/books/bookList.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
				</c:if>
			</c:if>
		</c:forEach>
		
		<fmt:parseNumber var="totalSection" value="${((totalRow/page_Row)+1)/section_Page}" integerOnly="true"/>
		<c:if test="${nowSection < totalSection}">
			<a href="${contextPath}/books/bookList.do?nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
		</c:if>
	</c:if>
	
	<c:if test="${(totalRow % page_Row) == 0}">
		<c:if test="${nowSection > 0 }">
			<a href="${contextPath}/books/bookList.do?nowSection=${nowSection-1}&nowPage=${((nowSection-1)*section_Page)+section_Page}">[이전]</a>
		</c:if>
		
		<c:forEach var="paging" begin="${(nowSection*section_Page)+1}" end="${(nowSection*section_Page)+section_Page}" step="1">
			<c:if test="${paging <= (totalRow/page_Row)}">
				<c:if test="${paging == nowPage}">
					<a style="font-weight:bold; color:blue;" href="${contextPath}/books/bookList.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
				</c:if>
				<c:if test="${paging != nowPage}">
					<a href="${contextPath}/books/bookList.do.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
					</c:if>
			</c:if>
		</c:forEach>
		
		<fmt:parseNumber var="totalSection" value="${((totalRow/page_Row)-1)/section_Page}" integerOnly="true" />
	
		<c:if test="${(nowSection < totalSection) && (nowSection ne totalSection)}">
			<a href="${contextPath}/books/bookList.do?nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
		</c:if>
	</c:if>
	</div>
	
	
	<form action="${contextPath}/books/booksSearch.do" style="text-align:center; padding-top:10px;">
		검색 : <select name="b_search">
				<option value="b_title||b_name">전체</option>
			 	<option value="b_title">책 제목</option>
			 	<option value="b_name">저자</option>
			 </select>
			 <input type="text" name="q">
			 <input type="submit" value="검색">
	</form>
</body>
</html>