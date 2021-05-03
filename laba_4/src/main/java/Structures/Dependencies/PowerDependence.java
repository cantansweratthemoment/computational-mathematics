package Structures.Dependencies;

import Structures.Point;
import Structures.SystemOfLinearEquations.Matrix;
import Structures.SystemOfLinearEquations.Solution;
import Utils.ColorfulString;

import java.util.ArrayList;

import static java.lang.Math.*;

public class PowerDependence extends Dependence {
    public PowerDependence(ArrayList<Point> values) {
        this.name = "Степенная функция";
        this.values = values;
        calculateDeviationMeasure(values);
        calculateStandardDeviation();
    }

    @Override
   public void findDependence() {
        double a1 = 0d, b1 = 0d, c1 = 0d, c2 = 0d;
        for (Point value : values) {
            a1 += pow(log(value.getX()), 2);
            b1 += log(value.getX());
            c1 += log(value.getX()) * log(value.getY());
            c2 += log(value.getY());
        }
        double[][] matrixC = {{b1, a1}, {values.size(), b1}};
        double[] coefficients = {c1, c2};
        Solution.matrix = new Matrix(2, matrixC, coefficients, 0.001);
        Solution.solve();
        if (Solution.matrix.getQuanityVector() == null) {
            ColorfulString.aggressivelyPrintln("Система не решилась.");
            canBeSolved = false;
        } else {
            this.coefficients = Solution.matrix.getQuanityVector();
            coefficients[0] = exp(coefficients[0]);
            this.function = (Double x) -> coefficients[0] * pow(x, coefficients[1]);
            this.functionName = String.format("%.3fx^%.3f", coefficients[0], coefficients[1]);
        }
    }
}
