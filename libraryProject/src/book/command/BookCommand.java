package book.command;

import java.util.*;
import javax.servlet.http.*;
import book.dao.*;
import book.vo.*;
import common.*;
import free.command.*;

public class BookCommand {
	BookDAO dao;
	BookVO vo;
	
	public BookCommand() {
		dao = new BookDAO();
		vo = new BookVO();
	}
	
	public void booksList(HttpServletRequest request, HttpServletResponse response, PagingVO paging) {
		int totalRow = dao.totalRow();
		//»èÁ¦ ¿¹ÂÄ System.out.println("command totalRow : " + totalRow);
		paging.setTotalRow(totalRow);
		
		ArrayList<BookVO> list = dao.booksList(paging);
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
	}
	
	public void bookNumGet(HttpServletRequest request, HttpServletResponse response) {
		int b_num = dao.bookNumGet();
		request.setAttribute("b_num", (b_num+1));
	}
	
	public void bookAdd(BookVO bookVO) {
		dao.bookAdd(bookVO);
	}
	
	public void bookView(HttpServletRequest request, HttpServletResponse response, int b_num) {
		vo = dao.bookView(b_num);
		request.setAttribute("vo", vo);
	}
	
	public void bookLoan(String id, int b_num, String b_title) {
		dao.bookLoan(id, b_num, b_title);
	}
	
	public void booksSearch(HttpServletRequest request, HttpServletResponse response, String b_search, String q) {
		ArrayList<BookVO> list = dao.bookSearch(b_search, q);
		request.setAttribute("list", list);
	}
	
	public void bookModify(BookVO vo) {
		dao.bookModify(vo);
	}
	
	public void bookDelete(int b_num, String b_imageName) {
		dao.bookDelete(b_num, b_imageName);
	}
	
	public void bookLoanList(HttpServletRequest request, HttpServletResponse response, String id) {
		ArrayList<BookVO> list = dao.bookLoanList(id);
		request.setAttribute("list", list);
	}
	
	public void bookReturn(int b_num, String id, String b_title) {
		dao.bookReturn(b_num, id, b_title);
	}
}
