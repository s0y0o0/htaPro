package aop_log;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("aop_log/aopPojo.xml");
		
		LoginInterface logout = ctx.getBean("logout",LoginInterface.class);
		logout.log("성은영");
	}
}
