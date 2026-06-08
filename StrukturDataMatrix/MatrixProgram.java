import java.util.*;

public class MatrixProgram {

    static Scanner input = new Scanner(System.in);
    static int[][] matrix;
    static int rows, cols;

    // ==========================
    // INPUT MATRIX
    // ==========================
    static void inputMatrix() {

        while (true) {
            try {
                System.out.print("Masukkan jumlah baris : ");
                rows = Integer.parseInt(input.nextLine());

                System.out.print("Masukkan jumlah kolom : ");
                cols = Integer.parseInt(input.nextLine());

                break;
            } catch (Exception e) {
                System.out.println("Input harus angka!");
            }
        }

        matrix = new int[rows][cols];

        System.out.println("\nMasukkan elemen matrix:");

        for (int i = 0; i < rows; i++) {

            while (true) {

                try {

                    System.out.print("Baris " + (i + 1) + ": ");
                    String[] data = input.nextLine().split(" ");

                    if (data.length != cols) {
                        System.out.println("Harus " + cols + " angka!");
                        continue;
                    }

                    for (int j = 0; j < cols; j++) {
                        matrix[i][j] = Integer.parseInt(data[j]);
                    }

                    break;

                } catch (Exception e) {
                    System.out.println("Masukkan angka saja!");
                }
            }
        }
    }

    // ==========================
    // TAMPILKAN MATRIX
    // ==========================
    static void printMatrix() {

        System.out.println("\nMatrix:");

        for (int[] row : matrix) {

            for (int value : row) {
                System.out.print(value + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    // ==========================
    // 1-a SORT ROW WISE
    // ==========================
    static void sortRowWise() {

        for (int i = 0; i < rows; i++) {
            Arrays.sort(matrix[i]);
        }

        System.out.println("\nMatrix setelah sort row-wise:");
        printMatrix();
    }

    // ==========================
    // 1-b SORT COLUMN WISE
    // ==========================
    static void sortColumnWise() {

        for (int c = 0; c < cols; c++) {

            int[] temp = new int[rows];

            for (int r = 0; r < rows; r++) {
                temp[r] = matrix[r][c];
            }

            Arrays.sort(temp);

            for (int r = 0; r < rows; r++) {
                matrix[r][c] = temp[r];
            }
        }

        System.out.println("\nMatrix setelah sort column-wise:");
        printMatrix();
    }

    // ==========================
    // 2-a ROTATE CLOCKWISE BY 1
    // ==========================
    static void rotateClockwiseOne() {

        if (rows < 2 || cols < 2) return;

        ArrayList<Integer> elements = new ArrayList<>();

        for (int j = 0; j < cols; j++)
            elements.add(matrix[0][j]);

        for (int i = 1; i < rows; i++)
            elements.add(matrix[i][cols - 1]);

        for (int j = cols - 2; j >= 0; j--)
            elements.add(matrix[rows - 1][j]);

        for (int i = rows - 2; i > 0; i--)
            elements.add(matrix[i][0]);

        elements.add(0, elements.remove(elements.size() - 1));

        int k = 0;

        for (int j = 0; j < cols; j++)
            matrix[0][j] = elements.get(k++);

        for (int i = 1; i < rows; i++)
            matrix[i][cols - 1] = elements.get(k++);

        for (int j = cols - 2; j >= 0; j--)
            matrix[rows - 1][j] = elements.get(k++);

        for (int i = rows - 2; i > 0; i--)
            matrix[i][0] = elements.get(k++);

        System.out.println("\nMatrix setelah rotate clockwise 1 langkah:");
        printMatrix();
    }

    // ==========================
    // 2-b ROTATE COUNTER CLOCKWISE BY 1
    // ==========================
    static void rotateCounterClockwiseOne() {

        if (rows < 2 || cols < 2) return;

        ArrayList<Integer> elements = new ArrayList<>();

        for (int j = 0; j < cols; j++)
            elements.add(matrix[0][j]);

        for (int i = 1; i < rows; i++)
            elements.add(matrix[i][cols - 1]);

        for (int j = cols - 2; j >= 0; j--)
            elements.add(matrix[rows - 1][j]);

        for (int i = rows - 2; i > 0; i--)
            elements.add(matrix[i][0]);

        elements.add(elements.remove(0));

        int k = 0;

        for (int j = 0; j < cols; j++)
            matrix[0][j] = elements.get(k++);

        for (int i = 1; i < rows; i++)
            matrix[i][cols - 1] = elements.get(k++);

        for (int j = cols - 2; j >= 0; j--)
            matrix[rows - 1][j] = elements.get(k++);

        for (int i = rows - 2; i > 0; i--)
            matrix[i][0] = elements.get(k++);

        System.out.println("\nMatrix setelah rotate counter-clockwise 1 langkah:");
        printMatrix();
    }

    // ==========================
    // 2-c ROTATE 90
    // ==========================
    static void rotate90() {

        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = matrix[i][j];
            }
        }

        matrix = result;

        int temp = rows;
        rows = cols;
        cols = temp;

        System.out.println("\nMatrix setelah rotate 90 derajat:");
        printMatrix();
    }

    // ==========================
    // 2-d ROTATE 180
    // ==========================
    static void rotate180() {

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[rows - 1 - i][cols - 1 - j] = matrix[i][j];
            }
        }

        matrix = result;

        System.out.println("\nMatrix setelah rotate 180 derajat:");
        printMatrix();
    }

