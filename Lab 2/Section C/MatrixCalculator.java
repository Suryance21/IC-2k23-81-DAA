import java.util.*;

public class MatrixCalculator {

    static Scanner sc = new Scanner(System.in);

    // Read matrix
    static int[][] readMatrix() {

        System.out.print("Enter rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter columns: ");
        int cols = sc.nextInt();

        if (rows <= 0 || cols <= 0) {
            System.out.println("Invalid dimensions.");
            return null;
        }

        int[][] matrix = new int[rows][cols];

        System.out.println("Enter elements:");

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        return matrix;
    }

    // Display matrix
    static void display(int[][] matrix) {

        for (int[] row : matrix) {

            for (int value : row) {
                System.out.print(value + "\t");
            }

            System.out.println();
        }
    }

    // Matrix addition
    static int[][] add(
            int[][] a,
            int[][] b) {

        if (a.length != b.length ||
            a[0].length != b[0].length) {

            System.out.println(
                "Addition not possible: dimensions must be same."
            );

            return null;
        }

        int rows = a.length;
        int cols = a[0].length;

        int[][] result =
                new int[rows][cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                result[i][j] =
                        a[i][j] + b[i][j];
            }
        }

        return result;
    }

    // Matrix multiplication
    static int[][] multiply(
            int[][] a,
            int[][] b) {

        if (a[0].length != b.length) {

            System.out.println(
                "Multiplication not possible."
            );

            System.out.println(
                "Columns of first matrix must equal " +
                "rows of second matrix."
            );

            return null;
        }

        int rows = a.length;
        int cols = b[0].length;
        int common = a[0].length;

        int[][] result =
                new int[rows][cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                for (int k = 0; k < common; k++) {

                    result[i][j] +=
                            a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    // Transpose
    static int[][] transpose(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result =
                new int[cols][rows];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                result[j][i] =
                        matrix[i][j];
            }
        }

        return result;
    }

    // Determinant
    static int determinant(int[][] matrix) {

        int n = matrix.length;

        if (n != matrix[0].length) {

            System.out.println(
                "Determinant requires a square matrix."
            );

            return Integer.MIN_VALUE;
        }

        // 1 x 1
        if (n == 1) {
            return matrix[0][0];
        }

        // 2 x 2
        if (n == 2) {

            return matrix[0][0] * matrix[1][1]
                    - matrix[0][1] * matrix[1][0];
        }

        // 3 x 3
        if (n == 3) {

            return
                matrix[0][0] *
                (matrix[1][1] * matrix[2][2]
                - matrix[1][2] * matrix[2][1])

                -

                matrix[0][1] *
                (matrix[1][0] * matrix[2][2]
                - matrix[1][2] * matrix[2][0])

                +

                matrix[0][2] *
                (matrix[1][0] * matrix[2][1]
                - matrix[1][1] * matrix[2][0]);
        }

        System.out.println(
            "Only 1x1, 2x2 and 3x3 determinants are supported."
        );

        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== MATRIX CALCULATOR =====");
            System.out.println("1. Add two matrices");
            System.out.println("2. Multiply two matrices");
            System.out.println("3. Transpose a matrix");
            System.out.println("4. Find determinant");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\nEnter Matrix A:");
                    int[][] a = readMatrix();

                    if (a == null) {
                        break;
                    }

                    System.out.println("\nEnter Matrix B:");
                    int[][] b = readMatrix();

                    if (b == null) {
                        break;
                    }

                    int[][] sum = add(a, b);

                    if (sum != null) {

                        System.out.println(
                            "\nResult of Addition:"
                        );

                        display(sum);
                    }

                    break;

                case 2:

                    System.out.println("\nEnter Matrix A:");
                    a = readMatrix();

                    if (a == null) {
                        break;
                    }

                    System.out.println("\nEnter Matrix B:");
                    b = readMatrix();

                    if (b == null) {
                        break;
                    }

                    int[][] product =
                            multiply(a, b);

                    if (product != null) {

                        System.out.println(
                            "\nResult of Multiplication:"
                        );

                        display(product);
                    }

                    break;

                case 3:

                    System.out.println("\nEnter Matrix:");
                    a = readMatrix();

                    if (a == null) {
                        break;
                    }

                    int[][] transposed =
                            transpose(a);

                    System.out.println(
                        "\nTranspose:"
                    );

                    display(transposed);

                    break;

                case 4:

                    System.out.println(
                        "\nEnter Matrix:"
                    );

                    a = readMatrix();

                    if (a == null) {
                        break;
                    }

                    if (a.length != a[0].length) {

                        System.out.println(
                            "Matrix must be square."
                        );

                        break;
                    }

                    if (a.length > 3) {

                        System.out.println(
                            "Only 2x2 and 3x3 " +
                            "determinants are supported."
                        );

                        break;
                    }

                    int det = determinant(a);

                    System.out.println(
                        "Determinant = " + det
                    );

                    break;

                case 5:

                    System.out.println(
                        "Program ended."
                    );

                    return;

                default:

                    System.out.println(
                        "Invalid choice!"
                    );
            }
        }
    }
}