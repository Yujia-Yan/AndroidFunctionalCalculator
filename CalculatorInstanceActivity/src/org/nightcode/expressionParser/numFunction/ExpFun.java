package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class ExpFun extends FunctionEval{

	@Override
	public double eval(Double[] doubles) throws Exception {
		if(doubles.length==1)
			return Math.exp(doubles[0]);
			else throw new Exception("exp函数只能有1个参数");		
	}

}
