package Absyn;
import Symbol.Symbol;
public class OpExp extends Exp {
   public Exp left, right;
   public int oper;
   public OpExp(int p, Exp l, int o, Exp r) {pos=p; left=l; oper=o; right=r;}
   public final static int AND=0, OR=1, NEG=2;
}
