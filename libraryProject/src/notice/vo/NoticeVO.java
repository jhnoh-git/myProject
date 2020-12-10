package notice.vo;

import java.sql.*;

public class NoticeVO {
	int rownum;          //게시판 정렬 순번(ROWNUM)
	int n_num;
	String id;
	String n_name;
	String n_title;
	String n_content;
	String imageFileName;
	Date n_date;
	
	public NoticeVO() {
	}
	
	/*삭제 예정
	 * public NoticeVO(int rownum, int n_num, String id, String n_name, String n_title, String n_content, String imageFileName, Date n_date) {
		this.rownum = rownum;
		this.n_num = n_num;
		this.id = id;
		this.n_name = n_name;
		this.n_title = n_title;
		this.n_content = n_content;
		this.imageFileName = imageFileName;
		this.n_date = n_date;
	}*/
	
	/*삭제 예정
	 * public NoticeVO(int rownum, int n_num, String n_title, String n_name, Date n_date) {
		this.rownum = rownum;
		this.n_num = n_num;
		this.n_title = n_title;
		this.n_name = n_name;
		this.n_date = n_date;
	}*/

	
	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getN_num() {
		return n_num;
	}

	public void setN_num(int n_num) {
		this.n_num = n_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getN_name() {
		return n_name;
	}

	public void setN_name(String n_name) {
		this.n_name = n_name;
	}

	public String getN_title() {
		return n_title;
	}

	public void setN_title(String n_title) {
		this.n_title = n_title;
	}

	public String getN_content() {
		return n_content;
	}

	public void setN_content(String n_content) {
		this.n_content = n_content;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Date getN_date() {
		return n_date;
	}

	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}
}
