package notice.controller;

import java.io.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.oreilly.servlet.*;

import common.*;
import free.command.*;
import member.vo.*;
import notice.command.*;
import notice.vo.*;

@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	//글에 첨부한 이미지 저장 위치를 상수로 선언한다.
	private static String IMAGE_FOLDER = "C:\\library\\notice\\notice_image";
	NoticeCommand command;
	NoticeVO noticeVO;
	
	public void init() throws ServletException{
		command = new NoticeCommand();
		noticeVO = new NoticeVO();
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
		String nextPage ="";
		String action = request.getPathInfo();
		String servletPath = request.getServletPath();
		String contextPath = request.getContextPath();
		request.setAttribute("action_", action);
		request.setAttribute("servletPath", servletPath);
		request.setAttribute("contextPath", contextPath);
		
		if(action.equals("/noticeList.do")) {
			PagingVO paging = new PagingVO();
			String _nowPage = request.getParameter("nowPage");
			int nowPage = Integer.parseInt(((_nowPage == null) ? "1" : _nowPage));
			paging.setNowPage(nowPage);
			
			String _nowSection = request.getParameter("nowSection");
			int nowSection = Integer.parseInt(((_nowSection == null) ? "0" : _nowSection));
			paging.setNowSection(nowSection);
			
			command.noticeList(request, response, paging);
			
			HttpSession session_ = request.getSession(false);
			MemberVO userInfo = (MemberVO)session_.getAttribute("userInfo");
			if(userInfo != null) {
				if(userInfo.getUser_id().equals("admin")) {
					request.setAttribute("msg", true);
				}
			}
			request.setAttribute("action", "noticeList");
			nextPage = "/views/notice/noticeList.jsp";
		}else if(action.equals("/noticeWriteForm.do")){
			HttpSession session_ = request.getSession(false);
			MemberVO userInfo = (MemberVO)session_.getAttribute("userInfo");
			request.setAttribute("user_id", userInfo.getUser_id());
			request.setAttribute("user_name", userInfo.getUser_name());
			
			nextPage = "/views/notice/noticeWriteForm.jsp";
		}else if(action.equals("/noticeWrite.do")) {
			MultipartRequest multi = new MultipartRequest(request, IMAGE_FOLDER, "utf-8");
			noticeVO.setId(multi.getParameter("id"));
			noticeVO.setN_name(multi.getParameter("n_name"));
			noticeVO.setN_title(multi.getParameter("n_title"));
			noticeVO.setN_content(multi.getParameter("n_content"));
			noticeVO.setImageFileName(multi.getOriginalFileName("imageFileName"));
			String date = multi.getParameter("n_date");
			noticeVO.setN_date(java.sql.Date.valueOf(date));
			
			command.noticeWrite(noticeVO);
			nextPage = "/notice/noticeList.do";
		}else if(action.equals("/noticeView.do")) {
			int n_num = Integer.parseInt(request.getParameter("n_num"));
			command.noticeView(request, response, n_num);
			
			HttpSession session = request.getSession(false);
			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			if(userInfo!=null) {
				if(userInfo.getUser_id().equals("admin")) {
					request.setAttribute("msg", true);
				}
			}else {
				request.setAttribute("mas", false);
			}
			nextPage = "/views/notice/noticeView.jsp";
		}else if(action.equals("/noticeModifyForm.do")) {
			int n_num = Integer.parseInt(request.getParameter("n_num"));
			command.noticeView(request, response, n_num);
			nextPage = "/views/notice/noticeModifyForm.jsp";
		}else if(action.equals("/noticeModify.do")) {
			MultipartRequest multi = new MultipartRequest(request, IMAGE_FOLDER, "utf-8");
			noticeVO.setN_num(Integer.parseInt(multi.getParameter("n_num")));
			noticeVO.setN_name(multi.getParameter("n_name"));
			noticeVO.setN_title(multi.getParameter("n_title"));
			noticeVO.setN_content(multi.getParameter("n_content"));
			noticeVO.setImageFileName(multi.getOriginalFileName("imageFileName"));
			
			String date = multi.getParameter("n_date");
			noticeVO.setN_date(java.sql.Date.valueOf(date));
			command.noticeModify(noticeVO);
			
			nextPage = "/notice/noticeList.do";
		}else if(action.equals("/noticeDelete.do")) {
			int n_num = Integer.parseInt(request.getParameter("n_num"));
			command.noticeDelete(n_num);
			nextPage = "/notice/noticeList.do";
		}else if(action.equals("/noticeSearch.do")) {
			PagingVO paging = new PagingVO();
			String _nowPage = request.getParameter("nowPage");
			int nowPage = Integer.parseInt(((_nowPage == null) ? "1" : _nowPage));
			paging.setNowPage(nowPage);
			
			String _nowSection = request.getParameter("nowSection");
			int nowSection = Integer.parseInt(((_nowSection == null) ? "0" : _nowSection));
			paging.setNowSection(nowSection);
			
			
			String n_search = request.getParameter("n_search");
			if(n_search.equals("all") || n_search==null) {
				n_search = "n_title || n_content";
				request.setAttribute("n_search", "all");
			}else {
				request.setAttribute("n_search", n_search);
			}
			String q = request.getParameter("q");
			request.setAttribute("q", q);
			
			command.noticeSearch(request, response, n_search, q, paging);
			request.setAttribute("action", "noticeSearch");
			
			nextPage = "/views/notice/noticeList.jsp";
		}else if(action.equals("/noticeNearestList.do")) {
			command.noticeNearestList(request, response);
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
