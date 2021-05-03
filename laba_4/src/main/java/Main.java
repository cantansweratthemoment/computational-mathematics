import Utils.ColorfulString;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
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
        if (!readFromConsole) {
            ColorfulString.println("Введите название файла.");
            String fileName = sc.next();
            File file = new File(fileName);
            while (!file.exists()) {
                ColorfulString.aggressivelyPrintln("Попробуйте ещё раз.");
                fileName = sc.next();
                file = new File(fileName);
            }
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
        }
    }
}
