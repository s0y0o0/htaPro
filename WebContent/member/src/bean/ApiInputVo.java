package bean;

public class ApiInputVo {

	public ApiInputVo() {}
	
	String query = null; //검색을 원하는 문자열로서 UTF-8로 인코딩한다.
	int display = 0; //10~100, 검색 결과 출력 건수 지정
	int start = 0; // 1~1000 , 검색 시작 위치로 최대 1000까지 가능
	String sort = null; //정렬 옵션: random(유사도순), comment(카페/블로그 리뷰 개수 순)
	
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
