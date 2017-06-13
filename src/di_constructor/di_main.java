package di_constructor;

import org.springframework.context.support.GenericXmlApplicationContext;

public class di_main {
	
	public di_main(){
		
	
	//스프링이 제공한 외부 조립기
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("di_constructor/value_context.xml");
	
	//생성자의 매개변수가 기본형인 클래스 테스트
	ValueObject o = ctx.getBean("obj",ValueObject.class);
	
	System.out.println("-------------------------------------");
	ValueObjectProperty pro = ctx.getBean("obj2",ValueObjectProperty.class);
	
	ValueObjectProperty pro2 = (ValueObjectProperty)ctx.getBean("obj2");
	pro.output();
	
	System.out.println("-------------------------------------");
	SpringDBConnect dbConnect = ctx.getBean("conn",SpringDBConnect.class);
	if(dbConnect.getConn()!= null){
		System.out.println("정상적으로 연결되었습니다.");
	}else{
		System.out.println("연결 중 오류 발생");
		}
	
	}
	
	public static void main(String[] args) {
		new di_main();
	}

}
