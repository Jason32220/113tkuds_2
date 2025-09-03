/**
 * 解決在字串 haystack 中尋找字串 needle 第一次出現的索引。
 * 核心思路：使用雙指針法，在不使用內建函式的情況下實現字串匹配。
 */
class Solution {
    /**
     * 尋找字串 needle 在字串 haystack 中第一次出現的索引。
     *
     * @param haystack 來源字串
     * @param needle 目標字串
     * @return 第一次出現的索引，如果未找到則返回 -1
     */
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // 檢查邊界情況：如果 haystack 的長度小於 needle，不可能包含 needle
        if (n < m) {
            return -1;
        }

        // 使用一個外部迴圈遍歷 haystack 的所有可能起始位置
        // 迴圈的終點是 n - m，以確保子字串不會超出 haystack 的範圍
        for (int i = 0; i <= n - m; i++) {
            int j;
            // 使用一個內部迴圈來比較從 i 開始的子字串與 needle
            for (j = 0; j < m; j++) {
                // 如果字元不匹配，則跳出內部迴圈，檢查 haystack 的下一個起始位置
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            // 如果內部迴圈成功完成（即 j 等於 m），表示找到了一個匹配
            if (j == m) {
                return i;
            }
        }

        // 遍歷所有可能位置後都沒有找到匹配，則返回 -1
        return -1;
    }
}
