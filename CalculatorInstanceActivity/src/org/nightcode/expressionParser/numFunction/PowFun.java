package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class PowFun extends FunctionEval {

	@Override
	public double eval(Double[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(args.length);
		if(args.length==2){
			return Math.pow(args[0], args[1]);
		}
		else throw new Exception("Pow函数只能有2个参数");
		
	}

}
