//import ErrorMsg.ErrorMsg;


%% 

%cup
%char

%{

StringBuffer string = new StringBuffer();

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg.ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg.ErrorMsg e) {
  this(s);
  errorMsg=e;
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

	
	{Identifier}	{return tok(sym.ID,yytext().toString() ); }







