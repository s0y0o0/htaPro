package aop_begin;

public class MainProxy {

	public static void main(String[] args) {
		ExeTimeCalculator c1 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(c1.factorial(20));
		
		c1 = new ExeTimeCalculator(new RecCalculator());
		System.out.println(c1.factorial(20));
	}

}
