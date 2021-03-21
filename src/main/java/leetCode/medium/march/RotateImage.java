package leetCode.medium.march;

/**
 * https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {
    public static void main(String[] args) {
        //int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = new int[][] {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        System.out.println("Original");
        printMatrix(matrix);
        rotateClockwise(matrix);
        System.out.println("Rotated Clockwise");
        printMatrix(matrix);
        matrix = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotateAntiClockwise(matrix);
        System.out.println("Rotated Anti Clockwise");
        printMatrix(matrix);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public static void rotateClockwise(int[][] matrix) {
        transpose(matrix);
        reflectVertically(matrix);
    }

    public static void rotateAntiClockwise(int[][] matrix) {
        reflectVertically(matrix);
        transpose(matrix);
    }

    private static void transpose(int[][] matrix) {
        int n = matrix.length;
        int temp;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void reflectVertically(int[][] matrix) {
        int n = matrix.length;
        int temp;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n / 2; ++j) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
