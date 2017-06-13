package di_constructor;

public class ValueObjectProperty {
	
	int sdan;
	int edan;

	public void setSdan(int sdan) {
		this.sdan = sdan;
	}
	public void setEdan(int edan) {
		this.edan = edan;
	}
	
	public void output(){
		for(int i=sdan; i<edan; i++){
			for(int j=1; j<10; j++){
			String temp = String.format("%d * %d = %d", i, j, (i*j));
			System.out.println(temp);
			}
		}
	}
	
}
