package begin;

import org.springframework.context.support.GenericXmlApplicationContext;

public class main {
	
	public static void main(String[] arg){
		/*스프링의 환경 설정 파일인 app_context.xml을 사용함으로써 같은 의미로 처리
		 * 
		 * Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요");
		System.out.println(String.format("%s %s %d qqqqqqqq", "aa","bbb",100));*/
	
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("begin/app_context.xml");
		Greeter g = ctx.getBean("greeter",Greeter.class);
		
		
		String temp = g.greet("Spring");
		System.out.println(temp);
	}

}
