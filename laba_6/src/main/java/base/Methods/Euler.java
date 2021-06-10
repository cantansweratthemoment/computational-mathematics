package base.Methods;

import base.Structures.Equation;
import base.Structures.Result;
import base.Structures.Table;
import base.Utils.FormatResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Euler implements Method {
    @Override
    public Result solve(Equation equation, double a, double b, double y0, double h, Double precision) {
        List<String> steps = new LinkedList<>();

        ArrayList<Double> X = new ArrayList<>();
        X.add(a);
        ArrayList<Double> Y = new ArrayList<>();
        Y.add(y0);

        int i = 1;
        for (double x = a; x + h <= b+0.00000000001; i++) {
            double yPrev = Y.get(Y.size() - 1);
            double func_value = equation.getFunction().apply(x, yPrev);

            double accuracy = Math.abs(equation.getExactValue(x) - yPrev);
            steps.add(FormatResult.formatResult(i, x, yPrev, equation.getExactValue(x), accuracy));
            if (accuracy > precision) {
                h /= 2;
                x = a;
                i = 1;
                X.clear();
                Y.clear();
                X.add(a);
                Y.add(y0);
                //x = X.get(X.size() - 1);
            } else {
                x += h;
                Y.add(yPrev + h * func_value);
                X.add(x);
            }
        }
        return new Result(new Table(X, Y), steps);
    }

    @Override
    public String getText() {
        return "Метод Эйлера";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
