import java.util.Hashtable;
import java.util.Vector;



public class Izracun {
	
	private  Hashtable<String, Variable> variable;
	private  String[] sprem;
	private  double[] ver_sprem ; //verjetnost da je Xi=1
	private  int[] zac_vre ;
	private  double apr_ver;
	private  Absyn.OpExp opExp;
	private  Vector<Delta> delte;
	private  Vector<Interakcija> interakcije;
	private  Vector<Prispevek> prispevki;
	private  Tree drevo;
	
	/**
	 * Konstruktor Izracun, izracuna delte, interakcije, ter prispevke spremenljivk. Kot argumente sprejme:
	 * 
	 * @param sprem tipa String[], vsebuje spremenljivke ki nastopajo v izrazu
	 * @param verSprem tipa double[], vsebuje verjetnosti spremenljivke da je 1. Te verjetnosti si sledijko kot spremenljivke podane
	 * v sprem. Se pravi verSprem[i]=P(sprem[i]=1)
	 * @param zac_vrednosti tipa int[], vsebuje zacetne vrednosti spremenljivk, sledijo si po istem vrstnem redu kot sprem.
	 * @param exp tipa Absyn.Exp, drevo ki ga vrne cup
	 */	
	public Izracun(String[] sprem, double[] verSprem, int[] zac_vrednosti, Absyn.Exp exp){
		this.ver_sprem = verSprem;
		this.zac_vre = zac_vrednosti;
		this.sprem = sprem;
		
		variable = new Hashtable<String, Variable>();
		delte = new Vector<Delta>();
		interakcije = new Vector<Interakcija>();
		prispevki = new Vector<Prispevek>();
		
		if (exp instanceof Absyn.OpExp)
			opExp = (Absyn.OpExp)exp;
		if(exp instanceof Absyn.SeqExp)
			opExp = (Absyn.OpExp)((Absyn.SeqExp)exp).list.head;
		
	    drevo = new Tree((NodeOp)pretvori(opExp));
		
		apr_ver = aprverjetnost(drevo.root);
		izracunajDelte(drevo.root);
		izr_Interakcije();
		izr_Prispevke();
		
	}
	
	/**
	 * 
	 * @return delte tipa Vector<Delta>
	 * 
	 */	
	public Vector<Delta> getDelte(){
		return this.delte;
	}
	
	/**
	 * 
	 * @return interakcije tipa Vector<Interakcija>
	 */
	public Vector<Interakcija> getInterakcija(){
		return this.interakcije;
	}
	
	/**
	 * 
	 * @return prispevki tipa Vector<Prispevek>
	 */
	public Vector<Prispevek> getPrispevek(){
		return this.prispevki;
	}
	
