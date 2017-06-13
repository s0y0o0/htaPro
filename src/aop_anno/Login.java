package aop_anno;

import org.aspectj.lang.annotation.Pointcut;

public class Login implements Log{
	
	
	String name = null;
	public Login() {
	}
	public Login(String name) {
		this.name = name;
	}
	
	@Override
	public String log(String name) {
		String str =name+"님이 로그인 되었습니다.";
		return str;
		
	}
	@Override
	public void log() {
		System.out.println(name+"님이 로그인 되었습니다.");
		
	}
	public void log2(){
		System.out.println("log2");
	}
	

}