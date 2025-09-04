import java.util.Scanner;
import java.util.Stack;

public class LC32_LongestValidParen_Metro {

    /**
     * 找出最長有效括號子字串的長度。
     *
     * @param s 包含 '(' 和 ')' 的字串
     * @return 最長合法括號片段的長度
     */
    public static int longestValidParentheses(String s) {
        int maxLength = 0;
        // 使用一個堆疊來儲存括號的索引
        Stack<Integer> stack = new Stack<>();
        
        // 初始時將 -1 壓入堆疊，作為有效子字串的起始基準
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                // 遇到 '('，將其索引壓入堆疊
                stack.push(i);
            } else { // 遇到 ')'
                // 彈出堆疊頂部的索引
                stack.pop();
                
                // 檢查堆疊是否為空
                if (stack.isEmpty()) {
                    // 如果堆疊為空，表示當前的 ')' 沒有匹配的 '('
                    // 將當前索引壓入堆疊，作為新的起始基準
                    stack.push(i);
                } else {
                    // 如果堆疊不為空，表示找到了一對有效括號
                    // 計算當前有效子字串的長度
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        
        int result = longestValidParentheses(s);
        System.out.println(result);
        
        scanner.close();
    }
}
