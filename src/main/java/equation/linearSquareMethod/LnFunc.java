package equation.linearSquareMethod;

import static java.lang.Math.*;

public class LnFunc extends AFunc implements IFunc{
    public double[] getCoefficients(double[] x, double[] y) {
        double[] new_x = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            new_x[i] = log(x[i]);
        }
        LinearFunc linearFunc = new LinearFunc();
        return linearFunc.getCoefficients(new_x, y);
    }

    @Override
    public double f(double X, double[] x, double[] y) {
        double[] ar = getCoefficients(x, y);
        func_name = "phi = (" + ar[0] + ")ln(x) + (" + ar[1] + ")";
        return ar[0] * log(X) + ar[1];
    }

//    public static void main(String[] args) {
//        double[] x = {1, 2, 3, 4, 5, 6, 7};
//        double[] y = {0.1, 0.4, 0.5, 0.6, 0.7, 0.74, 0.8};
//        LnFunc powFunc = new LnFunc();
//        double[] a = powFunc.getCoefficients(x, y);
//        System.out.println(a[0]);
//        System.out.println((a[1]));
//    }
}


//11
//        46.2 39.4 36 33.8 32.2 31.2 29.6 28.6 27.6 26.6 25.6
//        0.5 1 1.5 2 2.5 3 4 5 6 7 8