<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	td{
		font-weight:bold;
	}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function id_chk(){
		//user_id 값을 id 변수에 입력
		var id = $("#user_id").val();		
		if(id == ''){
			alert("ID를 입력하세요.");
			return;
		}
		
		$.ajax({
			type:"post",
			async : false,
			url : "${contextPath}/member/id_chk.do",
			dataType : "text",
			data : {id: id},
			success : function(data, textStatus){
				if(data=='usable'){
					$('#message').text("사용할 수 있는 ID입니다.").css("color","black");
				}else{
					$('#message').text("사용할 수 없는 ID입니다.").css("color","red");
				}
			}
		});
	}
</script>
<title>회원가입</title>
</head>
<body>
	<jsp:include page="/views/main/main_top.jsp" />
	<jsp:include page="/views/main/main_menu.jsp" />
	
	<h2 style="text-align:center;">회원가입</h2>
	<form action="${contextPath}/member/memberJoin.do" method="post">
		<table style="width:100%; height:100%; text-align:center">
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">아이디</td>
				<td style="text-align:left; width:55%;"><input type="text" name="user_id" id="user_id"></td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="button" value="아이디 중복 확인" onclick="id_chk();">
					<div id="message"></div>
				</td>
			</tr>
				
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">비밀번호</td>
				<td style="text-align:left; width:55%;"><input type="password" name="user_pw"></td>
			</tr>
			
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">성명</td>
				<td style="text-align:left; width:55%;"><input type="text" name="user_name"></td>
			</tr>
			
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">생년월일</td>
				<td style="text-align:left; width:55%;">
					<input type="date" id="user_birth" name="user_birth">
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">휴대전화</td>
				<td style="text-align:left; width:55%;"><input type="text" name="user_phone"></td>
			</tr>
			
			<tr>
				<td style="text-align:right; width:45%; padding:15px;">e-mail</td>
				<td style="text-align:left; width:55%;">
					<input type="text" name="user_email">
					<select name="email_address">
						<option value="@daum.net">@daum.net</option>
						<option value="@naver.com">@naver.com</option>
						<option value="@google.com">@google.com</option>
						<option value="직접입력">직접 입력</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="회원가입">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>		
	</form>
	
</body>
</html>