package member.vo;

import java.sql.*;

public class MemberVO {
	String user_id;
	String user_pw;
	String user_name;
	Date user_birth; 
	String user_phone;
	String user_email;
	int loan_num;      //도서 대출 번호
	
	public MemberVO() {
		
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(Date user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getLoan_num() {
		return loan_num;
	}

	public void setLoan_num(int loan_num) {
		this.loan_num = loan_num;
	}
}
