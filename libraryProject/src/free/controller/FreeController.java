package free.controller;

import java.io.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import common.*;
import free.command.*;
import free.vo.*;
import member.vo.*;

@WebServlet("/free/*")
public class FreeController extends HttpServlet {
	FreeCommand freeCommand;
	FreeVO freeVO;
	HttpSession session;
	
	public void init() throws ServletException{
		freeCommand = new FreeCommand();
		freeVO = new FreeVO();
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
		//���� ó�� �� �̵��� ������
		String nextPage = "";
		//URI �߰� ��� ���� ��������
		String action = request.getPathInfo();
		//���� ���� System.out.println("action : " + action);
		
		
		if(action.equals("/freeList.do")) {     //�Խñ� ��� ��ȸ
			//����¡�� �ʿ��� �� �����ϱ� ���Ͽ� VO��ü ����
			PagingVO paging = new PagingVO();
			//����ڰ� ������ ������ ��ȣ.
			String _nowPage = request.getParameter("nowPage");
			//nowPage�� null�� ��� 1���� �����Ѵ�.(�ʱⰪ)
			int nowPage = Integer.parseInt(((_nowPage == null) ? "1" : _nowPage));
			paging.setNowPage(nowPage);
			
			//����ڰ� ������ �������� ���� ��ȣ.
			String _nowSection = request.getParameter("nowSection");
			int nowSection = Integer.parseInt(((_nowSection == null) ? "0" : _nowSection));
			paging.setNowSection(nowSection);
			
			freeCommand.freeList(request, response, paging);
			
			//������ ����('�۸��','�˻����')�� ����.
			request.setAttribute("action", "freeList");
			nextPage ="/views/free/freeList.jsp";
		}else if(action.equals("/freeWrite.do")) {     //�Խñ� �ۼ��ϱ�
			freeVO.setId(request.getParameter("id"));
			//���� ���� int f_num = Integer.parseInt(request.getParameter("f_num"));
			freeVO.setF_name(request.getParameter("f_name"));
			freeVO.setF_title(request.getParameter("f_title"));
			freeVO.setF_content(request.getParameter("f_content"));
			String date = request.getParameter("f_date");
			//���� ���� System.out.println("date : " + date);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			// ���� ���� java.sql.Date f_date;
			freeVO.setF_date(java.sql.Date.valueOf(date));
			// ���� ���� System.out.println("f_date : " + f_date);
			
			//���� ���� freeVO.setId(id);
			//freeVO.setF_num(f_num);
			/*���� ����freeVO.setF_name(f_name);
			freeVO.setF_title(f_title);
			freeVO.setF_content(f_content);
			freeVO.setF_date(f_date);
			*/
			freeCommand.freeWrite(freeVO);
			nextPage = "/free/freeList.do";
		}else if(action.equals("/freeView.do")) {     //�Խñ� �� ����
			int f_num = Integer.parseInt(request.getParameter("f_num"));
			freeVO = freeCommand.freeView(f_num);
			request.setAttribute("freeVO", freeVO);
			
			//�Խñ� '����' ������ ���ؼ� session�� ����� ������ �����´�.
			session = request.getSession(false);
			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			if(userInfo != null) {
				String user_id = (String)userInfo.getUser_id();
				//���� ���� System.out.println("user_id : " + user_id);
				if(user_id.equals(freeVO.getId())){
					request.setAttribute("login", true);
				}
			}

			nextPage ="/views/free/freeView.jsp";
		}else if(action.equals("/freeWriteForm.do")) {     //�Խñ� �ۼ��ϱ�
			session = request.getSession(false);
			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			if(userInfo != null) {
				request.setAttribute("user_name", userInfo.getUser_name());
				request.setAttribute("user_id", userInfo.getUser_id());
				//���� ���� int f_num = freeCommand.freeNumGet();
				//���� ���� request.setAttribute("f_num", f_num+1);
			}else {
				request.setAttribute("login", false);
			}
			nextPage = "/views/free/freeWriteForm.jsp";
		}else if(action.equals("/freeModifyForm.do")) {     //�Խñ� ���� �������� �̵�
			int f_num = Integer.parseInt(request.getParameter("f_num"));
			freeVO = freeCommand.freeView(f_num);
			request.setAttribute("freeVO", freeVO);
			nextPage = "/views/free/freeModifyForm.jsp";
		}else if(action.equals("/freeModify.do")) {     //�Խñ� �����ϱ�
			session = request.getSession(false);
			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			String user_id = null;
			if(userInfo!=null) {
				user_id = userInfo.getUser_id();
			}
		
			freeVO.setF_num(Integer.parseInt(request.getParameter("f_num")));
			freeVO.setF_name(request.getParameter("f_name"));
			freeVO.setF_title(request.getParameter("f_title"));
			freeVO.setF_content(request.getParameter("f_content"));
			String date = request.getParameter("f_date");
			//���� ���� System.out.println("date : " + date);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-");
			//���� ���� f_date);java.sql.Date f_date = 
			freeVO.setF_date(java.sql.Date.valueOf(date));
			
			/*���� ����
			freeVO.setF_num(f_num);
			freeVO.setF_name(f_name);
			freeVO.setF_title(f_title);
			freeVO.setF_content(f_content);
			freeVO.setF_date(f_date);
			freeVO.setId(user_id);
			*/
			freeCommand.freeModify(freeVO);
			nextPage = "/free/freeList.do";
		}else if(action.equals("/freeDelete.do")) {     //�Խñ� �����ϱ�
			int f_num = Integer.parseInt(request.getParameter("f_num"));
			freeCommand.freeDelete(f_num);
			nextPage ="/free/freeList.do";
		}else if(action.equals("/freeSearch.do")) {     //�Խñ� �˻��ϱ�
			//�˻� �η�(�ۼ���, ������ ���)
			String f_search = request.getParameter("f_search");
			//�˻���
			String q = request.getParameter("q");
			freeCommand.freeSearch(request, response, f_search, q);
			request.setAttribute("action", "freeSearch");
			nextPage = "/views/free/freeList.jsp";
		}
				
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
