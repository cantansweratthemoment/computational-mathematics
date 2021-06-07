package base.Methods;

import base.Structures.Equation;
import base.Structures.Result;
import base.Structures.Table;
import base.Utils.FormatResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RungeKutta implements Method {
    @Override
    public Result solve(Equation equation, double a, double b, double y0, double h, Double precision) {
        List<String> steps = new LinkedList<>();

        ArrayList<Double> X = new ArrayList<>();
        X.add(a);
        ArrayList<Double> Y = new ArrayList<>();
        Y.add(y0);

        var func = equation.getFunction();
        int i = 1;
        for (double x = a; x + h <= b; i++) {
            double yPrev = Y.get(Y.size() - 1);

            double k1 = h * func.apply(x, yPrev);
            double k2 = h * func.apply(x + h / 2, yPrev + k1 / 2);
            double k3 = h * func.apply(x + h / 2, yPrev + k2 / 2);
            double k4 = h * func.apply(x + h, yPrev + k3);

            double yNext = yPrev + 1f / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            Y.add(yNext);

            steps.add(FormatResult.formatResult(i, x, yNext, func.apply(x, yPrev), equation.getExactValue(x)));

            x += h;
            X.add(x);
        }

        return new Result(new Table(X, Y), steps);
    }

    @Override
    public String getText() {
        return "Метод Рунге-Кутта";
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
