/**
 * 解決在字串 s 中尋找 words 中所有單詞的排列組合在字串中出現的起始索引。
 * 核心思路：使用滑動窗口與兩個雜湊表來進行高效匹配。
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    /**
     * 尋找所有 concatenated substrings 的起始索引。
     *
     * @param s 輸入字串
     * @param words 單詞陣列
     * @return 所有 concatenated substrings 的起始索引列表
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;

        // 建立一個雜湊表來儲存 words 中每個單詞的頻率
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // 遍歷所有可能的起始位置
        for (int i = 0; i <= s.length() - totalLen; i++) {
            // 建立一個新的雜湊表來追蹤當前窗口中的單詞頻率
            Map<String, Integer> currentCount = new HashMap<>();
            boolean isValid = true;

            // 遍歷當前窗口內的單詞
            for (int j = 0; j < words.length; j++) {
                int startIndex = i + j * wordLen;
                String currentWord = s.substring(startIndex, startIndex + wordLen);

                // 檢查單詞是否存在於 words 陣列中
                if (!wordCount.containsKey(currentWord)) {
                    isValid = false;
                    break;
                }
                
                // 更新當前窗口中的單詞頻率
                currentCount.put(currentWord, currentCount.getOrDefault(currentWord, 0) + 1);

                // 檢查單詞頻率是否超出預期
                if (currentCount.get(currentWord) > wordCount.get(currentWord)) {
                    isValid = false;
                    break;
                }
            }

            // 如果所有單詞都成功匹配，則將起始索引加入結果列表
            if (isValid) {
                result.add(i);
            }
        }
        return result;
    }
}
