package equation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class GaussMethod {

    private static void to_triangle(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int k = 0; k < rows; k++) {
            boolean isReplacement = true;
            for (int i = k; i < rows; i++) {
                int counter = 0;
                for (int j = 0; j < rows; j++) {
                    if (matrix[i][j] == 0) counter++;
                }
                if (counter < rows) {
                    if (matrix[k][k] == 0) isReplacement = switch_rows(matrix, k);
                    if (!isReplacement) return;
                    break;
                }
            }

            for (int i = 0; i < rows; i++) {
                double to_mul;
                if (matrix[i][k] == 0) to_mul = 0;
                else to_mul = -matrix[i][k] / matrix[k][k];
                for (int j = 0; j < cols; j++) {
                    if (i <= k || j < k) continue;
                    matrix[i][j] = matrix[i][j] + matrix[k][j] * to_mul;
                }
            }
        }
    }

    private static boolean switch_rows(double[][] matrix, int i) {
        int c = 0;
        int isInf = 0;
        while (matrix[i + c][i] == 0) {
            if (isInf > matrix.length + 2) return false;
            int temp = i + c == matrix.length - 1 ? c = -i : c++;
            isInf++;
        }
        if (c != -1) shift_rows(i, c, matrix);
        return true;
    }


    private static void shift_rows(int i, int c, double[][] matrix) {
        for (int j = 0; j < matrix[i].length; j++) {
            double temp = matrix[i + c][j];
            matrix[i + c][j] = matrix[i][j];
            matrix[i][j] = temp;
        }
    }

    public static double[] get_solution(double[][] matrix) {
        to_triangle(matrix);
        MathContext context = new MathContext(20, RoundingMode.HALF_UP);
        int rows = matrix.length - 1;
        int cols = matrix[0].length - 1;
        double[] solutions = new double[cols];
        for (int i = rows; i >= 0; --i) {
            for (int j = cols - 1; j >= 0; --j) {
                float to_sub = 0;
                for (int k = j; k < cols; ++k) {
                    to_sub += solutions[k] * matrix[i][k];
                }
                solutions[j] = new BigDecimal((matrix[i][cols] - to_sub) / matrix[i][j], context).floatValue();
                i--;
            }
        }
        return solutions;
    }
}
