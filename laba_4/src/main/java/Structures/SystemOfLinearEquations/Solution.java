
package Structures.SystemOfLinearEquations;

public class Solution {
    public static Matrix matrix;

    public static void solve() {
        // System.out.println("Исходная матрица:\n");
        /*matrix.printMatrix();*/
        // if (matrix.checkDiagonalDominance()) {
        //System.out.println("Матрица имеет диагональное преобладание.");
        // } else {
          /*;  System.out.println("Матрица не имеет диагонального преобладания. Попробуем совершить перестановку...");
            if (matrix.searchingForDiagonalDominance()) {
                if (matrix.checkDiagonalDominance()) {
                    System.out.println("Новая матрица:");
                    /*matrix.printMatrix();*/
               /* } else {
                    System.out.println("Не получилось.");
                    System.exit(0);
                }
            } else {
                System.out.println("Не получилось.");
                System.exit(0);
            }
        }*/
        /*matrix.setTransformedMatrix();*/
        matrix.findSolution();
    }
}