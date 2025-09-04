import java.util.Scanner;

public class LC28_StrStr_NoticeSearch {

    /**
     * 在 haystack 字串中尋找 needle 字串第一次出現的索引。
     *
     * @param haystack 待搜尋的主字串
     * @param needle 欲尋找的子字串
     * @return 第一次出現的起始索引，若不存在則回傳 -1
     */
    public static int strStr(String haystack, String needle) {
        // 處理邊界情況：如果 needle 為空字串，則回傳 0
        if (needle.isEmpty()) {
            return 0;
        }

        int n = haystack.length();
        int m = needle.length();

        // 遍歷所有可能的起始位置
        for (int i = 0; i <= n - m; i++) {
            int j;
            // 逐字元比較
            for (j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    // 如果有任何一個字元不匹配，就跳出內層迴圈，嘗試下一個起始位置
                    break;
                }
            }
            // 如果內層迴圈完整執行完畢，表示找到匹配
            if (j == m) {
                return i;
            }
        }

        // 遍歷所有可能的起始位置後仍未找到，回傳 -1
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String haystack = scanner.nextLine();
        String needle = scanner.nextLine();
        
        int index = strStr(haystack, needle);
        System.out.println(index);
        
        scanner.close();
    }
}
