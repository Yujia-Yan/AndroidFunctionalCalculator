package org.nightcode.expressionParser.Symbol;
public class NUM extends Token{
	 double value;
	 public NUM(double value){
	  this.value=value;
	 }
	 	public int getType() {
	 		// TODO Auto-generated method stub
	 		return Token.NUM;
	 	}
	 	
	 	@Override
	 	public String toString(){
			return "<NUM> "+Double.toString(value);
	 	}
	 	public double getValue(){
	 		return value;
	 	}
}