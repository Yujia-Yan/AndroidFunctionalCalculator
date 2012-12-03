package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class SqrtFun extends FunctionEval{

	@Override
	public double eval(Double[] doubles) throws Exception {
		if(doubles.length==1)
			return Math.sqrt(doubles[0]);
			else throw new Exception("sqrt����ֻ����1������");
	}

}
