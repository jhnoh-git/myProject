<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	<%--
	td.menu:hover{
		background-color:#d0b5b5;
	}
	.dropdown{
		position:relative;
		display:inline-block;
	}
	.dropdown-button{
		background-color: #FFDAB9;
			padding: 8px;
			font-size: 15px;
			border: none;
	}
	.dropdown-content{
		display:none;
		position:absolute;
		background-color:#F9F9F9;
		min-width:160px;
		padding:8px;
		box-shadow:0px 8px 16px 0px rgba(0,0,0,0.2);    
	}
	.dropdown-content a {
			color: black;
			padding: 8px;
			text-decoration: none;
			display: block;
		}
	.dropdown:hover .dropdown-content {display:bolck;}
	.dropduwn.menu.dropdown-button{
	}--%>
	
	<%--ul{
		background-color:#E6E6E6;
		list-style-type:none;
		margin:0;
		padding:0;
		overflow:hidden; 
	}
	li{
		float:left; 
		width:20%;
		position:relative;
		display:inline-block;
	}
	li a{
		display:block;
		padding:8px;
		text-decoration:none;
		text-align:center;
		font-weight:bold;
		color:#000000;
	}
	li a:hover{
		background-color:#d0b5b5;
	}
	
	.dropdown{
		position:relative;
		display:inline-block;
	}
	.dropdown-content {
			display:none;
		position:absolute;
		background-color:#F9F9F9;
		min-width:160px;
		padding:8px;
		box-shadow:0px 8px 16px 0px rgba(0,0,0,0.2);    
	}--%>
	
	/* CSS Document */

	/** {
		margin: 0;
		padding: 0;
	}*/
	ul{
		text-align:center;
		
		background-color:#E6E6E6;
		list-style: none;
	}
	li, ol {
		text-align:center;
		
		background-color:#E6E6E6;
		list-style: none;
	}
	a {
		text-decoration: none;
		color:black;
		/*color: inherit;*/
	}
	/**{
		text-align:center;
	}*/
	
	/* 부메뉴가 있는 네비게이션바
	2depth 네비게이션바 */
	
	.navi{
		
		}
	.navi>ul{
		display:flex;
		width:60%;
		}
	.navi>ul:after{
	}
	.navi>ul>li{
		width:20%;
		/* 말줄임표 공식 */
		white-space:nowrap;	/* 줄안바꾸기 */
		text-overflow:ellipsis; /* 생략기호 */
		padding:15px;
		background-color:#E6E6E6;
		color:black;
		border-left:1px solid white;
		position:relative;
	}
	.navi>ul>li>ul{
		height:0;
		transition:0.5s;
		overflow:hidden;
		background-color:#E6E6E6;
		width:100%;
		position:absolute;
		left:-2px;
		top:54px;
	}
	.navi>ul>li:hover>ul{
		height:100px;
		left:-2px;
		top:54px;
		border:3px solid lightblue;
	}
	.navi>ul>li>ul>li{
		padding:10px;
		visibility:hidden;
		opacity:0;
		transition:0.3s;
	}
	.navi>ul>li:hover>ul li{
		visibility:visible;
		opacity:1;
	}
	.navi>ul>li:hover{
		background-color:#E6E6E6;
		color:black;
		border:3px solid steelblue;
	}
	.navi ul>li{
			border:3px solid transparent;
	}
	.navi>ul>li>ul>li:hover{
		background-color:#E6E6E6;
		color:yellow;
	}
	
