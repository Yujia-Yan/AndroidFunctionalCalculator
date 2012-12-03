package org.nightcode.expressionParser;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

import org.nightcode.expressionParser.Symbol.ID;
import org.nightcode.expressionParser.Symbol.NUM;
import org.nightcode.expressionParser.Symbol.Token;
import org.nightcode.expressionParser.Symbol.WORD;
import org.nightcode.expressionParser.numFunction.CosFun;
import org.nightcode.expressionParser.numFunction.ExpFun;
import org.nightcode.expressionParser.numFunction.GammaFun;
import org.nightcode.expressionParser.numFunction.LogFun;
import org.nightcode.expressionParser.numFunction.PowFun;
import org.nightcode.expressionParser.numFunction.SinFun;
import org.nightcode.expressionParser.numFunction.SinhFun;
import org.nightcode.expressionParser.numFunction.SqrtFun;
import org.nightcode.expressionParser.numFunction.TanFun;
import org.nightcode.expressionParser.numFunction.coshFun;



public class ExpressionParser {
	Lexer lexer;
	public ExpressionParser(char[] source){
		lexer=new Lexer(source);
		 next=lexer.getNextToken();

		 
	}

	//function Table
	public static Hashtable<String, FunctionEval> functionTable=new Hashtable<String, FunctionEval>();
	public static Hashtable<String,Double> VariableTable=new Hashtable<String, Double>();
	static{
		 functionTable.put("pow", new PowFun());
		 functionTable.put("log", new LogFun());
		 functionTable.put("cos", new CosFun());
		 functionTable.put("sin", new SinFun());
		 functionTable.put("tan", new TanFun());
		 functionTable.put("sqrt", new SqrtFun());
		 functionTable.put("gamma", new GammaFun());
		 functionTable.put("exp", new ExpFun());
		 functionTable.put("sinh", new SinhFun());
		 functionTable.put("cosh", new coshFun());
		 VariableTable.put("pi", Math.PI);
		 VariableTable.put("e", Math.E);
	}
/*
 * LL(1)Parser
 */
	Token current=null;
	Token next=null;
	Token peekNextToken(){
		return next;
	}
	Token getCurrentToken(){
		return current;
	}
	void move(){
		current=next;
		next=lexer.getNextToken();
	}
	/*
	 *
	<ID><MIDDLEOPT><FRONTOPT><BACKOPT><COMMA><RP><LP><NUM><EPSILON> #
	<exp>#
	<exp>::=<exp1>|<EOF>
	<exp1>::=<term> <exp2>
	<exp2>::=<MIDDLEOPT> <term><exp2>|<EPSILON>
	<term>::=<factor> <term1>
	<term1>::=<MIDDLEOPT2> <factor><term1>|<EPSILON>
	<factor>::=<LP><exp1><RP>
	<factor>::=<FRONTOPT> <NUM> 
	<factor>::=<FRONTOPT> <NUM> <BACKOPT>
	<factor>::=<FRONTOPT> <fun> 
	<factor>::=<FRONTOPT> <fun> <BACKOPT>
	<factor>::=<fun> 
	<factor>::=<fun> <BACKOPT>
	<factor>::= <NUM> <BACKOPT>
	<factor>::= <NUM>
	<fun>::=<ID><LP><args><RP>
	<args>::=<exp1><COMMA><args>
	<args>::=<exp1>
	##
	exp:FIRST集为
LP	NUM	ID	FRONTOPT	
exp1:FIRST集为
LP	NUM	ID	FRONTOPT	
EOF:FIRST集为

term:FIRST集为
LP	NUM	ID	FRONTOPT	
exp2:FIRST集为
EPSILON	MIDDLEOPT	
factor:FIRST集为
LP	NUM	ID	FRONTOPT	
term1:FIRST集为
EPSILON	
MIDDLEOPT2:FIRST集为

fun:FIRST集为
ID	
args:FIRST集为
LP	NUM	ID	FRONTOPT	
exp2:FOLLOW集为
#	COMMA	RP	
term1:FOLLOW集为
EPSILON	#	MIDDLEOPT	COMMA	RP		

*/
	
	public double parse() throws Exception{
		move();
		return exp();
	}
	//<exp>::=<exp1>|<EOF>
	//FIRST:LP	NUM	ID	FRONTOPT	EOF
	double exp() throws Exception{
		Token cur=getCurrentToken();
		if(cur==Token.EOF)
		return 0;
		else
		{
			double result= exp1();
			cur=getCurrentToken();
			if(cur!=Token.EOF){
				throw new Exception("Expression Error");
			}
			return result;
		}
		
	}

