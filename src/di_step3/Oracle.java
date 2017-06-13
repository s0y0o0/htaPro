package di_step3;

 //DI를 구현하기 전에 특정 환경에 맞는 스페셜한 클레스

public class Oracle implements MyDB {

	@Override
	public String getDB() {
		return "오라클";
	}
	

}
