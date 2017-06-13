package aop_log;

public class Login implements LoginInterface {

	@Override
	public String log(String id) {
		System.out.println("로그인 : " + id);
		return null;
	}

}
