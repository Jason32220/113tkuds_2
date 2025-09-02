import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        // 如果輸入字串為空，直接返回 0
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 用於儲存字元及其索引的雜湊表
        // Key: 字元, Value: 該字元在字串中的索引
        Map<Character, Integer> charMap = new HashMap<>();
        
        // 左指針
        int left = 0;
        // 最長無重複子字串的長度
        int maxLength = 0;

        // 右指針從頭開始遍歷整個字串
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果當前字元已經在雜湊表中
            if (charMap.containsKey(currentChar)) {
                // 更新左指針的位置。
                // 新的左指針位置是「當前重複字元的上一個索引」+ 1。
                // 並且要確保左指針只向右移動，不能回退。
                left = Math.max(left, charMap.get(currentChar) + 1);
            }

            // 將當前字元和它的索引存入雜湊表
            // 如果字元已存在，會更新其索引
            charMap.put(currentChar, right);

            // 計算當前視窗的長度，並更新最大長度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
} 