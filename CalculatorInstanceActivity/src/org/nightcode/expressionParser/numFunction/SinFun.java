package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class SinFun extends FunctionEval{

	@Override
	public double eval(Double[] doubles) throws Exception {
		// TODO Auto-generated method stub
		if(doubles.length==1)
			return Math.sin(doubles[0]);
			else throw new Exception("sin函数只能有1个参数");
		
	}

}
