import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void fromFile() throws IOException {
        System.out.println("Введите название файла.");
        String fileName = sc.nextLine();
        File file = new File(fileName);
        while (!file.exists()) {
            System.out.println("Попробуйте ещё раз.");
            fileName = sc.nextLine();
            file = new File(fileName);
        }
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String accuracy_s = reader.readLine();
            double accuracy = Double.parseDouble(accuracy_s);
            String size_s = reader.readLine();
            int size = Integer.parseInt(size_s);
            String matrix_s = "";
            double[][] matrix = new double[size][size];
            String[] matrix_arr;
            int k = 0;
            for (int i = 0; i < size; i++) {
                matrix_s = "";
                matrix_s = matrix_s.concat(reader.readLine());
                matrix_arr = matrix_s.split(" ");
                for (int j = 0; j < size; j++) {
                    matrix[j][k] = Double.parseDouble(matrix_arr[j]);
                }
                k++;
            }
            String[] coefficients_arr = reader.readLine().split(" ");
            double[] coefficients = new double[size];
            for (int i = 0; i < size; i++) {
                coefficients[i] = Double.parseDouble(coefficients_arr[i]);
            }
            Solution.matrix = new Matrix(size, matrix, coefficients, accuracy);
            Solution.solve();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

    public static void fromConsole() {
        System.out.println("Введите точность.");
        double accuracy = sc.nextDouble();
        System.out.println("Введите размер матрицы.");
        int size = sc.nextInt();
        System.out.println("Введите коэффициенты матрицы (без свободных членов).");
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        double[] coefficients = new double[size];
        System.out.println("Введите свободные члены.");
        for (int i = 0; i < size; i++) {
            coefficients[i] = sc.nextDouble();
        }
        Solution.matrix = new Matrix(size, matrix, coefficients, accuracy);
        Solution.solve();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Читать из файла? [y/n]");
        String y = sc.nextLine();
        if (y.equals("y")) {
            fromFile();
        } else {
            if (!y.equals("n")) {
                System.out.println("Попробуйте еще раз.");
                System.exit(0);
            }
            fromConsole();
        }
    }
}