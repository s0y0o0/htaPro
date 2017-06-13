package aop_log;

public class Logout implements LoginInterface {

	@Override
	public String log(String id) {
		System.out.println("로그아웃 : " + id);
		return null;
	}

	

}
