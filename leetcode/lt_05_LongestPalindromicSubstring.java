public class Solution {

    // 用於追蹤找到的最長回文子字串
    private String longestPalindrome = "";

    public String longestPalindrome(String s) {
        // 如果字串長度小於 2，它本身就是最長回文
        if (s == null || s.length() < 2) {
            return s;
        }

        // 遍歷所有可能的中心點
        for (int i = 0; i < s.length(); i++) {
            // 1. 以單個字元為中心向兩邊擴散 (例如: "aba")
            expandFromCenter(s, i, i);
            
            // 2. 以兩個相同字元為中心向兩邊擴散 (例如: "abba")
            expandFromCenter(s, i, i + 1);
        }

        return longestPalindrome;
    }

    /**
     * 輔助函數：從給定的中心向兩邊擴散以尋找回文
     * @param s 輸入字串
     * @param left 左指針
     * @param right 右指針
     */
    private void expandFromCenter(String s, int left, int right) {
        // 檢查邊界並比較左右兩邊的字元
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // 計算當前回文的長度
            int currentLength = right - left + 1;
            
            // 如果當前回文比已知的最長回文更長，則更新最長回文
            if (currentLength > longestPalindrome.length()) {
                longestPalindrome = s.substring(left, right + 1);
            }
            // 向外擴散
            left--;
            right++;
        }
    }
}