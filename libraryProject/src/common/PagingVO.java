package common;

public class PagingVO {
	int page_Row = 10; //�� �������� �Խñ� �� 10
	int section_Page = 3; //�� ���Ǵ� ������ �� 3
	int totalRow; //�Խñ� �� ����(totList)
	int nowPage; //����ڰ� Ŭ���� page ��ȣ
	int nowSection; //section page ��ȣ
	//int totalSection; //�� section ���� ���� ����
	
	public PagingVO() {
		
	}
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getNowSection() {
		return nowSection;
	}
	public void setNowSection(int nowSection) {
		this.nowSection = nowSection;
	}
	/*���� ����
	 * public int getTotalSection() {
		return totalSection;
	}
	public void setTotalSection(int totalSection) {
		this.totalSection = totalSection;
	}*/

	public int getPage_Row() {
		return page_Row;
	}

	public void setPage_Row(int page_Row) {
		this.page_Row = page_Row;
	}

	public int getSection_Page() {
		return section_Page;
	}

	public void setSection_Page(int section_Page) {
		this.section_Page = section_Page;
	}
}
