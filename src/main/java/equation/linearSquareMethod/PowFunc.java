package equation.linearSquareMethod;

import static java.lang.Math.*;

public class PowFunc extends AFunc implements IFunc{
    public double[] getCoefficients(double[] x, double[] y) {
        double[] new_x = new double[x.length];
        double[] new_y = new double[y.length];
        for (int i = 0; i < x.length; i++) {
            new_x[i] = log(x[i]);
            new_y[i] = log(y[i]);
        }
        LinearFunc linearFunc = new LinearFunc();
        return linearFunc.getCoefficients(new_x, new_y);
    }

    @Override
    public double f(double X, double[] x, double[] y) {
        double[] ar = getCoefficients(x, y);
        func_name = "phi = (" + exp(ar[1]) + ")x^(" + ar[0] + ")";
        return exp(ar[1]) * pow(X, ar[0]);
    }


//    public static void main(String[] args) {
//        double[] x = {1, 2, 3, 4, 5, 6};
//        double[] y = {1, 1.5, 3, 4.5, 7, 8.5};
//        PowFunc powFunc = new PowFunc();
//        double[] a = powFunc.getCoefficients(x, y);
//        System.out.println(a[0]);
//        System.out.println(exp(a[1]));
//    }
}
