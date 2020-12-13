<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String act = (String)request.getAttribute("action_"); 
		String servletP = (String)request.getAttribute("servletPath"); 
		String contextP = (String)request.getAttribute("contextPath"); 
		String a_href = contextP + servletP + act;
	%>

	<c:set var="totalRow" value="${paging.totalRow}" />
	<c:set var="page_Row" value="${paging.page_Row}" />
	<c:set var="nowSection" value="${paging.nowSection}" />
	<c:set var="section_Page" value="${paging.section_Page}" />
	<c:set var="nowPage" value="${paging.nowPage}" />
	<fmt:parseNumber var="totalSection" value="${((totalRow/page_Row)+1)/section_Page}" integerOnly="true" />
	 	
	<c:if test="${(totalRow % page_Row)!=0}">
		<c:if test="${nowSection>0}">
			<c:if test="${action eq 'freeSearch'}">
				<a href="<%=a_href%>?nowSection=${nowSection-1}&nowPage=${((nowSection-1)*section_Page)+section_Page}">[이전]</a>
			</c:if>
			<c:if test="${action ne 'freeSearch'}">
				<a href="<%=a_href%>?nowSection=${nowSection-1}&nowPage=${((nowSection-1)*section_Page)+section_Page}">[이전]</a>
			</c:if>
		</c:if>
	 	<c:forEach var="paging" begin="${(nowSection*section_Page)+1}" end="${(nowSection*section_Page)+section_Page}" step="1">
			<c:if test="${paging <= (totalRow/page_Row+1)}">
			 	<c:if test="${paging == nowPage}">
			 		<c:if test="${action eq 'freeSearch'}">
						<a style="font-weight:bold; color:blue;" href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
					</c:if>
			 		<c:if test="${action ne 'freeSearch'}">
			 			<a style="font-weight:bold; color:blue;" href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
			 		</c:if>
			 	</c:if>
			 	<c:if test="${paging != nowPage}">
			 		<c:if test="${action eq 'freeSearch'}">
						<a href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
					</c:if>
					<c:if test="${action ne 'freeSearch'}">
			 			<a href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
	 				</c:if>
	 			</c:if>
	 		</c:if>
		</c:forEach>
	
			
	 	<c:if test="${nowSection < totalSection}">
	 		<c:if test="${action eq 'freeSearch'}">
				<a href="<%= a_href %>?f_search=${f_search}&q=${q}&nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
			</c:if>
			<c:if test="${action ne 'freeSearch'}">
	 			<a href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
			</c:if>
	 </c:if>
	</c:if>
	
	<c:if test="${(totalRow % page_Row)==0}">
		<c:if test="${nowSection>0}">
			<c:if test="${action eq 'freeSearch'}">
				<a href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${((nowSection-1)*section_Page)-1}&nowSection=${nowSection - 1}">[이전]</a>
			</c:if>
			<c:if test="${action ne 'freeSearch'}">
				<a href="<%= a_href %>?f_search=${f_search}&q=${q}&nowSection=${nowSection-1}&nowPage=${((nowSection-1)*section_Page)+section_Page}">[이전]</a>
			</c:if>
		</c:if>
	 	<c:forEach var="paging" begin="${(nowSection*section_Page)+1}" end="${(nowSection*section_Page)+section_Page}" step="1">
			<c:if test="${paging <= (totalRow/page_Row)}">
			 	<c:if test="${paging == nowPage}">
			 		<c:if test="${action eq 'freeSearch'}">
						<a style="font-weight:bold; color:blue;" href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
					</c:if>
					<c:if test="${action ne 'freeSearch'}">
			 			<a style="font-weight:bold; color:blue;" href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
			 		</c:if>
			 	</c:if>
			 	<c:if test="${paging != nowPage}">
			 		<c:if test="${action eq 'freeSearch'}">
						<a href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
					</c:if>
					<c:if test="${action ne 'freeSearch'}">
			 			<a href="<%=a_href %>?f_search=${f_search}&q=${q}&nowPage=${paging}&nowSection=${nowSection}">${paging}</a>
	 				</c:if>
	 			</c:if>
	 		</c:if>
		</c:forEach>
	 	
	 	<c:if test="${nowSection < totalSection}">
	 		<c:if test="${action eq 'freeSearch'}">
				<a href="<%=a_href %>?nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
			</c:if>
			<c:if test="${action ne 'freeSearch'}">
	 			<a href="<%=a_href %>?nowPage=${((nowSection+1)*section_Page)+1}&nowSection=${nowSection + 1}">[다음]</a>
	 		</c:if>
	 </c:if>
	</c:if>
	
</body>
</html>