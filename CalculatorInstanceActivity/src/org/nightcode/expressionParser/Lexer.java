package org.nightcode.expressionParser;

import java.util.Hashtable;

import org.nightcode.expressionParser.Symbol.Token;
import org.nightcode.expressionParser.Symbol.NUM;
import org.nightcode.expressionParser.Symbol.ID;
import org.nightcode.expressionParser.Symbol.WORD;
public class Lexer{
	char[] source;
	int index=0;
	char peek=' ';

	Hashtable<String,Token> words = new Hashtable<String,Token>();
	public Lexer(char[] source) {	
		this.source=source;
	}
	public void readch(){
		while(index<source.length){
		  if(source[index]!=' ')
		  {
			  peek=source[index];
			  break;
		  }
		  index++;
		}
		if(index>=source.length)
			peek='\0';
		
	}

	 void move(){
		index++;
	}
	
	public Token getNextToken(){
		readch();
	    switch( peek ) {
	      case '+':
	          move();return Token.ADD;
	      case '-':
	    	  move();return Token.MIN;
	      case '*':
	    	  move();return Token.MUL;
	      case '/':
	    	  move();return Token.DIV;
	      case '(':
	    	  move();return Token.LEFTPAR;
	      case ')':
	    	  move();return Token.RIGHTPAR;
	      case ',': 
	    	  move();return Token.COMMA;
	      case '!': 
	    	  move();return Token.EXCLAIM;
	      case '\0':
	    	  move();return Token.EOF;
	    	  
	      }
	    if( Character.isDigit(peek) ) {
	         int v = 0;
	         do {
	            v = 10*v + Character.digit(peek, 10); 
	            move();readch();
	         } while( Character.isDigit(peek) );
	         
	         if( peek != '.' ) return new NUM(v);
	         double x = v; double d = 10;
	         for(;;) {

		        move();
	            readch();
	            if( ! Character.isDigit(peek) ) 
	            	{break;}
	            x = x + Character.digit(peek, 10) / d; d = d*10;
	         }
	         return new NUM(x);
	      }
	    if( Character.isLetter(peek) ) {
	         StringBuffer b = new StringBuffer();
	         do {
	            b.append(peek); 
	            move();readch();
	         } while( Character.isLetterOrDigit(peek) );
	         
	         String s = b.toString().toLowerCase();
	         Token w = (Token)words.get(s);
	         if( w != null ) return w;
	         w = new ID(s);
	         words.put(s, w);
	         return w;
	      } 
	      Token tok = new WORD(peek); peek = ' ';
	      return tok;
	}
	public static void main(String[] args){
		String testcase="+8(+(9*6+log(6))/6";
		Lexer l=new Lexer(testcase.trim().toCharArray());
		
	}
}
