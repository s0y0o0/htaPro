package aop_anno;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain {
	public static void main(String[] args) {
		System.out.println("start");
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("aop_anno/aopPojo.xml");
		Log login = ctx.getBean("login",Log.class);
		Log logout = ctx.getBean("logout",Log.class);
		Log search = ctx.getBean("search",Log.class);
		
		logout.log();
		search.log();
		System.out.println(login.log("kim"));
		
		
	}
}