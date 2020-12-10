<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%= new Date() %>" />
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
<title>공지사항</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">공지사항</h2>
	<h3 align="center">수정하기</h3>
	
	<form action="${contextPath}/notice/noticeModify.do" method="post" enctype="multipart/form-data">
		<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
			<%-- <tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">순번</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
					${noticeVO.n_num}
					<input type="hidden" name="n_num" value="${noticeVO.n_num}">
				</td>
			</tr>
			--%>
		
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">작성자</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">${noticeVO.n_name}
				</td>
			</tr>

			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">제목</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
					<input type="text" name="n_title" value="${noticeVO.n_title}" style="border:0;">
				</td>
			</tr>
			
			<tr height="100">
				<td style="width:20%; border:1px solid; text-align:right; vertical-align:top; padding-right:10px; padding-top:10px; font-weight:bold;">내용</td>
				<td style="border:1px solid; text-align:left; padding-left:10px; vertical-align:top; padding-top:10px;">
					<textarea rows="8" name="n_content" style="text-align:left; font-size:15px; border:0; width:99%;">${noticeVO.n_content}</textarea>
				</td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">첨부파일</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:10px">
					<c:if test="${noticeVO.imageFileName != null}">
						<img id="preview" src="${contextPath}/download/n_image.do?imageFileName=${noticeVO.imageFileName}&n_num=${noticeVO.n_num}" width="100" height="100">
						<input type="file" id="imageFileName" name="imageFileName" onchange="readURL(this);">
					</c:if>
					
					<c:if test="${noticeVO.imageFileName == null}">
						<img id="preview" src="#" width="100" height="100">
						<input type="file" id="imageFileName" name="imageFileName" onchange="readURL(this);">
					</c:if>
				</td>
			</tr>

			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">날짜</td>
				<td style="width:80%; border:1px solid; text-align:left; padding-left:10px">
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="n_date" />
					<input type="text" name="n_date" value="${n_date}" disabled style="border:0;">		
					<input type="hidden" name="n_date" value="${n_date}" >
				</td>		
			</tr>
				
			<tr>
				<td colspan="3" style="text-align:center; border:1px solid white; padding-top:10px;">
					<input type="button" value="뒤로가기" onclick="history.back()">
					<input type="hidden" name="n_num" value="${noticeVO.n_num}">
					<input type="submit" value="수정완료">
					<input type="button" value="삭제하기" onclick="location.href='${contextPath}/notice/noticeDelete.do?n_num=${noticeVO.n_num}'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>