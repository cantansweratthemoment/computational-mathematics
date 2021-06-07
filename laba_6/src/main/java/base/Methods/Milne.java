package base.Methods;

import base.Structures.Equation;
import base.Structures.Result;
import base.Structures.Table;
import base.Utils.FormatResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Milne implements Method {
    private final RungeKutta rungeKutta = new RungeKutta();

    @Override
    public Result solve(Equation equation, double a, double b, double y0, double h, Double precision) {
        var func = equation.getFunction();
        var result = rungeKutta.solve(equation, a, a + 3.5 * h, y0, h, null);
        List<String> steps = new LinkedList<>(result.getSteps());
        var data = result.getTable();
        ArrayList<Double> X = data.getXs();
        ArrayList<Double> Y = data.getYs();
        ArrayList<Double> F = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            F.add(func.apply(X.get(i), Y.get(i)));
        }
        int i = 4;
        for (double x = X.get(X.size() - 1) + h; x <= b; i++) {
            double yNew = Y.get(Y.size() - 4) + 4 * h * (2 * F.get(0) - F.get(1) + 2 * F.get(2)) / 3;
            double fNew = func.apply(x, yNew);
            double yCheck = Y.get(Y.size() - 2) + h * (F.get(1) + 4 * F.get(2) + fNew) / 3;
            while (Math.abs(yCheck - yNew) > precision) {
                yNew = yCheck;
                fNew = func.apply(x, yNew);
                yCheck = Y.get(Y.size() - 2) + h * (F.get(1) + 4 * F.get(2) + fNew) / 3;
            }
            Y.add(yNew);
            updateF(F, fNew);
            steps.add(FormatResult.formatResult(i, x, yNew, fNew, equation.getExactValue(x)));
            X.add(x);
            x += h;
        }
        return new Result(new Table(X, Y), steps);
    }

    private void updateF(ArrayList<Double> F, double fNew) {
        F.set(0, F.get(1));
        F.set(1, F.get(2));
        F.set(2, fNew);
    }

    @Override
    public String getText() {
        return "Метод Милна";
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
