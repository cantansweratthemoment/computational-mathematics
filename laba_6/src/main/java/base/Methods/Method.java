package base.Methods;

import base.Structures.Equation;
import base.Structures.Result;

public interface Method {
    Result solve(Equation equation, double a, double b, double y0, double h, Double precision);

    String getText();

    int getOrder();
}
