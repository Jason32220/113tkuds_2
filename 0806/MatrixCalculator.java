
public class MatrixCalculator {

    // 矩陣加法：兩矩陣維度需相同
    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }

        return result;
    }

    // 矩陣乘法：A的列數需等於B的行數
    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    // 矩陣轉置：將行列對調
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] transposed = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }

    // 尋找矩陣最大值與最小值
    public static int[] findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
                if (val < min) {
                    min = val;
                }
            }
        }

        return new int[]{max, min};
    }

    // 印出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    // 主程式測試
    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] B = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] C = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        // 矩陣加法
        System.out.println("矩陣 A + B：");
        int[][] sum = add(A, B);
        printMatrix(sum);

        // 矩陣乘法 A * C
        System.out.println("\n矩陣 A * C：");
        int[][] product = multiply(A, C);
        printMatrix(product);

        // 矩陣轉置 A^T
        System.out.println("\n矩陣 A 的轉置：");
        int[][] transposed = transpose(A);
        printMatrix(transposed);

        // 找最大最小值
        System.out.println("\n矩陣 B 的最大與最小值：");
        int[] maxMin = findMaxMin(B);
        System.out.println("最大值：" + maxMin[0]);
        System.out.println("最小值：" + maxMin[1]);
    }
}
