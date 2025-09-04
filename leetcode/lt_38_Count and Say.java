class Solution {
    /**
     * 計算並返回 "數數並說" 數列的第 n 個元素。
     *
     * @param n 一個正整數，表示要返回的序列元素索引。
     * @return 第 n 個序列元素。
     */
    public String countAndSay(int n) {
        // 基本情況：當 n = 1 時，序列為 "1"
        if (n == 1) {
            return "1";
        }

        // 從第二個元素開始，使用迭代方式生成序列
        String result = "1";
        for (int i = 2; i <= n; i++) {
            // 使用 StringBuilder 效率更高
            StringBuilder sb = new StringBuilder();
            
            // 遍歷上一個序列 (result)
            int count = 1;
            for (int j = 0; j < result.length(); j++) {
                // 如果當前字元與下一個字元相同，則計數器加一
                if (j + 1 < result.length() && result.charAt(j) == result.charAt(j + 1)) {
                    count++;
                } else {
                    // 如果字元改變或已到達字串末尾，將計數和字元追加到 StringBuilder
                    sb.append(count);
                    sb.append(result.charAt(j));
                    // 重置計數器為 1
                    count = 1;
                }
            }
            // 將新生成的字串賦予 result，用於下一次迭代
            result = sb.toString();
        }

        // 返回最終結果
        return result;
    }
}
