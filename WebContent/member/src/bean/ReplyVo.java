package bean;

public class ReplyVo {
	
	static final long serialVersionUID = 1l;
	
	int comment_num;
	String comment_pwd;
	String comment_id;
	String comment_date;
	int comment_parent;
	String comment_content;
	String comment_serial;
	
	/*public ReplyVo(int comment_num,
	String comment_pwd,
	String comment_id,
	String comment_date,
	int comment_parent,
	String comment_content,
	String comment_serial){
		this.comment_content=comment_content;
		this.comment_date=comment_date;
		this.comment_id=comment_id;
		this.comment_num=comment_num;
		this.comment_parent=comment_parent;
		this.comment_pwd=comment_pwd;
		this.comment_serial=comment_serial;
	}*/
	
	
	
	public String getComment_serial() {
		return comment_serial;
	}
	public void setComment_serial(String comment_serial) {
		this.comment_serial = comment_serial;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getComment_pwd() {
		return comment_pwd;
	}
	public void setComment_pwd(String comment_pwd) {
		this.comment_pwd = comment_pwd;
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public int getComment_parent() {
		return comment_parent;
	}
	public void setComment_parent(int comment_parent) {
		this.comment_parent = comment_parent;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