    // ==========================
    // 3-a ROW TRAVERSAL
    // ==========================
    static void rowTraversal() {

        System.out.println("\nRow-wise Traversal:");

        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
        }

        System.out.println("\n");
    }

    // ==========================
    // 3-b COLUMN TRAVERSAL
    // ==========================
    static void columnTraversal() {

        System.out.println("\nColumn-wise Traversal:");

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                System.out.print(matrix[r][c] + " ");
            }
        }

        System.out.println("\n");
    }

    // ==========================
    // 4 SPIRAL
    // ==========================
    static void spiralPrint() {

        System.out.println("\nSpiral Traversal:");

        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (top <= bottom && left <= right) {

            for (int i = left; i <= right; i++)
                System.out.print(matrix[top][i] + " ");
            top++;

            for (int i = top; i <= bottom; i++)
                System.out.print(matrix[i][right] + " ");
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    System.out.print(matrix[bottom][i] + " ");
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    System.out.print(matrix[i][left] + " ");
                left++;
            }
        }

        System.out.println("\n");
    }

    // ==========================
    // 5 TRANSPOSE
    // ==========================
    static void transpose() {

        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        matrix = result;

        int temp = rows;
        rows = cols;
        cols = temp;

        System.out.println("\nTranspose Matrix:");
        printMatrix();
    }

    // ==========================
    // MAIN
    // ==========================
    public static void main(String[] args) {

        inputMatrix();

        while (true) {

            System.out.println("============== MENU ==============");
            System.out.println("0. Tampilkan Matrix");
            System.out.println("1-a. Sort the matrix row-wise");
            System.out.println("1-b. Sort the matrix column-wise");
            System.out.println("2-a. Rotate Matrix Clockwise by 1");
            System.out.println("2-b. Rotate Matrix Counter-Clockwise by 1");
            System.out.println("2-c. Rotate a matrix by 90");
            System.out.println("2-d. Rotate a matrix by 180");
            System.out.println("3-a. Row-wise traversal of matrix");
            System.out.println("3-b. Column-wise traversal of matrix");
            System.out.println("4. Print matrix in spiral form");
            System.out.println("5. Transpose matrix");
            System.out.println("6. Input Matrix Baru");
            System.out.println("7. Quit");
            System.out.println("==================================");

            String choice = input.nextLine().toLowerCase();

            switch (choice) {

                case "0":
                    printMatrix();
                    break;

                case "1-a":
                    sortRowWise();
                    break;

                case "1-b":
                    sortColumnWise();
                    break;

                case "2-a":
                    rotateClockwiseOne();
                    break;

                case "2-b":
                    rotateCounterClockwiseOne();
                    break;

                case "2-c":
                    rotate90();
                    break;

                case "2-d":
                    rotate180();
                    break;

                case "3-a":
                    rowTraversal();
                    break;

                case "3-b":
                    columnTraversal();
                    break;

                case "4":
                    spiralPrint();
                    break;

                case "5":
                    transpose();
                    break;

                case "6":
                    inputMatrix();
                    break;

                case "7":
                    System.out.println("Program selesai.");
                    return;

                default:
                    System.out.println("Menu tidak valid!");
            }
        }
    }
}