package main;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import book.dao.*;
import book.vo.*;
import notice.dao.*;
import notice.vo.*;

@WebServlet("/main/*")
public class MainController extends HttpServlet {
	//공지사항 DAO
	private NoticeDAO notice = new NoticeDAO();
	ArrayList<NoticeVO> n_list = new ArrayList<NoticeVO>();
	
	//베스트셀러 DAO
	private BookDAO book = new BookDAO();
	ArrayList<BookVO> b_list = new ArrayList<BookVO>();
	
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
		//공지사항 최근글 불러오기
		n_list = notice.noticeNearestList();
		request.setAttribute("n_list", n_list);
		//베스트셀러 불러오기
		b_list = book.b_bestList();
		request.setAttribute("b_list", b_list);
		String nextPate = "/views/main/main.jsp";
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPate);
		dispatch.forward(request, response);
	}
}
