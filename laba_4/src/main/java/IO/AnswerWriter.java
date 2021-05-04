package IO;

import Structures.Dependencies.Dependence;
import Structures.Point;

import java.io.*;
import java.util.ArrayList;

public class AnswerWriter {
    static final int decimal = 3;
    private ArrayList<Dependence> dependencies;
    private boolean writeToConsole;

    public AnswerWriter(ArrayList<Dependence> dependencies, boolean writeToConsole) {
        this.dependencies = dependencies;
        this.writeToConsole = writeToConsole;
    }

    public void printAnswer() throws IOException {
        //:D
        FileWriter fw = new FileWriter("/Users/ilinskaya/Documents/GitHub/computational-mathematics/laba_4/src/main/resources/output");
        BufferedWriter writer = new BufferedWriter(fw);
        for (Dependence dependence : dependencies) {
            printTable(dependence, writer);
        }
        printBest(writer);
        writer.flush();
    }

    public void printBest(BufferedWriter writer) throws IOException {
        double min = 10000;
        String minDependency = ":)";
        for (Dependence dependence : dependencies) {
            if (dependence.isCanBeSolved()) {
                if (dependence.getStandardDeviation() < min) {
                    min = dependence.getStandardDeviation();
                    minDependency = dependence.getName();
                }
            }
        }
        if (writeToConsole) {
            System.out.println("Функция с наименьшим среднеквадратическим отклонением: " + minDependency + ".");
        } else {
            writer.write("Функция с наименьшим среднеквадратическим отклонением: " + minDependency + ".");
        }
    }

    public void printTable(Dependence dependence, BufferedWriter writer) throws IOException {
        if (writeToConsole) {
            if (dependence.isCanBeSolved()) {
                System.out.println(dependence.getName());
                System.out.print("\nx=   ");
                for (Point point : dependence.getValues()) {
                    System.out.printf("%." + decimal + "f  ", point.getX());
                }
                System.out.print("\ny=   ");
                for (Point point : dependence.getValues()) {
                    System.out.printf("%." + decimal + "f  ", point.getY());
                }
                System.out.println("\n" + dependence.getFunctionName() + "=   ");
                for (Point point : dependence.getValues()) {
                    System.out.printf("%." + decimal + "f  ", dependence.getFunction().apply(point.getX()));
                }
                System.out.println("\nε=   ");
                for (Point point : dependence.getValues()) {
                    System.out.printf("%." + decimal + "f  ", point.getY() - dependence.getFunction().apply(point.getX()));
                }
                System.out.println();
                if (dependence.isLinear()) {
                    System.out.println("Коэффициент Пирсона = " + dependence.getPirsons());
                }
            } else {
                System.out.println(dependence.getName() + " не существует.");
            }
        } else {
            if (dependence.isCanBeSolved()) {
                writer.write(dependence.getName());
                writer.write("\nx=   ");
                for (Point point : dependence.getValues()) {
                    writer.write(point.getX() + " ");
                }
                writer.write("\ny=   ");
                for (Point point : dependence.getValues()) {
                    writer.write(point.getY() + " ");
                }
                writer.write("\n" + dependence.getFunctionName() + "=   ");
                for (Point point : dependence.getValues()) {
                    writer.write(dependence.getFunction().apply(point.getX()) + " ");
                }
                writer.write("\nε=   ");
                for (Point point : dependence.getValues()) {
                    writer.write(point.getY() - dependence.getFunction().apply(point.getX()) + " ");
                }
                writer.write("\n");
                if (dependence.isLinear()) {
                    writer.write("Коэффициент Пирсона = " + dependence.getPirsons());
                }
            } else {
                writer.write(dependence.getName() + " не существует.");
            }
        }
    }
}
