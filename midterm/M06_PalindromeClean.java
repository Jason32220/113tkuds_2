import java.util.*;

public class M06_PalindromeClean {

    /*
     * 回文檢測（忽略非字母與大小寫）
     *
     * Time Complexity: O(n)
     * 說明：
     * - 需走訪整個字串長度 n，清洗並比較。
     * - 每次比較與移動指標為 O(1)，總計 O(n)。
     */
    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 跳過非字母
            while (left < right && !Character.isLetter(s.charAt(left))) left++;
            while (left < right && !Character.isLetter(s.charAt(right))) right--;

            char c1 = Character.toLowerCase(s.charAt(left));
            char c2 = Character.toLowerCase(s.charAt(right));
            if (c1 != c2) return false;

            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        if (isPalindrome(line)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

/*
 * Overall Time Complexity: O(n)
 * 說明：需遍歷一次字串長度 n → O(n)。
 * 空間複雜度 O(1)，僅使用指標與變數。
 */
