package aop_log;

public class Search implements LoginInterface {

		
	
	@Override
	public String log(String id) {
		System.out.println("검색 :  " + id);
		return null;
	}

	
}
