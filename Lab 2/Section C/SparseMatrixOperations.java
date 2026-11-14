import java.util.*;

public class SparseMatrixOperations {

    static Scanner sc = new Scanner(System.in);

    // Sparse matrix entry
    static class Triple {
        int row;
        int col;
        int value;

        Triple(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    // Convert full matrix to sparse matrix
    static ArrayList<Triple> toSparse(int[][] matrix) {

        ArrayList<Triple> sparse = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] != 0) {
                    sparse.add(
                        new Triple(i, j, matrix[i][j])
                    );
                }
            }
        }

        return sparse;
    }

    // Convert sparse matrix to full matrix
    static int[][] toFull(
            ArrayList<Triple> sparse,
            int rows,
            int cols) {

        int[][] matrix = new int[rows][cols];

        for (Triple t : sparse) {
            matrix[t.row][t.col] = t.value;
        }

        return matrix;
    }

    // Add two sparse matrices directly
    static ArrayList<Triple> addSparse(
            ArrayList<Triple> a,
            ArrayList<Triple> b) {

        ArrayList<Triple> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < a.size() && j < b.size()) {

            Triple x = a.get(i);
            Triple y = b.get(j);

            if (x.row == y.row && x.col == y.col) {

                int sum = x.value + y.value;

                if (sum != 0) {
                    result.add(
                        new Triple(x.row, x.col, sum)
                    );
                }

                i++;
                j++;

            } else if (
                    x.row < y.row ||
                    (x.row == y.row && x.col < y.col)) {

                result.add(
                    new Triple(x.row, x.col, x.value)
                );

                i++;

            } else {

                result.add(
                    new Triple(y.row, y.col, y.value)
                );

                j++;
            }
        }

        // Remaining elements of A
        while (i < a.size()) {

            Triple x = a.get(i);

            result.add(
                new Triple(x.row, x.col, x.value)
            );

            i++;
        }

        // Remaining elements of B
        while (j < b.size()) {

            Triple y = b.get(j);

            result.add(
                new Triple(y.row, y.col, y.value)
            );

            j++;
        }

        return result;
    }

    // Display sparse matrix
    static void displaySparse(
            ArrayList<Triple> sparse) {

        System.out.println(
            "Row\tColumn\tValue"
        );

        for (Triple t : sparse) {

            System.out.println(
                t.row + "\t" +
                t.col + "\t" +
                t.value
            );
        }
    }

    // Display full matrix
    static void displayFull(int[][] matrix) {

        for (int[] row : matrix) {

            for (int value : row) {
                System.out.print(value + " ");
            }

            System.out.println();
        }
    }

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

    public static void main(String[] args) {

        System.out.println("Enter Matrix A");

        int[][] matrixA = readMatrix();

        if (matrixA == null) {
            return;
        }

        System.out.println("\nEnter Matrix B");

        int[][] matrixB = readMatrix();

        if (matrixB == null) {
            return;
        }

        // Dimension validation
        if (matrixA.length != matrixB.length ||
            matrixA[0].length != matrixB[0].length) {

            System.out.println(
                "Cannot add matrices: dimensions do not match."
            );

            return;
        }

        ArrayList<Triple> sparseA =
                toSparse(matrixA);

        ArrayList<Triple> sparseB =
                toSparse(matrixB);

        System.out.println("\nSparse Matrix A:");
        displaySparse(sparseA);

        System.out.println("\nSparse Matrix B:");
        displaySparse(sparseB);

        // Sparse addition
        ArrayList<Triple> result =
                addSparse(sparseA, sparseB);

        System.out.println("\nSparse Result:");
        displaySparse(result);

        // Reconstruct result
        int[][] fullResult =
                toFull(
                    result,
                    matrixA.length,
                    matrixA[0].length
                );

        System.out.println("\nReconstructed Full Result:");
        displayFull(fullResult);

        // Reconstruct A and B for comparison
        System.out.println("\nReconstructed Matrix A:");
        displayFull(
            toFull(
                sparseA,
                matrixA.length,
                matrixA[0].length
            )
        );

        System.out.println("\nReconstructed Matrix B:");
        displayFull(
            toFull(
                sparseB,
                matrixB.length,
                matrixB[0].length
            )
        );
    }
}