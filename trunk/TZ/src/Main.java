import java.util.Enumeration;
import java.util.Hashtable;

public class Main {

	public static Hashtable<String, Variable> variable = new Hashtable<String, Variable>();

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
			
		
		System.out.println("naj bi bilo :P");
		System.out.print("vrednost je: "+izracunaj(drevo.root));

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


	public static int izracunaj(NodeOp node){
		int v=0;


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


}


