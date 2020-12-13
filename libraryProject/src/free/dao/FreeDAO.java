package free.dao;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.http.*;
import javax.sql.*;

import common.*;
import free.vo.*;

public class FreeDAO {
	DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	FreeVO freeVO;
	ArrayList<FreeVO> list;
	
	public FreeDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<FreeVO> freeList(PagingVO paging){
		list = new ArrayList<FreeVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from (select ROWNUM as rown, a.* from (select * from lib_freebulletin order by f_num asc) a order by rownum desc) where rown > (?-(?*?)) and rown<=(?-(?*?))+?";
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
				FreeVO freeVO = new FreeVO();
				freeVO.setRownum(rs.getInt("rown"));
				freeVO.setF_num(rs.getInt("f_num"));
				freeVO.setF_name(rs.getString("f_name"));
				freeVO.setF_title(rs.getString("f_title"));
				freeVO.setF_content(rs.getString("f_content"));
				freeVO.setF_date(rs.getDate("f_date"));
				list.add(freeVO);
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
	
	public void freeWrite(FreeVO freeVO) {
		try {
			conn = dataFactory.getConnection();
			String query="insert into lib_freebulletin(f_num, f_name, id, f_title, f_content, f_date) values(SEQ_FREE.NEXTVAL,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, freeVO.getF_name());
			pstmt.setString(2, freeVO.getId());
			pstmt.setString(3, freeVO.getF_title());
			pstmt.setString(4, freeVO.getF_content());
			pstmt.setDate(5, freeVO.getF_date());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public FreeVO freeView(int num) {
		freeVO = new FreeVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from lib_freeBulletin where f_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				freeVO.setF_num(rs.getInt("f_num"));
				freeVO.setId(rs.getString("id"));
				freeVO.setF_name(rs.getString("f_name"));
				freeVO.setF_title(rs.getString("f_title"));
				freeVO.setF_content(rs.getString("f_content"));
				freeVO.setF_date(rs.getDate("f_date"));
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
		return freeVO;
	}
	
	public void freeModify(FreeVO freeVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "update lib_freebulletin set f_title=?, f_content=? where f_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, freeVO.getF_title());
			pstmt.setString(2, freeVO.getF_content());
			pstmt.setInt(3, freeVO.getF_num());
			pstmt.executeUpdate();					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void freeDelete(int f_num) {
		try {
			conn = dataFactory.getConnection();
			String query ="delete from lib_freebulletin where f_num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, f_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<FreeVO> freeSearch(String f_search, String q, PagingVO paging){
		list = new ArrayList<FreeVO>();

		try {
			conn = dataFactory.getConnection();
			String query = "select * from (select ROWNUM as rown, a.* from (select * from lib_freebulletin where "
							+f_search
							+" like ? order by f_num asc) a order by rown desc) where rown>(?-(?*?)) and rown<=(?-(?*?))+?";			
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
				freeVO = new FreeVO();
				freeVO.setRownum(rs.getInt("rown"));
				freeVO.setF_num(rs.getInt("f_num"));
				freeVO.setF_title(rs.getString("f_title"));
				freeVO.setF_name(rs.getString("f_name"));
				freeVO.setF_date(rs.getDate("f_date"));
				list.add(freeVO);
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
	
	//페이징을 위하여 총 게시글 수 가져오기
	public int totalRow() {
		int totRow = 0;
		try {
			conn = dataFactory.getConnection();
			String qeury = "select count(*) from lib_freebulletin";
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
	
	public int totalRow(String f_search, String q) {
		int totRow = 0;
		try {
			conn = dataFactory.getConnection();
			String qeury = "select count(*) from lib_freebulletin where " + f_search + " like ?";
			
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
