package org.nightcode.expressionParser.Symbol;

public class ID extends Token{
	 String identifier;
	 public ID(String id){
		 identifier=id;
	 }
	 	public int getType() {
	 		// TODO Auto-generated method stub
	 		return Token.ID;
	 	}
		@Override
	 	public String toString(){
			return "<ID> "+identifier;
	 	}
		public String getID(){
			return identifier;
		}
}
