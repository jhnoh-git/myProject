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
	.center{
		text-align:center;
	}
</style>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function pw_chk(){
		var pw = $("#user_pw").val();
		//alert(pw);
		
		var id = "<c:out value="${userInfo.user_id}" />"
		//alert(id);
		
		if(pw == ''){
			alert("비밀번호를 입력하세요.");
			return;
		}
		
		$.ajax({
			type:"post",
			async:false,
			url:"${contextPath}/member/pw_chk.do",
			dataType:"text",
			data:{pw:pw, id:id},
			success:function(data, textStatus){
				if(data == 'usable'){
					//$('#message').text("비밀번호가 맞습니다.");
					location.href="${contextPath}/member/myPage.do";
				}else{
					$('#message').text("비밀번호가 틀립니다.");
				}
			}
			
		});
		
	}
</script>
<title>내 페이지</title>
</head>
<body>
	<div class="top">
		<jsp:include page="/views/main/main_top.jsp" />
	</div>
		<jsp:include page="/views/main/main_menu.jsp" />

	<div class="center">
	<h2 align="center">비밀번호 확인</h2>

	<h3 style="font-weight:bold; display:inline;"> 비밀번호 : </h3>
	<input type="password" id="user_pw" name="user_pw">
	<input type="button" value="확인" onclick="pw_chk();">
	<h5 style="padding-top:0px; color:blue;">현재 비밀번호를 입력해주세요.</h5>
	<div id="message" style="color:red;font-weight:bold;"></div>
	</div>
</body>
</html>