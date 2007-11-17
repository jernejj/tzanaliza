import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

public class Main {

	public static Hashtable<String, Variable> variable = new Hashtable<String, Variable>();
	public static String[] sprem={"x1", "x2", "x3" };
	public static double[] ver_sprem = {0.5, 0.5, 0.7}; //verjestno da je Xi=1

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
		
		short[][] tabela = generate(4);
	//	print(tabela);
		
		System.out.println("naj bi bilo :P");
		System.out.print("vrednost je: "+aprverjetnost(drevo.root));

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
	 * Vrne apriorno vrednost logičnega izraza. Rezultat je tipa double
	 * 
	 * @param node prvo vozlišče drevesa
	 * @return rezultat tipa double, ki predstavlja apriorno verjetnost
	 */
	
	public static double aprverjetnost(NodeOp nod){
		NodeOp node = nod;
		int st_spremenljivk = variable.size();
		double rezultat = 0;
		short[][] tabela = generate(st_spremenljivk);
		int st_kom = tabela[1].length;			//stevilo kombinacij
		int[] rez_verjetnosti = new int[st_kom];
		
		//izracun verjetnosti za vse kombinacije
		for(int rez_i = 0; rez_i < st_kom; rez_i++){
			//nastavitev vrednosti spremenljivkam
			double tmp_ver = 1;
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
	
	private static void print(short[][] t) {
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t[0].length; j++ ) {
				System.out.print(t[i][j]+ " ");
			}
			System.out.println("");
		}
	}

}


