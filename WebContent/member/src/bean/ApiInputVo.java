package bean;

public class ApiInputVo {

	public ApiInputVo() {}
	
	String query = null; //�˻��� ���ϴ� ���ڿ��μ� UTF-8�� ���ڵ��Ѵ�.
	int display = 0; //10~100, �˻� ��� ��� �Ǽ� ����
	int start = 0; // 1~1000 , �˻� ���� ��ġ�� �ִ� 1000���� ����
	String sort = null; //���� �ɼ�: random(���絵��), comment(ī��/��α� ���� ���� ��)
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}
