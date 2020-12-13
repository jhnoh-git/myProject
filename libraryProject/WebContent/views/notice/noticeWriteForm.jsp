<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%= new Date() %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	function blank_chk(){	
		var n_title = $("input#n_title").val();
		var n_content = document.noticeWrite.n_content.value;
		if(n_title == '' || n_title == null || n_title.length == 0){
			alert("제목을 입력하세요.");
			return false;
		}else{
			if(n_content == '' || n_content == null || n_content.length == 0){
				alert("내용을 입력하세요.");
				return false;
			}else{
				return true;
			}
			return true;
		}
	}

</script>
<style>
	.top{
		text-align:right;
	}
</style>
<title>공지사항</title>
</head>
<body>
	<div class="top"><jsp:include page="/views/main/main_top.jsp" /></div>
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 align="center">공지사항</h2>
	<h3 align="center">글 쓰기</h3>
	
	<form name="noticeWrite" action="${contextPath}/notice/noticeWrite.do" method="post" enctype="multipart/form-data" onsubmit="return blank_chk()">
		<table style="width:80%; border:1px solid; border-collapse:collapse; margin:auto;">
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">작성자</td>
				<td colspan="2" style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
					<input type="hidden" name="id" value="${user_id}">
					<input type="text" name="n_name" value="${user_name}" disabled style="border:0;">
			    	<input type="hidden" name="n_name" value="${user_name}" />
			    </td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">제목</td>
				<td colspan="2" style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
					<input type="text" id="n_title" name="n_title" style="border:0;">
			    </td>
			</tr>
			
			<tr height="100">
				<td style="width:20%; border:1px solid; vertical-align:top; text-align:right; font-weight:bold; padding-right:10px; padding-top:10px;">내용</td>
				<td colspan="2" style="border:1px solid; text-align:left; padding-left:10px;">
					<textarea  id="n_content" rows="8" name="n_content" style="text-align:left; font-size:15px; border:none; width:98%;"></textarea>
				</td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; vertical-align:top; text-align:right; font-weight:bold; padding-right:10px; padding-top:10px;">첨부파일</td>
				<td width="20%">
					<input type="file" name="imageFileName" onchange="readURL(this);" />
				</td>
				<td bordercolor="1">				
					<img id="preview" src="#" width="100" height="100" />
				</td>
			</tr>
			
			<tr>
				<td style="width:20%; border:1px solid; text-align:right; font-weight:bold; padding-right:10px;">날짜</td>
				<td colspan="2" style="width:80%; border:1px solid; text-align:left; padding-left:12px;">
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
					<input type="date" name="n_date" value="${today}" disabled style="border:0;"><br>	
					<input type="hidden" name="n_date" value="${today}">
				</td>
			</tr>
			
			<tr>
				<td colspan="3" style="text-align:center; border:1px solid white; padding-top:10px;">
					<input type="submit" value="저장">
					<input type="reset" value="다시입력">
				</td>
			</tr>	
		</table>
	</form>
</body>
</html>