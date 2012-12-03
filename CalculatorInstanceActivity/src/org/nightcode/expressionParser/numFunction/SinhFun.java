package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class SinhFun extends FunctionEval{

	@Override
	public double eval(Double[] doubles) throws Exception {
		if(doubles.length==1)
			return Math.sinh(doubles[0]);
			else throw new Exception("sinh函数只能有1个参数");
	}

}
