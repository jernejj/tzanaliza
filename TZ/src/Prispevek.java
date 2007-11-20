
public class Prispevek {
	
	String ime;
	float vrednost;
	
	public Prispevek(String ime, float vr){
		this.ime = ime;
		this.vrednost = vr;
	}
	
	public String getIme(){
		return this.ime;
	}
	
	public float getVr(){
		return this.vrednost;
	}
}