	//<exp1>::=<term> <exp2>
	//exp1:FIRST集为
	//LP	NUM	ID	FRONTOPT
	//exp1:FOLLOW集为
	//#	COMMA	RP	
	double exp1() throws Exception{
		double t1=term();
		return exp2(t1);

		
	}
	//<exp2>::=<MIDDLEOPT> <term><exp2>|<EPSILON>
	//exp2:FIRST集为
	//EPSILON	MIDDLEOPT
	//exp2:FOLLOW集为
	//#	COMMA	RP	
	double exp2(double t1) throws Exception{
		Token peek=getCurrentToken();
		
		if(peek==Token.ADD)
		{
			move();
			return exp2(t1+term());
		}
		else if(peek==Token.MIN){
			move();
			return exp2(t1-term());
		}
		else {
			Token cur=getCurrentToken();
			//System.out.println(cur);
			if(cur!=Token.EOF&&cur!=Token.COMMA&&cur!=Token.RIGHTPAR){
				throw new Exception("Expression Error");
			}
			return t1;
			}
	}
	//<term>::=<factor> <term1>
	//<term1>::=<MIDDLEOPT2> <factor><term1>|<EPSILON>
	double term() throws Exception{
		double t1=factor();
		return term1(t1);
	}
	//follow:#	MIDDLEOPT	COMMA	RP
	double term1(double t1) throws Exception{
		Token peek=getCurrentToken();
		
		if(peek==Token.MUL)
		{
			move();
			return term1(t1*factor());
		}
		else if(peek==Token.DIV){
			move();
			return term1(t1/factor());
		}
		else {
			Token cur=getCurrentToken();
			//System.out.println(cur);
			if(cur!=Token.EOF&&cur!=Token.COMMA&&cur!=Token.RIGHTPAR&&cur!=Token.ADD&&cur!=Token.MIN){
				throw new Exception("Expression Error");
			}
			return t1;
			}
		
	}
	
	//<factor>::=[<FRONTOPT>] <fun>|<NUM>|<LP><exp1><RP>  [<BACKOPT>]
	//FIRST:LP	NUM	ID	FRONTOPT	
	double factor() throws Exception{
		Token peek=getCurrentToken();
	
		double sign=1;
		if(peek==Token.ADD)
		{
			move();
		}
		else if(peek==Token.MIN){
			move();
			sign=-1;
		}
		
		double t1;
		peek=getCurrentToken();
		if(peek.getType()==Token.NUM) {
			move();
			t1=((NUM)peek).getValue();
		}else {
			if(peek==Token.LEFTPAR){
				move();
				t1=exp1();
				peek=getCurrentToken();
				if(peek==Token.RIGHTPAR){
					move();
				}
				else throw new Exception("Expression ERROR");
			}
			else	if(peek.getType()==Token.ID)
			{
				if(peekNextToken()!=Token.LEFTPAR){
				move();
				ID tt=(ID)peek;
				if(VariableTable.containsKey(tt.getID()))
				 t1=VariableTable.get(tt.getID());
				else throw new Exception(tt.getID()+" does not exist");
				}
				else t1=fun();
			}
			else throw new Exception("Expression ERROR");
		}
		
		peek=getCurrentToken();
		if(peek==Token.EXCLAIM){
			move();
			
			t1=GammaFun.Gamma(t1);
		}
		System.out.println(t1);
		return t1*sign;
		
	}
	
	//<fun>::=<ID><LP><args><RP>
	//FIRST: ID
	double fun() throws Exception{
		Token peek=getCurrentToken();
		double result;
		ID  id;
		if(peek.getType()==Token.ID){
			move();
			id=(ID)peek;
			peek=getCurrentToken();
			System.out.println(peek);
			if(peek==Token.LEFTPAR){
				move();
				FunctionEval f=functionTable.get(id.getID());
				if(f!=null){
				result=f.eval(args());
				}
				else  throw new Exception("function "+id.getID()+"does not exist");
				peek=getCurrentToken();
				if(peek==Token.RIGHTPAR){
					move();
					return result;
				}
				else throw new Exception("Expression ERROR");
			}
			else  throw new Exception("Expression ERROR");
		}
		 throw new Exception("Expression ERROR");
		
		
	}
	
	//<args>::=<exp1><COMMA><args>
	//<args>::=<exp1>
	//FIRST:LP	NUM	ID	FRONTOPT	
	Double[] args() throws Exception{
		Double [] tmp1=new Double[1];
		Double [] tmp2;
		Double[] tmp12;
		tmp1[0]=exp1();
		Token peek=getCurrentToken();
		if(peek==Token.COMMA)
		{
			move();
			tmp2=args();
			tmp12 = new Double[tmp1.length + tmp2.length];
			System.arraycopy(tmp1, 0, tmp12, 0, tmp1.length);
			System.arraycopy(tmp2, 0, tmp12, tmp1.length, tmp2.length);
			return tmp12;
		}
		return  tmp1;
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("begin");
		String testcase1="4 !";
		char[] source= testcase1.trim().toCharArray();
		for(char i : source) System.out.println(i);
		System.out.println("end");
		ExpressionParser p=new ExpressionParser(source);
		try {
			System.out.println("result is"+  Double.toString(p.parse()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
