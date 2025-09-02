class Solution {
    public boolean isPalindrome(int x) {
        // 1. 處理邊界情況：
        // 負數、或除了 0 之外以 0 結尾的數，都不是回文數
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        
        // 2. 反轉數字的後半部分
        // 迴圈條件是當原始數字大於反轉後的後半部分
        while (x > reversedHalf) {
            // 從 x 取出最後一位，並加到 reversedHalf 中
            reversedHalf = reversedHalf * 10 + x % 10;
            // 去掉 x 的最後一位
            x /= 10;
        }

        // 3. 比較與判斷
        // 當位數為偶數時，x 和 reversedHalf 相等
        // 當位數為奇數時，reversedHalf 比 x 多一位（中間位數），所以需要除以 10
        return x == reversedHalf || x == reversedHalf / 10;
    }

    // 主方法，用於測試
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 範例 1
        int x1 = 121;
        System.out.println("輸入: " + x1 + " -> 輸出: " + solution.isPalindrome(x1)); // 輸出: true

        // 範例 2
        int x2 = -121;
        System.out.println("輸入: " + x2 + " -> 輸出: " + solution.isPalindrome(x2)); // 輸出: false

        // 範例 3
        int x3 = 10;
        System.out.println("輸入: " + x3 + " -> 輸出: " + solution.isPalindrome(x3)); // 輸出: false
        
        // 額外測試：偶數位數的回文數
        int x4 = 1221;
        System.out.println("輸入: " + x4 + " -> 輸出: " + solution.isPalindrome(x4)); // 輸出: true
    }
}