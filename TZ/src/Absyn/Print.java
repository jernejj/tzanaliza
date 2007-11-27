package Absyn;

public class Print {

  java.io.PrintStream out;

  public Print(java.io.PrintStream o) {out=o;}

  void indent(int d) {
      for(int i=0; i<d; i++) 
            out.print(' ');
  }

  void say(String s) {
            out.print(s);
  }

  void say(int i) {
    Integer local = new Integer(i);
    out.print(local.toString());
  }

  void say(boolean b) {
    Boolean local = new Boolean(b);
    out.print(local.toString());
  }

  void sayln(String s) {
	say(s); say("\n");
  }

  void prVar(SimpleVar v, int d) {
    say("SimpleVar("); say(v.name.toString()); say(")");
  }
  
  void prVar(Var v, int d) {
	    indent(d);
	    if (v instanceof SimpleVar) prVar((SimpleVar) v, d);
  }

  
  void prExp(OpExp e, int d) {
    sayln("OpExp(");
    indent(d+1); 
    switch(e.oper) {
    case OpExp.AND: say("AND"); break;
    case OpExp.OR: say("OR"); break;
    case OpExp.NEG: say("NEG"); break;
    default:
      throw new Error("Print.prExp.OpExp");
    }
    sayln(",");
    prExp(e.left, d+1); sayln(",");
    prExp(e.right, d+1); say(")");
  }

  void prExp(VarExp e, int d) {
    sayln("varExp("); prVar(e.var, d+1);
    say(")");
  }



  void prExp(IntExp e, int d) {
    say("IntExp("); say(e.value); say(")");
  }





  void prExp(SeqExp e, int d) {
    sayln("SeqExp(");
    prExplist(e.list, d+1); say(")");
  }

  


 

  /* Print Exp class types. Indent d spaces. */
  public void prExp(Exp e, int d) {
    indent(d);
    if (e instanceof OpExp) prExp((OpExp)e, d);
    else if (e instanceof VarExp) prExp((VarExp) e, d);

    else if (e instanceof IntExp) prExp((IntExp) e, d);

    else if (e instanceof SeqExp) prExp((SeqExp) e, d);

    else throw new Error("Print.prExp");
  }

 

  void prExplist(ExpList e, int d) {
    indent(d);
    say("ExpList("); 
    if (e!=null) {
      sayln("");
      prExp(e.head, d+1); 
      if (e.tail != null) {
	sayln(","); prExplist(e.tail, d+1);
      }
    }
    say(")");
  }

 
}



