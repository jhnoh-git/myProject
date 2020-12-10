<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.top { 
		text-align:right;
	}
</style>
<title>도서 추가</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h3 align="center">도서 추가</h3>
	
	<form action="${contextPath}/books/bookAdd.do" method="post" enctype="multipart/form-data">
		<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; padding-right:10px; font-weight:bold;">책 번호</td>
				<td width="60%" colspan="2" style="border:1px solid; text-align:left; padding-left:10px;">
					<input type="number" name="b_num" value="${b_num}" disabled style="border:none;">
					<input type="hidden" name="b_num" value="${b_num}"></td>
				<td rowspan="4" style="border:1px solid;">
					<input type="file" name="imageName">
				</td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; padding-right:10px; font-weight:bold;">책 제목</td>
				<td colspan="2" style="width:60%; border:1px solid; text-align:left; padding-left:10px; font-weight:bold;">
					<input type="text" name="b_title" style="border:none;">
				</td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; padding-right:10px; font-weight:bold;">저자</td>
				<td colspan="2" style="width:60%; border:1px solid; text-align:left; padding-left:10px; font-weight:bold;">
					<input type="text" name="b_name" style="border:none;">
				</td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; padding-right:10px; font-weight:bold;">발행일</td>
				<td colspan="2" style="width:60%; border:1px solid; text-align:left; padding-left:10px; font-weight:bold;">
					<input type="date" name="b_date" style="border:none;">
				</td>
			</tr>
			
			<tr height="100">
				<td style="width:20%; border:1px solid; text-align:right; vertical-align:top; padding-top:10px; padding-right:10px; font-weight:bold;">줄거리</td>
				<td colspan="4" style="width:20%; border:1px solid; text-align:left; padding-left:10px; font-weight:bold;">
					<textarea rows="5" cols="50" name="b_content" style="border:none;"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="5" align="left" style="border-bottom:1px solid;">
					<input type="checkbox" id="best" name="best" value=1>베스트 셀러
				</td>
			</tr>
			
			<tr>
				<td colspan="4" style="border:1px solid white; text-align:center; padding-top:10px;">
					<input type="submit" value="저장">
					<input type="reset" value="다시 입력">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>