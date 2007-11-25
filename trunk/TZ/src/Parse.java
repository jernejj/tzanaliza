
public class Parse {

  public ErrorMsg.ErrorMsg errorMsg;
  public Absyn.Exp absyn;

  public Parse(String izraz) {
      {java.io.Reader inp;
       try {inp=new java.io.StringReader(izraz);
       } catch (Exception e) {
	 throw new Error("Ni izraza/napaka v izrazu: " + izraz);
       }
       Grm parser = new Grm(new Yylex(inp));
      /* open input files, etc. here */

      try {
          parser./*debug_*/parse();
      } catch (Throwable e) {
	e.printStackTrace();
	throw new Error(e.toString());
      } 
      finally {
         try {inp.close();} catch (java.io.IOException e) {}
      }
      absyn=parser.parseResult;
//      Absyn.Print printer = new Absyn.Print(System.out);
//      printer.prExp(absyn,0);
//      System.out.println("\n");
} }   }

