package notice.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import common.*;
import free.command.*;
import notice.vo.*;

public class NoticeDAO {
	DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	NoticeVO noticeVO;
	ArrayList<NoticeVO> list;
	
	public NoticeDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NoticeVO> noticeList(PagingVO paging) {
		list = new ArrayList<NoticeVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from (select rownum as rown, a.* from (select * from lib_notice order by n_num asc) a order by rown desc) where rown > (?-(?*?)) and rown <= (?-(?*?))+?";
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
				noticeVO = new NoticeVO();
				noticeVO.setRownum(rs.getInt("rown"));
				noticeVO.setN_num(rs.getInt("n_num"));
				noticeVO.setId(rs.getString("id"));
				noticeVO.setN_name(rs.getString("n_name"));
				noticeVO.setN_title(rs.getString("n_title"));
				noticeVO.setN_content(rs.getString("n_content"));
				noticeVO.setImageFileName(rs.getString("imageFileName"));
				noticeVO.setN_date(rs.getDate("n_date"));
				list.add(noticeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void noticeWrite(NoticeVO noticeVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into lib_notice(n_num, n_title, n_name, n_content, n_date, imageFileName, id)"+
			"values(seq_notice.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeVO.getN_title());
			pstmt.setString(2, noticeVO.getN_name());
			pstmt.setString(3, noticeVO.getN_content());
			pstmt.setDate(4, noticeVO.getN_date());
			pstmt.setString(5, noticeVO.getImageFileName());
			pstmt.setString(6, noticeVO.getId());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public NoticeVO noticeView(int n_num) {
		noticeVO = new NoticeVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from lib_notice where n_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, n_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				noticeVO.setId(rs.getString("id"));
				noticeVO.setImageFileName(rs.getString("imageFileName"));
				noticeVO.setN_content(rs.getString("n_content"));
				noticeVO.setN_date(rs.getDate("n_date"));
				noticeVO.setN_name(rs.getString("n_name"));
				noticeVO.setN_num(rs.getInt("n_num"));
				noticeVO.setN_title(rs.getString("n_title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return noticeVO;
	}
	
	public void noticeModify(NoticeVO noticeVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "";
			
			if(noticeVO.getImageFileName()==null || noticeVO.getImageFileName().length()==0) {
				query = "update lib_notice set n_title=?, n_content=?, n_date=? where n_num=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, noticeVO.getN_title());
				pstmt.setString(2, noticeVO.getN_content());
				pstmt.setDate(3, noticeVO.getN_date());
				pstmt.setInt(4, noticeVO.getN_num());
			}else {
				query = "update lib_notice set n_title=?, n_content=?, n_date=?, imageFileName=? where n_num=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, noticeVO.getN_title());
				pstmt.setString(2, noticeVO.getN_content());
				pstmt.setDate(3, noticeVO.getN_date());
				pstmt.setString(4, noticeVO.getImageFileName());
				pstmt.setInt(5, noticeVO.getN_num());
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void noticeDelete(int n_num) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from lib_notice where n_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, n_num);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<NoticeVO> noticeSearch(String n_search, String q, PagingVO paging){
		list = new ArrayList<NoticeVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from (select ROWNUM as rown, a.* from (select * from lib_notice where "
							+n_search
							+" like ? order by n_num asc) a order by rown desc) where rown>(?-(?*?)) and rown<=(?-(?*?))+?";
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
				noticeVO = new NoticeVO();
				noticeVO.setRownum(rs.getInt("rown"));
				noticeVO.setN_num(rs.getInt("n_num"));
				noticeVO.setN_title(rs.getString("n_title"));
				noticeVO.setN_name(rs.getString("n_name"));
				noticeVO.setN_date(rs.getDate("n_date"));
				list.add(noticeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<NoticeVO> noticeNearestList(){
		list = new ArrayList<NoticeVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from (select ROWNUM, a.* from (select * from lib_notice order by n_num asc) a order by rownum desc) where ROWNUM <= 5";
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				noticeVO = new NoticeVO();	
				noticeVO.setN_date(rs.getDate("n_date"));
				noticeVO.setN_name(rs.getString("n_name"));
				noticeVO.setN_num(rs.getInt("n_num"));
				noticeVO.setN_title(rs.getString("n_title"));
				noticeVO.setRownum(rs.getInt("rownum"));
				list.add(noticeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	public int totalRow() {
		int totRow = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) from lib_notice";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next())
				totRow = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	
	public int totalRow(String n_search, String q) {
		int totRow = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) from lib_notice where " + n_search + " like ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+q+"%");
			rs = pstmt.executeQuery();
			if(rs.next())
				totRow = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
