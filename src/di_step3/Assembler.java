package di_step3;
//DI를 구현하기 위한 외부 조립기
//외부에서 객체를 생성한 후 반환 하는 목적
public class Assembler {
	public MyDB get(){
		return new Oracle();
		
	}
}
