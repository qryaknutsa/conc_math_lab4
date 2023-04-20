package equation.linearSquareMethod;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

abstract class AFunc implements IFunc{
    public String func_name;
    double getSX(double[] x, int pow) {
        double sum = 0;
        for (double i : x) {
            double to_sum = 1;
            for (int j = 0; j < pow; j++) {
                to_sum *= i;
            }
            sum += to_sum;
        }
        return sum;
    }

    double getSXY(double[] x, double[] y, int pow_X) {
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            double xx = 1;
            for (int j = 0; j < pow_X; j++) {
                xx *= x[i];
            }
            double yy = 1;
            for (int j = 0; j < 1; j++) {
                yy *= y[i];
            }
            double mul = xx * yy;
            sum += mul;
        }
        return sum;
    }

    public double getDeviation(double[] x, double[] y) {
        double sum = 0;
        for (int i = 0; i < x.length; i++)
            sum += pow(f(x[i], x, y) - y[i], 2);
        return sqrt(sum / x.length);
    }
}
