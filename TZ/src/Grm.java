
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sun Nov 25 10:26:18 CET 2007
//----------------------------------------------------


/** CUP v0.11a beta 20060608 generated parser.
  * @version Sun Nov 25 10:26:18 CET 2007
  */
public class Grm extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public Grm() {super();}

  /** Constructor which sets the default scanner. */
  public Grm(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Grm(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\011\000\002\002\004\000\002\002\003\000\002\003" +
    "\003\000\002\003\006\000\002\003\005\000\002\003\005" +
    "\000\002\003\005\000\002\004\003\000\002\005\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\022\000\010\004\004\005\010\011\007\001\002\000" +
    "\012\002\ufffa\006\ufffa\007\ufffa\010\ufffa\001\002\000\012" +
    "\002\uffff\006\uffff\007\uffff\010\uffff\001\002\000\004\002" +
    "\024\001\002\000\004\005\021\001\002\000\010\004\004" +
    "\005\010\011\007\001\002\000\010\002\000\007\012\010" +
    "\013\001\002\000\010\004\004\005\010\011\007\001\002" +
    "\000\010\004\004\005\010\011\007\001\002\000\012\002" +
    "\ufffb\006\ufffb\007\012\010\ufffb\001\002\000\012\002\ufffc" +
    "\006\ufffc\007\ufffc\010\ufffc\001\002\000\004\006\020\001" +
    "\002\000\010\006\ufff9\007\012\010\013\001\002\000\012" +
    "\002\ufffd\006\ufffd\007\ufffd\010\ufffd\001\002\000\010\004" +
    "\004\005\010\011\007\001\002\000\004\006\023\001\002" +
    "\000\012\002\ufffe\006\ufffe\007\ufffe\010\ufffe\001\002\000" +
    "\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\022\000\010\002\005\003\010\004\004\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\010\003\016\004\004\005\015\001\001\000" +
    "\002\001\001\000\006\003\014\004\004\001\001\000\006" +
    "\003\013\004\004\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\010\003\016\004\004\005\021\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Grm$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Grm$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Grm$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


 
  public Absyn.Exp parseResult;
  
  public void syntax_error(java_cup.runtime.Symbol current) {
   report_error("Syntax error (" + current.sym + ")", current);
  }


  


}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$Grm$actions {
  private final Grm parser;

  /** Constructor */
  CUP$Grm$actions(Grm parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$Grm$do_action(
    int                        CUP$Grm$act_num,
    java_cup.runtime.lr_parser CUP$Grm$parser,
    java.util.Stack            CUP$Grm$stack,
    int                        CUP$Grm$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Grm$result;

      /* select the action based on the action number */
      switch (CUP$Grm$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // exps ::= exp 
            {
              Absyn.ExpList RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).right;
		Absyn.Exp e = (Absyn.Exp)((java_cup.runtime.Symbol) CUP$Grm$stack.peek()).value;
		 RESULT = new Absyn.ExpList(e , null); 
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("exps",3, ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // lvalue ::= ID 
            {
              Absyn.Var RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$Grm$stack.peek()).value;
		 RESULT = new Absyn.SimpleVar(ileft, Symbol.Symbol.symbol(i) ); 
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("lvalue",2, ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // exp ::= exp OR exp 
            {
              Absyn.Exp RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)).right;
		Absyn.Exp l = (Absyn.Exp)((java_cup.runtime.Symbol) CUP$Grm$stack.elementAt(CUP$Grm$top-2)).value;
		int rleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).left;
		int rright = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).right;
		Absyn.Exp r = (Absyn.Exp)((java_cup.runtime.Symbol) CUP$Grm$stack.peek()).value;
		 RESULT = new Absyn.OpExp(lleft, l, Absyn.OpExp.OR, r); 
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // exp ::= exp AND exp 
            {
              Absyn.Exp RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)).left;
		int lright = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)).right;
		Absyn.Exp l = (Absyn.Exp)((java_cup.runtime.Symbol) CUP$Grm$stack.elementAt(CUP$Grm$top-2)).value;
		int rleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).left;
		int rright = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).right;
		Absyn.Exp r = (Absyn.Exp)((java_cup.runtime.Symbol) CUP$Grm$stack.peek()).value;
		 RESULT = new Absyn.OpExp(lleft, l, Absyn.OpExp.AND, r); 
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // exp ::= LPAREN exps RPAREN 
            {
              Absyn.Exp RESULT =null;
		int lpleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)).left;
		int lpright = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)).right;
		Object lp = (Object)((java_cup.runtime.Symbol) CUP$Grm$stack.elementAt(CUP$Grm$top-2)).value;
		int elleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-1)).left;
		int elright = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-1)).right;
		Absyn.ExpList el = (Absyn.ExpList)((java_cup.runtime.Symbol) CUP$Grm$stack.elementAt(CUP$Grm$top-1)).value;
		 RESULT = new Absyn.SeqExp(lpleft, el); 
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-2)), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // exp ::= NOT LPAREN exps RPAREN 
            {
              Absyn.Exp RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-1)).right;
		Absyn.ExpList e = (Absyn.ExpList)((java_cup.runtime.Symbol) CUP$Grm$stack.elementAt(CUP$Grm$top-1)).value;
		 RESULT = new Absyn.OpExp(eleft, new Absyn.IntExp(0,0), Absyn.OpExp.NOT, new Absyn.SeqExp(eleft,e)); 
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-3)), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // exp ::= lvalue 
            {
              Absyn.Exp RESULT =null;
		int lvleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).left;
		int lvright = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).right;
		Absyn.Var lv = (Absyn.Var)((java_cup.runtime.Symbol) CUP$Grm$stack.peek()).value;
		 RESULT = new Absyn.VarExp(lv.pos, lv); 
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("exp",1, ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // program ::= exp 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()).right;
		Absyn.Exp e = (Absyn.Exp)((java_cup.runtime.Symbol) CUP$Grm$stack.peek()).value;
		parser.parseResult = e;
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          return CUP$Grm$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Grm$stack.elementAt(CUP$Grm$top-1)).value;
		RESULT = start_val;
              CUP$Grm$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Grm$stack.elementAt(CUP$Grm$top-1)), ((java_cup.runtime.Symbol)CUP$Grm$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Grm$parser.done_parsing();
          return CUP$Grm$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

