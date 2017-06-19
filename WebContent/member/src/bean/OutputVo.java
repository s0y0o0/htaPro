package bean;

public class OutputVo {
	double avgStar=0;
	int serial = 0;
	String genre = "";
	String movie_name = "";
	String open_year = "";
	String watch_age = "";
	String play_time = "";
	String nation = "";
	String director = "";
	String director_photo = "";
	String actor = "";
	String actor_part = "";
	String actor_photo = "";
	String actor2 = "";
	String actor2_part = "";
	String actor2_photo = "";
	String surpporter = "";
	String surpporter_part = "";
	String surpporter_photo = "";
	String surpporter2 = "";
	String surpporter2_part = "";
	String surpporter2_photo = "";
	String surpporter3 = "";
	String surpporter3_part = "";
	String surpporter3_photo = "";
	String poster = "";
	String slide_photo = "";
	String slide_photo2 = "";
	String slide_photo3 = "";
	String preview_url = "";
	String similar_movie_photo = "";
	String story = "";
	
	
	String r_boxofficeType=""; // 문자열 박스오피스 종류를 출력합니다.
	String r_showRange=""; // 문자열 박스오피스 조회 일자를 출력합니다.
	String r_rnum=""; // 문자열 순번을 출력합니다.
	String r_rank=""; // 문자열 해당일자의 박스오피스 순위를 출력합니다.
	String r_rankInten=""; // 문자열 전일대비 순위의 증감분을 출력합니다.
	String r_rankOldAndNew=""; // 문자열 랭킹에 신규진입여부를 출력합니다. “OLD” : 기존 , “NEW” : 신규
	String r_movieCd=""; // 문자열 영화의 대표코드를 출력합니다.
	String r_movieNm=""; // 문자열 영화명(국문)을 출력합니다.
	String r_openDt=""; // 문자열 영화의 개봉일을 출력합니다.
	String r_salesAmt=""; // 문자열 해당일의 매출액을 출력합니다.
	String r_salesShare=""; // 문자열 해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력합니다.
	String r_salesInten=""; // 문자열 전일 대비 매출액 증감분을 출력합니다.
	String  r_salesChange=""; // 문자열 전일 대비 매출액 증감 비율을 출력합니다.
	String r_salesAcc=""; // 문자열 누적매출액을 출력합니다.
	String r_audiCnt=""; // 문자열 해당일의 관객수를 출력합니다.
	String r_audiInten=""; // 문자열 전일 대비 관객수 증감분을 출력합니다.
	String r_audiChange=""; // 문자열 전일 대비 관객수 증감 비율을 출력합니다.
	String r_audiAcc=""; // 누적관객수를 출력합니다.
	String r_scrnCnt=""; // 문자열 해당일자에 상영한 스크린수를 출력합니다.
	String r_showCnt=""; // 해당일자에 상영된 횟수를 출력합니다.
	
	public OutputVo() {	}

	
	
