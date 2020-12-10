package notice.command;

import java.util.*;

import javax.servlet.http.*;

import common.*;
import free.command.*;
import notice.dao.*;
import notice.vo.*;

public class NoticeCommand {
	NoticeDAO dao;
	NoticeVO noticeVO;
	
	public NoticeCommand() {
		dao = new NoticeDAO();
		noticeVO = new NoticeVO();
	}
	
	public void noticeList(HttpServletRequest request ,HttpServletResponse response, PagingVO paging) {
		int totalRow = dao.totalRow();
		paging.setTotalRow(totalRow);
		
		ArrayList<NoticeVO> list = dao.noticeList(paging);
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
	}
	
	/*���� ���� 
	 * public int noticeNumGet() {
		int n_num = dao.noticeNumGet();
		return n_num;
	}*/
	
	public void noticeWrite(NoticeVO noticeVO) {
		dao.noticeWrite(noticeVO);
	}
	
	public void noticeView(HttpServletRequest request, HttpServletResponse response, int n_num) {
		noticeVO = dao.noticeView(n_num);
		request.setAttribute("noticeVO", noticeVO);
	}
	
	public void noticeModify(NoticeVO noticeVO) {
		dao.noticeModify(noticeVO);
	}
	
	public void noticeDelete(int n_num) {
		dao.noticeDelete(n_num);
	}
	
	public void noticeSearch(HttpServletRequest request, HttpServletResponse response,
							String n_search, String q) {
		ArrayList<NoticeVO> list = dao.noticeSearch(n_search, q);
		request.setAttribute("list", list);
	}
	
	public void noticeNearestList(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<NoticeVO> n_list = dao.noticeNearestList();
		request.setAttribute("n_list", n_list);
	}
}
