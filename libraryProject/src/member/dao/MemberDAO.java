package member.dao;

import java.sql.*;
import java.text.*;

import javax.naming.*;
import javax.sql.*;

import member.vo.*;

public class MemberDAO {
	DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	MemberVO memberVO;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void memberJoin(MemberVO memberVO) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into lib_member(id, pw, name, birth, phone, email, join_date) values(?, ?, ?, ?, ?, ?, sysdate)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVO.getUser_id());
			pstmt.setString(2, memberVO.getUser_pw());
			pstmt.setString(3, memberVO.getUser_name());
			pstmt.setDate(4, memberVO.getUser_birth());
			pstmt.setString(5, memberVO.getUser_phone());
			pstmt.setString(6, memberVO.getUser_email());
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
	
	public MemberVO login_chk(String id, String pw) {
		memberVO = new MemberVO();
		try {
			conn = dataFactory.getConnection();		                                               
			String query="select * from lib_member where id=? and pw=?";                        			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO.setUser_id(rs.getString("id"));
				memberVO.setUser_pw(rs.getString("pw"));
				memberVO.setUser_name(rs.getString("name"));
				memberVO.setUser_birth(rs.getDate("birth"));
				memberVO.setUser_phone(rs.getString("phone"));
				memberVO.setUser_email(rs.getString("email"));
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
		return memberVO;
	}
	
	public boolean id_chk(String id) {
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			String query = "select decode(count(*),0,'true','false') as result from lib_member where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
				result = Boolean.parseBoolean(rs.getString("result"));
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
		return result;
	}
	
	public boolean pw_chk(String id, String pw) {
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			String query = "select decode(pw, ?, 'true','false') result from lib_member where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			rs.next();
				result = Boolean.parseBoolean(rs.getString("result"));			
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
		return result;
	}
	
	public void memberInfoModify(MemberVO vo) {
		try {
			conn = dataFactory.getConnection();
			String query = "update lib_member set pw=?, name=?, birth=?, phone=?, email=? where id=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getUser_pw());
			pstmt.setString(2, vo.getUser_name());
			pstmt.setDate(3, vo.getUser_birth());
			pstmt.setString(4, vo.getUser_phone());
			pstmt.setString(5, vo.getUser_email());
			pstmt.setString(6, vo.getUser_id());
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
}
