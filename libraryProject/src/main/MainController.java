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
	//�������� DAO
	private NoticeDAO notice = new NoticeDAO();
	ArrayList<NoticeVO> n_list = new ArrayList<NoticeVO>();
	
	//����Ʈ���� DAO
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
		//�������� �ֱٱ� �ҷ�����
		n_list = notice.noticeNearestList();
		request.setAttribute("n_list", n_list);
		//����Ʈ���� �ҷ�����
		b_list = book.b_bestList();
		request.setAttribute("b_list", b_list);
		String nextPate = "/views/main/main.jsp";
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPate);
		dispatch.forward(request, response);
	}
}
