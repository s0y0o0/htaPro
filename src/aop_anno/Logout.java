package aop_anno;

import org.aspectj.lang.annotation.Pointcut;

public class Logout implements Log{

	@Override
	public String log(String name) {
		return name;
	}
	
	
	@Override
	public void log() {
		System.out.println("로그아웃 되었습니다.");
		
	}

	@Override
	public void log2() {
		
	}
	

}