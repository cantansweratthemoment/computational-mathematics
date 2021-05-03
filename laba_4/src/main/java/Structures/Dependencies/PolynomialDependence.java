package Structures.Dependencies;

import Structures.Point;
import Structures.SystemOfLinearEquations.Matrix;
import Structures.SystemOfLinearEquations.Solution;
import Utils.ColorfulString;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class PolynomialDependence extends Dependence {
    public PolynomialDependence(ArrayList<Point> values) {
        this.name = "Полиномиальная функция второй степени";
        this.values = values;
        calculateDeviationMeasure(values);
        calculateStandardDeviation();
    }

    @Override
   public void findDependence() {
        double a1 = 0d, b1 = 0d, d1 = 0d, a2 = 0d, d2 = 0d, a3 = 0d, d3 = 0d;
        for (Point point : values) {
            a1 += pow(point.getX(), 2);
            b1 += point.getX();
            d1 += point.getY();
            a2 += pow(point.getX(), 3);
            d2 += point.getX() * point.getY();
            a3 += pow(point.getX(), 4);
            d3 += pow(point.getX(), 2) * point.getY();
        }
        double[][] matrixC = {{a1, b1, values.size()}, {a2, a1, b1}, {a3, a2, a1}};
        double[] coefficients = {d1, d2, d3};
        Solution.matrix = new Matrix(3, matrixC, coefficients, 0.001);
        Solution.solve();
        if (Solution.matrix.getQuanityVector() == null) {
            ColorfulString.aggressivelyPrintln("Система не решилась.");
            canBeSolved = false;
        } else {
            this.coefficients = Solution.matrix.getQuanityVector();
            this.function = (Double x) -> coefficients[0] * pow(x, 2) + coefficients[1] * x + coefficients[2];
            this.functionName = String.format("%.3fx²%+.3fx%+.3f", coefficients[0], coefficients[1], coefficients[2]);
        }
    }
}
