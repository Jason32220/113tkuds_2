import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String convert(String s, int numRows) {
        // 處理特殊情況：如果行數為 1 或字串長度小於行數，直接返回原字串
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // 創建一個 StringBuilder 列表，用來存放每一行的字元
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int currentRow = 0;
        int direction = -1; // -1 代表向上，1 代表向下

        // 遍歷字串中的每個字元
        for (char c : s.toCharArray()) {
            // 將當前字元添加到對應的行中
            rows.get(currentRow).append(c);

            // 判斷是否需要改變移動方向
            // 如果在第一行或最後一行，就改變方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                direction *= -1; // 從 -1 變為 1，或從 1 變為 -1
            }

            // 根據方向更新當前行號
            currentRow += direction;
        }

        // 將所有行中的字元合併成一個最終字串
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}