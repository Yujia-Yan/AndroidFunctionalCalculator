package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class CosFun extends FunctionEval{

	@Override
	public double eval(Double[] doubles) throws Exception {
		// TODO Auto-generated method stub
		if(doubles.length==1)
			return Math.cos(doubles[0]);
			else throw new Exception("cos函数只能有1个参数");
	}

}
