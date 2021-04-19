package Utils;

import java.util.function.Function;

import static java.lang.Math.*;

public enum Functions {
    function1(x -> 3 * pow(x, 3) + 5 * pow(x, 2) + 3 * x - 6, "∫(3x^3+5x^2+3x-6)dx"),
    function2(x -> sin(x) / x, "∫(sin(x)/x)dx"),
    function3(x -> 1 / x, "∫(1/x)dx");
    private final Function<Double, Double> function;
    private final String text;

    Functions(Function<Double, Double> function, String text) {
        this.function = function;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}