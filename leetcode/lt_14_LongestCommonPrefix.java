// 導入需要的類別
import java.util.Arrays;

/**
 * 解決最長公共前綴問題的類別
 */
public class Solution {
    
    /**
     * 尋找字串陣列中的最長公共前綴。
     * @param strs 輸入的字串陣列
     * @return 最長的公共前綴字串
     */
    public String longestCommonPrefix(String[] strs) {
        // 檢查邊界情況：如果陣列為空或為 null，則沒有公共前綴
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一個字串作為初始的公共前綴
        String prefix = strs[0];

        // 遍歷陣列中其餘的字串
        for (int i = 1; i < strs.length; i++) {
            // 檢查當前的公共前綴是否是 strs[i] 的前綴
            // indexOf() 方法會返回子字串在字串中的索引，如果不在則返回 -1
            // 如果 prefix 不是 strs[i] 的前綴 (即 indexOf() 不為 0)
            while (strs[i].indexOf(prefix) != 0) {
                // 縮短公共前綴，將其最後一個字元移除
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // 如果在縮短過程中，prefix 變成了空字串，表示沒有共同前綴，直接返回
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        // 迴圈結束後，剩下的 prefix 就是最長公共前綴
        return prefix;
    }
}
