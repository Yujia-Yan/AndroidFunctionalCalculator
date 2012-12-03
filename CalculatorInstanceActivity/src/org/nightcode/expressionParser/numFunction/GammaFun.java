package org.nightcode.expressionParser.numFunction;

import org.nightcode.expressionParser.FunctionEval;

public class GammaFun extends FunctionEval {

    public    static double Gamma(double xx)
         {
    	if((Math.round(xx)-xx)<1E-14){
    		long x=Math.round(xx);
    		if(x==0) return 1.0d;
    		else{
    			long b=1;
    			for(int i=1;i<=x;i++){
    				b*=i;
    			}
    			return b;
    		}
    	}
             double x, y, tmp, ser;
             double[] cof=new double[] {76.18009172947146,-86.50532032941677,24.01409824083091,-1.231739572450155,0.1208650973866179e-2,-0.5395239384953e-5};
            int j;
            x = xx;
            y = x;
            tmp = x + 5.5;
            tmp -= (x + 0.5) * Math.log(tmp);
            ser = 1.000000000190015;
            for (j = 0; j <= 5; j++)
             {
                ser += cof[j] / (++y);
             }
             return xx*Math.exp((-tmp + Math.log(2.5066282746310005 * ser / x)));
	}
	@Override
	public double eval(Double[] doubles) throws Exception {
		// TODO Auto-generated method stub
		if(doubles.length==1)
		return Gamma(doubles[0]);
		else throw new Exception("gamma函数只能有1个参数");
	}

}
