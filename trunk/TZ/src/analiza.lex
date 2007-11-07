import java_cup.runtime.*; 
import java.io.IOException;




%%

%class Lexer

%line
%column

%public 
%final

%cupsym AnalizaSym
%cup

%{
	int comment_depth = 0;
	int string_depth = 0;
	final int OK = 0;
	StringBuffer string = new StringBuffer();

	private Symbol sym(int type)
	{
		return new Symbol(type, yyline+1, yycolumn+1, yytext());
	}
	
	private Symbol sym(int type, Object value)
	{
		return new Symbol(type, yyline+1, yycolumn+1, value);
	}
%}

%eofval{
	if(comment_depth != OK) {
		return sym(AnalizaSym.error);
	}
	
	if(string_depth != OK) {
		return sym(AnalizaSym.error);
	}
	
	return sym(AnalizaSym.EOF);
%eofval}

IDENTIFIER				= [A-Za-z_][A-Za-z0-9_]*


%%

/* white space */ 
<YYINITIAL> [ \t\n\r]+		{ /* ignore */	} 

/* keywords */ 
<YYINITIAL> "and"			{	return sym(AnalizaSym.AND); }
<YYINITIAL> "not"			{	return sym(AnalizaSym.NOT); }
<YYINITIAL> "or"			{	return sym(AnalizaSym.OR); }

/* symbols */
<YYINITIAL> "("				{	return sym(AnalizaSym.LPAREN); } 
<YYINITIAL> ")"				{	return sym(AnalizaSym.RPAREN); }


<YYINITIAL> { 

	{IDENTIFIER}			{	return sym(AnalizaSym.IDENTIFIER); }
	
	.						{	return sym(AnalizaSym.error); } 
	
	<<EOF>>					{	return sym(AnalizaSym.EOF); }  
	
}