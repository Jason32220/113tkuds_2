import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LC03_NoRepeat_TaipeiMetroTap {

    /**
     * 找出最長不含重複字元的子字串長度。
     *
     * @param s 捷運進出站刷卡流水字串
     * @return 最長不重複子字串的長度
     */
    public static int lengthOfLongestSubstring(String s) {
        // 如果字串為空，直接返回 0
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 使用 HashMap 儲存每個字元最近一次出現的索引
        // Map<字元, 索引>
        Map<Character, Integer> charIndexMap = new HashMap<>();
        
        int maxLength = 0; // 最長不重複子字串的長度
        int left = 0;      // 滑動視窗的左邊界

        // 使用右指針遍歷字串
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果當前字元已存在於視窗中 (HashMap 包含它，且其索引 >= 左邊界)
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                // 將左邊界移動到重複字元的下一個位置
                left = charIndexMap.get(currentChar) + 1;
            }

            // 更新當前字元在 HashMap 中的最新索引
            charIndexMap.put(currentChar, right);

            // 計算當前視窗的長度並更新最大長度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
        
        scanner.close();
    }
}
