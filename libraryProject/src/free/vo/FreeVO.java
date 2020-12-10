package free.vo;

import java.sql.*;

public class FreeVO {
	int f_num;        //�Խ��� ��ȣ
	String id;        //�ۼ��� id
	String f_name;    //�ۼ��� �̸�
	String f_title;   //����
	String f_content; //����
	Date f_date;      //�ۼ� ��¥
	int rownum;       //�Խ��� ���� ����(ROWNUM)
	
	public FreeVO() {
	
	}
	
	/*���� ����
	 * public FreeVO(int rownum, int f_num, String f_name, String f_title, String f_content, Date f_date) {
		this.rownum = rownum;
		this.f_num = f_num;
		this.f_name = f_name;
		this.f_title = f_title;
		this.f_content = f_content;
		this.f_date = f_date;
	}*/
	
	/*���� ����
	 * 	public FreeVO(int f_num, String id, String f_name, String f_title, String f_content, Date f_date) {
		this.f_num = f_num;
		this.id = id;
		this.f_name = f_name;
		this.f_title = f_title;
		this.f_content = f_content;
		this.f_date = f_date;
	}*/

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
		
	public int getF_num() {
		return f_num;
	}

	public void setF_num(int f_num) {
		this.f_num = f_num;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_name() {
		return f_name;
	}

	public String getF_title() {
		return f_title;
	}

	public void setF_title(String f_title) {
		this.f_title = f_title;
	}

	public String getF_content() {
		return f_content;
	}

	public void setF_content(String f_content) {
		this.f_content = f_content;
	}

	public Date getF_date() {
		return f_date;
	}

	public void setF_date(Date f_date) {
		this.f_date = f_date;
	}
}
