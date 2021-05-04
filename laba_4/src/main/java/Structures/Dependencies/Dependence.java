package Structures.Dependencies;

import Structures.Point;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

import static java.lang.Math.*;

public abstract class Dependence {
    protected boolean canBeSolved = true;
    protected String name;
    protected String functionName;
    protected ArrayList<Point> values;
    protected double deviationMeasure;
    protected UnaryOperator<Double> function;
    protected double standardDeviation;
    protected double[] answers;
    protected boolean isLinear=false;
    protected double pirsons;

    public double getPirsons() {
        return pirsons;
    }

    public boolean isLinear() {
        return isLinear;
    }

    public boolean isCanBeSolved() {
        return canBeSolved;
    }

    public void calculateDeviationMeasure(ArrayList<Point> points) {
        double deviation = 0;
        for (Point point : points) {
            deviation += pow(point.getY() - function.apply(point.getX()), 2);
        }
        this.deviationMeasure = deviation;
    }

    public void calculateStandardDeviation() {
        this.standardDeviation = sqrt(this.deviationMeasure / this.values.size());
    }

    public abstract void findDependence();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public ArrayList<Point> getValues() {
        return values;
    }

    public void setValues(ArrayList<Point> values) {
        this.values = values;
    }

    public double getDeviationMeasure() {
        return deviationMeasure;
    }

    public void setDeviationMeasure(double deviationMeasure) {
        this.deviationMeasure = deviationMeasure;
    }

    public UnaryOperator<Double> getFunction() {
        return function;
    }

    public void setFunction(UnaryOperator<Double> function) {
        this.function = function;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
