import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;



public class Main {

	public static void main(String argv[])  {
		String filename = argv[0];
		Parse parse = new Parse(filename);
		Absyn.OpExp op = null;
		
		String[] spremenljivke = { "x1", "x2", "x3" };
		float[] verjetnosti = { (float)0.5, (float)0.5, (float)0.5};
		int[]	zac_vr = { 1,1,0};
		

		Izracun izracun = new Izracun(spremenljivke, verjetnosti, zac_vr, parse.absyn );
		
		Vector<Prispevek> pris = izracun.getPrispevek();
		
		System.out.println("done");
		
	//	short[][] tabela = generate(4);
	//	print(tabela);
		
		

	}




	

}


