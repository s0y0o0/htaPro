package begin;

public class Greeter {
	private String format;
	
	public String greet(String guest){
		return String.format(format, guest);
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	/*public static void main(String[] arg){
		Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요");
		String temp = g.greet("Spring");
	
		System.out.println(temp);
		System.out.println(String.format("%s %s %d qqqqqqqq", "aa","bbb",100));
	}*/
}
