package equation.linearSquareMethod;

import static java.lang.Math.exp;
import static java.lang.Math.log;

public class ExpFunc extends AFunc implements IFunc {

    public double[] getCoefficients(double[] x, double[] y) {
        double[] new_y = new double[y.length];
        for (int i = 0; i < y.length; i++) {
            new_y[i] = log(y[i]);
        }
        LinearFunc linearFunc = new LinearFunc();
        return linearFunc.getCoefficients(x, new_y);
    }

    @Override
    public double f(double X, double[] x, double[] y) {
        PowFunc powFunc = new PowFunc();
        double[] ar = powFunc.getCoefficients(x, y);
        func_name = "phi = " + exp(ar[1]) + "e^(" + ar[0] + "x)";
        return exp(ar[1]) * exp(ar[0] * X);
    }


//    public static void main(String[] args) {
//        double[] x = {1, 2, 3, 4, 5, 6};
//        double[] y = {1, 1.5, 3, 4.5, 7, 8.5};
//        ExpFunc powFunc = new ExpFunc();
//        double[] a = powFunc.getCoefficients(x, y);
//        System.out.println(a[0]);
//        System.out.println(exp(a[1]));
//    }
}
