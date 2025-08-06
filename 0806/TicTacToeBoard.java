
import java.util.Scanner;

public class TicTacToeBoard {

    // 宣告3x3棋盤
    static char[][] board = new char[3][3];

    // 初始化棋盤，全部設為空白 ' '
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 顯示棋盤狀態
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 放置棋子：檢查位置是否有效與空白
    public static boolean placeMark(int row, int col, char player) {
        // 行列必須在 0~2 範圍內，且格子是空白的
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    // 檢查是否有玩家獲勝
    public static boolean checkWin(char player) {
        // 檢查每一行
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player
                    && board[i][1] == player
                    && board[i][2] == player) {
                return true;
            }
        }

        // 檢查每一列
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player
                    && board[1][j] == player
                    && board[2][j] == player) {
                return true;
            }
        }

        // 檢查對角線
        if (board[0][0] == player
                && board[1][1] == player
                && board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player
                && board[1][1] == player
                && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // 檢查是否平手（棋盤已滿）
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // 有空格表示還沒滿
                }
            }
        }
        return true; // 沒空格，平手
    }

    // 主程式：控制遊戲流程
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        char currentPlayer = 'X'; // 先手是 X
        boolean gameEnded = false;

        System.out.println("歡迎來到井字遊戲！");
        printBoard();

        while (!gameEnded) {
            System.out.println("輪到玩家 " + currentPlayer + "，請輸入列與行（0~2）：");

            System.out.print("列（row）: ");
            int row = scanner.nextInt();

            System.out.print("行（col）: ");
            int col = scanner.nextInt();

            if (placeMark(row, col, currentPlayer)) {
                printBoard();

                if (checkWin(currentPlayer)) {
                    System.out.println("玩家 " + currentPlayer + " 獲勝！");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    System.out.println("平手！");
                    gameEnded = true;
                } else {
                    // 換下一位玩家
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }

            } else {
                System.out.println("位置無效，請重新輸入！");
            }
        }

        scanner.close();
    }
}
