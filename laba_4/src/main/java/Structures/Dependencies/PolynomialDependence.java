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
        double[][] matrixC = {{values.size(), b1, a1}, {b1, a1, a2}, {a1, a2, a3}};
        double[] coefficients = {d1, d2, d3};
        Solution.matrix = new Matrix(3, matrixC, coefficients, 0.001);
        Solution.solve();
        if (Solution.matrix.getQuanityVector() == null) {
            ColorfulString.aggressivelyPrintln("Система не решилась.");
            canBeSolved = false;
        } else {
            this.answers = Solution.matrix.getQuanityVector();
            if (Double.isNaN(this.answers[0])) {
                ColorfulString.aggressivelyPrintln("Система не решилась.");
                canBeSolved = false;
            }
            this.function = (Double x) -> this.answers[0] * pow(x, 2) + this.answers[1] * x + this.answers[2];
            this.functionName = String.format("%.3fx²%+.3fx%+.3f", this.answers[0], this.answers[1], this.answers[2]);
            calculateDeviationMeasure(values);
            calculateStandardDeviation();
        }
    }
}
