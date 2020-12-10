package common;

public class PagingVO {
	int page_Row = 10; //한 페이지당 게시글 수 10
	int section_Page = 3; //한 섹션당 페이지 수 3
	int totalRow; //게시글 총 갯수(totList)
	int nowPage; //사용자가 클릭한 page 번호
	int nowSection; //section page 번호
	//int totalSection; //총 section 갯수 삭제 예정
	
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
	/*삭제 예정
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
