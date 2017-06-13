package aop_xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainXmlPojo {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx= 
				new GenericXmlApplicationContext("aop_xml/aopPojo.xml");
		
		Calculator impeCal = ctx.getBean("impeCal",Calculator.class);
		long fiveFact1 = impeCal.factorial(5);
		System.out.println("impeCal.factorial(5) = "+fiveFact1);
		
		Calculator recCal = ctx.getBean("recCal",Calculator.class);
		long fiveFact2 = recCal.factorial(5);
		System.out.println("recCal.factorial(5)="+fiveFact2);
		
		//befor advice
		Login log = ctx.getBean("login", Login.class);
		log.loginCheck("kim");
	
	} 
}