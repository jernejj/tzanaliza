import java.util.*;

public class Test {
	
	static Prispevek[] prispevek=null;
	
	public static void main(String[] args) {
		String izraz = "(((p or q) and neg(p and q)) or r) and neg(((p or q) and neg(p and q) ) and r)";
		Parse parse  = new Parse(izraz);
		String[] spremenljivke = new String[parse.spremenljivke.size()];
		
		int i=0;
		for(String tmp : parse.spremenljivke){
			spremenljivke[i] = tmp;
			i++;
		}
		
		
		double[] verjetnosti = { 0.5, 0.5, 0.5};
		short[][] zac_vrednosti = generate(spremenljivke.length);
		int[] zacVr = new int[zac_vrednosti.length];
		prispevek = new Prispevek[spremenljivke.length];
		
		for(int k = 0; k < prispevek.length; k++){
			prispevek[k] = new Prispevek(spremenljivke[k], 0);
		}
		
		for(i = 0; i < zac_vrednosti[0].length; i++  ){
			
			for(int j = 0; j < zac_vrednosti.length; j++){
				zacVr[j] = (int)zac_vrednosti[j][i];
			}
			
			Izracun izr = new Izracun(spremenljivke, verjetnosti, zacVr, parse.absyn );
			System.out.println(izr.izracunajVrednost());
			
			for(Prispevek tmp : izr.getPrispevek()){
				prispevek[getDelta(tmp.getIme())].vrednost += Math.abs(tmp.vrednost);
			}
			
		}
		
		for(i = 0; i < spremenljivke.length; i++){
			System.out.println("Pi("+spremenljivke[i]+")="+prispevek[getDelta(spremenljivke[i])].vrednost/zac_vrednosti[0].length );
		}
		
		
//		System.out.println(0.48-0.5);
//		
//		String[] elements = {"x1", "x2", "x3", "x4", "x5"};
//		int[] indices;
//		CombinationGenerator x = new CombinationGenerator (elements.length, 2);
//		StringBuffer combination;
//		System.out.println("vseh kom je: "+x.getTotal());
//		while (x.hasMore ()) {
//		  combination = new StringBuffer ();
//		  indices = x.getNext ();
//		  for (int i = 0; i < indices.length; i++) {
//		    combination.append (elements[indices[i]]);
//		  }
//		  System.out.println (combination.toString ());
//		}
	
	
	}
	
	public static String trimWhiteSpace(String niz) {
		String[] tmp1 = niz.split(" ");
		String tmp2 = "";
		
		
		for(int i=0; i < tmp1.length; i++ )
			tmp2 = tmp2 + tmp1[i];
			
		return tmp2;		
	}
	
	private static  short[][] generate(int number) {
		int n = number;
		short[][] tabela = new short[n][(int)Math.pow((double)2,(double)n)];
		String bin="";

		int indeks = 0;
		for(int i=0; i < tabela[0].length; i++) {
			bin=Integer.toBinaryString(i);
			indeks = bin.length();
			for(int j = tabela.length - 1; j >= tabela.length - bin.length(); j--)
				tabela[j][i] = Short.parseShort(String.valueOf((bin.charAt(--indeks))));
		}
		
		return tabela;
	
	}
	
	public static int getDelta(String ime){
		for(int i=0; i < prispevek.length; i++){		
			if(prispevek[i].ime.equals(ime))
				return i;
		}
		return -1;
	}
	
	public static String[] get() {
		return null;
	}
}
