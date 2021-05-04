import IO.PointsReader;
import Utils.ColorfulString;
import Structures.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        boolean q = false;
        boolean readFromConsole = false;
        String w;
        while (!q) {
            ColorfulString.println("Читать входные данные из консоли? [y]/[n]");
            w = sc.nextLine();
            switch (w) {
                case "y" -> {
                    readFromConsole = true;
                    q = true;
                }
                case "n" -> q = true;
                default -> ColorfulString.aggressivelyPrintln("Попробуйте ещё раз!");
            }
        }
        ArrayList<Point> points;
        PointsReader pointsReader = new PointsReader();
        if (!readFromConsole) {
            ColorfulString.println("Введите название файла.");
            String fileName = sc.nextLine();
            File file = new File(fileName);
            while (!file.exists()) {
                ColorfulString.aggressivelyPrintln("Попробуйте ещё раз.");
                fileName = sc.nextLine();
                file = new File(fileName);
            }
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            points = pointsReader.readFromFile(reader);

        } else {
            points = pointsReader.readFromConsole(sc);
        }
        Solver solver = new Solver(points);
        solver.solve();
    }
}