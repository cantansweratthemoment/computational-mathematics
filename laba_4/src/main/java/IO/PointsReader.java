package IO;

import Structures.Point;
import Utils.ColorfulString;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PointsReader {
    static int MIN = 12;

    public ArrayList<Point> readFromConsole(Scanner sc) {
        boolean q = false;
        int w = 0;
        while (!q) {
            ColorfulString.println("Какое количество точек вы хотите ввести? (Не менее 12.)");
            try {
                String ws = sc.nextLine();
                w = Integer.parseInt(ws);
            } catch (NumberFormatException e) {
                ColorfulString.aggressivelyPrintln("Попробуйте ещё раз!");
            }
            q = w >= MIN;
            if (!q) {
                ColorfulString.aggressivelyPrintln("Не менее 12!");
            }
        }
        ColorfulString.println("Вводите точки в формате <x y>.");
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            double x = 0, y = 0;
            q = false;
            while (!q) {
                try {
                    String xs = sc.nextLine();
                    String ys = sc.nextLine();
                    x = Double.parseDouble(xs);
                    y = Double.parseDouble(ys);
                } catch (NumberFormatException e) {
                    ColorfulString.aggressivelyPrintln("Попробуйте ещё раз!");
                }
                q = true;
            }
            Point point = new Point(x, y);
            points.add(point);
        }
        return points;
    }

    public ArrayList<Point> readFromFile(BufferedReader reader) throws IOException {
        String amount_s = reader.readLine();
        int amount = Integer.parseInt(amount_s);
        double x, y;
        String xAndY;
        String[] q;
        String x_s, y_s;
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            xAndY = reader.readLine();
            q = xAndY.split(" ");
            x_s = q[0];
            y_s = q[1];
            x = Double.parseDouble(x_s);
            y = Double.parseDouble(y_s);
            Point point = new Point(x, y);
            points.add(point);
        }
        return points;
    }
}
