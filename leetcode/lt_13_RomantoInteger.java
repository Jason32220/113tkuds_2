// 題目：羅馬數字轉整數

// 羅馬數字由七個不同的符號組成：I、V、X、L、C、D 和 M。
// 寫一個函式，將一個羅馬數字字串轉換成對應的整數。

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        // 建立羅馬數字與整數值的對照表
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0;
        int n = s.length();

        // 從左到右遍歷字串，只處理到倒數第二個字元
        for (int i = 0; i < n - 1; i++) {
            // 獲取當前符號和下一個符號的值
            int currentValue = romanMap.get(s.charAt(i));
            int nextValue = romanMap.get(s.charAt(i + 1));

            // 如果當前值小於下一個值，則為減法情況
            if (currentValue < nextValue) {
                total -= currentValue;
            } else {
                // 否則為加法情況
                total += currentValue;
            }
        }

        // 加上最後一個字元的值，因為它不可能形成減法
        total += romanMap.get(s.charAt(n - 1));

        return total;
    }
}
