<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%--<c:set var="user_name" value="${user_name}" /> --%>
<c:set var="now" value="<%=new Date()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<c:if test="${login=='false'}">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			window.onload = function()
			{
				result();
			}
			function result(){
				alert("로그인이 필요합니다.");
				location.href="${contextPath}/views/member/loginForm.jsp?next_page=/free/freeWriteForm.do";
			}			
		</script>
	</c:if>
	<style>
		.top{text-align:right}
	</style>
<title>자유게시판</title>
</head>
<body>
	<div class="top">
		<jsp:include page="/views/main/main_top.jsp" />
	</div>
	<jsp:include page="/views/main/main_menu.jsp" />

	<h2 align="center">자유게시판</h2>
	<h3 align="center">글 쓰기</h3>
	
	<form name="freeWrite" action="${contextPath}/free/freeWrite.do" method="post" onsubmit="return blank_chk()">
	
		<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">작성자</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
					<input type="hidden" name="id" value="${user_id}">
					<input type="text" name="f_name" value="${user_name}" disabled style="border:0;">
					<input type="hidden" name="f_name" value="${user_name}">
				</td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">제목</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
				<input type="text" id="f_title" name="f_title" style="border:0;">
				</td>
			</tr>
			
			<tr height="100">
				<td style="width:20%; border:1px solid; vertical-align:top; text-align:right; font-weight:bold; padding-right:10px; padding-top:10px;">내용</td>
				<td style="text-align:left; padding-left:10px;">
					<textarea rows="8" id="f_content" name="f_content" style="text-align:left; font-size:15px; border:none; width:99%;"></textarea>
				<td>
			</td>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">날짜</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
					<input type="date" name="f_date" value="${today}" disabled style="border:0;"><br>	
					<input type="hidden" name="f_date" value="${today}">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:center; border:1px solid white; padding-top:10px;">
					<input type="submit" value="저장">
					<input type="reset" value="다시입력">
				</td>
			</tr>		
		</table>
	</form>
	
	<script>
		function blank_chk(){
			var f_title = document.freeWrite.f_title.value;
			var f_content = document.freeWrite.f_content.value;
	
			if(f_title == '' || f_title == null || f_title.length == 0){
				alert("제목을 입력하세요");
				return false;
			}else{
				if(f_content == '' || f_content == null || f_content.length == 0){
				alert("내용을 입력하세요.");
				return false;
			}else{
				return true;
			}
			return true;
		}
	}
</script>
	
</body>
</html>