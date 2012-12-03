package org.nightcode.expressionParser.Symbol;


public class WORD extends Token{
 	public char value;
 	public WORD(char v){
 		value=v;
 	}
 	@Override
 	public int getType() {
 		// TODO Auto-generated method stub
 		return Token.WORD;
 	}
	@Override
 	public String toString(){
		return "<WORD> "+value;
 	}
	public char getValue(){
		return value;
	}
}