import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Objekt, ki vsebuje funkcijo za izpis delt, interakcij ter prispevkov
 * @author Jernej, Samo, Simon
 *
 */

public class Izpis {
	
	private Izracun izracun;
	/**
	 * Konstruktor
	 * @param izr tipa Izracun
	 */
	public Izpis(Izracun izr){
		this.izracun = izr;
	}
	
	/**
	 * Izpiše delte, interakcije ter prispevke Izracuna, kot parameter pa podamo ime datoteke
	 * @param filename tipa String
	 */
	public void izpisi(String filename){
		
		try {
			FileWriter wfile = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(wfile);
			
			for(Delta dltTmp : izracun.getDelte()){
				out.write("dlt("+dltTmp.getIme()+") = "+dltTmp.getVr());
				out.newLine();
			}
			
			out.newLine();
			for(Interakcija interTmp : izracun.getInterakcija()){
				out.write("I("+interTmp.getIme()+") = "+interTmp.getVr());
				out.newLine();
			}
			
			out.newLine();
			
			for(Prispevek prisTmp : izracun.getPrispevek()){
				out.write("Pi("+prisTmp.getIme()+") = "+prisTmp.getVr());
				out.newLine();
			}
			
			out.close();
			wfile.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
