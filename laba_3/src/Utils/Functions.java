package Utils;

import java.util.function.Function;

import static java.lang.Math.*;

public enum Functions {
    function1(x -> 3 * pow(x, 3) + 5 * pow(x, 2) + 3 * x - 6, "∫(3x^3+5x^2+3x-6)dx", false),
    function2(x -> sin(x) / x, "∫(sin(x)/x)dx", false),
    function3(x -> 1 / x, "∫(1/x)dx", true);
    private final Function<Double, Double> function;
    private final String text;
    private final boolean symmetrical;

    Functions(Function<Double, Double> function, String text, boolean symmetrical) {
        this.function = function;
        this.text = text;
        this.symmetrical = symmetrical;
    }

    public String getText() {
        return text;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public boolean isSymmetrical() {
        return symmetrical;
    }
}