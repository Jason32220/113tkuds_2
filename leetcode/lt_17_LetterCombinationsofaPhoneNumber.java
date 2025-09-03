import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// 核心解題思路：
// 使用回溯（Backtracking）演算法。
// 遞迴地遍歷每個數字，對於每個數字，將其對應的字母逐一加入
// 到當前組合中，然後對下一個數字進行遞迴。當所有數字都遍歷完畢時，
// 意味著找到了一個完整的組合，將其加入結果列表。

class Solution {

    // 儲存數字到字母的映射
    private Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    // 存放所有有效組合的結果列表
    private List<String> result = new ArrayList<>();

    /**
     * @param digits 輸入的數字字串
     * @return 所有可能的字母組合列表
     */
    public List<String> letterCombinations(String digits) {
        // 處理邊界情況：如果輸入字串為空，直接返回空列表
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }

        // 啟動回溯程序
        backtrack("", digits);
        
        return result;
    }

    /**
     * 遞迴回溯方法
     * @param currentCombination 當前已形成的字母組合
     * @param nextDigits 待處理的剩餘數字
     */
    private void backtrack(String currentCombination, String nextDigits) {
        // 基本情況：如果沒有剩餘數字了，表示找到一個完整的組合
        if (nextDigits.length() == 0) {
            result.add(currentCombination);
            return;
        }

        // 取出剩餘數字的第一個字元
        char digit = nextDigits.charAt(0);
        // 取得這個數字對應的字母字串
        String letters = phoneMap.get(digit);
        
        // 遍歷所有可能的字母
        for (int i = 0; i < letters.length(); i++) {
            // 選擇一個字母
            String letter = letters.substring(i, i + 1);
            
            // 進行遞迴，對剩餘的數字字串進行處理
            // 將當前字母加入組合中，並移除已處理的數字
            backtrack(currentCombination + letter, nextDigits.substring(1));
        }
    }
}
