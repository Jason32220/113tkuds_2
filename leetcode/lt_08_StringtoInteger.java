public class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        
        // 1. 忽略前導空格
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 如果字串只有空格，或已到末尾，則返回 0
        if (i == n) {
            return 0;
        }

        // 2. 判斷正負號
        int sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (s.charAt(i) == '+') {
            i++;
        }

        // 3. 轉換數字並處理溢位
        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // 溢位檢查 (使用 Integer.MAX_VALUE 的值 2147483647)
            // 檢查是否會超過 Integer.MAX_VALUE
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                // 如果是正數，返回最大值
                if (sign == 1) {
                    return Integer.MAX_VALUE;
                } else { // 如果是負數，返回最小值
                    return Integer.MIN_VALUE;
                }
            }
            
            // 累加數字
            result = result * 10 + digit;
            i++;
        }

        // 4. 返回最終結果
        return result * sign;
    }

}