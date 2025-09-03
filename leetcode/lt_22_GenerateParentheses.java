import java.util.ArrayList;
import java.util.List;

/**
 * 解決生成有效括號組合問題
 * 核心思路：使用回溯法（backtracking）
 */
class Solution {
    
    /**
     * 生成所有 n 對括號的有效組合。
     * @param n 括號的對數
     * @return 包含所有有效組合的列表
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    /**
     * 回溯函式，用於遞迴生成括號組合
     * @param result 儲存最終結果的列表
     * @param currentString 當前正在建構的字串
     * @param openCount 已使用的左括號數
     * @param closeCount 已使用的右括號數
     * @param max 總共需要使用的括號對數
     */
    private void backtrack(List<String> result, String currentString, int openCount, int closeCount, int max) {
        // 基本情況：當字串長度達到 2 * max 時，表示一個完整的有效組合已生成
        if (currentString.length() == max * 2) {
            result.add(currentString);
            return;
        }

        // 遞迴規則：
        // 1. 只要左括號數量小於 n，就可以添加左括號
        if (openCount < max) {
            backtrack(result, currentString + "(", openCount + 1, closeCount, max);
        }

        // 2. 只要右括號數量小於左括號數量，就可以添加右括號
        //    這確保了括號的閉合順序是正確的
        if (closeCount < openCount) {
            // 修正：當添加右括號時，應增加 closeCount，而不是 openCount
            backtrack(result, currentString + ")", openCount, closeCount + 1, max);
        }
    }
}
