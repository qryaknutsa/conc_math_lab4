package equation.linearSquareMethod;

import equation.GaussMethod;

import static java.lang.Math.exp;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class LinearFunc extends AFunc implements IFunc{
    public double[] getCoefficients(double[] x, double[] y) {
        double sx1 = getSX(x, 1);
        double sx2 = getSX(x, 2);
        double sy1 = getSX(y, 1);
        double sx1y1 = getSXY(x, y, 1);

        double[][] matrix = {{sx2, sx1, sx1y1}, {sx1, x.length, sy1}};

        return GaussMethod.get_solution(matrix);
    }


    @Override
    public double f(double X, double[] x, double[] y) {
        double[] ar = getCoefficients(x, y);
        func_name = "phi = (" + ar[0] + ")x + (" + ar[1] + ")";
        return ar[0] * X + ar[1];
    }

    double getAverage(double[] ar) {
        double sum = 0;
        for (double i : ar) {
            sum += i;
        }
        return sum / ar.length;
    }

    public double getCorrelation(double[] x, double[] y) {
        double arx = getAverage(x);
        double ary = getAverage(y);

        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;

        for (int i = 0; i < x.length; i++) {
            double xx = x[i] - arx;
            double yy = y[i] - ary;
            sum1 += (xx * yy);
            sum2 += pow(xx, 2);
            sum3 += pow(yy, 2);
        }
        return sum1 / sqrt(sum2 * sum3);
    }


//    public static void main(String[] args) {
//        double[] x = {1, 2, 3, 4, 5, 6};
//        double[] y = {1, 1.5, 3, 4.5, 7, 8.5};
//        LinearFunc powFunc = new LinearFunc();
//        double[] a = powFunc.getCoefficients(x, y);
//        System.out.println(a[0]);
//        System.out.println(a[1]);
//    }

}
