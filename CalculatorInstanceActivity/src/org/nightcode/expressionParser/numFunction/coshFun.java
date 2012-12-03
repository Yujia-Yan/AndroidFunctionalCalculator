package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class coshFun extends FunctionEval{

	@Override
	public double eval(Double[] doubles) throws Exception {
		if(doubles.length==1)
			return Math.cosh(doubles[0]);
			else throw new Exception("cosh函数只能有1个参数");
	}

}
