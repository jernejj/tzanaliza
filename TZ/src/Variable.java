
public class Variable {
	private String name;
	private int value;
	
	public Variable() {
		
	}
	
	public Variable(String name) {
		this.name = name;
	}	
	
	public Variable(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
}
