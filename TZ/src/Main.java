
public class Main {

  public static void main(String argv[])  {
      String filename = argv[0];
      Parse parse = new Parse(filename);
      Absyn.OpExp op;

      if( parse.absyn instanceof Absyn.OpExp)
    	  op = (Absyn.OpExp)parse.absyn;

      else if(parse.absyn instanceof Absyn.SeqExp)
    	  op = (Absyn.OpExp) ((Absyn.SeqExp) parse.absyn).list.head;
      
      
  }

  public static void getList() {
	  
  }
  
}


