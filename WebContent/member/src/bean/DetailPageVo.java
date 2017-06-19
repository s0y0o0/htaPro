package bean;

public class DetailPageVo {

	String id ="";
	String movieNm="";
	int star_point=0;
	String reply="";
	String star_date="";
	String star="";
	String comment_name ="";
	String Detail_cnum = "";
	String logId = "";
	String gender = "";
	
	public DetailPageVo(){}

	public DetailPageVo(String id, String movieNm, int star_point, String reply, String star_date, String star,
			String comment_name, String detail_cnum, String logId, String gender) {
		super();
		this.id = id;
		this.movieNm = movieNm;
		this.star_point = star_point;
		this.reply = reply;
		this.star_date = star_date;
		this.star = star;
		this.comment_name = comment_name;
		Detail_cnum = detail_cnum;
		this.logId = logId;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMovieNm() {
		return movieNm;
	}

	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}

	public int getStar_point() {
		return star_point;
	}

	public void setStar_point(int star_point) {
		this.star_point = star_point;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getStar_date() {
		return star_date;
	}

	public void setStar_date(String star_date) {
		this.star_date = star_date;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getComment_name() {
		return comment_name;
	}

	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}

	public String getDetail_cnum() {
		return Detail_cnum;
	}

	public void setDetail_cnum(String detail_cnum) {
		Detail_cnum = detail_cnum;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}

