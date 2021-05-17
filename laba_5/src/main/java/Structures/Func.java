package Structures;

import Utils.ColorfulString;

import java.util.Scanner;
import java.util.function.Function;

public enum Func {
    function1(x -> 0.5 * x, "f(x)=0.5*x"),
    function2(Math::sin, "f(x)=sin(x)");
    private final Function<Double, Double> function;
    private final String text;
    private double left;
    private double right;

    Func(Function<Double, Double> function, String text) {
        this.function = function;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public void setRight(double right) {
        this.right = right;
    }
}