package di_step3;
		
	//내부 아키텍쳐는 오픈되면 안되는 개념이기 때문에
	//객체 생성은 외부 조립기에 의해서 생성한 뒤 반환 받도록 조치

public class DiMain {
	
	static void prn(MyDB db){
		System.out.println(db.getDB());
	}
	
	public static void main(String[] args){
		
		Assembler asm = new Assembler();
		prn(asm.get());
	}

		
}
