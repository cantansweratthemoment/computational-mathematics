package base.Structures;

import lombok.Data;

import java.util.function.BinaryOperator;

@Data
public class Equation {
    private final String text;
    private final BinaryOperator<Double> function;
    private final BinaryOperator<Double> constantCalculatorFunction;
    private final BinaryOperator<Double> exactValueFunction;

    private Double c = null;

    public Equation(String text, BinaryOperator<Double> function,
                    BinaryOperator<Double> constantCalculatorFunction,
                    BinaryOperator<Double> exactValueFunction) {
        this.function = function;
        this.text = text;
        this.constantCalculatorFunction = constantCalculatorFunction;
        this.exactValueFunction = exactValueFunction;
    }

    public void calculateC(double x0, double y0) {
        c = constantCalculatorFunction.apply(x0, y0);
    }

    public double getExactValue(double x) {
        return exactValueFunction.apply(c, x);
    }
}
