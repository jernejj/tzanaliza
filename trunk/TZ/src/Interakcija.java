
public class Interakcija {
	String ime;
	float vrednost;
	int st_sprem;
	
	public Interakcija(String ime, float vr, int st){
		this.ime = ime;
		this.vrednost = vr;
		this.st_sprem = st;
	}

	public String getIme(){
		return this.ime;
	}
	
	public float getVr(){
		return this.vrednost;
	}
	
	public int getSt_sprem(){
		return this.st_sprem;
	}
}
