
public class RecursionVsIteration {

    // 1. 二項式係數：遞迴
    public static long binomialCoeffRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialCoeffRecursive(n - 1, k - 1) + binomialCoeffRecursive(n - 1, k);
    }

    // 1. 二項式係數：迭代 (動態規劃)
    public static long binomialCoeffIterative(int n, int k) {
        long[][] C = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            int maxJ = Math.min(i, k);
            for (int j = 0; j <= maxJ; j++) {
                if (j == 0 || j == i) C[i][j] = 1;
                else C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        return C[n][k];
    }

    // 2. 陣列乘積：遞迴
    public static long productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    // 2. 陣列乘積：迭代
    public static long productIterative(int[] arr) {
        long product = 1;
        for (int val : arr) {
            product *= val;
        }
        return product;
    }

    // 3. 字串元音數量：遞迴
    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    // 3. 字串元音數量：迭代
    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    // 4. 括號配對檢查：遞迴
    public static boolean isBalancedRecursive(String s) {
        return checkBalanced(s, 0, 0);
    }

    private static boolean checkBalanced(String s, int index, int count) {
        if (count < 0) return false; // 右括號多於左括號
        if (index == s.length()) return count == 0;

        char c = s.charAt(index);
        if (c == '(') return checkBalanced(s, index + 1, count + 1);
        else if (c == ')') return checkBalanced(s, index + 1, count - 1);
        else return checkBalanced(s, index + 1, count);
    }

    // 4. 括號配對檢查：迭代
    public static boolean isBalancedIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    // 主程式測試與簡易效能比較
    public static void main(String[] args) {
        int n = 20, k = 10;
        int[] arr = {2, 3, 5, 7};
        String testStr = "Recursion vs Iteration Example";
        String parens1 = "((()))()";
        String parens2 = "(()))(";

        System.out.println("== 二項式係數 ==");
        long start = System.nanoTime();
        System.out.println("遞迴 C(" + n + "," + k + ") = " + binomialCoeffRecursive(n, k));
        System.out.println("遞迴耗時: " + (System.nanoTime() - start) + " ns");

        start = System.nanoTime();
        System.out.println("迭代 C(" + n + "," + k + ") = " + binomialCoeffIterative(n, k));
        System.out.println("迭代耗時: " + (System.nanoTime() - start) + " ns");

        System.out.println("\n== 陣列乘積 ==");
        System.out.println("遞迴乘積 = " + productRecursive(arr, 0));
        System.out.println("迭代乘積 = " + productIterative(arr));

        System.out.println("\n== 字串元音數量 ==");
        System.out.println("遞迴元音數 = " + countVowelsRecursive(testStr, 0));
        System.out.println("迭代元音數 = " + countVowelsIterative(testStr));

        System.out.println("\n== 括號配對檢查 ==");
        System.out.println(parens1 + " 遞迴結果: " + isBalancedRecursive(parens1));
        System.out.println(parens1 + " 迭代結果: " + isBalancedIterative(parens1));
        System.out.println(parens2 + " 遞迴結果: " + isBalancedRecursive(parens2));
        System.out.println(parens2 + " 迭代結果: " + isBalancedIterative(parens2));
    }
}
