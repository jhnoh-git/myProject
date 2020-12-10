package free.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import common.*;
import free.command.*;
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
			//���� ���� String query = "select ROWNUM, a.* from (select * from lib_freebulletin order by f_num asc) a order by rownum desc";
			//���� ���� String query ="select * from ( "+
			//���� ����			"select ROWNUM, a.* from (select * from lib_freebulletin order by f_num asc) a order by rownum desc)"
				//���� ����		+ "where ROWNUM between(?-1)*10 and (?*10)+10";
			//���� ���� String query = "select * from (select ROWNUM as rown, a.* from (select * from lib_freebulletin order by f_num desc) a order by rownum asc) where rown between (?-1)*10 and (?*10)";
			String query = "select * from (select ROWNUM as rown, a.* from (select * from lib_freebulletin order by f_num asc) a order by rownum desc) where rown > (?-(?*?)) and rown<=(?-(?*?))+?";
			//���� ���� System.out.println("query : " + query);
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
				//int rown = rs.getInt("rown"); ���� ����
				freeVO.setRownum(rs.getInt("rown"));
				//int f_num = rs.getInt("f_num");���� ����
				freeVO.setF_num(rs.getInt("f_num"));
				//String f_name = rs.getString("f_name");���� ����
				freeVO.setF_name(rs.getString("f_name"));
				//String f_title = rs.getString("f_title");���� ����
				freeVO.setF_title(rs.getString("f_title"));
				//String f_content = rs.getString("f_content");���� ����
				freeVO.setF_content(rs.getString("f_content"));
				freeVO.setF_date(rs.getDate("f_date"));
				//FreeVO freeVO = new FreeVO(rown, f_num, f_name, f_title, f_content, f_date);���� ����
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
			/*���� ���� String query="insert into lib_freebulletin(f_num, f_name, id, f_title, f_content, f_date)" + 
			"values(?,?,?,?,?,?)";*/
			String query="insert into lib_freebulletin(f_num, f_name, id, f_title, f_content, f_date) values(SEQ_FREE.NEXTVAL,?,?,?,?,?)";
			//���� ���� System.out.println("query : " + query);
			pstmt = conn.prepareStatement(query);
			//���� ���� pstmt.setInt(1, freeVO.getF_num());
			pstmt.setString(1, freeVO.getF_name());
			pstmt.setString(2, freeVO.getId());
			pstmt.setString(3, freeVO.getF_title());
			pstmt.setString(4, freeVO.getF_content());
			pstmt.setDate(5, freeVO.getF_date());
			//���� ���� pstmt.setDate(5, java.sql.Date.valueOf("2020-11-25"));
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
			//���� ���� System.out.println("query : " + query);
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
				
				/* ���� ����
				freeVO = new FreeVO();
				freeVO.setF_num(f_num);
				freeVO.setId(id);
				freeVO.setF_name(f_name);
				freeVO.setF_title(f_title);
				freeVO.setF_content(f_content);
				freeVO.setF_date(f_date);
				*/
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
	
	/* ���� ���� public int freeNumGet() {
		try {
			conn = dataFactory.getConnection();
			//String query = "SELECT max(F_NUM) from lib_freebulletin";
			String query = "select ex_seq.nextval from dual";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}*/
	
	
	public ArrayList<FreeVO> freeSearch(String f_search, String q){
		list = new ArrayList<FreeVO>();
		try {
			conn = dataFactory.getConnection();
			//���� ���� System.out.println("f_search : " + f_search);
			//���� ���� System.out.println("q : " + q);
			/*���� ���� String query = "select * from lib_freebulletin where "+ f_search + " like ? order by f_num desc"; */
			String query = "select rownum, a.* from (select * from lib_freebulletin where "
							+f_search
							+" like ? order by f_num asc) a order by rownum desc";			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+q+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				freeVO = new FreeVO();
				freeVO.setRownum(rs.getInt("rownum"));
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
	
	//����¡�� ���Ͽ� �� �Խñ� �� ��������
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
		//���� ���� System.out.println("totList ���� : " + rs);
		return totRow;
	}
}
