class Solution {
    /**
     * 數獨問題的求解入口。
     *
     * @param board 9x9 的數獨盤面，空單元格用 '.' 表示。
     */
    public void solveSudoku(char[][] board) {
        // 從 (0,0) 位置開始回溯求解
        backtrack(board);
    }

    /**
     * 核心回溯演算法。
     *
     * @param board 數獨盤面
     * @return 如果找到一個有效的解，返回 true；否則返回 false
     */
    private boolean backtrack(char[][] board) {
        // 遍歷整個盤面，尋找空單元格
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 如果找到空單元格
                if (board[i][j] == '.') {
                    // 嘗試填入 1 到 9 的數字
                    for (char c = '1'; c <= '9'; c++) {
                        // 檢查當前數字在 (i, j) 位置是否有效
                        if (isValid(board, i, j, c)) {
                            // 做出選擇：將數字 c 填入單元格
                            board[i][j] = c;

                            // 遞歸地繼續求解下一個空單元格
                            if (backtrack(board)) {
                                // 如果遞歸呼叫成功，表明找到解，立即返回
                                return true;
                            } else {
                                // 如果遞歸呼叫失敗，撤銷選擇
                                board[i][j] = '.';
                            }
                        }
                    }
                    // 如果嘗試了 1-9 所有數字都無法找到解，返回 false
                    return false;
                }
            }
        }
        // 如果遍歷完畢沒有找到空單元格，說明盤面已填滿，找到一個解
        return true;
    }

    /**
     * 檢查在指定位置填入數字是否有效。
     *
     * @param board 數獨盤面
     * @param row   行索引
     * @param col   列索引
     * @param c     要檢查的數字
     * @return 如果有效，返回 true；否則返回 false
     */
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 檢查行：當前行 i 中是否已存在數字 c
            if (board[row][i] == c) return false;
            // 檢查列：當前列 i 中是否已存在數字 c
            if (board[i][col] == c) return false;
            // 檢查九宮格：計算九宮格的起始位置，並遍歷九宮格
            // 3 * (row / 3) 和 3 * (col / 3) 確保從九宮格的左上角開始遍歷
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