	public OutputVo(double avgStar, int serial, String genre, String movie_name, String open_year, String watch_age, String play_time,
			String nation, String director, String director_photo, String actor, String actor_part, String actor_photo,
			String actor2, String actor2_part, String actor2_photo, String surpporter, String surpporter_part,
			String surpporter_photo, String surpporter2, String surpporter2_part, String surpporter2_photo,
			String surpporter3, String surpporter3_part, String surpporter3_photo, String poster, String slide_photo,
			String slide_photo2, String slide_photo3, String preview_url, String similar_movie_photo, String story,
			String r_boxofficeType, String r_showRange, String r_rnum, String r_rank, String r_rankInten,
			String r_rankOldAndNew, String r_movieCd, String r_movieNm, String r_openDt, String r_salesAmt,
			String r_salesShare, String r_salesInten, String r_salesChange, String r_salesAcc, String r_audiCnt,
			String r_audiInten, String r_audiChange, String r_audiAcc, String r_scrnCnt, String r_showCnt) {
		super();
		this.avgStar=avgStar;
		this.serial = serial;
		this.genre = genre;
		this.movie_name = movie_name;
		this.open_year = open_year;
		this.watch_age = watch_age;
		this.play_time = play_time;
		this.nation = nation;
		this.director = director;
		this.director_photo = director_photo;
		this.actor = actor;
		this.actor_part = actor_part;
		this.actor_photo = actor_photo;
		this.actor2 = actor2;
		this.actor2_part = actor2_part;
		this.actor2_photo = actor2_photo;
		this.surpporter = surpporter;
		this.surpporter_part = surpporter_part;
		this.surpporter_photo = surpporter_photo;
		this.surpporter2 = surpporter2;
		this.surpporter2_part = surpporter2_part;
		this.surpporter2_photo = surpporter2_photo;
		this.surpporter3 = surpporter3;
		this.surpporter3_part = surpporter3_part;
		this.surpporter3_photo = surpporter3_photo;
		this.poster = poster;
		this.slide_photo = slide_photo;
		this.slide_photo2 = slide_photo2;
		this.slide_photo3 = slide_photo3;
		this.preview_url = preview_url;
		this.similar_movie_photo = similar_movie_photo;
		this.story = story;
		
		
		this.r_boxofficeType = r_boxofficeType;
		this.r_showRange = r_showRange;
		this.r_rnum = r_rnum;
		this.r_rank = r_rank;
		this.r_rankInten = r_rankInten;
		this.r_rankOldAndNew = r_rankOldAndNew;
		this.r_movieCd = r_movieCd;
		this.r_movieNm = r_movieNm;
		this.r_openDt = r_openDt;
		this.r_salesAmt = r_salesAmt;
		this.r_salesShare = r_salesShare;
		this.r_salesInten = r_salesInten;
		this.r_salesChange = r_salesChange;
		this.r_salesAcc = r_salesAcc;
		this.r_audiCnt = r_audiCnt;
		this.r_audiInten = r_audiInten;
		this.r_audiChange = r_audiChange;
		this.r_audiAcc = r_audiAcc;
		this.r_scrnCnt = r_scrnCnt;
		this.r_showCnt = r_showCnt;
	}



	public double getAvgStar() {
		return avgStar;
	}

