package member.controller;

import java.io.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import member.command.*;
import member.vo.*;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberCommand memberCommand;
	MemberVO memberVO;
	
	public void init() throws ServletException{
		memberCommand = new MemberCommand();
		memberVO = new MemberVO();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String nextPage = null;
		String action = request.getPathInfo();
		
		if(action.equals("/loginForm.do")) {     //로그인 Form으로 이동
			nextPage = "/views/member/loginForm.jsp";
		}else if(action.equals("/login_chk.do")) {     //로그인 실행
			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			//로그인 후 원래 있던 페이지로 돌아갈 원래 페이지
			String next_page = request.getParameter("next_page"); 
			memberVO = memberCommand.login_chk(user_id, user_pw);
			
			if(memberVO != null && memberVO.getUser_id() != null) {
				HttpSession session = request.getSession();                   				
				session.setAttribute("userInfo", memberVO);
				request.setAttribute("msg", true);                                    				
				if(next_page.equals("null")) {
					nextPage = "/main/main.jsp";
				}else {
					System.out.println("next_page : " +next_page);
					nextPage = next_page;
				}
			}else {
				request.setAttribute("msg", false);
				nextPage = "/member/loginForm.do";
			}
		}else if(action.equals("/logout.do")){    
			HttpSession session = request.getSession(false);
			session.removeAttribute("userInfo");                                                   
			nextPage = "/main/main.jsp";
		}else if(action.equals("/memberJoinForm.do")) {
			nextPage = "/views/member/memberJoinForm.jsp";
		}else if(action.equals("/memberJoin.do")) {
			memberVO.setUser_id(request.getParameter("user_id"));
			memberVO.setUser_pw(request.getParameter("user_pw"));
			memberVO.setUser_name(request.getParameter("user_name"));
			memberVO.setUser_phone(request.getParameter("user_phone"));
			String user_email = request.getParameter("user_email") + request.getParameter("email_address");
			memberVO.setUser_email(user_email);
			//생년월일은 자료형 변환 후 VO에 저장
			String birth = request.getParameter("user_birth");
			memberVO.setUser_birth(java.sql.Date.valueOf(birth));
			
			memberCommand.memberJoin(memberVO);
			nextPage = "/views/member/loginForm.jsp";
		}else if(action.equals("/id_chk.do")) {
			String id = request.getParameter("id");	
			boolean result = false;
			result = memberCommand.id_chk(id);
			if(result == true) {
				pw.print("usable");
			}else{
				pw.print("not_usable");
			}
			return;
		}else if(action.equals("/pw_chkForm.do")) {
			nextPage = "/views/member/pw_chkForm.jsp";
		}else if(action.equals("/pw_chk.do")) {
			boolean result = false;
			String user_id = request.getParameter("id");
			String user_pw = request.getParameter("pw");
		
			result = memberCommand.pw_chk(user_id, user_pw);
			if(result == true) {
				pw.print("usable");
			}else {
				pw.print("not_usable");
			}
			return;
		}else if(action.equals("/myPage.do")) {
			nextPage = "/views/member/myPage.jsp";
		}else if(action.equals("/memberInfoModify.do")) {
			memberVO.setUser_id(request.getParameter("user_id"));
			memberVO.setUser_pw(request.getParameter("user_pw"));
			memberVO.setUser_name(request.getParameter("user_name"));
			memberVO.setUser_phone(request.getParameter("user_phone"));
			String user_email = request.getParameter("user_email") + request.getParameter("email_address");
			memberVO.setUser_email(user_email);
			String birth = request.getParameter("user_birth");
			memberVO.setUser_birth( java.sql.Date.valueOf(birth));
			
			memberCommand.memberInfoModify(memberVO);
			nextPage = "/main/main.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);		
	}
}
