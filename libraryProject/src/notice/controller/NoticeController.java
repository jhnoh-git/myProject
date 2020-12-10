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
		String action = request.getPathInfo();
		//삭제 예쩡 System.out.println("action : " + action);
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
					//삭제 예정 System.out.println(userInfo.getUser_id());
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
			
			//삭제 예정int n_num = command.noticeNumGet();
			//삭제 예정request.setAttribute("n_num", n_num+1);
			nextPage = "/views/notice/noticeWriteForm.jsp";
		}else if(action.equals("/noticeWrite.do")) {
			MultipartRequest multi = new MultipartRequest(request, IMAGE_FOLDER, "utf-8");
			//삭제 예정int n_num = Integer.parseInt(multi.getParameter("n_num"));
			noticeVO.setId(multi.getParameter("id"));
			noticeVO.setN_name(multi.getParameter("n_name"));
			noticeVO.setN_title(multi.getParameter("n_title"));
			noticeVO.setN_content(multi.getParameter("n_content"));
			noticeVO.setImageFileName(multi.getOriginalFileName("imageFileName"));
			String date = multi.getParameter("n_date");
			//삭제 예정SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			noticeVO.setN_date(java.sql.Date.valueOf(date));
			////삭제 예정java.sql.Date n_date;
		////삭제 예정n_date = java.sql.Date.valueOf(date);
			
			//noticeVO.setN_num(n_num);
			//삭제 예정noticeVO.setId(id);
			//삭제 예정noticeVO.setN_name(n_name);
			//삭제 예정noticeVO.setN_title(n_title);
			//삭제 예정noticeVO.setN_content(n_content);
			//삭제 예정noticeVO.setImageFileName(imageFileName);
		////삭제 예정noticeVO.setN_date(n_date);
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
						
		////삭제 예정int n_num = Integer.parseInt(multi.getParameter("n_num"));
		////삭제 예정System.out.println(n_num);
			noticeVO.setN_num(Integer.parseInt(multi.getParameter("n_num")));
			noticeVO.setN_name(multi.getParameter("n_name"));
			noticeVO.setN_title(multi.getParameter("n_title"));
			noticeVO.setN_content(multi.getParameter("n_content"));
			noticeVO.setImageFileName(multi.getOriginalFileName("imageFileName"));
			
			String date = multi.getParameter("n_date");
		////삭제 예정SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		////삭제 예정java.sql.Date n_date;
			//삭제 예정n_date = java.sql.Date.valueOf(date);
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
			//삭제 예정 nextPage = null;
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

	/*//삭제 예정private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		Map<String ,String> articleMap = new HashMap<String ,String>();
		String encoding="utf-8";
		//글 이미지 저장 폴더에 대해 파일 객체를 생성한다.
		File currentDirPath = new File(IMAGE_FOLDER);
		
		//서블릿, 이 구현은 FileItem더 작은 항목의 경우 메모리에 콘텐츠를 유지하고 큰 항목의 경우 디스크의 임시 파일에 콘텐츠를 유지하는 인스턴스를 만듭니다 . 
		//콘텐츠가 디스크에 저장되는 크기 임계 값은 임시 파일이 생성 될 디렉터리와 마찬가지로 구성 가능합니다.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//구성된 크기 임계 값보다 큰 파일을 임시로 저장하는 데 사용되는 디렉토리를 설정합니다.
		factory.setRepository(currentDirPath);
		//파일이 디스크에 직접 기록되는 크기 임계 값을 반환합니다.
		factory.setSizeThreshold(1024 * 1024);
		//파일 업로드 처리를 위한 API
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest((RequestContext) request);
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()
							+"="+fileItem.getString(encoding));
					//파일 업로드로 같이 전송된 새 글 관련 매개변수를 Map에 (key, value)로 저장한 후 반환하고,
					//새 글과 관련된 title 등을 Map 저장한다.
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("파라미터이름:" + fileItem.getFieldName());
					System.out.println("파일이름:" + fileItem.getName());
					System.out.println("파일 크기:"+fileItem.getSize() + "bytes");
					//업로드된 파일의 파일 이름을 Map에 ("imageFileName", "업로드파일이름")로 저장한다.
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					//업로드한 파일이 존재하는 경우 업로드한 파일의 파일 이름으로 저장소에 업로드한다.
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
