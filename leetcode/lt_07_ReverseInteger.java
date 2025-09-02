public class Solution {
    public int reverse(int x) {
        // reversed 用於構建反轉後的數字
        int reversed = 0;

        // 當原始數字不為 0 時，持續進行
        while (x != 0) {
            // 1. 取出最後一位數字
            int digit = x % 10;

            // 2. 溢位檢查
            // 正數溢位檢查
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            // 負數溢位檢查
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            // 3. 將當前 reversed 數字向左移一位（*10），並加上新的 digit
            reversed = reversed * 10 + digit;
            
            // 4. 移除原始數字的最後一位
            x /= 10;
        }

        return reversed;
    }
}