package aop_xml;

import org.aspectj.lang.JoinPoint;

public class BeforeAdvice {
	public Object before(JoinPoint point) throws Throwable{
		String methodName = point.getSignature().getName();
		
		System.out.println(methodName + "  calling ...");
		
		return null;
	}
}
