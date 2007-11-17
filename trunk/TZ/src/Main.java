import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Main {

	public static Hashtable<String, Variable> variable = new Hashtable<String, Variable>();
	public static String[] sprem={"x1", "x2", "x3" };
	public static float[] ver_sprem = {(float)0.5, (float)0.5, (float)0.7}; //verjestno da je Xi=1
	public static int[] zac_vre = {1, 0, 1};
	public static float apr_ver;

	public static void main(String argv[])  {
		String filename = argv[0];
		Parse parse = new Parse(filename);
		Absyn.OpExp op = null;
		
		
		if (parse.absyn instanceof Absyn.OpExp)
			op = (Absyn.OpExp)parse.absyn;
		if(parse.absyn instanceof Absyn.SeqExp)
			op = (Absyn.OpExp)((Absyn.SeqExp)parse.absyn).list.head;

		Tree drevo = new Tree();

		drevo.root = (NodeOp)pretvori(op);


		
		
		for(Enumeration<String> e = variable.keys(); e.hasMoreElements(); ){
			String ime = e.nextElement();
			nastaviVrednosti(ime, 0);
		}
		
	//	short[][] tabela = generate(4);
	//	print(tabela);
		
		apr_ver = aprverjetnost(drevo.root);
		
		float[] delte = izracunajDelte(drevo.root);
		
		System.out.println("naj bi bilo :P");
		System.out.print("vrednost je: "+apr_ver);

	}

	public static Node pretvori(Absyn.OpExp exp){

		NodeOp nop = new NodeOp();

		if(exp.oper == Absyn.OpExp.AND){
			nop.setOp('&');
			if(exp.left instanceof Absyn.VarExp){  
				Absyn.VarExp var = (Absyn.VarExp)exp.left;
				NodeVar tmpNV;
				String ime =  ((Absyn.SimpleVar)var.var).name.toString() ;
				if(variable.containsKey(ime))
					tmpNV = new NodeVar(variable.get(ime));
				else
				{
					Variable v = new Variable(ime);
					variable.put(ime, v);
					tmpNV = new NodeVar(v);
				}
				nop.setLeft( tmpNV );
			}
			else {
				if( exp.left instanceof Absyn.OpExp )
					nop.setLeft(pretvori ( (Absyn.OpExp)exp.left ) );
				else
					nop.setLeft( pretvori( (Absyn.OpExp)((Absyn.SeqExp)exp.left).list.head) );
			}

			if(exp.right instanceof Absyn.VarExp ){
				Absyn.VarExp var = (Absyn.VarExp)exp.right;
				NodeVar tmpNV;
				String ime =  ((Absyn.SimpleVar)var.var).name.toString() ;
				if(variable.containsKey(ime))
					tmpNV = new NodeVar(variable.get(ime));
				else{
					Variable v = new Variable(ime);
					variable.put(ime, v);
					tmpNV = new NodeVar(v);
				}
				nop.setRight( tmpNV );
			}
			else {
				if( exp.right instanceof Absyn.OpExp )
					nop.setRight(pretvori ( (Absyn.OpExp)exp.right ) );
				else
					nop.setRight( pretvori( (Absyn.OpExp)((Absyn.SeqExp)exp.right).list.head) );
			}  

		}


		if(exp.oper == Absyn.OpExp.NOT){
			nop.setOp('!');

			if(exp.right instanceof Absyn.VarExp ){
				Absyn.VarExp var = (Absyn.VarExp)exp.right;
				NodeVar tmpNV;
				String ime =  ((Absyn.SimpleVar)var.var).name.toString() ;
				if(variable.containsKey(ime))
					tmpNV = new NodeVar(variable.get(ime));
				else
				{
					Variable v = new Variable(ime);
					variable.put(ime, v);
					tmpNV = new NodeVar(v);
				}
				nop.setRight( tmpNV );
			}
			else {
				if( exp.right instanceof Absyn.OpExp )
					nop.setRight(pretvori ( (Absyn.OpExp)exp.right ) );
				else
					nop.setRight( pretvori( (Absyn.OpExp)((Absyn.SeqExp)exp.right).list.head) );
			}  
		}


		if(exp.oper == Absyn.OpExp.OR){
			nop.setOp('|');
			if(exp.left instanceof Absyn.VarExp){
				Absyn.VarExp var = (Absyn.VarExp)exp.left;
				NodeVar tmpNV;
				String ime =  ((Absyn.SimpleVar)var.var).name.toString() ;
				if(variable.containsKey(ime))
					tmpNV = new NodeVar(variable.get(ime));
				else
				{
					Variable v = new Variable(ime);
					variable.put(ime, v);
					tmpNV = new NodeVar(v);
				}
				nop.setLeft( tmpNV );
			}
			else {
				if( exp.left instanceof Absyn.OpExp )
					nop.setLeft(pretvori ( (Absyn.OpExp)exp.left ) );
				else
					nop.setLeft( pretvori( (Absyn.OpExp)((Absyn.SeqExp)exp.left).list.head) );
			}

			if(exp.right instanceof Absyn.VarExp ){
				Absyn.VarExp var = (Absyn.VarExp)exp.right;
				NodeVar tmpNV;
				String ime =  ((Absyn.SimpleVar)var.var).name.toString() ;
				if(variable.containsKey(ime))
					tmpNV = new NodeVar(variable.get(ime));
				else
				{
					Variable v = new Variable(ime);
					variable.put(ime, v);
					tmpNV = new NodeVar(v);
				}
				nop.setRight( tmpNV );
			}
			else {
				if (exp.right instanceof Absyn.OpExp )
					nop.setRight(pretvori ( (Absyn.OpExp)exp.right ) );
				else
					nop.setRight( pretvori( (Absyn.OpExp)((Absyn.SeqExp)exp.right).list.head) );
			}  

		}


		return nop;

	}


	public static int izracunaj(NodeOp nod){
		int v=0;
		NodeOp node = nod;

		if(node.getOp()=='|'){
			int l;
			int r;
			if(node.left instanceof NodeVar)
				l = ((NodeVar)node.left).getVarValue();
			else
				l = izracunaj((NodeOp)node.left);
			if(node.right instanceof NodeVar)
				r = ((NodeVar)node.right).getVarValue();
			else
				r = izracunaj((NodeOp)node.right);

			v = l|r;

		}

		else if(node.getOp()=='&'){
			int l;
			int r;
			if(node.left instanceof NodeVar)
				l = ((NodeVar)node.left).getVarValue();
			else
				l = izracunaj((NodeOp)node.left);
			if(node.right instanceof NodeVar)
				r = ((NodeVar)node.right).getVarValue();
			else
				r = izracunaj((NodeOp)node.right);

			v = l&r;

		}

		else if(node.getOp()=='!'){
			int r;
			if(node.right instanceof NodeVar)
				r = ((NodeVar)node.right).getVarValue();
			else
				r = izracunaj((NodeOp)node.right);

			v = ~r;
			v&=1;

		}

		return v; 
	}


	public static void nastaviVrednosti(String ime, int vr){
		variable.get(ime).setValue(vr);
	}
	
	/**
	 * Vrne apriorno vrednost logičnega izraza. Rezultat je tipa float
	 * 
	 * @param node prvo vozlišče drevesa
	 * @return rezultat tipa float, ki predstavlja apriorno verjetnost
	 */
	
	public static float aprverjetnost(NodeOp nod){
		NodeOp node = nod;
		int st_spremenljivk = variable.size();
		float rezultat = 0;
		short[][] tabela = generate(st_spremenljivk);
		int st_kom = tabela[1].length;			//stevilo kombinacij
		int[] rez_verjetnosti = new int[st_kom];
		
		//izracun verjetnosti za vse kombinacije
		for(int rez_i = 0; rez_i < st_kom; rez_i++){
			//nastavitev vrednosti spremenljivkam
			float tmp_ver = 1;
			for(int i=0; i<sprem.length; i++){
				tmp_ver*= (tabela[i][rez_i] == 1 ) ? ver_sprem[i] : (1-ver_sprem[i]);
				variable.get(sprem[i]).setValue(tabela[i][rez_i]);
			}
			rez_verjetnosti[rez_i]=izracunaj(node);
			
		//	System.out.println(rez_verjetnosti[rez_i]);
			
			rezultat += (rez_verjetnosti[rez_i]*tmp_ver);
				
		}
		
		
		
		
		return rezultat;
	}
	
	/**
	 * Izracuna delte za podani koren drevesa node
	 * @param node koren drevesa
	 * @return delte float[], izracunane delte.
	 */
	
	public static float[] izracunajDelte(NodeOp node){
		int st_dlt = 0;
		for(int i = 1; i <= sprem.length; i++){
			st_dlt += binom(sprem.length, i);
		}
		float[] delte = new float[st_dlt];
		
		int poz=0;
		for(int i = 1; i <= sprem.length; i++){
			
			int[] indeksi;
			CombinationGenerator x = new CombinationGenerator (sprem.length, i); 
			while(x.hasMore()){
				indeksi = x.getNext();
				Vector<String> fiksirane = new Vector<String>();
				for(int k=0; k<indeksi.length; k++){
					fiksirane.add(sprem[indeksi[k]]);
				}
				delte[poz] = izr_ver(fiksirane, node) -  apr_ver;
				poz++;
			}
		}
		
		return delte;
		
	}
	
	/**
	 * Funkcija izr_ver, izracuna delta' za fiksirane spremenljivke podane v stat.
	 * 
	 * @param stat spremenljivke ki jih fiksiramo, tipa Vector<String>
	 * @param node koren drevesa
	 * @return rezultat delta'
	 */
	
	public static float izr_ver(Vector<String> stat, NodeOp node){
		int st_spremenljivk = variable.size()-stat.size();
		float rezultat = 0;
		
		
		int rez_verjetnosti;
		String[] spremenljivke = new String[st_spremenljivk];
		float[] tmp_ver_spr = new float[st_spremenljivk];
		
		/* spremenljivke ki ne fiksiramo si shranimo v zacasno tabelo spremenljivke, 
		 * ter ob tem tudi njihove verjetnosti da je ta spremenljivka 1
		 */
		int j=0;
		for(int i = 0; i < sprem.length; i++){
			
			if(!stat.contains(sprem[i])){
				spremenljivke[j]=sprem[i];
				tmp_ver_spr[j]=ver_sprem[i];
				j++;
			}
			else{
				variable.get(sprem[i]).setValue(zac_vre[i]);
			}
			
		}
		
		//fiksiramo vse spremenljivke
		if(st_spremenljivk==0){
			rezultat = izracunaj(node);
			return rezultat;
		}
		
		short[][] tabela = generate(st_spremenljivk);
		int st_kom = tabela[0].length;			//stevilo kombinacij
		
		//izracun verjetnosti za vse kombinacije
		for(int rez_i = 0; rez_i < st_kom; rez_i++){
			//nastavitev vrednosti spremenljivkam
			float tmp_ver = 1;
			for(int i=0; i<spremenljivke.length; i++){
				
				tmp_ver*= (tabela[i][rez_i] == 1 ) ? tmp_ver_spr[i] : (1-tmp_ver_spr[i]);
				variable.get(spremenljivke[i]).setValue(tabela[i][rez_i]);
				
			}
			rez_verjetnosti=izracunaj(node);
			
		//	System.out.println(rez_verjetnosti[rez_i]);
			
			rezultat += (rez_verjetnosti*tmp_ver);
				
		}
		
		
		return rezultat;

	}
	
	
	/**
	 * Funkcija generate sprejme argument number tipa int, kateri predstavlja
	 * število logičnih spremenljivk. Kot rezultat pa vrne
	 * tabela tipa short[][], kjer so shranjene vse mozne kombinacije logičnih spremenljivk.
	 * tabela[i][] -> v i-ti vrstici so vrednosti za i-to spremenljivko.
	 * 
	 * @param number argument tipa int
	 * @return tabela tipa short[][]
	 */
	
	public static short[][] generate(int number) {
		int n = number;
		short[][] tabela = new short[n][(int)Math.pow((float)2,(float)n)];
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
	
	private static void print(short[][] t) {
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t[0].length; j++ ) {
				System.out.print(t[i][j]+ " ");
			}
			System.out.println("");
		}
	}
	
	private static int binom(int n, int r){
		int[] b = new int[n+1];
		b[0] = 1;
		for(int i = 1; i <= n; i++){
			b[i] = 1;
			for(int j = i-1; j > 0; j--){
				b[j] += b[j+1];
			}
		}
		return b[r];
	}

}


