package equation.linearSquareMethod;

import equation.EquationGraphic;
import equation.GaussMethod;

public class SquareFunc extends AFunc implements IFunc {
    public double[] getCoefficients(double[] x, double[] y) {
        double sx1 = getSX(x, 1);
        double sx2 = getSX(x, 2);
        double sx3 = getSX(x, 3);
        double sx4 = getSX(x, 4);
        double sy1 = getSX(y, 1);
        double sx1y1 = getSXY(x, y, 1);
        double sx2y1 = getSXY(x, y, 2);

        double[][] matrix = {{x.length, sx1, sx2, sy1}, {sx1, sx2, sx3, sx1y1}, {sx2, sx3, sx4, sx2y1}};

        return GaussMethod.get_solution(matrix);
    }

    @Override
    public double f(double X, double[] x, double[] y) {
        double[] ar = getCoefficients(x, y);
        func_name = "phi = (" + ar[2] + ")x^2 + (" + ar[1] + ")x + (" + ar[0] + ")";
        return ar[0] + ar[1] * X + ar[2] * X * X;
    }

//    public static void main(String[] args) {
//        double[] x = {46.2, 39.4, 36, 33.8, 32.2, 31.2, 29.6, 28.6, 27.6, 26.6, 25.6};
//        double[] y = {0.5, 1, 1.5, 2, 2.5, 3, 4, 5, 6, 7, 8};
//        SquareFunc powFunc = new SquareFunc();
//        double[] a = powFunc.getCoefficients(x, y);
//        System.out.println(a[0]);
//        System.out.println(a[1]);
//        System.out.println(a[2]);
//
//        System.out.println(powFunc.getDeviation(x, y));
//        EquationGraphic equationGraphic = new EquationGraphic();
//        equationGraphic.drawGraphic(2, x, y);
//    }
}
