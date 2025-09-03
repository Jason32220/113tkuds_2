import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 解決有效的括號問題。
 * 核心思路：使用一個堆疊（Stack）來匹配括號。
 */
class Solution {
    
    /**
     * 判斷輸入字串是否是有效的括號組合。
     * @param s 輸入的字串，僅包含括號 '()[]{}'
     * @return 如果字串有效則回傳 true，否則回傳 false
     */
    public boolean isValid(String s) {
        // 如果字串長度是奇數，則不可能有效，直接返回 false
        if (s.length() % 2 != 0) {
            return false;
        }

        // 使用一個堆疊來追蹤開括號
        Stack<Character> stack = new Stack<>();
        
        // 建立一個對照表，方便查找匹配的括號
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');

        // 遍歷字串中的每個字元
        for (char c : s.toCharArray()) {
            // 如果是右括號
            if (bracketMap.containsKey(c)) {
                // 從堆疊中取出最上方的元素，如果堆疊為空，則設為特殊值 '#'
                char topElement = stack.isEmpty() ? '#' : stack.pop();

                // 檢查堆疊頂部的元素是否與當前右括號的匹配
                if (topElement != bracketMap.get(c)) {
                    return false;
                }
            } else {
                // 如果是左括號，將其推入堆疊
                stack.push(c);
            }
        }
        
        // 如果所有字元都已處理完畢，且堆疊為空，則表示所有括號都已成功匹配
        return stack.isEmpty();
    }
}
