package bean;

public class MemberVo {
	
	String id;
	String name;
	String email;
	String gender;
	int age_range;
	String join_date;
	
	public MemberVo(){}
	public MemberVo(String id, String name, String email, String gender, int age_range, String join_date) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.age_range = age_range;
		this.join_date = join_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge_range() {
		return age_range;
	}
	public void setAge_range(int age_range) {
		this.age_range = age_range;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	
	
}
