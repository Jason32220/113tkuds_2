/**
 * 解決不使用乘法、除法和取模運算來實現整數除法。
 * 核心思路：利用位元運算，將除法轉化為一系列的減法和位移。
 */
class Solution {
    /**
     * 實現整數除法並截斷結果。
     *
     * @param dividend 被除數
     * @param divisor 除數
     * @return 兩者相除後的商
     */
    public int divide(int dividend, int divisor) {
        // 處理特殊邊界情況：
        // 1. 除數為 0 的情況由題目約束排除
        // 2. 當被除數為最小整數且除數為 -1 時，結果會溢出 32 位整數範圍
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 確定結果的正負號
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // 將被除數和除數轉換為 long 的絕對值，避免溢出問題
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        long quotient = 0;
        
        // 核心演算法：將被除數不斷減去除數的 2 的冪次
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            long multiple = 1;

            // 尋找最大的 2 的冪次，使得 (divisor << 2^n) 仍小於或等於 dividend
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            // 從被除數中減去這個最大的 2 的冪次
            absDividend -= tempDivisor;
            // 將對應的商加到結果中
            quotient += multiple;
        }

        // 根據正負號返回最終結果
        return isNegative ? (int) -quotient : (int) quotient;
    }
}
