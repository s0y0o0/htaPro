package aop_log;

import org.aspectj.lang.JoinPoint;

public class PrintLog {
	private String id;
	private String findStr;
	
	public void before(){
		System.out.println("before");
	}

}
