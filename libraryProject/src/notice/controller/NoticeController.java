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
	//�ۿ� ÷���� �̹��� ���� ��ġ�� ����� �����Ѵ�.
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
		String action = request.getPathInfo();
		//���� ���� System.out.println("action : " + action);
		String nextPage ="";
		
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
					//���� ���� System.out.println(userInfo.getUser_id());
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
			
			//���� ����int n_num = command.noticeNumGet();
			//���� ����request.setAttribute("n_num", n_num+1);
			nextPage = "/views/notice/noticeWriteForm.jsp";
		}else if(action.equals("/noticeWrite.do")) {
			MultipartRequest multi = new MultipartRequest(request, IMAGE_FOLDER, "utf-8");
			//���� ����int n_num = Integer.parseInt(multi.getParameter("n_num"));
			noticeVO.setId(multi.getParameter("id"));
			noticeVO.setN_name(multi.getParameter("n_name"));
			noticeVO.setN_title(multi.getParameter("n_title"));
			noticeVO.setN_content(multi.getParameter("n_content"));
			noticeVO.setImageFileName(multi.getOriginalFileName("imageFileName"));
			String date = multi.getParameter("n_date");
			//���� ����SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			noticeVO.setN_date(java.sql.Date.valueOf(date));
			////���� ����java.sql.Date n_date;
		////���� ����n_date = java.sql.Date.valueOf(date);
			
			//noticeVO.setN_num(n_num);
			//���� ����noticeVO.setId(id);
			//���� ����noticeVO.setN_name(n_name);
			//���� ����noticeVO.setN_title(n_title);
			//���� ����noticeVO.setN_content(n_content);
			//���� ����noticeVO.setImageFileName(imageFileName);
		////���� ����noticeVO.setN_date(n_date);
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
						
		////���� ����int n_num = Integer.parseInt(multi.getParameter("n_num"));
		////���� ����System.out.println(n_num);
			noticeVO.setN_num(Integer.parseInt(multi.getParameter("n_num")));
			noticeVO.setN_name(multi.getParameter("n_name"));
			noticeVO.setN_title(multi.getParameter("n_title"));
			noticeVO.setN_content(multi.getParameter("n_content"));
			noticeVO.setImageFileName(multi.getOriginalFileName("imageFileName"));
			
			String date = multi.getParameter("n_date");
		////���� ����SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		////���� ����java.sql.Date n_date;
			//���� ����n_date = java.sql.Date.valueOf(date);
			noticeVO.setN_date(java.sql.Date.valueOf(date));
			command.noticeModify(noticeVO);
			
			nextPage = "/notice/noticeList.do";
		}else if(action.equals("/noticeDelete.do")) {
			int n_num = Integer.parseInt(request.getParameter("n_num"));
			command.noticeDelete(n_num);
			nextPage = "/notice/noticeList.do";
		}else if(action.equals("/noticeSearch.do")) {
			String n_search = request.getParameter("n_search");
			String q = request.getParameter("q");
			command.noticeSearch(request, response, n_search, q);
			request.setAttribute("action", "noticeSearch");
			nextPage = "/views/notice/noticeList.jsp";
		}else if(action.equals("/noticeNearestList.do")) {
			command.noticeNearestList(request, response);
			//���� ���� nextPage = null;
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

	/*//���� ����private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		Map<String ,String> articleMap = new HashMap<String ,String>();
		String encoding="utf-8";
		//�� �̹��� ���� ������ ���� ���� ��ü�� �����Ѵ�.
		File currentDirPath = new File(IMAGE_FOLDER);
		
		//����, �� ������ FileItem�� ���� �׸��� ��� �޸𸮿� �������� �����ϰ� ū �׸��� ��� ��ũ�� �ӽ� ���Ͽ� �������� �����ϴ� �ν��Ͻ��� ����ϴ� . 
		//�������� ��ũ�� ����Ǵ� ũ�� �Ӱ� ���� �ӽ� ������ ���� �� ���͸��� ���������� ���� �����մϴ�.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//������ ũ�� �Ӱ� ������ ū ������ �ӽ÷� �����ϴ� �� ���Ǵ� ���丮�� �����մϴ�.
		factory.setRepository(currentDirPath);
		//������ ��ũ�� ���� ��ϵǴ� ũ�� �Ӱ� ���� ��ȯ�մϴ�.
		factory.setSizeThreshold(1024 * 1024);
		//���� ���ε� ó���� ���� API
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest((RequestContext) request);
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()
							+"="+fileItem.getString(encoding));
					//���� ���ε�� ���� ���۵� �� �� ���� �Ű������� Map�� (key, value)�� ������ �� ��ȯ�ϰ�,
					//�� �۰� ���õ� title ���� Map �����Ѵ�.
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("�Ķ�����̸�:" + fileItem.getFieldName());
					System.out.println("�����̸�:" + fileItem.getName());
					System.out.println("���� ũ��:"+fileItem.getSize() + "bytes");
					//���ε�� ������ ���� �̸��� Map�� ("imageFileName", "���ε������̸�")�� �����Ѵ�.
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					//���ε��� ������ �����ϴ� ��� ���ε��� ������ ���� �̸����� ����ҿ� ���ε��Ѵ�.
					if(fileItem.getSize()>0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx + 1);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						try {
							fileItem.write(uploadFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return articleMap;
	}*/

}
