package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class TanhFun extends FunctionEval {

	@Override
	public double eval(Double[] doubles) throws Exception {
		if(doubles.length==1)
			return Math.tanh(doubles[0]);
			else throw new Exception("tan����ֻ����1������");
	}

}
