package Structures.SystemOfLinearEquations;

public class Matrix {
    private final int size;
    private final double[][] matrix;
    private final double[] coefficients;
    private final double accuracy;
    private double[][] transformedMatrix;
    private double[] quanityVector;

    public double[] getQuanityVector() {
        return quanityVector;
    }


    public Matrix(int size, double[][] matrix, double[] coefficients, double accuracy) {
        this.size = size;
        this.matrix = matrix;
        this.coefficients = coefficients;
        this.accuracy = accuracy;
    }

    /*public void printMatrix() {
        int maxLength = 0;
        String format = "";
        int temp = 0;
        for (double[] doubles : matrix) {
            for (double aDouble : doubles) {
                temp = String.valueOf(aDouble).length();
                if (temp > maxLength) {
                    maxLength = temp;
                }
            }
        }
        format = "%" + maxLength + ".2f\t";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf(format, matrix[i][j]);
            }
            System.out.print("| ");
            System.out.printf(format, coefficients[i]);
            System.out.println();
        }
    }*/

    public boolean checkDiagonalDominance() {
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            int sum = 0;
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    sum += Math.abs(matrix[i][j]);
                }
            }
            if (Math.abs(matrix[i][i]) > sum) {
                flag = true;
            }
            if (Math.abs(matrix[i][i]) < sum) {
                return false;
            }
        }
        double proizv = 1;
        for (int k = 0; k < size; k++) {
            proizv *= matrix[k][k];
        }
        if (proizv == 0) {
            return false;
        }
        return flag;
    }

    public boolean searchingForDiagonalDominance() {
        Matrix copy = new Matrix(this.size, new double[this.size][this.size], new double[this.size], this.accuracy);
        boolean[] pasted = new boolean[size];
        for (int i = 0; i < size; i++) {
            pasted[i] = false;
        }
        for (int i = 0; i < size; i++) {
            int sum = 0;
            for (int j = 0; j < size; j++) {
                sum += Math.abs(matrix[i][j]);
            }
            boolean flag = false;
            int index = 0;
            for (int j = 0; j < size; j++) {
                if (Math.abs(matrix[i][j]) >= sum - Math.abs(matrix[i][j])) {
                    flag = true;
                    index = j;
                }
            }
            if (flag) {
                if (!pasted[index]) {
                    for (int k = 0; k < size; k++) {
                        copy.matrix[index][k] = matrix[i][k];
                    }
                    copy.coefficients[index] = this.coefficients[i];
                    pasted[index] = true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        Solution.matrix = copy;
        return true;
    }

    public void setTransformedMatrix() {
        this.transformedMatrix = new double[size][size + 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                if (i == j) {
                    transformedMatrix[i][j] = 0;
                } else {
                    if (j == size) {
                        transformedMatrix[i][j] = this.coefficients[i] / this.matrix[i][i];
                    } else {
                        transformedMatrix[i][j] = -1 * (this.matrix[i][j] / this.matrix[i][i]);
                    }
                }
            }
        }
    }

    public void findSolution() {
        double[] result;
        if (size == 2) {
            double det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[0][1];
            double det1 = coefficients[0] * matrix[1][1] - matrix[0][1] * coefficients[1];
            double det2 = matrix[0][0] * coefficients[1] - matrix[0][1] * coefficients[0];
            result = new double[]{det1 / det, det2 / det};
            this.quanityVector = result;
        }
        if (size == 3) {
            double SX = matrix[0][1];
            double SXX = matrix[0][2];
            double SXXX = matrix[1][2];
            double SXXXX = matrix[2][2];
            double SY = coefficients[0];
            double SXY = coefficients[1];
            double SXXY = coefficients[2];
            double n = matrix[0][0];
            double delta = (n * SXX * SXXXX) + (SX * SXXX * SXX) + (SX * SXXX * SXX) - (SXX * SXX * SXX) - (SXXX * SXXX * n) - (SX * SX * SXXXX);
            double delta1 = (SY * SXX * SXXXX) + (SXY * SXXX * SXX) + (SX * SXXX * SXXY) - (SXXY * SXX * SXX) - (SXXX * SXXX * SY) - (SXY * SX * SXXXX);
            double delta2 = (n * SXY * SXXXX) + (SX * SXXY * SXX) + (SY * SXXX * SXX) - (SXX * SXY * SXX) - (SX * SY * SXXXX) - (SXXY * SXXX * n);
            double delta3 = (n * SXX * SXXY) + (SX * SXXX * SY) + (SX * SXY * SXX) - (SXX * SXX * SY) - (SX * SX * SXXY) - (SXXX * SXY * n);
            double a = delta1 / delta;
            double b = delta2 / delta;
            double c = delta3 / delta;
            this.quanityVector= new double[]{c, b, a};
        }
//        double[] quanityVector = new double[size];
//        double[] newQuanityVector = new double[size];
//        double[] errorVector = new double[size];
//        int iteration = 0;
//        for (int i = 0; i < size; i++) {
//            quanityVector[i] = 0;
//        }
//        while (true) {
//            for (int i = 0; i < size; i++) {
//                double x = 0;
//                for (int j = 0; j < size; j++) {
//                    x += this.transformedMatrix[i][j] * quanityVector[j];
//                    if (j == size - 1) {
//                        x += this.transformedMatrix[i][j + 1];
//                    }
//                }
//                errorVector[i] = Math.abs(quanityVector[i] - x);
//                newQuanityVector[i] = x;
//                if (i == size - 1) {
//                    for (int l = 0; l < size; l++) {
//                        quanityVector[l] = newQuanityVector[l];
//                    }
//                    this.quanityVector = quanityVector;
//                    /*System.out.println();
//                    System.out.println("Вектор решений:");
//                    for (int k = 0; k < size; k++) {
//                        System.out.printf("%.5f ", quanityVector[k]);
//                    }
//                    System.out.println();
//                    System.out.println("Вектор погрешностей:");
//                    for (int k = 0; k < size; k++) {
//                        System.out.printf("%e ", errorVector[k]);
//                    }*/
//                    iteration++;
//                    boolean flag = true;
//                    for (int k = 0; k < size; k++) {
//                        if (errorVector[k] > accuracy) {
//                            flag = false;
//                            break;
//                        }
//                    }
//                    if (flag) {
                        /*System.out.println("Вектор решений:");
                        for (int k = 0; k < size; k++) {
                            System.out.printf("%.5f ", quanityVector[k]);
                        }
                        System.out.println();
                        System.out.println("Вектор погрешностей:");
                        for (int k = 0; k < size; k++) {
                            System.out.printf("%e ", errorVector[k]);
                        }
                        System.out.println();
                        System.out.print("Количество итераций: ");
                        System.out.println(iteration);*/
        return;
    }
}