</style>
<script type="text/javascript">
	function login(){
		alert("로그인이 필요합니다.");
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div class='navi'>
		<ul style="width:100%; padding:0;">
			<li><a href="${contextPath}/main/main" style="text-decoration:none; color:#000000; font-weight:bold;">도서 관리 프로그램</a></li>
			<li><a href="${contextPath}/books/bookList.do" style="text-decoration:none; color:#000000; font-weight:bold;">도서 목록</a></li>
			<li><a href="${contextPath}/notice/noticeList.do" style="text-decoration:none; color:#000000; font-weight:bold;">공지사항</a></li>
			<li><a href="${contextPath}/free/freeList.do" style="text-decoration:none; color:#000000; font-weight:bold;">자유 게시판</a></li>
			<li style="font-weight:bold;">내 정보
				<ul>
					<c:if test="${(userInfo!= null) && (userInfo.user_id!=null)}">
						<li class="dropdown-content"><a href="${contextPath}/member/pw_chkForm.do" style="text-decoration:none; color:#000000; font-weight:bold;">정보 수정</a></li>
		 			</c:if>
		 			<c:if test="${userInfo==null}">
		 				<li class="dropdown-content"><a href="${contextPath}/member/loginForm.do" style="text-decoration:none; color:#000000; font-weight:bold;" onclick="login();">정보 수정</a></li>
		 			</c:if>
		 			
						<li><a href="${contextPath}/books/bookLoanList.do?id=${userInfo.user_id}" style="text-decoration:none; color:#000000; font-weight:bold;">대출 내역</a></li>
				</ul>
			</li>
		</ul>
	</div>
	
	
	
	
	<%--<ul>
		<li><a href="${contextPath}/main/main">도서 관리 프로그램</a></li>
		<li><a href="${contextPath}/books/bookList.do" style="text-decoration:none; color:#000000; font-weight:bold;">도서 목록</a></li>
		<li><a href="${contextPath}/notice/noticeList.do" style="text-decoration:none; color:#000000; font-weight:bold;">공지사항</a></li>
		<li><a href="${contextPath}/free/freeList.do" style="text-decoration:none; color:#000000; font-weight:bold;">자유 게시판</a></li>
		
		
			<li>내 정보
				<ul>
				<c:if test="${(userInfo!= null) && (userInfo.user_id!=null)}">
					<li class="dropdown-content"><a href="${contextPath}/member/pw_chkForm.do" style="text-decoration:none; color:#000000; font-weight:bold;">정보 수정</a></li>
		 		</c:if>
		 		<c:if test="${userInfo==null}">
		 			<li class="dropdown-content"><a href="${contextPath}/member/loginForm.do" style="text-decoration:none; color:#000000; font-weight:bold;" onclick="login();">정보 수정</a></li>
		 		</c:if>
				<li><a href="#">대출 내역</a></li>
				</ul>
			</li>
			
	
		
		
	</ul> --%>

	<%--<table style="background-color:#E6E6E6; border-collapse:collapse; width:100%; text-align:center;">
	 	<tr>
	 		<td class="menu" width="20%"><a href="${contextPath}/main/main" style="text-decoration:none; color:#000000; font-weight:bold;">도서 관리<br>프로그램</a></td>
	 		<td class="menu" width="20%"><a href="${contextPath}/books/bookList.do" style="text-decoration:none; color:#000000; font-weight:bold;">도서 목록</a></td>
	 		<td class="menu" width="20%"><a href="${contextPath}/notice/noticeList.do" style="text-decoration:none; color:#000000; font-weight:bold;">공지사항</a></td>
	 		<td class="menu" width="20%"><a href="${contextPath}/free/freeList.do" style="text-decoration:none; color:#000000; font-weight:bold;">자유 게시판</a></td>
	 		
	 		
	 		<td class="dropdown menu dropdown-button" width="20%">내 정보
	 			<td class="dropdown-content"><a class="dropdown-conetent" href="${contextPath}/member/pw_chkForm.do" style="text-decoration:none; color:#000000; font-weight:bold;">내 페이지</a></td>
	 			<td class="dropdown-content"><a href="${contextPath}/member/loginForm.do" style="text-decoration:none; color:#000000; font-weight:bold;" onclick="login();">내 페이지</a></td>
			</td>	
	 	
	 					
	 	 --%>	
	 	<%-- 
			<c:if test="${(userInfo!= null) && (userInfo.user_id!=null)}">
				<td class="menu" width="20%"><a href="${contextPath}/member/pw_chkForm.do" style="text-decoration:none; color:#000000; font-weight:bold;">내 페이지</a></td>
	 		</c:if>
	 		<c:if test="${userInfo==null}">
	 			<td class="menu" width="20%"><a href="${contextPath}/member/loginForm.do" style="text-decoration:none; color:#000000; font-weight:bold;" onclick="login();">내 페이지</a></td>
	 		</c:if>
	 		--%>
	 <%-- 	</tr>
	</table>--%>
</body>
</html>