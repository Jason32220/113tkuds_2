public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        // dp[i][j] 代表 s 的前 i 個字元是否匹配 p 的前 j 個字元
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // 基本情況：空字串匹配空模式
        dp[0][0] = true;

        // 處理 p 的第一行，例如 "a*b*c*" 匹配空字串
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                // * 匹配零個前面字元，所以 dp[0][j] 的結果取決於 dp[0][j-2]
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填寫 DP 表格
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                // 如果 p 的當前字元不是 '*'
                if (pChar != '*') {
                    // 如果 s 和 p 的當前字元匹配，則結果取決於前一個子問題
                    if (sChar == pChar || pChar == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else { // 如果 p 的當前字元是 '*'
                    // '*' 匹配零個前面的字元
                    // dp[i][j] 的結果取決於 dp[i][j-2]
                    dp[i][j] = dp[i][j - 2];
                    
                    // 如果 '*' 匹配一個或多個前面的字元
                    // 則 s 的當前字元需要匹配 p 的前一個字元
                    char pPrevChar = p.charAt(j - 2);
                    if (sChar == pPrevChar || pPrevChar == '.') {
                        // 如果匹配，則結果取決於 dp[i-1][j]
                        // 這裡 dp[i-1][j] 意味著 '*' 匹配多個字元，我們移除了 s 的一個字元，但模式 p 保持不變
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}