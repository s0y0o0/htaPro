package bean;

public class InputVo {
   
   String q;         //검색을 원하는 질의어
   String pageno;      //검색 결과의 페이지번호 기본:1, 최소:1, 최대:500
   int result;         //한 페이지에 출력될 결과수 기본:10, 최소:1, 최대:20
   String output;      //포맷 json or xml
   
   String r_key;   //문자열(필수)   발급받은키 값을 입력합니다.
   String r_targetDt;   //문자열(필수)   조회하고자 하는 날짜를 yyyymmdd 형식으로 입력합니다.
   String r_itemPerPage;   //문자열   결과 ROW 의 개수를 지정합니다.(default : “10”, 최대 : “10“)
   String r_multiMovieYn;   //문자열   다양성 영화/상업영화를 구분지어 조회할 수 있습니다. “Y” : 다양성 영화 “N” : 상업영화 (default : 전체)
   String r_repNationCd;   //문자열   한국/외국 영화별로 조회할 수 있습니다.  “K: : 한국영화 “F” : 외국영화 (default : 전체)
   String r_wideAreaCd;   //문자열   상영지역별로 조회할 수 있으며, 지역코드는 공통코드 조회 서비스에서 “0105000000” 로서 조회된 지역코드입니다. (default : 전체)
   
   public InputVo(){}
   
   
   
}