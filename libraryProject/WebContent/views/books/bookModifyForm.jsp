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
</style>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

<title>도서정보_${vo.b_title}_수정</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">도서 정보 수정</h2>
	<h3 align="center">${vo.b_title}</h3>
	
	<form action="${contextPath}/books/bookModify.do" method="post" enctype="multipart/form-data">
	<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">책 번호</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:12px;">
				<input type="text" name="b_num" value="${vo.b_num}" disabled>
				<input type="hidden" name="b_num" value="${vo.b_num}">
			</td>
			<td rowspan="4" style="text-align:center;">
				
				<c:if test="${vo.b_imageName != null}">
					<img id="preview" src="${contextPath}/download/b_image.do?b_imageName=${vo.b_imageName}&b_num=${vo.b_num}" width="100" height="150">
					<input type="file" id="b_imageName" name="b_imageName" onchange="readURL(this);">
				</c:if>
				<c:if test="${vo.b_imageName == null}">
					<img id="preview" src="#" width="100" height="150">
					<input type="file" id="b_imageName" name="b_imageName" onchange="readURL(this);">
				</c:if>
			</td>
		</tr>
			
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">책 제목</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				<input type="text" name="b_title" value="${vo.b_title}">
			</td>
		</tr>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">저자</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				<input type="text" name="b_name" value="${vo.b_name}">
			</td>
		</tr>
		
		<tr>
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">발행일</td>
			<td style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				<input type="date" name="b_date" value="${vo.b_date}">	
			</td>
		</tr>
		
		<tr height="100">
			<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">줄거리</td>
			<td colspan="2" style="width:40%; border:1px solid; text-align:left; padding-left:10px;">
				<textarea rows="5" cols="50" name="b_content" style="border:none;">${vo.b_content}</textarea>
			</td>
		</tr>
		
		<tr>
			<c:if test="${(vo.best eq 0) || (vo.best == null)}">
				<td colspan="3">
					<input type="checkbox" id="best" name="best" value=1>베스트 셀러
				</td>
			</c:if>
			<c:if test="${vo.best eq 1}">
				<td colspan="3">
					<input type="checkbox" id="best" name="best" value=0 checked="checked">베스트 셀러
				</td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="3" style="text-align:center; border:1px solid white; border-top:1px solid black; padding-top:10px;">
				<input type="button" value="뒤로가기" onclick="history.back()" >
				<input type="button" value="삭제하기" onclick="location.href='${contextPath}/books/bookDelete.do?b_num=${vo.b_num}'" />
				<input type="submit" value="수정완료">
			</td>
		</tr>
	</table>
	</form>
	
</body>
</html>