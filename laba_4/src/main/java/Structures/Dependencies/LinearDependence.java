package Structures.Dependencies;

import Structures.Point;
import Structures.SystemOfLinearEquations.Matrix;
import Structures.SystemOfLinearEquations.Solution;
import Utils.ColorfulString;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class LinearDependence extends Dependence {
    private double pirsons;

    public double getPirsons() {
        return pirsons;
    }

    public LinearDependence(ArrayList<Point> values) {
        this.name = "Линейная функция";
        this.values = values;
        calculateDeviationMeasure(values);
        calculateStandardDeviation();
    }

    @Override
   public void findDependence() {
        double a1 = 0d, b1 = 0d, c1 = 0d, c2 = 0d;
        for (Point value : values) {
            a1 += pow(value.getX(), 2);
            b1 += value.getX();
            c1 += value.getX() * value.getY();
            c2 += value.getY();
        }
        double[][] matrixC = {{a1, b1}, {b1, values.size()}};
        double[] coefficients = {c1, c2};
        Solution.matrix = new Matrix(2, matrixC, coefficients, 0.001);
        Solution.solve();
        if (Solution.matrix.getQuanityVector() == null) {
            ColorfulString.aggressivelyPrintln("Система не решилась.");
            canBeSolved = false;
        } else {
            this.coefficients = Solution.matrix.getQuanityVector();
            this.function = (Double x) -> coefficients[0] * x + coefficients[1];
            this.functionName = String.format("%.3fx%+.3f", coefficients[0], coefficients[1]);
            this.pirsons = calculateCoefficientOfCorrelation();
        }
    }

    public double calculateCoefficientOfCorrelation() {
        double aX = 0, aY = 0;
        for (Point point : values) {
            aX += point.getX() / values.size();
            aY += point.getY() / values.size();
        }
        double answer = 0, numeral = 0, denominator1 = 0, denominator2 = 0;
        for (Point point : values) {
            numeral += (point.getX() - aX) * (point.getY() - aY);
            denominator1 += pow((point.getX() - aX), 2);
            denominator2 += pow((point.getY() - aY), 2);
        }
        answer = numeral / sqrt(denominator1 * denominator2);
        return answer;
    }
}
