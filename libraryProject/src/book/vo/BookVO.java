package book.vo;

import java.util.*;

public class BookVO {
	int b_num;            //å ��ȣ
	String b_title;       //å ����
	String b_name;        //����
	String b_content;     //�ٰŸ�
	Date b_date;          //������
	String b_state;       //���� ���� ����
	String b_imageName;   //�̹����̸�
	String id;            //������ ���̵�
	String best;          //����Ʈ ���� ����
	Date loanDate;        //���� ��¥
	Date returnDate;      //�ݳ� ��¥
	Date returnexpected;  //�ݳ� ������
	
	public BookVO() {
		
	}
	
	/*���� ����
	 * public BookVO(int b_num, String b_title, String b_name, String b_content, Date b_date, String b_state, String b_imageName, String id, String best) {
		this.b_num = b_num;
		this.b_title = b_title;
		this.b_name = b_name;
		this.b_content = b_content;
		this.b_date = b_date;
		this.b_state = b_state;
		this.b_imageName = b_imageName;
		this.id = id;
		this.best = best;
	}*/
	
	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	public String getB_state() {
		return b_state;
	}

	public void setB_state(String b_state) {
		this.b_state = b_state;
	}

	public String getB_imageName() {
		return b_imageName;
	}

	public void setB_imageName(String b_imageName) {
		this.b_imageName = b_imageName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBest() {
		return best;
	}

	public void setBest(String best) {
		this.best = best;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getReturnexpected() {
		return returnexpected;
	}

	public void setReturnexpected(Date returnexpected) {
		this.returnexpected = returnexpected;
	}
}
