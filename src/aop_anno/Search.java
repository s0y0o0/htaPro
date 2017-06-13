package aop_anno;

import org.aspectj.lang.annotation.Pointcut;

public class Search implements Log{

	@Override
	public String log(String name) {
		return name;
	}
	
	
	@Override
	public void log() {
		System.out.println("DB접근");
		
	}

	@Override
	public void log2() {
		
	}

}