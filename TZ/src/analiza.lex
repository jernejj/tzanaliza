import java.util.Vector;


%% 

%cup
%char

%{

public Vector<String> spremenljivke = new Vector<String>();





private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}








%}


 
ControlChar = \^[@ABCDEFGHIJKLMNOPQRSTUVWXYZ\[\\\]\^_\?] 
Identifier = [a-zA-Z][a-zA-Z0-9_]* 



%%
<YYINITIAL>{
	" "			{ /* ignore */ }
	\r|\n|\r\n	{ }
	[\t\f]		{ /* ignore */ }
	
	"("		{return tok(sym.LPAREN,null); }
	")"		{return tok(sym.RPAREN,null); }
	"and"		{return tok(sym.AND,null); }
	"or"		{return tok(sym.OR,null); }
	"neg"		{return tok(sym.NOT,null); }
}	

	
	{Identifier}	{spremenljivke.add(yytext().toString()); return tok(sym.ID,yytext().toString() ); }








