
import java.util.Scanner;

public class RecursiveMathCalculator {

    // 1. 計算組合數 C(n, k) = C(n-1,k-1) + C(n-1,k)
    public static int combination(int n, int k) {
        // 邊界條件：C(n, 0) = C(n, n) = 1
        if (k == 0 || k == n) {
            return 1;
        }
        // 遞迴公式
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 2. 計算卡塔蘭數 Catalan(n)
    public static int catalan(int n) {
        if (n == 0) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 3. 漢諾塔移動步數 hanoi(n) = 2 * hanoi(n-1) + 1
    public static int hanoiMoves(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 4. 判斷是否為回文數（數字左右對稱）
    public static boolean isPalindrome(int number) {
        return number == reverse(number);
    }

    // 輔助：將數字反轉
    public static int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + num % 10;
            num = num / 10;
        }
        return rev;
    }

    // 主程式：提供選單給使用者
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 遞迴數學計算器 ==");
        System.out.println("1. 組合數 C(n, k)");
        System.out.println("2. 卡塔蘭數 Catalan(n)");
        System.out.println("3. 漢諾塔最少步數 Hanoi(n)");
        System.out.println("4. 判斷是否為回文數");
        System.out.print("請輸入功能選項 (1~4): ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("請輸入 n: ");
                int n1 = scanner.nextInt();
                System.out.print("請輸入 k: ");
                int k = scanner.nextInt();
                System.out.println("C(" + n1 + ", " + k + ") = " + combination(n1, k));
                break;

            case 2:
                System.out.print("請輸入 n: ");
                int n2 = scanner.nextInt();
                System.out.println("Catalan(" + n2 + ") = " + catalan(n2));
                break;

            case 3:
                System.out.print("請輸入圓盤數 n: ");
                int n3 = scanner.nextInt();
                System.out.println("Hanoi(" + n3 + ") 最少移動次數 = " + hanoiMoves(n3));
                break;

            case 4:
                System.out.print("請輸入數字：");
                int num = scanner.nextInt();
                if (isPalindrome(num)) {
                    System.out.println(num + " 是回文數。");
                } else {
                    System.out.println(num + " 不是回文數。");
                }
                break;

            default:
                System.out.println("請輸入有效的選項！");
        }

        scanner.close();
    }
}
