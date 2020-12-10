package free.command;

import java.util.*;

import javax.servlet.http.*;

import common.*;
import free.dao.*;
import free.vo.*;

public class FreeCommand {
	FreeDAO freeDAO;
	FreeVO freeVO;

	public FreeCommand() {
		freeDAO = new FreeDAO();
		freeVO = new FreeVO();
	}
	
	//게시글 목록 조회
	public void freeList(HttpServletRequest request, HttpServletResponse response, PagingVO paging) {
		//게시물 총 개수 조회
		int totalRow = freeDAO.totalRow();
		paging.setTotalRow(totalRow);
		//request.setAttribute("totList", totList); 삭제 예정
		//pageNum값을 DAO로 보낸다. 삭제 예정
		ArrayList<FreeVO> list = freeDAO.freeList(paging);
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
	}
	
	//게시글 작성하기
	public void freeWrite(FreeVO freeVO) {
		freeDAO.freeWrite(freeVO);
	}
	
	//게시글 상세 정보
	public FreeVO freeView(int f_num) {
		freeVO = freeDAO.freeView(f_num);
		return freeVO;
	}
	
	//게시글 수정하기
	public void freeModify(FreeVO freeVO) {
		freeDAO.freeModify(freeVO);
	}
	
	//게시글 삭제하기
	public void freeDelete(int f_num) {
		freeDAO.freeDelete(f_num);
	}
	
	/*삭제 예정
	 * public int freeNumGet() {
		int f_num = freeDAO.freeNumGet();
		return f_num;
	}*/
	
	//게시글 검색하기 f_search : 검색 종류(작성자, 글제목 등..) / q : 검색어
	public void freeSearch(HttpServletRequest request, HttpServletResponse response, String f_search, String q) {
		ArrayList<FreeVO> list = freeDAO.freeSearch(f_search, q);
		request.setAttribute("list", list);
	}
}
