import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * 檢查數獨盤面是否有效。
     *
     * @param board 9x9 的數獨盤面
     * @return 如果盤面有效，返回 true；否則返回 false
     */
    public boolean isValidSudoku(char[][] board) {
        // 使用三個 Set 陣列來追蹤行、列和九宮格中的數字
        // rows[i] 儲存第 i 行出現過的數字
        Set<Character>[] rows = new HashSet[9];
        // cols[j] 儲存第 j 列出現過的數字
        Set<Character>[] cols = new HashSet[9];
        // boxes[k] 儲存第 k 個九宮格出現過的數字
        Set<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // 遍歷整個 9x9 盤面
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char currentVal = board[i][j];

                // 如果當前單元格為空，則跳過
                if (currentVal == '.') {
                    continue;
                }

                // 計算當前單元格所屬的九宮格索引
                // (i / 3) 確定第幾行九宮格 (0, 1, 2)
                // (j / 3) 確定第幾列九宮格 (0, 1, 2)
                // (i / 3) * 3 + (j / 3) 將其轉換為單一索引 (0-8)
                int boxIndex = (i / 3) * 3 + (j / 3);

                // 檢查當前數字是否已在行、列或九宮格中出現
                // add() 方法返回 false 表示元素已存在
                if (!rows[i].add(currentVal) || 
                    !cols[j].add(currentVal) || 
                    !boxes[boxIndex].add(currentVal)) {
                    // 如果任一檢查發現重複，則盤面無效
                    return false;
                }
            }
        }

        // 遍歷完畢，未發現任何重複，盤面有效
        return true;
    }
}
