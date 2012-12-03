package org.nightcode.expressionParser.Symbol;

public class Token {
	public final static Token  ADD=new WORD('+');
	public final static Token  MIN=new WORD('-');
	public final  static  Token MUL=new WORD('*');
	public final static  Token  DIV=new WORD('/');
	public final static Token LEFTPAR=new WORD('(');
	public final static Token RIGHTPAR=new WORD(')');
	public final static Token COMMA=new WORD(',');
	public final static Token EOF=new WORD('\0');
	public final static Token EXCLAIM=new WORD('!');
	public final static int ID=1;
	public final static int WORD=2;
	public final static int NUM=3;
	public final static int UNKNOWN=0;
	public  int getType(){
		return UNKNOWN;
	}
	@Override
 	public String toString(){
		return "<Unknown> ";
 	}
}
