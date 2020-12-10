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
	
	//�Խñ� ��� ��ȸ
	public void freeList(HttpServletRequest request, HttpServletResponse response, PagingVO paging) {
		//�Խù� �� ���� ��ȸ
		int totalRow = freeDAO.totalRow();
		paging.setTotalRow(totalRow);
		//request.setAttribute("totList", totList); ���� ����
		//pageNum���� DAO�� ������. ���� ����
		ArrayList<FreeVO> list = freeDAO.freeList(paging);
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
	}
	
	//�Խñ� �ۼ��ϱ�
	public void freeWrite(FreeVO freeVO) {
		freeDAO.freeWrite(freeVO);
	}
	
	//�Խñ� �� ����
	public FreeVO freeView(int f_num) {
		freeVO = freeDAO.freeView(f_num);
		return freeVO;
	}
	
	//�Խñ� �����ϱ�
	public void freeModify(FreeVO freeVO) {
		freeDAO.freeModify(freeVO);
	}
	
	//�Խñ� �����ϱ�
	public void freeDelete(int f_num) {
		freeDAO.freeDelete(f_num);
	}
	
	/*���� ����
	 * public int freeNumGet() {
		int f_num = freeDAO.freeNumGet();
		return f_num;
	}*/
	
	//�Խñ� �˻��ϱ� f_search : �˻� ����(�ۼ���, ������ ��..) / q : �˻���
	public void freeSearch(HttpServletRequest request, HttpServletResponse response, String f_search, String q) {
		ArrayList<FreeVO> list = freeDAO.freeSearch(f_search, q);
		request.setAttribute("list", list);
	}
}
