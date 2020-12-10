<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	
	<h2 align="center">자유게시판</h2>
	<c:if test="${action eq 'freeList'}">
		<h3 align="center">글 목록</h3>
	</c:if>
	<c:if test="${action eq 'freeSearch'}">
		<h3 align="center">검색 결과</h3>
	</c:if>
	
	
	<table style="width:80%; margin:auto; text-align:center; border-top:1px solid;">
		<tr>
			<td width="10%" style="border-bottom:1px solid; padding:8px;"><b>순번</b></td>
			<td width="40%" style="border-bottom:1px solid; padding:8px;"><b>제목</b></td>
			<td width="30%" style="border-bottom:1px solid; padding:8px;"><b>작성자</b></td>
			<td width="20%" style="border-bottom:1px solid; padding:8px;"><b>날짜</b></td>
		</tr>

		<c:choose>
			<c:when test="${not empty list}">
				<c:forEach items="${list}" var="list">
					<tr>
						<td style="border-bottom:1px dashed; padding:8px;">${list.rownum}</td>
						<%-- <td style="border-bottom:1px dashed; padding:8px;">${list.f_num}</td>--%>
						<td style="border-bottom:1px dashed; padding:8px;"><a style="text-decoration: none; color: #000000;" href="${contextPath}/free/freeView.do?f_num=${list.f_num}">${list.f_title}</a></td>
						<td style="border-bottom:1px dashed; padding:8px;">${list.f_name}</td>
						<td style="border-bottom:1px dashed; padding:8px;">${list.f_date}</td>
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

	<div class="text_center">
	<c:set var="totalRow" value="${paging.totalRow}" />
	<c:set var="page_Row" value="${paging.page_Row}" />
	<c:set var="nowSection" value="${paging.nowSection}" />
	<c:set var="section_Page" value="${paging.section_Page}" />
	<c:set var="nowPage" value="${paging.nowPage}" />
	
	<c:if test="${(totalRow % page_Row)!=0}">
		<c:if test="${nowSection>0}">
			<a href="${contextPath}/free/freeList.do?nowSection=${nowSection-1}&nowPage=${((nowSection-1)*section_Page)+section_Page}">[이전]</a>
		</c:if>
	 	<c:forEach var="paging" begin="${(nowSection*section_Page)+1}" end="${(nowSection*section_Page)+section_Page}" step="1">
			<%--<c:forEach var="section" begin="${page}" end="${page+3}" step="1"> --%>
			<c:if test="${paging <= (totalRow/page_Row+1)}">
			 	<c:if test="${paging == nowPage}">
			 		<a style="font-weight:bold; color:blue;" href="${contextPath}/free/freeList.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
			 	</c:if>
			 	<c:if test="${paging != nowPage}">
			 		<a href="${contextPath}/free/freeList.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
	 			</c:if>
	 		</c:if>
		</c:forEach>
	 <%--</c:forEach> --%>
	 <%--<c:if test="${totList > 3}"> --%>
	 	
	 	<fmt:parseNumber var="totalSection" value="${((totalRow/page_Row)+1)/section_Page}" integerOnly="true" />
	 	
		
	 	<c:if test="${nowSection < totalSection}">
	 		<a href="${contextPath}/free/freeList.do?nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
	 </c:if>
	</c:if>
	
	<c:if test="${(totalRow % page_Row)==0}">
		<c:if test="${nowSection>0}">
			<a href="${contextPath}/free/freeList.do?nowSection=${nowSection-1}&nowPage=${((nowSection-1)*section_Page)+section_Page}">[이전]</a>
		</c:if>
	 	<c:forEach var="paging" begin="${(nowSection*section_Page)+1}" end="${(nowSection*section_Page)+section_Page}" step="1">
			<%--<c:forEach var="section" begin="${page}" end="${page+3}" step="1"> --%>
			<c:if test="${paging <= (totalRow/page_Row)}">
			 	<c:if test="${paging == nowPage}">
			 		<a style="font-weight:bold; color:blue;" href="${contextPath}/free/freeList.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
			 	</c:if>
			 	<c:if test="${paging != nowPage}">
			 		<a href="${contextPath}/free/freeList.do?nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
	 			</c:if>
	 		</c:if>
		</c:forEach>
	 <%--</c:forEach> --%>
	 <%--<c:if test="${totList > 3}"> --%>
	 	
	 	<fmt:parseNumber var="totalSection" value="${((totalRow/page_Row)+1)/section_Page}" integerOnly="true" />
	 	
		
	 	<c:if test="${nowSection < totalSection}">
	 		<a href="${contextPath}/free/freeList.do?nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
	 </c:if>
	</c:if>
	
	
	
	<%--
	<c:if test="${(totList%10)==0}">
		<c:forEach var="page" begin="1" end="${totList/10}" step="1">
		
		<c:if test="${page < 3}">
		
			<c:forEach var="section" begin="${page}" end="3" step="1">
				<c:if test="${page == nowPage}">
			 		<a style="margin:auto; padding:auto; font-weight:bold; color:blue;" href="${contextPath}/free/freeList.do?nowPage=${page}&section=${section}">${page}</a>
			 	</c:if>
			 	<c:if test="${page != nowPage}">
			 		<a style="margin:auto; padding:auto;" href="${contextPath}/free/freeList.do?nowPage=${page}&section=${section}">${page}</a>
			 	</c:if>
			</c:forEach>

		</c:if>
		
		
	 	
	 </c:forEach>
	</c:if>
	 --%>
	
	
	
	</div>
	
	
	<h4 align="center"><a style="display:inline; text-decoration:none; color:#000000;" href="${contextPath}/free/freeWriteForm.do">글 쓰기</a></h4>
	<form action="${contextPath}/free/freeSearch.do" style="text-align:center; padding-top:10px;" >
		검색 : <select name="f_search">
				<option value="f_title||f_content||f_name">전체</option>
				<option value="f_name">작성자</option>
				<option value="f_title">제목</option>
				<option value="f_content">내용</option>
			</select>
			<input type="text" name="q">
			<input type="submit" value="검색">
	</form>
</body>
</html>