	private  Node pretvori(Absyn.OpExp exp){

		NodeOp nop = new NodeOp();

		if(exp.oper == Absyn.OpExp.AND){
			nop.setOp('&');
			if(exp.left instanceof Absyn.VarExp){  
				Absyn.VarExp var = (Absyn.VarExp)exp.left;
				NodeVar tmpNV;
				String ime =  ((Absyn.SimpleVar)var.var).name.toString() ;
				if(variable.containsKey(ime)){
					tmpNV = new NodeVar(variable.get(ime));
				}
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
				if(variable.containsKey(ime)){
					tmpNV = new NodeVar(variable.get(ime));
				}
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


		if(exp.oper == Absyn.OpExp.NEG){
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
				if(variable.containsKey(ime)){
					tmpNV = new NodeVar(variable.get(ime));
				}
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
				if(variable.containsKey(ime)){
					tmpNV = new NodeVar(variable.get(ime));
				}
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
	
	
	private  int izracunaj(NodeOp nod){
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



	
	/**
	 * Vrne apriorno vrednost logičnega izraza. Rezultat je tipa double
	 * 
	 * @param node prvo vozlišče drevesa
	 * @return rezultat tipa double, ki predstavlja apriorno verjetnost
	 */
	
	private  double aprverjetnost(NodeOp nod){
		NodeOp node = nod;
		int st_spremenljivk = variable.size();
		double rezultat = 0;
		short[][] tabela = generate(st_spremenljivk);
		int st_kom = tabela[0].length;			//stevilo kombinacij
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
		
		
		
	//	System.out.println("aprVer: "+rezultat);
		return rezultat;
	}
	
	/**
	 * Izracuna delte za podani koren drevesa node, v Vector delte shrani delte objekta Delta
	 * 
	 * @param node koren drevesa
	 *
	 */
	
	private  void izracunajDelte(NodeOp node){
		int st_dlt = 0;
		for(int i = 1; i <= sprem.length; i++){
			st_dlt += binom(sprem.length, i);
		}
	//	double[] delte = new double[st_dlt];
		
	//	int poz=0;
		for(int i = 1; i <= sprem.length; i++){
			
			int[] indeksi;
			CombinationGenerator x = new CombinationGenerator (sprem.length, i); 
			while(x.hasMore()){
				indeksi = x.getNext();
				Vector<String> fiksirane = new Vector<String>();
				String ime="";
				for(int k=0; k<indeksi.length; k++){
					fiksirane.add(sprem[indeksi[k]]);
					ime +=sprem[indeksi[k]];
				}
				System.out.println("za delto: "+ime);
				double tmp = izr_ver(fiksirane, node);
				double tmp_rez = tmp - apr_ver;
			//	System.out.println("apr ver: "+apr_ver);
			//	System.out.println("delta je: "+tmp_rez);
				Delta delta = new Delta(ime,tmp_rez);
				delte.add(delta);
			//	delte[poz] = izr_ver(fiksirane, node) -  apr_ver;
			//	poz++;
			}
		}
		
	}
	
	/**
	 * Funkcija izr_ver, izracuna delta' za fiksirane spremenljivke podane v stat.
	 * 
	 * @param stat spremenljivke ki jih fiksiramo, tipa Vector<String>
	 * @param node koren drevesa
	 * @return rezultat delta'
	 */
	
	private  double izr_ver(Vector<String> stat, NodeOp node){
		int st_spremenljivk = variable.size()-stat.size();
		double rezultat = 0;
		
		
		int rez_verjetnosti;
		String[] spremenljivke = new String[st_spremenljivk];
		double[] tmp_ver_spr = new double[st_spremenljivk];
		
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
			double tmp_ver = 1.0;
			for(int i=0; i<spremenljivke.length; i++){
			//	System.out.println("vr: "+tmp_ver_spr[i]);
			//	System.out.println("vr-1: "+(1.0-tmp_ver_spr[i]));
				tmp_ver*= (tabela[i][rez_i] == 1 ) ? tmp_ver_spr[i] : (1.0-tmp_ver_spr[i]);
				variable.get(spremenljivke[i]).setValue(tabela[i][rez_i]);
				
				
			}
			rez_verjetnosti=izracunaj(node);
			
		//	System.out.println(rez_verjetnosti[rez_i]);
		//	System.out.println("zmnozek: "+tmp_ver);
			rezultat += (rez_verjetnosti*tmp_ver);
			
				
		}
		
	//	System.out.println("rez: "+rezultat);
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
	
	private  short[][] generate(int number) {
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
	
	private  void izr_Interakcije(){
		
		for(int i = 0; i < sprem.length; i++ ){
			interakcije.add( new Interakcija(sprem[i],getDelta(sprem[i]).getVr(),1) );
		}
		
		for (int i = 2; i <= sprem.length; i++){
			
			CombinationGenerator gen = new CombinationGenerator(sprem.length, i);
			
			while(gen.hasMore()){
				int[] indeksi = gen.getNext();
				String ime="";
				String[] spremTmp = new String[indeksi.length];
				for(int j=0; j < indeksi.length; j++){
					ime += sprem[indeksi[j]];
					spremTmp[j] = sprem[indeksi[j]];
				}
				
				double vsotaI = (double)0.0;
				for(int k = 1; k < indeksi.length; k++){
					CombinationGenerator cg = new CombinationGenerator(indeksi.length, k);
					
					while(cg.hasMore()){
						int[] indx = cg.getNext();
						String imeTmp="";
						for(int j = 0; j < indx.length; j++){
							imeTmp += spremTmp[indx[j]];
						}
						vsotaI += getInter(imeTmp).getVr();	
					}
				}
				
				double vrInter = getDelta(ime).getVr() - vsotaI;
				Interakcija inter = new Interakcija(ime, vrInter, indeksi.length);
				interakcije.add(inter);
				
			}
			
		}
	}
	
	
	private  void izr_Prispevke(){
		
		for(int i = 0; i < sprem.length; i++){
			String ime = sprem[i];
			
			double vrednost = (double)0.0;
			Vector<Interakcija> inter = getInterContainIme(ime);
			double[] tmpVrednost = new double[sprem.length];
			int[] tmpStevilo = new int[sprem.length];

			for(Interakcija interTmp : inter){
				tmpVrednost[interTmp.getSt_sprem()-1] += interTmp.getVr();
				tmpStevilo[interTmp.getSt_sprem()-1] = interTmp.getSt_sprem();
			}
			
			for(int j = 0; j < tmpStevilo.length; j++){
				vrednost += tmpVrednost[j]/(double)tmpStevilo[j];
			}
			
			
			prispevki.add(new Prispevek(ime,vrednost));
			
		}
		
	}
	
	private  Vector<Interakcija> getInterContainIme(String ime){
		
		Vector<Interakcija> inter = new Vector<Interakcija>();
		
		for(Interakcija interTmp : interakcije){
			if( interTmp.getIme().contains(ime) )
				inter.add(interTmp);
				
		}
		
		return inter;
		
	}
	
	/**
	 * Funckija getDelta vrne delto objekta Delta za paramater ime
	 * @param ime parameter tipa String
	 * @return delta tipa Delta
	 */
	
	private  Delta getDelta(String ime ){
		for(Delta deltaTmp : delte) {
			if(deltaTmp.getIme().equals(ime))
				return deltaTmp;
		}
			
		return null;
	}
	
	/**
	 * Funkcija getInter vrne interakcijo iz vektorja interakcija za parameter <B>ime</B>
	 * @param ime parameter tipa String
	 * @return interTmp iz vektorja interakcije, ki ima ime <B>ime</B>;
	 */
	
	private  Interakcija getInter(String ime){
		for(Interakcija interTmp : interakcije)
			if(interTmp.getIme().equals(ime))
				return interTmp;
		
		return null;
		
	}
	
	private  void print(short[][] t) {
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t[0].length; j++ ) {
				System.out.print(t[i][j]+ " ");
			}
			System.out.println("");
		}
	}
	
	private  int binom(int n, int r){
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
	
	
	public int izracunajVrednost(){
		
		for(int i = 0; i < zac_vre.length; i++){
			variable.get(sprem[i]).setValue(zac_vre[i]);
		}
		
		return izracunaj(drevo.root);
		
	}
	
	
}
