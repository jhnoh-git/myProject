<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
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
	}
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
		white-space:nowrap;	
		text-overflow:ellipsis; 
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
	
</body>
</html>