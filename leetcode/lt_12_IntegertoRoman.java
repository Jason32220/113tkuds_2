// 題目：整數轉羅馬數字

// 核心解題思路：
// 使用貪婪演算法。將羅馬數字的所有可能值（包含特殊減法組合）
// 從大到小排列，然後依序將最大的可能值從輸入數字中減去，並將對應的符號
// 加入結果字串，直到數字變為 0。

class Solution {
    public String intToRoman(int num) {
        // 兩個平行的陣列，一個儲存數值，一個儲存對應的羅馬符號。
        // 這裡的順序至關重要，必須由大到小排序，且包含特殊的減法形式。
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder();
        
        // 遍歷所有值，從最大開始
        for (int i = 0; i < values.length; i++) {
            // 使用 while 迴圈，只要當前數字大於或等於當前值，就進行「貪婪」減法
            while (num >= values[i]) {
                // 將對應的羅馬符號加入結果字串
                result.append(symbols[i]);
                // 從數字中減去這個值
                num -= values[i];
            }
        }
        
        return result.toString();
    }
}
