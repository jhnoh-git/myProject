package book.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.oreilly.servlet.*;

import book.command.*;
import book.vo.*;
import common.*;
import free.command.*;
import member.vo.*;

@WebServlet("/books/*")
public class BookController extends HttpServlet {
	private static String IMAGE_FOLDER = "C:\\library\\books\\books_image";
	BookCommand command;
	BookVO bookVO;
	
	public void init() throws ServletException{
		command = new BookCommand();
		bookVO = new BookVO();
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
		String nextPage = null;
		PrintWriter out = response.getWriter();
		
		
		//삭제 System.out.println("book action : " + action);
		if(action.equals("/bookList.do")) {
			PagingVO paging = new PagingVO();
			String _nowPage = request.getParameter("nowPage");
			int nowPage = Integer.parseInt(((_nowPage == null) ? "1" : _nowPage));
			//삭제 System.out.println("nowPage : " + nowPage);
			paging.setNowPage(nowPage);
			
			String _nowSection = request.getParameter("nowSection");
			int nowSection = Integer.parseInt(((_nowSection == null) ? "0" : _nowSection));
			//삭제 System.out.println("nowSection : " + nowSection);
			paging.setNowSection(nowSection);
			
			
			command.booksList(request, response, paging);
			
			request.setAttribute("imagePath", IMAGE_FOLDER);
			
			HttpSession session = request.getSession(false);
			MemberVO userInfo = (MemberVO)session.getAttribute("userInfo");
			if(userInfo != null) {
				if(userInfo.getUser_id()!=null && userInfo.getUser_id().equals("admin")) {
				request.setAttribute("admin", true);
				}
			}
			nextPage = "/views/books/booksList.jsp";
		}else if(action.equals("/bookAddForm.do")) {
			command.bookNumGet(request, response);
			nextPage = "/views/books/bookAddForm.jsp";
		}else if(action.equals("/bookAdd.do")) {
			MultipartRequest multi = new MultipartRequest(request, IMAGE_FOLDER, "utf-8");
			bookVO.setB_num(Integer.parseInt(multi.getParameter("b_num")));
			bookVO.setB_imageName(multi.getFilesystemName("imageName"));
			bookVO.setB_title(multi.getParameter("b_title"));
			bookVO.setB_name(multi.getParameter("b_name"));
			//삭젝 SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			bookVO.setB_date(java.sql.Date.valueOf(multi.getParameter("b_date")));
			bookVO.setB_content(multi.getParameter("b_content"));
			bookVO.setBest(multi.getParameter("best"));
			
			command.bookAdd(bookVO);
			nextPage = "/books/bookList.do";
		}else if(action.equals("/bookView.do")) {
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			HttpSession session_ = request.getSession(false);
			MemberVO userInfo = (MemberVO)session_.getAttribute("userInfo");
			if(userInfo != null) {
				if(userInfo.getUser_id() != null && userInfo.getUser_id().length() != 0) {
					request.setAttribute("id", userInfo.getUser_id());
				}
			}
			command.bookView(request, response, b_num);
			nextPage = "/views/books/bookView.jsp";
		}else if(action.equals("/bookLoan.do")) {
			String id = request.getParameter("id");
			if(id == null || id.length() == 0) {
				String next_page = request.getParameter("next_page");
				nextPage = "/views/member/loginForm.jsp?next_page="+next_page;
				//삭제 System.out.println("nextPage : "+ nextPage);
			}else {
				int b_num = Integer.parseInt(request.getParameter("b_num"));
				String b_title = request.getParameter("b_title");
				command.bookLoan(id, b_num, b_title);
				request.setAttribute("msg", true);
				nextPage = "/books/bookList.do";
				/*out.println("<script>alert('대출이 완료 되었습니다.');</script>");
				out.flush();*/
			}
		}else if(action.equals("/booksSearch.do")) {
			String b_search = request.getParameter("b_search");
			String q = request.getParameter("q");
			command.booksSearch(request, response, b_search, q);
			nextPage = "/views/books/booksList.jsp";
		}else if(action.equals("/bookModifyForm.do")) {
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			command.bookView(request, response, b_num);
			nextPage = "/views/books/bookModifyForm.jsp";
		}else if(action.equals("/bookModify.do")) {
			MultipartRequest multi = new MultipartRequest(request, IMAGE_FOLDER, "utf-8");
			bookVO.setB_num(Integer.parseInt(multi.getParameter("b_num")));
			bookVO.setB_imageName(multi.getFilesystemName("b_imageName"));
			//삭제 System.out.println("Contol b_imageName : " + multi.getFilesystemName("b_imageName"));
			bookVO.setB_title(multi.getParameter("b_title"));
			bookVO.setB_name(multi.getParameter("b_name"));
			bookVO.setB_content(multi.getParameter("b_content"));			
			bookVO.setB_date(java.sql.Date.valueOf(multi.getParameter("b_date")));
			bookVO.setBest(multi.getParameter("best"));
			command.bookModify(bookVO);
			nextPage = "/books/bookList.do";
		}else if(action.equals("/bookDelete.do")) {
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			String b_imageName = request.getParameter("b_imageName");
			command.bookDelete(b_num, b_imageName);
			nextPage = "/books/bookList.do";
		}else if(action.equals("/bookLoanList.do")) {
			String id = request.getParameter("id");
			command.bookLoanList(request, response, id);
			nextPage = "/views/member/loanList.jsp";
		}else if(action.equals("/bookReturn.do")) {
			int b_num = Integer.parseInt(request.getParameter("b_num"));
			String id = request.getParameter("id");
			String b_title = request.getParameter("b_title");
			command.bookReturn(b_num, id, b_title);
			nextPage = "/books/bookList.do";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
