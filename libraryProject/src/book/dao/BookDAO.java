package book.dao;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import book.vo.*;
import common.*;
import free.command.*;
import free.vo.*;

public class BookDAO {
	DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	private static String B_IMAGE_FOLDER = "C:\\library\\books\\books_image";
	
	BookVO bookVO;
	ArrayList<BookVO> list;
	
	public BookDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BookVO> booksList(PagingVO paging) {
		list = new ArrayList<BookVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from (select ROWNUM as rown, a.* from (select * from lib_books order by b_num asc) a order by rown desc) where rown>(?-(?*?)) and rown<=(?-(?*?))+?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, paging.getTotalRow());
			pstmt.setInt(2, paging.getNowPage());
			pstmt.setInt(3, paging.getPage_Row());
			pstmt.setInt(4, paging.getTotalRow());
			pstmt.setInt(5, paging.getNowPage());
			pstmt.setInt(6, paging.getPage_Row());
			pstmt.setInt(7, paging.getPage_Row());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setB_num(rs.getInt("b_num"));
				bookVO.setB_title(rs.getString("b_title"));
				bookVO.setB_name(rs.getString("b_name"));
				bookVO.setB_content(rs.getString("b_content"));
				bookVO.setB_date(rs.getDate("b_date"));
				bookVO.setB_state(rs.getString("b_state"));
				bookVO.setB_imageName(rs.getString("b_imagename"));
				bookVO.setId(rs.getString("id"));
				list.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int bookNumGet() {
		try {
			conn = dataFactory.getConnection();
			String query = "select max(b_num) from lib_books";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public void bookAdd(BookVO bookVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into lib_books(b_num, b_imageName, b_title, b_name, b_date, b_content, best)"+
						"values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookVO.getB_num());
			pstmt.setString(2, bookVO.getB_imageName());
			pstmt.setString(3, bookVO.getB_title());
			pstmt.setString(4, bookVO.getB_name());
			pstmt.setDate(5, (Date) bookVO.getB_date());
			pstmt.setString(6, bookVO.getB_content());
			pstmt.setString(7, bookVO.getBest());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public BookVO bookView(int b_num) {
		try {
			conn = dataFactory.getConnection();
			String query = "select * from lib_books where b_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setB_num(rs.getInt("b_num"));
				bookVO.setB_title(rs.getString("b_title"));
				bookVO.setB_name(rs.getString("b_name"));
				bookVO.setB_content(rs.getString("b_content"));
				bookVO.setB_date(rs.getDate("b_date"));
				bookVO.setB_state(rs.getString("b_state"));
				bookVO.setB_imageName(rs.getString("b_imageName"));
				bookVO.setId(rs.getString("id"));
				bookVO.setBest(rs.getString("best"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bookVO;
	}
	
	public void bookLoan(String id, int b_num, String b_title) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into lib_loanList(loan_num, id, b_num, b_title)"+
			"values(loan_num.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, b_num);
			pstmt.setString(3, b_title);
			pstmt.executeUpdate();
			bookState(b_num, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void bookState(int b_num, String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "update lib_books set b_state=?, id=? where b_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "0");
			pstmt.setString(2, id);
			pstmt.setInt(3, b_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<BookVO> bookSearch(String b_search, String q, PagingVO paging){
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from (select ROWNUM as rown, a.* from (select * from lib_books where "
							+b_search
							+" like ? order by b_num desc) a order by rown desc) where rown > (?-(?*?)) and rown <= (?-(?*?))+?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+q+"%");
			pstmt.setInt(2, paging.getTotalRow());
			pstmt.setInt(3, paging.getNowPage());
			pstmt.setInt(4, paging.getPage_Row());
			pstmt.setInt(5, paging.getTotalRow());
			pstmt.setInt(6, paging.getNowPage());
			pstmt.setInt(7, paging.getPage_Row());
			pstmt.setInt(8, paging.getPage_Row());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setB_num(rs.getInt("b_num"));
				bookVO.setB_title(rs.getString("b_title"));
				bookVO.setB_imageName(rs.getString("b_imageName"));
				bookVO.setB_name(rs.getString("b_name"));
				bookVO.setB_state(rs.getString("b_state"));
				list.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void bookModify(BookVO vo) {
		try {
			conn = dataFactory.getConnection();
			String query = null;
			if(vo.getB_imageName()==null) {
				query = "update lib_books set b_title=?, b_name=?, b_date=?, b_content=?, best=? where  b_num=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, vo.getB_title());
				pstmt.setString(2, vo.getB_name());
				pstmt.setDate(3, (Date)vo.getB_date());
				pstmt.setString(4, vo.getB_content());
				pstmt.setString(5, vo.getBest());
				pstmt.setInt(6, vo.getB_num());
			}else {
				query = "update lib_books set b_imageName=?, b_title=?, b_name=?, b_date=?, b_content=?, best=? where  b_num=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, vo.getB_imageName());
				pstmt.setString(2, vo.getB_title());
				pstmt.setString(3, vo.getB_name());
				pstmt.setDate(4, (Date)vo.getB_date());
				pstmt.setString(5, vo.getB_content());
				pstmt.setString(6, vo.getBest());
				pstmt.setInt(7, vo.getB_num());
			}
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void bookDelete(int b_num, String b_imageName) {
		File file = new File(B_IMAGE_FOLDER + "\\" +b_imageName);
		try {
			conn = dataFactory.getConnection();
			String query = "delete from lib_books where b_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b_num);
			pstmt.execute();
			
			if(file.exists()) {
				file.delete();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<BookVO> b_bestList(){
		list = new ArrayList<BookVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select b_imageName, b_num, b_title, b_state from lib_books where best=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "1");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setB_imageName(rs.getString("b_imageName"));
				bookVO.setB_num(rs.getInt("b_num"));
				bookVO.setB_title(rs.getString("b_title"));
				bookVO.setB_state(rs.getString("b_state"));
				list.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<BookVO> bookLoanList(String id){
		list = new ArrayList<BookVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select b_num, b_title, LOANDATE, RETURNDATE, RETURNEXPECTED from lib_loanlist where id=? order by loan_num desc";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bookVO = new BookVO();
				bookVO.setB_num(rs.getInt("b_num"));
				bookVO.setB_title(rs.getString("b_title"));
				bookVO.setLoanDate(rs.getDate("loanDate"));
				bookVO.setReturnDate(rs.getDate("returnDate"));
				bookVO.setReturnexpected(rs.getDate("returnexpected"));
				list.add(bookVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void bookReturn(int b_num, String id, String b_title) {
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE LIB_LOANLIST SET RETURNDATE=SYSDATE where LOAN_NUM=(SELECT MAX(LOAN_NUM) FROM LIB_LOANLIST where id='"+id+"' AND b_title='"+b_title+"')";
			pstmt = conn.prepareStatement(query);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		bookReturnState(b_num);
	}
	
	public void bookReturnState(int b_num) {
		try {
			conn = dataFactory.getConnection();
			String query = "update lib_books set b_state=?, id=? where b_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "1");
			pstmt.setString(2, null);
			pstmt.setInt(3, b_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int totalRow() {
		int totRow = 0;
		try {
			conn = dataFactory.getConnection();
			String qeury = "select count(*) from lib_books";
			pstmt = conn.prepareStatement(qeury);
			rs = pstmt.executeQuery();
			if(rs.next())
				totRow = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totRow;
	}
	
	public int totalRow(String b_search, String q) {
		int totRow = 0;
		try {
			conn = dataFactory.getConnection();
			String qeury = "select count(*) from lib_books where " + b_search + " like ?";
			pstmt = conn.prepareStatement(qeury);
			pstmt.setString(1, "%"+q+"%");
			rs = pstmt.executeQuery();
			if(rs.next())
				totRow = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totRow;
	}
}
