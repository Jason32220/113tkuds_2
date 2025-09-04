class Solution {
    /**
     * 計算最長有效（格式正確）括號子字串的長度。
     *
     * @param s 包含 '(' 和 ')' 的字串
     * @return 最長有效括號子字串的長度
     */
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        // 使用一個堆疊來追蹤括號的索引
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        // 推入 -1 作為初始的標記
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 如果是開括號，將其索引推入堆疊
                stack.push(i);
            } else { // c == ')'
                // 如果是閉括號，彈出堆疊頂部的元素
                stack.pop();
                
                if (stack.empty()) {
                    // 如果堆疊為空，表示當前的 ')' 沒有匹配的 '('
                    // 將當前索引作為新的起始點標記
                    stack.push(i);
                } else {
                    // 堆疊不為空，表示找到了有效的匹配
                    // 計算當前有效子字串的長度：當前索引 - 堆疊頂部索引
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}
