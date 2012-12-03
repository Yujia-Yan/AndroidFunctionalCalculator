package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class TanFun extends FunctionEval{

	@Override
	public double eval(Double[] doubles) throws Exception {
		if(doubles.length==1)
			return Math.tan(doubles[0]);
			else throw new Exception("tan函数只能有1个参数");
	}

}
