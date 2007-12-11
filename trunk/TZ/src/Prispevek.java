
public class Prispevek {
	
	String ime;
	double vrednost;
	
	public Prispevek(String ime, double vr){
		this.ime = ime;
		this.vrednost = vr;
	}
	
	public String getIme(){
		return this.ime;
	}
	
	public double getVr(){
		return this.vrednost;
	}
}