	public void setAvgStar(double avgStar) {
		this.avgStar = avgStar;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public String getOpen_year() {
		return open_year;
	}

	public void setOpen_year(String open_year) {
		this.open_year = open_year;
	}

	public String getWatch_age() {
		return watch_age;
	}

	public void setWatch_age(String watch_age) {
		this.watch_age = watch_age;
	}

	public String getPlay_time() {
		return play_time;
	}

	public void setPlay_time(String play_time) {
		this.play_time = play_time;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDirector_photo() {
		return director_photo;
	}

	public void setDirector_photo(String director_photo) {
		this.director_photo = director_photo;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getActor_part() {
		return actor_part;
	}

	public void setActor_part(String actor_part) {
		this.actor_part = actor_part;
	}

	public String getActor_photo() {
		return actor_photo;
	}

	public void setActor_photo(String actor_photo) {
		this.actor_photo = actor_photo;
	}

	public String getActor2() {
		return actor2;
	}

	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}

	public String getActor2_part() {
		return actor2_part;
	}

	public void setActor2_part(String actor2_part) {
		this.actor2_part = actor2_part;
	}

	public String getActor2_photo() {
		return actor2_photo;
	}

	public void setActor2_photo(String actor2_photo) {
		this.actor2_photo = actor2_photo;
	}

	public String getSurpporter() {
		return surpporter;
	}

	public void setSurpporter(String surpporter) {
		this.surpporter = surpporter;
	}

	public String getSurpporter_part() {
		return surpporter_part;
	}

	public void setSurpporter_part(String surpporter_part) {
		this.surpporter_part = surpporter_part;
	}

	public String getSurpporter_photo() {
		return surpporter_photo;
	}

	public void setSurpporter_photo(String surpporter_photo) {
		this.surpporter_photo = surpporter_photo;
	}

	public String getSurpporter2() {
		return surpporter2;
	}

	public void setSurpporter2(String surpporter2) {
		this.surpporter2 = surpporter2;
	}

	public String getSurpporter2_part() {
		return surpporter2_part;
	}

	public void setSurpporter2_part(String surpporter2_part) {
		this.surpporter2_part = surpporter2_part;
	}

	public String getSurpporter2_photo() {
		return surpporter2_photo;
	}

	public void setSurpporter2_photo(String surpporter2_photo) {
		this.surpporter2_photo = surpporter2_photo;
	}

	public String getSurpporter3() {
		return surpporter3;
	}

	public void setSurpporter3(String surpporter3) {
		this.surpporter3 = surpporter3;
	}

	public String getSurpporter3_part() {
		return surpporter3_part;
	}

	public void setSurpporter3_part(String surpporter3_part) {
		this.surpporter3_part = surpporter3_part;
	}

	public String getSurpporter3_photo() {
		return surpporter3_photo;
	}

	public void setSurpporter3_photo(String surpporter3_photo) {
		this.surpporter3_photo = surpporter3_photo;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getSlide_photo() {
		return slide_photo;
	}

	public void setSlide_photo(String slide_photo) {
		this.slide_photo = slide_photo;
	}

	public String getSlide_photo2() {
		return slide_photo2;
	}

	public void setSlide_photo2(String slide_photo2) {
		this.slide_photo2 = slide_photo2;
	}

	public String getSlide_photo3() {
		return slide_photo3;
	}

	public void setSlide_photo3(String slide_photo3) {
		this.slide_photo3 = slide_photo3;
	}

	public String getPreview_url() {
		return preview_url;
	}

	public void setPreview_url(String preview_url) {
		this.preview_url = preview_url;
	}

	public String getSimilar_movie_photo() {
		return similar_movie_photo;
	}

	public void setSimilar_movie_photo(String similar_movie_photo) {
		this.similar_movie_photo = similar_movie_photo;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getR_boxofficeType() {
		return r_boxofficeType;
	}

	public void setR_boxofficeType(String r_boxofficeType) {
		this.r_boxofficeType = r_boxofficeType;
	}

	public String getR_showRange() {
		return r_showRange;
	}

	public void setR_showRange(String r_showRange) {
		this.r_showRange = r_showRange;
	}

	public String getR_rnum() {
		return r_rnum;
	}

	public void setR_rnum(String r_rnum) {
		this.r_rnum = r_rnum;
	}

	public String getR_rank() {
		return r_rank;
	}

	public void setR_rank(String r_rank) {
		this.r_rank = r_rank;
	}

	public String getR_rankInten() {
		return r_rankInten;
	}

	public void setR_rankInten(String r_rankInten) {
		this.r_rankInten = r_rankInten;
	}

	public String getR_rankOldAndNew() {
		return r_rankOldAndNew;
	}

	public void setR_rankOldAndNew(String r_rankOldAndNew) {
		this.r_rankOldAndNew = r_rankOldAndNew;
	}

	public String getR_movieCd() {
		return r_movieCd;
	}

	public void setR_movieCd(String r_movieCd) {
		this.r_movieCd = r_movieCd;
	}

	public String getR_movieNm() {
		return r_movieNm;
	}

	public void setR_movieNm(String r_movieNm) {
		this.r_movieNm = r_movieNm;
	}

	public String getR_openDt() {
		return r_openDt;
	}

	public void setR_openDt(String r_openDt) {
		this.r_openDt = r_openDt;
	}

	public String getR_salesAmt() {
		return r_salesAmt;
	}

	public void setR_salesAmt(String r_salesAmt) {
		this.r_salesAmt = r_salesAmt;
	}

	public String getR_salesShare() {
		return r_salesShare;
	}

	public void setR_salesShare(String r_salesShare) {
		this.r_salesShare = r_salesShare;
	}

	public String getR_salesInten() {
		return r_salesInten;
	}

	public void setR_salesInten(String r_salesInten) {
		this.r_salesInten = r_salesInten;
	}

	public String getR_salesChange() {
		return r_salesChange;
	}

	public void setR_salesChange(String r_salesChange) {
		this.r_salesChange = r_salesChange;
	}

	public String getR_salesAcc() {
		return r_salesAcc;
	}

	public void setR_salesAcc(String r_salesAcc) {
		this.r_salesAcc = r_salesAcc;
	}

	public String getR_audiCnt() {
		return r_audiCnt;
	}

	public void setR_audiCnt(String r_audiCnt) {
		this.r_audiCnt = r_audiCnt;
	}

	public String getR_audiInten() {
		return r_audiInten;
	}

	public void setR_audiInten(String r_audiInten) {
		this.r_audiInten = r_audiInten;
	}

	public String getR_audiChange() {
		return r_audiChange;
	}

	public void setR_audiChange(String r_audiChange) {
		this.r_audiChange = r_audiChange;
	}

	public String getR_audiAcc() {
		return r_audiAcc;
	}

	public void setR_audiAcc(String r_audiAcc) {
		this.r_audiAcc = r_audiAcc;
	}

	public String getR_scrnCnt() {
		return r_scrnCnt;
	}

	public void setR_scrnCnt(String r_scrnCnt) {
		this.r_scrnCnt = r_scrnCnt;
	}

	public String getR_showCnt() {
		return r_showCnt;
	}

	public void setR_showCnt(String r_showCnt) {
		this.r_showCnt = r_showCnt;
	}

}